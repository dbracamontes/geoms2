package com.bracamod.geo.aspect;

import java.lang.reflect.*;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAspect {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Before(value = "execution(* com.bracamod.geo.controller.*.*(..))")
	public void before() {
		log.info("Before executing");
	}
	
	@After(value = "execution(* com.bracamod.geo.controller.*.*(..))")
	public void after(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		log.info("After executing - method: {}, target: {}", method.getName(), joinPoint.getTarget());
	}
	
	@AfterReturning(value = "execution(* com.bracamod.geo.controller.*.*(..))",
			returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		log.info("After returning - method: {}", joinPoint.getSignature().getName());
	}
	
	@AfterThrowing(pointcut ="execution(* com.bracamod.geo.controller.*.*(..))", throwing = "e")
	public void myAfterThrowing(JoinPoint joinPoint, Throwable e) {
		Signature signature = joinPoint.getSignature();
		String methodName = signature.getName();
		String stuff = signature.toString();
		String arguments = Arrays.toString(joinPoint.getArgs());
		log.error("Exception in method: {} with arguments {} ({}): {}", methodName, arguments, stuff, e.getMessage());
	}
	
}
