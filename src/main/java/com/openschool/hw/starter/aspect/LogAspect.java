package com.openschool.hw.starter.aspect;

import com.openschool.hw.starter.aspect.exception.CustomException;
import com.openschool.hw.starter.config.LogProperties;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.util.List;

@Aspect
public class LogAspect {

    private final Logger log = LoggerFactory.getLogger(LogAspect.class);

    private final LogProperties logProperties;

    private final Level level;

    public LogAspect(LogProperties logProperties) {
        this.logProperties = logProperties;
        this.level = logProperties.getLevel();
    }

    @Before("@annotation(com.openschool.hw.starter.aspect.annotation.Logging)")
    public void logCall(JoinPoint joinPoint) {
        log.atLevel(level).log("Called method {}#{} with args {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                joinPoint.getArgs());

    }

    @AfterReturning(value = "@annotation(com.openschool.hw.starter.aspect.annotation.Logging)", returning = "object")
    public void logResult(JoinPoint joinPoint, Object object) {
        log.atLevel(level).log("Method {}#{} returned {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                object);
    }

    @AfterReturning(value = "@annotation(com.openschool.hw.starter.aspect.annotation.Logging)", returning = "objects")
    public void logResult(JoinPoint joinPoint, List<Object> objects) {
        log.atLevel(level).log("Method {}#{} returned {} objects",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                objects.size());

    }

    @AfterThrowing(value = "@annotation(com.openschool.hw.starter.aspect.annotation.Logging)", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        log.atLevel(level).log("Method {}#{} threw exception: {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                exception.toString());
    }

    @Around("@annotation(com.openschool.hw.starter.aspect.annotation.Logging)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        long startTime = System.currentTimeMillis();
        try {
            result = joinPoint.proceed();
        } catch (Exception ex) {
            throw new CustomException(ex.getMessage());
        } finally {
            long endTime = System.currentTimeMillis();
            log.atLevel(level).log("{}#{} method execution time: {} ms",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    endTime - startTime);
        }
        return result;
    }
}
