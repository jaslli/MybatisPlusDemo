package com.yww.demo.task;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yww.demo.entity.Batch;
import com.yww.demo.entity.BatchTask;
import com.yww.demo.service.impl.BatchServiceImpl;
import com.yww.demo.service.impl.BatchTaskServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

/**
 * <p>
 *      定时任务
 * </P>
 *
 * @author yww
 * @since 2023/5/28
 */
@Slf4j
@Component
public class Task {

    @Qualifier("taskExecutor")
    @Autowired
    Executor taskExecutor;

    @Autowired
    BatchTaskServiceImpl batchTaskService;

    @Autowired
    BatchServiceImpl batchService;

    @Scheduled(fixedDelay = 5000)
    public void exec() {
        List<Batch> batchList = batchService.list(Wrappers.lambdaQuery(Batch.builder().status(0).build()));
        if (CollectionUtil.isEmpty(batchList)) {
            return;
        }
        Batch batch = batchList.get(0);
        log.info("批次ID为{}，批次名称为{}的任务，开始执行", batch.getId(), batch.getName());

        // 更新批次状态
        batch.setStatus(1);
        batchService.updateById(batch);

        CountDownLatch countDownLatch;

        List<BatchTask> batchTaskList = batchTaskService.list(Wrappers.lambdaQuery(BatchTask.builder().batchId(batch.getId()).build()));
        countDownLatch = new CountDownLatch(batchTaskList.size());
        for (BatchTask batchTask : batchTaskList) {
            taskExecutor.execute(() -> this.invoke(batchTask, countDownLatch));
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        batch.setStatus(2);
        batchService.updateById(batch);
        log.info("批次ID为{}，批次名称为{}的任务，执行完成", batch.getId(), batch.getName());

    }

    public void invoke(BatchTask batchTask, CountDownLatch countDownLatch) {
        try {
            log.info("任务ID为{}，任务名称为{}的任务，开始执行，当前线程为{}", batchTask.getId(), batchTask.getName(), Thread.currentThread().getName());

            batchTask.setStatus(1);
            batchTaskService.updateById(batchTask);

            Thread.sleep(1000 * 10);

            batchTask.setStatus(2);
            batchTaskService.updateById(batchTask);

            log.info("任务ID为{}，任务名称为{}的任务，任务完成，当前线程为{}", batchTask.getId(), batchTask.getName(), Thread.currentThread().getName());
        } catch (InterruptedException e) {
            log.error("任务执行异常", e);
        } finally {
            countDownLatch.countDown();
        }
    }


}
