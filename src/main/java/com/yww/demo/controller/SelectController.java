package com.yww.demo.controller;

import com.yww.demo.service.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
