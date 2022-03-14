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
		System.out.println("Before executing");
		log.info("Logging info before executing");

	}
	
	@After(value = "execution(* com.bracamod.geo.controller.*.*(..))")
	public void after(JoinPoint joinPoint) {
		System.out.println("After executing");
		System.out.println(joinPoint.getTarget());
		System.out.println(joinPoint.getSignature());
		System.out.println(joinPoint.toShortString());
		System.out.println(joinPoint.toLongString());
		
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		System.out.println("Method name  - " + method.getName());

	}
	
	@AfterReturning(value = "execution(* com.bracamod.geo.controller.*.*(..))",
			returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		System.out.println("After returninhg");

	}
	
	@AfterThrowing(pointcut ="execution(* com.bracamod.geo.controller.*.*(..))", throwing = "e")
	public void myAfterThrowing(JoinPoint joinPoint, Throwable e) {
		System.out.println("Okay - we're in the handler...");
	    Signature signature = joinPoint.getSignature();
	    String methodName = signature.getName();
	    String stuff = signature.toString();
	    String arguments = Arrays.toString(joinPoint.getArgs());
	   System.out.println("Write something in the log... We have caught exception in method: "
	        + methodName + " with arguments "
	        + arguments + "\nand the full toString: " + stuff + "\nthe exception is: "
	        + e.getMessage());
	  }
	
}
