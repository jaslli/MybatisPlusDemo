package com.yww.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yww.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户信息实体类 Mapper 接口
 * </p>
 *
 * @Author yww
 * @Date 2022-10-21
 */
@Repository
public interface UserMapper extends BaseMapper<User> {


    /**
     * 批量插入 仅适用于mysql
     *
     * @param batchList 实体列表
     * @return 影响行数
     */
    @SuppressWarnings("MybatisMapperMethodInspection")
    int insertBatchSomeColumn(@Param("list") List<User> batchList);

}
