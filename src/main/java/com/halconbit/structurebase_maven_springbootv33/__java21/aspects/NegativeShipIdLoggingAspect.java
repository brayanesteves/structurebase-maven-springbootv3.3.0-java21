package com.halconbit.structurebase_maven_springbootv33.__java21.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class NegativeShipIdLoggingAspect {

    @Pointcut("execution(* *..findById(Long)) && args(id) && id < 0")
    public void onFindShipWithNegativeId(Long id) {
        // Pointcut to intercept method calls to 'findById' with a negative 'id' argument
    }

    //@Before(value = "onFindShipWithNegativeId(Long)", argNames = "id")
    public void logNegativeShipIdRequest(Long id) {
        log.warn("Requesting ship with negative ID: {}", id);
    }
}