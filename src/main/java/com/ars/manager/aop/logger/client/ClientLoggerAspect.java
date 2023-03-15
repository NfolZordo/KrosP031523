package com.ars.manager.aop.logger.client;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Aspect
@Component
@Slf4j
public class ClientLoggerAspect {

    @AfterThrowing(pointcut = "execution(* com.ars.manager.client.*.*(..))", throwing = "ex")
    public void logWebClientException(WebClientResponseException ex) {
        log.error("WebClientResponseException occurred. HTTP status: {}, Response body: {}", ex.getStatusCode(), ex.getResponseBodyAsString());
    }
}
