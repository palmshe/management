package com.genius.shiro.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public ModelAndView handleUnauthenticatedException(NativeWebRequest request, UnauthorizedException exception){
        ModelAndView mv= new ModelAndView();
        mv.setViewName("exception/unauthenticated");
        return mv;
    }
}
