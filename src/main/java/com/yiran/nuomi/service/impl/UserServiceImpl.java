package com.yiran.nuomi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yiran.nuomi.common.BusinessException;
import com.yiran.nuomi.common.ErrorCode;
import com.yiran.nuomi.component.ObjectValidator;
import com.yiran.nuomi.dao.UserMapper;
import com.yiran.nuomi.entity.User;
import com.yiran.nuomi.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author Super-Zhang
 * @date 2021-09-17 19:59
 */
@Service
public class UserServiceImpl implements UserService, ErrorCode {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ObjectValidator validator;

    private final QueryWrapper<User> queryWrapper = new QueryWrapper<User>();

    @Transactional
    public void register(User user) {

        if (user == null) {
            throw new BusinessException(PARAMETER_ERROR, "没填全哦~");
        }

        // 先校验实体输入的正确与否，不正确直接抛异常。
        Map<String, String> result = validator.validate(user);
        if (result != null && result.size() > 0) {
            throw new BusinessException(PARAMETER_ERROR,
                    StringUtils.join(result.values().toArray(), ", ") + "！");
        }

        try {
            user.setRole(1);
            userMapper.insert(user);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(PARAMETER_ERROR, "该昵称已注册！");
        }
    }

    public User login(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new BusinessException(PARAMETER_ERROR, "空没填全哦");
        }

        queryWrapper.eq("username",username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null || !StringUtils.equals(password, user.getPassword())) {
            throw new BusinessException(USER_LOGIN_FAILURE, "昵称或密码错误！");
        }

        return user;
    }

    public User findUserById(int id) {
        if (id <= 0) {
            return null;
        }
        return userMapper.selectById(id);
    }

}
