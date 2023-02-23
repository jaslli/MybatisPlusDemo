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

    /**
     * 根据条件删除数据
     *
     * @param user  实体条件
     * @return      删除成功返回true
     */
    boolean deleteByCondition(User user);

    /**
     * 根据ID列表删除数据
     *
     * @param userIds 用户ID列表
     * @return 删除成功返回true
     */
    boolean deleteByIds(List<String> userIds);

    /**
     * 根据ID列表批量删除数据
     *
     * @param userIds 用户ID列表
     * @return 删除成功返回true
     */
    boolean deleteBatchByIds(List<String> userIds);

}
