package com.yww.demo.service;


import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yww.demo.entity.User;
import com.yww.demo.mapper.UserMapper;
import com.yww.demo.util.UserUtil;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return this.save(insertUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
        // 按每次500条数据进行插入
        return saveBatch(userList, 500);
    }

    @Override
    public int insertBatch3(List<User> userList) {
        userList = UserUtil.getUsers(1000);
        // 按每次500条数据进行插入
        List<List<User>> list = ListUtil.partition(userList, 500);
        int res = 0;
        for (List<User> i : list) {
            res += this.baseMapper.insertBatchSomeColumn(i);
        }
        return res;
    }

    @Override
    public boolean deleteById(String userId) {
//      有必要的话可以先查出数据，进行处理后在删除
//      entity = select(userId);     this.removeById(entity)
        this.removeById(userId);
        return false;
    }

    @Override
    public boolean deleteByCondition(User user) {
        return this.remove(Wrappers.lambdaQuery(user));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByIds(List<String> userIds) {
        // 按每次1000条数据进行操作
        List<List<String>> list = ListUtil.partition(userIds, 1000);
        boolean res = true;
        for (List<String> i : list) {
            res = res && this.removeByIds(i);
        }
        return this.removeByIds(userIds);
    }

    @Override
    public boolean deleteBatchByIds(List<String> userIds) {
        return this.removeBatchByIds(userIds, 1000);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBatchByUsernames(List<String> usernames) {
        // 按每次1000条数据进行操作
        List<List<String>> list = ListUtil.partition(usernames, 1000);
        int count = 0;
        for (List<String> i : list) {
            count = count + baseMapper.deleteBatchByUsernames(i);
        }
        return count;
    }

    @Override
    public boolean update1(User user) {
        // 将名字设置为WHERE条件，将状态设置为SET语句
        return this.update(
            Wrappers.lambdaUpdate(User.class).eq(User::getNickname, user.getNickname())
                    .set(User::getStatus, user.getStatus())
        );
    }

    @Override
    public boolean update2(User user) {
        return this.update(user,
                Wrappers.lambdaQuery(User.class).eq(User::getNickname, user.getNickname())
        );
    }

    @Override
    public User getByUsername(String username) {
        return this.getOne(
                Wrappers.lambdaQuery(User.class).eq(User::getUsername, username)
        );
    }

    @Override
    public List<User> getByUser(User user) {
        return this.list(
                Wrappers.lambdaQuery(User.class).eq(User::getUsername, user.getUsername()).eq(User::getNickname, user.getNickname())
        );
    }

}
