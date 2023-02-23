package com.yiran.nuomi.controller.learnning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SuperZhang
 * @description
 * @since 2023/2/11 8:52
 */
@RestController // @RestController 只返回Json， @Controller 返回一个页面。 通常与Thymeleaf模板引擎配合，但就不是前后端分离了。
public class HelloController {

    //    http://localhost:8080/hello?username=zhangsan&phone=123123
    @GetMapping("/hello")
    public String hello1(String username, String phone) {
        return "hello word: " + username + "&&" + phone;
    }

    @RequestMapping(value = "/rm", method = RequestMethod.GET)
    public String testRequsetMapping() {
        return "request mapping";
    }




}
