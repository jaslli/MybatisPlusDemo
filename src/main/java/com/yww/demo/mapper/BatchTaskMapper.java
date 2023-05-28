package com.yww.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yww.demo.entity.BatchTask;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *       Mapper 接口
 * </p>
 *
 * @author   yww
 * @since   2023-05-28
 */
@Repository
@SuppressWarnings("all")
public interface BatchTaskMapper extends BaseMapper<BatchTask> {

    /**
     * 批量插入 仅适用于mysql
     *
     * @param batchList 实体列表
     * @return 影响行数
     */
    int insertBatchSomeColumn(@Param("list") List<BatchTask> batchList);

}
