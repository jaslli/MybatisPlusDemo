package com.yww.demo.controller;

import com.yww.demo.service.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
