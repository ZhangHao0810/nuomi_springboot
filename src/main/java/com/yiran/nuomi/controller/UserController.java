package com.yiran.nuomi.controller;

import com.yiran.nuomi.common.BusinessException;
import com.yiran.nuomi.common.StateCode;
import com.yiran.nuomi.common.ResponseModel;
import com.yiran.nuomi.common.Toolbox;
import com.yiran.nuomi.entity.User;
import com.yiran.nuomi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author Super-Zhang
 * @date 2021-09-16 20:23
 */

@Controller
@RequestMapping("user")
@Slf4j
public class UserController implements StateCode {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/register")
    public String register(User user, Model model) {
        if (Strings.isEmpty(user.getUsername())|| Strings.isEmpty(user.getPassword())){
            throw new BusinessException(Code.ERROR, "没填全哦~");
        }
        // 加密处理
        user.setPassword(Toolbox.md5(user.getPassword()));
        userService.register(user);
        model.addAttribute("user", user);
        log.info("用户{}已注册", user.getUsername());
        return "../static/login";
    }

    @RequestMapping(path = "/login")
    public String login(String username, String password, HttpSession session, Model model) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new BusinessException(Code.ERROR, "参数不合法！");
        }
        String md5pwd = Toolbox.md5(password);
        User user = userService.login(username, md5pwd);
        session.setAttribute("loginUser", user);
        model.addAttribute("user", user);
        log.info("用户Session:{}", session.getAttribute("loginUser"));
        return "index";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public ResponseModel logout(HttpSession session) {
        session.invalidate();
        return new ResponseModel();
    }

    @RequestMapping(path = "/status", method = RequestMethod.GET)
    @ResponseBody
    public ResponseModel getUser(HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        return new ResponseModel(user);
    }

}
