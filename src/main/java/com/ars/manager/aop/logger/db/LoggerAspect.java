package com.ars.manager.aop.logger.db;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Aspect
@Slf4j
public class LoggerAspect {

    @SneakyThrows
    @Around("Pointcuts.onDeleteJpaRepository() || Pointcuts.onDeleteCustomRepository()")
    public Object onDelete(ProceedingJoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        List<String> args = Arrays.stream(joinPoint.getArgs()).map(Object::toString).toList();

        LogInfo logInfo = new LogInfo(className, methodName, args);

        log.info("Class: " + logInfo.className
                + " \nmethod: " + logInfo.methodName
                + " \nargs: " + args);

        return joinPoint.proceed();
    }

    record LogInfo(String className, String methodName, List<String> args) {
    }
}
