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
 * 用户查询接口
 * </p>
 *
 * @author yww
 * @since 2023/2/22 16:30
 */
@Tag(name = "用户查询接口")
@RestController
@RequestMapping("/user")
public class SelectController {

    private final IUserService service;

    @Autowired
    public SelectController(IUserService service) {
        this.service = service;
    }

    @Operation(summary = "根据ID获取数据")
    @GetMapping("/getById/{userId}")
    public Result<?> getById(@PathVariable Integer userId) {
        return Result.success(service.getById(userId));
    }

    @Operation(summary = "根据用户名获取数据")
    @GetMapping("/getByUsername/{username}")
    public Result<?> getByUsername(@PathVariable String username) {
        return Result.success(service.getByUsername(username));
    }

    @Operation(summary = "根据条件获取数据")
    @GetMapping("/getByUser")
    public Result<?> getByUser(@RequestBody User user) {
        return Result.success(service.getByUser(user));
    }

    @Operation(summary = "根据ID批量查询")
    @GetMapping("/getByIds")
    public Result<?> getByUser(@RequestBody List<String> userIds) {
        return Result.success(service.listByIds(userIds));
    }

}
