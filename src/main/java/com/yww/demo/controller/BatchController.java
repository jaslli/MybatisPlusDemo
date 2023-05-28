package com.yww.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yww.demo.entity.Batch;
import com.yww.demo.entity.BatchTask;
import com.yww.demo.mapper.BatchTaskMapper;
import com.yww.demo.service.IBatchService;
import com.yww.demo.util.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *       前端控制器
 * </p>
 *
 * @author   yww
 * @since   2023-05-28
 */
@Tag(name = "接口")
@RestController
@RequestMapping("/batch")
public class BatchController {
    private final IBatchService service;
    private final BatchTaskMapper batchTaskMapper;
    @Autowired
    public BatchController(IBatchService service, BatchTaskMapper batchTaskMapper) {
        this.service = service;
        this.batchTaskMapper = batchTaskMapper;
    }

    @GetMapping("/createBatch/{name}")
    public Result<?> createBatch(@PathVariable String name) {
        Batch batch = Batch.builder()
                .name(name)
                .status(0)
                .build();
        service.save(batch);

        List<BatchTask> batchTasks = new ArrayList<>(1000);
        for(int i = 0; i < 1000; i++) {
            BatchTask batchTask = BatchTask.builder()
                    .name("任务" + i)
                    .status(0)
                    .batchId(batch.getId())
                    .build();
            batchTasks.add(batchTask);
        }
        batchTaskMapper.insertBatchSomeColumn(batchTasks);

        return Result.success();
    }

    @DeleteMapping("/delete/{batchId}")
    public Result<?> delete(@PathVariable Integer batchId) {
        service.removeById(batchId);

        List<BatchTask> batchTasks = batchTaskMapper.selectList(Wrappers.lambdaQuery(BatchTask.builder().batchId(batchId).build()));
        batchTaskMapper.deleteBatchIds(batchTasks.stream().map(BatchTask::getId).collect(Collectors.toList()));

        return Result.success();
    }

}
