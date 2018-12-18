package com.api.dse.alerts.poc.logging;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Pointcut("execution(* com.api.dse.alerts.poc.controller.*.*(..)) || execution(* com.api.dse.alerts.poc.services.*.*(..)) || execution(* com.api.dse.alerts.poc.dao.*.*(..))")
	private void selectAll() {
	}

	@AfterReturning(pointcut = "execution(* com.api.dse.alerts.poc.controller.*.*(..))", returning = "retVal")
	public void logAfterMethod(JoinPoint joinPoint, Object retVal) {
		log.info("returing " + retVal.getClass());
		log.debug("-----------returning--" + retVal);

	}

	@Before("selectAll()")
	public void logBeforeMethod(JoinPoint joinPoint) {
		log.info("***" + new Date(System.currentTimeMillis()) + " " + "Entering in Method :  "
				+ joinPoint.getSignature().getName() + " " + "Of " + joinPoint.getTarget().getClass().getName());
	}

	@After("selectAll()")
	public void logAfterMethod(JoinPoint joinPoint) {

		log.info("***" + new Date(System.currentTimeMillis()) + " " + "Exiting from Method :  "
				+ joinPoint.getSignature().getName() + " " + "Of " + joinPoint.getTarget().getClass().getName());
	}

	@AfterThrowing(pointcut = "selectAll()", throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
		log.error("An exception has been thrown in " + joinPoint.getSignature().getName() + "()");
		log.error("Cause :" + e.getCause());
	}

	@Pointcut("execution(* com.api.dse.alerts.poc.controller.*.*(..))")
	public void businessMethods() {

	}

	@Around("businessMethods()")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		try {
			long start = System.currentTimeMillis();

			Object output = pjp.proceed();

			Long elapsedTime = System.currentTimeMillis() - start;
			MDC.put("API", pjp.getSignature().toLongString());
			MDC.put("executionTime", String.valueOf(elapsedTime));
			log.info("Method execution time for API " + pjp.getSignature() + " : " + elapsedTime + " milliseconds.");			
			return output;
		} finally {
			MDC.clear();
		}
	}
}
