package com.yiran.nuomi.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Super-Zhang
 * @date 2021-09-17 21:31
 */
@RestController
public class ExceptionController {

    @RequestMapping("/query")
    public void hello() {
        int num = 1 / 0;
    }

    @RequestMapping("/home")
    public String home() throws Exception {
        throw new Exception("throw new Exception");
//        throw new BusinessException(101, "BusinessException 错误");
    }

}