package com.yiran.nuomi.controller.Interceptor;

import com.yiran.nuomi.common.StateCode;
import com.yiran.nuomi.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * @description: 拦截器要实现 HandlerInterceptor接口
 * @author SuperZhang
 * @date 2021-09-10 21:43
 * @version 1.Bootstrap
 */

/**
 * 实现 HandlerInterceptor 接口。
 * <p>
 * 一个拦截器可以拦截 N多个方法。 感觉跟AOP 类似，也方便。
 * <p>
 * AOP 是面向方法名，面向对象角度去过滤的。
 * 拦截器是面向 URL 层面过滤的。
 * Servlet 的 Filter 是面向服务器的角度过滤的。 因为现在都是由 Dispatcher 分发了，就不用Filter了
 */
@Component
public class LoginCheckInterceptor implements HandlerInterceptor, StateCode {

    @Override
    public boolean preHandle(HttpServletRequest request, //handler 就相当于Controller
                             HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            response.sendRedirect("hi");
            return false;
        }
        return true;
    }
}
