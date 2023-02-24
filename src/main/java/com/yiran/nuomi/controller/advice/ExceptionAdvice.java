package com.yiran.nuomi.controller.advice;

import com.yiran.nuomi.common.BusinessException;
import com.yiran.nuomi.common.StateCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理
 *
 * @author Super-Zhang
 * @date 2021-09-17 20:15
 */
@ControllerAdvice
public class ExceptionAdvice implements StateCode {

    private Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(value = Exception.class) // 这个注解意味着，当Spring 抛出异常的时候，会根据value设的异常的类型将这个异常传进来。
    //可以传自定义异常，也可以传其父类Exception，然后方法体内逻辑判断具体是那种异常。
    public ModelAndView handleBusinessException(Exception e) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404"); //这里要和templates下的错误页面同名

        if (e instanceof BusinessException) {
            modelAndView.addObject("code", ((BusinessException) e).getCode());
            modelAndView.addObject("message", ((BusinessException) e).getMessage());
        } else if (e instanceof NoHandlerFoundException) { //NoHandlerFoundException 404 资源不存在
            modelAndView.addObject("code", NOT_Found);
            modelAndView.addObject("message", "该资源不存在！");
        } else {
            modelAndView.addObject("code", UNDEFINED_ERROR);
            modelAndView.addObject("message", "发生未知错误:" + e.getMessage());
            // 记录日志
            logger.error("发生未知错误", e);
        }
        return modelAndView;
    }
}


