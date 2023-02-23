package com.yww.demo.service;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yww.demo.entity.User;
import com.yww.demo.mapper.UserMapper;
import com.yww.demo.util.UserUtil;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * <p>
 * 添加接口实现类
 * </p>
 *
 * @author yww
 * @since 2023/2/22 17:08
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public void random(Integer number) {
        Faker cnFaker = new Faker(new Locale("zh-CN"));
        Faker enFaker = new Faker(new Locale("en"));
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            User user = User.builder()
                    .username(enFaker.name().username() + i)
                    .password(enFaker.passport().valid())
                    .nickname(cnFaker.name().name())
                    .avatar(enFaker.avatar().image())
                    .email(StrUtil.cleanBlank(enFaker.name().fullName().toLowerCase()) + "@qq.com")
                    .sex(random.nextBoolean())
                    .phone(StrUtil.cleanBlank(cnFaker.phoneNumber().phoneNumber()))
                    .status(true)
                    .build();
            this.baseMapper.insert(user);
        }
    }

    @Override
    public boolean insert(User user) {
        User insertUser = User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .avatar(user.getAvatar())
                .email(user.getEmail())
                .sex(user.getSex())
                .phone(user.getPhone())
                .status(user.getStatus())
                .build();
        // 可以进行一些操作校验数据，比如说username是唯一的等等
        int count = baseMapper.insert(insertUser);
        // this.save(insertUser);
        return count > 0;
    }

    @Override
    public boolean insertBatch1(List<User> userList) {
        userList = UserUtil.getUsers(10);
        boolean res = true;
        for (User user : userList) {
            res = res && this.insert(user);
        }
        return res;
    }

    @Override
    public boolean insertBatch2(List<User> userList) {
        userList = UserUtil.getUsers(10);
        return this.saveBatch(userList);
    }

    @Override
    public int insertBatch3(List<User> userList) {
        userList = UserUtil.getUsers(10);
        return this.baseMapper.insertBatchSomeColumn(userList);
    }

    @Override
    public boolean deleteById(String userId) {
//        this.removeById(userId);
        this.baseMapper.deleteById(userId);
        return false;
    }

}
