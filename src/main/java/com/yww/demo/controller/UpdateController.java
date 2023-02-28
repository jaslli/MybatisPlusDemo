package com.yww.demo.controller;

import com.yww.demo.entity.User;
import com.yww.demo.service.IUserService;
import com.yww.demo.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户更新接口
 * </p>
 *
 * @author yww
 * @since 2023/2/22 16:29
 */
@Tag(name = "用户更新接口")
@RestController
@RequestMapping("/user")
public class UpdateController {

    private final IUserService service;

    @Autowired
    public UpdateController(IUserService service) {
        this.service = service;
    }

    @Operation(summary = "根据ID更新数据")
    @PutMapping("/updateById")
    public Result<?> updateById(@RequestBody User user) {
        if (service.updateById(user)) {
            return Result.success("更新成功");
        } else {
            return Result.failure("更新失败");
        }
    }

    @Operation(summary = "根据ID批量更新数据")
    @PutMapping("/updateByIds")
    public Result<?> updateByIds(@RequestBody List<User> users) {
        if (service.updateBatchById(users, 1000)) {
            return Result.success("更新成功");
        } else {
            return Result.failure("更新失败");
        }
    }

    @Operation(summary = "使用UpdateWrapper更新数据")
    @PutMapping("/update1")
    public Result<?> update1(@RequestBody User user) {
        if (service.update1(user)) {
            return Result.success("更新成功");
        } else {
            return Result.failure("更新失败");
        }
    }

    @Operation(summary = "使用Wrapper更新数据")
    @PutMapping("/update2")
    public Result<?> update2(@RequestBody User user) {
        if (service.update2(user)) {
            return Result.success("更新成功");
        } else {
            return Result.failure("更新失败");
        }
    }

    @Operation(summary = "保存或者更新")
    @PutMapping("/saveOrUpdate")
    public Result<?> saveOrUpdate(@RequestBody User user) {
        if (service.saveOrUpdate(user)) {
            return Result.success("更新成功");
        } else {
            return Result.failure("更新失败");
        }
    }

}
