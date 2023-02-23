package com.yiran.nuomi.controller.learnning;

import com.yiran.nuomi.entity.User;
import com.yiran.nuomi.dao.UserMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController_hello {

    @Autowired
    private UserMapper userMapper;

    @ApiOperation("在Swagger显示获取用户")
    @GetMapping("/user/{id}")
    public List<User> getUserById(@PathVariable int id) {
        System.out.println(id);
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
        return users;
    }

    @PostMapping("/user/add1")
    public String save(User user) {
        int insert = userMapper.insert(user);
        if (insert > 0) {
            return "添加成功";
        }else {
            return "添加失败";
        }
    }

    @PutMapping("/user")
    public String update(User user) {
        return "更新用户";
    }

    @DeleteMapping("/user/{id}")
    public String deleteById(@PathVariable int id) {
        System.out.println(id);
        return "根据ID删除用户";
    }
}
