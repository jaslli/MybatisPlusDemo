package com.yww.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yww.demo.entity.Batch;
import com.yww.demo.mapper.BatchMapper;
import com.yww.demo.service.IBatchService;
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
public class BatchServiceImpl extends ServiceImpl<BatchMapper, Batch> implements IBatchService {

}
