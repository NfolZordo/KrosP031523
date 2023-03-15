package com.ars.manager.aop.logger.db;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* org.springframework.data.jpa.repository.JpaRepository.delete*(..))")
    public void onDeleteJpaRepository() {
    }


    @Pointcut("execution(* com.ars.manager.repository.*.delete*(..))")
    public void onDeleteCustomRepository() {
    }
}

