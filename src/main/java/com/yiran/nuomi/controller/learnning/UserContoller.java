package com.yiran.nuomi.controller.learnning;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yiran.nuomi.entity.User;
import com.yiran.nuomi.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserContoller {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/findAll")
    public List<User> find(){
        return userMapper.selectAllUserAndOrders();
    }

//  条件查询
    @GetMapping("/find")
    public List<User> findByCond(){
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username","zhangsan");
        return userMapper.selectList(queryWrapper);
    }

//  分页查询
    @GetMapping("/findByPage")
    public IPage findByPage(){
        //设置起始值及每页条数
        Page<User> page = new Page<>(0,2);
        IPage iPage = userMapper.selectPage(page,null); // IPage 描述 结果集。Page 描述查哪页每页多少。
        return iPage;
    }

//  插入数据
    @PostMapping("add")
    public String save(User user){
        int r= userMapper.insert(user);
        if(r > 0){
            return "插入成功";
        }else{
            return "插入失败";
        }
    }
}
