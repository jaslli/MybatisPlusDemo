package com.yww.demo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yww.demo.entity.User;

import java.util.List;

/**
 * <p>
 * 用户信息实体类 服务类
 * </p>
 *
 * @Author yww
 * @Date 2022-10-21
 */
public interface IUserService extends IService<User> {

    /**
     * 随机生成指定条数据
     *
     * @param number 指定数量
     */
    void random(Integer number);

    /**
     * 添加单个用户
     *
     * @param user 用户数据
     * @return 添加成功返回true
     */
    boolean insert(User user);

    /**
     * 批量添加用户(使用for循环的方式)
     *
     * @param userList 用户列表
     * @return 添加成功返回true
     */
    boolean insertBatch1(List<User> userList);

    /**
     * 批量添加用户(使用savaBatch()的伪批量插入)
     *
     * @param userList 用户列表
     * @return 添加成功返回true
     */
    boolean insertBatch2(List<User> userList);

    /**
     * 批量添加用户(真实批量插入)
     *
     * @param userList 用户列表
     * @return 添加成功的数据数
     */
    int insertBatch3(List<User> userList);

    /**
     * 根据ID删除数据
     *
     * @param userId 用户ID
     * @return 删除成功返回true
     */
    boolean deleteById(String userId);

}
