package com.yiran.nuomi.controller.learnning;

import com.yiran.nuomi.entity.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Date;

/**
 * @author Super-Zhang
 * @date 2021-09-16 20:25
 */
@Controller
public class IndexController {

    @RequestMapping("/hi")
    public String index() {
        return "index";
    }

    /**
     * ======================测试URL=======================
     */

    @RequestMapping("/basicTrain")
    public String basicTrain(Model model) {
        UserVO userVO = new UserVO();
        userVO.setUserName("ZhangHao");
        userVO.setAge(24);
        userVO.setIsVip(true);
        userVO.setCreateTime(new Date());
        userVO.setTags(Arrays.asList("Java", "Python", "C"));
        model.addAttribute("user", userVO);
        return "Study_thymeleaf/studay_thymeleaf_basic";
    }

//    @RequestMapping("/Demo")
//    public String demo(Model model) {
//
//        ArrayList<String> list = new ArrayList<>();
//        int i = 4;
//        while (i > 0) {
//            list.add("Test/" + i + ".jpg");
//            i--;
//        }
//        model.addAttribute("list", list);
//        String mp4 = "Test/_p0Q3rEb-sTUYB3A.mp4";
//        model.addAttribute("mp4", mp4);
//        return "NuoMiDemo";
//    }

    @RequestMapping("/404")
    public String error() {
        return "test/404";
    }

}
