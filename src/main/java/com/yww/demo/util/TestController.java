package com.yww.demo.util;

import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yww.demo.entity.User;
import com.yww.demo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *      测试
 * </p>
 *
 * @author yww
 * @since 2023/2/23 17:26
 */
@Slf4j
@SpringBootTest
public class TestController {

    @Autowired
    IUserService userService;

    /**
     * 批量删除测试
     */
    @Test
    public void deleteTest() {
        List<String> userIds = userService.list(Wrappers.lambdaQuery(User.class).select(User::getId))
                .stream().map(User::getId).collect(Collectors.toList());
        List<List<String>> list = ListUtil.partition(userIds, userIds.size() / 2);
        Long start1 = System.currentTimeMillis();
        userService.deleteByIds(list.get(0));
        Long end1 = System.currentTimeMillis();
        Long start2 = System.currentTimeMillis();
        userService.deleteBatchByIds(list.get(1));
        Long end2 = System.currentTimeMillis();
        log.info("此处测试的两种方法，测试删除的数据数量为{}", userIds.size() / 2);
        log.info("deleteByIds测试时间为{}毫秒", end1 - start1);
        log.info("deleteBatchByIds测试时间为{}毫秒", end2 - start2);
    }


    @Test
    public void test() {
        userService.list(
                Wrappers.lambdaQuery(User.class).last("ORDER BY TO_NUMBER(ID)")
        );
    }


}
