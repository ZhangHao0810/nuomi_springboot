package com.yiran.nuomi.controller;

import com.yiran.nuomi.common.BusinessException;
import com.yiran.nuomi.common.ErrorCode;
import com.yiran.nuomi.common.ResponseModel;
import com.yiran.nuomi.common.Toolbox;
import com.yiran.nuomi.entity.User;
import com.yiran.nuomi.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class UserController implements ErrorCode {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/register")
    public String register(User user, Model model) {
        logger.info("用户{}已进入Register", user.getUsername());
        // 加密处理
        user.setPassword(Toolbox.md5(user.getPassword()));

        userService.register(user);
        model.addAttribute("user", user);

        logger.info("用户{}已注册", user.getUsername());

        return "index";
    }

    @RequestMapping(path = "/login")
    public String login(String username, String password, HttpSession session, Model model) {
        if (StringUtils.isEmpty(username)
                || StringUtils.isEmpty(password)) {
            throw new BusinessException(PARAMETER_ERROR, "参数不合法！");
        }

        String md5pwd = Toolbox.md5(password);
        User user = userService.login(username, md5pwd);
        session.setAttribute("loginUser", user);
        model.addAttribute("user", user);
        logger.info("用户Session:{}", session.getAttribute("loginUser"));
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
