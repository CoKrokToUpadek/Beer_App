package com.cokroktoupadek.beersandmealsbackend.client.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class TokenValidator {
    @Before("execution(* com.cokroktoupadek.beersandmealsbackend.controller.*.*(..))")
    public void validateToken() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest();
        if (request.getServletPath()!="/user/login" || request.getServletPath()!="/user/create_user"){
            //TODO implementacja weryfikatora
        }
    }
}
