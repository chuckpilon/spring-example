package com.pilon.example.item;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// Use AOP to log calls to methods in com.pilon.example

@Component
@Aspect
class LoggingAspect {
    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    
    @Around("execution( * com.pilon.example..*.*(..) )")
    public Object log (ProceedingJoinPoint pjp) throws Throwable {
        logger.debug(pjp.toString());
        Object object = pjp.proceed();
        return object;
    }
}