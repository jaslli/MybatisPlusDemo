package com.yww.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yww.demo.entity.BatchTask;
import com.yww.demo.mapper.BatchTaskMapper;
import com.yww.demo.service.IBatchTaskService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *       服务实现类
 * </p>
 *
 * @author   yww
 * @since   2023-05-28
 */
@Service
public class BatchTaskServiceImpl extends ServiceImpl<BatchTaskMapper, BatchTask> implements IBatchTaskService {

}
