package com.yiran.nuomi.controller;

import com.yiran.nuomi.entity.User;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParamsController {

    @RequestMapping(value = "/getTest1",method = RequestMethod.GET)
    public String getTest1(){
        return "GET请求";
    }

    @RequestMapping(value = "/getTest2",method = RequestMethod.GET)
//  http://localhost:8080/getTest2?nickname=xxx&phone=xxx
    public String getTest2(String nickname,String phone){
        System.out.println("nickname:"+nickname);
        System.out.println("phone:"+phone);
        return "GET请求";
    }

    @RequestMapping(value = "/getTest3",method = RequestMethod.GET)
//  http://localhost:8080/getTest2?nickname=xxx
//    required = false 表示该参数可以不传。若不写且没传参数，@RequestParam这种传参形式会报错。
    public String getTest3(@RequestParam(value = "nickname",required = false) String name){
        System.out.println("nickname:"+name);
        return "GET请求";
    }

    @RequestMapping(value = "/postTest1",method = RequestMethod.POST)
    public String postTest1(){
        return "POST请求";
    }
    @RequestMapping(value = "/postTest2",method = RequestMethod.POST)
    public String postTest2(String username,String password){
        System.out.println("username:"+username);
        System.out.println("password:"+password);
        return "POST请求";
    }
    @RequestMapping(value = "/postTest3",method = RequestMethod.POST)
    public String postTest3(User user){
        System.out.println(user);
        return "POST请求";
    }

    @RequestMapping(value = "/postTest4",method = RequestMethod.POST)
    public String postTest4(@RequestBody User user){
        System.out.println(user);
        return "POST请求";
    }

    @GetMapping("/test/*")
    public String test(){
        return "通配符请求";
    }
}
