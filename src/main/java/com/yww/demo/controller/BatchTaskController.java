package com.yww.demo.controller;

import com.yww.demo.service.IBatchTaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/batch-task")
public class BatchTaskController {
}
