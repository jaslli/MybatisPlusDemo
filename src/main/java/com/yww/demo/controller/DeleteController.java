package com.yww.demo.controller;

import com.yww.demo.service.IUserService;
import com.yww.demo.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
