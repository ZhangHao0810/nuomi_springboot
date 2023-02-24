package com.yiran.nuomi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yiran.nuomi.entity.User;

/**
 * @author Super-Zhang
 * @date 2021-09-17 19:59
 */
public interface UserService extends IService<User> {

    void register(User user);

    User login(String phone, String password);

    User findUserById(int id);

}
