package com.yww.demo.controller;

import com.yww.demo.entity.User;
import com.yww.demo.service.IUserService;
import com.yww.demo.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户添加接口
 * </p>
 *
 * @author yww
 * @since 2023/2/22 15:53
 */
@Tag(name = "用户添加接口")
@RestController
@RequestMapping("/user")
public class InsertController {

    private final IUserService service;

    @Autowired
    public InsertController(IUserService service) {
        this.service = service;
    }

    @Operation(summary = "随机生成指定条数据")
    @GetMapping("/random/{number}")
    public Result<?> random(@PathVariable Integer number) {
        service.random(number);
        return Result.success();
    }

    @Operation(summary = "添加单个用户")
    @PostMapping("/insert")
    public Result<?> insert(@RequestBody User user) {
        if (service.insert(user)) {
            return Result.success("添加成功");
        } else {
            return Result.failure("添加失败");
        }
    }

    @Operation(summary = "批量添加用户，for循环")
    @PostMapping("/insertBatch1")
    public Result<?> insertBatch1() {
        if (service.insertBatch1(null)) {
            return Result.success("添加成功");
        } else {
            return Result.failure("添加失败");
        }
    }

    @Operation(summary = "批量添加用户，伪批量插入")
    @PostMapping("/insertBatch2")
    public Result<?> insertBatch2() {
        if (service.insertBatch2(null)) {
            return Result.success("添加成功");
        } else {
            return Result.failure("添加失败");
        }
    }

    @Operation(summary = "批量添加用户，批量插入")
    @PostMapping("/insertBatch3")
    public Result<?> insertBatch3() {
        int count = service.insertBatch3(null);
        if (count > 0) {
            return Result.success("添加成功,添加了" + count + "条数据");
        } else {
            return Result.failure("添加失败");
        }
    }

}
