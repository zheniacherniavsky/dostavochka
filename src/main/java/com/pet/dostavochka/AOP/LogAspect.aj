package com.pet.dostavochka.AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {
    @Pointcut("@annotation(com.pet.dostavochka.AOP.LogAnnotation)")
    public void callAtAppController() {

    }

    @Before("callAtAppController()")
    public void beforeCallAt() {
//        String args = Arrays.stream(jp.getArgs())
//                .map(obj -> obj.toString())
//                .collect(Collectors.joining(","));
//        log.info("before " + jp.toString() + ", args=[" + args + "]");
        log.info("hi!");
    }

    @After("callAtAppController()")
    public void afterCallAt(JoinPoint jp) {
        log.info("after " + jp.toString());
    }
}
