package com.yww.demo.controller;

import com.yww.demo.entity.User;
import com.yww.demo.service.IUserService;
import com.yww.demo.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户删除接口
 * </p>
 *
 * @author yww
 * @since 2023/2/22 16:29
 */
@Tag(name = "用户删除接口")
@RestController
@RequestMapping("/user")
public class DeleteController {

    private final IUserService service;

    @Autowired
    public DeleteController(IUserService service) {
        this.service = service;
    }

    @Operation(summary = "根据ID删除数据")
    @GetMapping("/deleteById/{userId}")
    public Result<?> deleteById(@PathVariable String userId) {
        if (service.deleteById(userId)) {
            return Result.success("删除成功");
        } else {
            return Result.failure("删除失败");
        }
    }

    @Operation(summary = "根据条件删除数据")
    @PostMapping("/deleteByCondition")
    public Result<?> deleteByCondition(@RequestBody User user) {
        if (service.deleteByCondition(user)) {
            return Result.success("删除成功");
        } else {
            return Result.failure("删除失败");
        }
    }

    @Operation(summary = "removeByIds方法")
    @PostMapping("/deleteByIds")
    public Result<?> deleteByIds(@RequestBody List<String> userIds) {
        if (service.deleteByIds(userIds)) {
            return Result.success("删除成功");
        } else {
            return Result.failure("删除失败");
        }
    }

    @Operation(summary = "removeBatchByIds方法")
    @PostMapping("/deleteBatchByIds")
    public Result<?> deleteBatchByIds(@RequestBody List<String> userIds) {
        if (service.deleteBatchByIds(userIds)) {
            return Result.success("删除成功");
        } else {
            return Result.failure("删除失败");
        }
    }

}
