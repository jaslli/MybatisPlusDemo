package com.yww.demo.util;

import cn.hutool.core.util.StrUtil;
import com.yww.demo.entity.User;
import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * <p>
 *      用户测试工具类
 * </p>
 *
 * @author yww
 * @since 2023/2/23 9:11
 */
public class UserUtil {

    /**
     * 随机生成number条用户数据
     *
     * @param number 指定数量
     * @return 用户数据列表
     */
    public static List<User> getUsers(int number) {
        Faker cnFaker = new Faker(new Locale("zh-CN"));
        Faker enFaker = new Faker(new Locale("en"));
        Random random = new Random();
        List<User> userList = new ArrayList<>(number);
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
            userList.add(user);
        }
        return userList;
    }

}
