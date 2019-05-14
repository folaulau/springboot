package com.lovemesomecoding.user;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAspect {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Before("execution(* com.lovemesomecoding.user.UserController.getUserByUid(..))")
	public void before(JoinPoint joinPoint) {
		log.debug("before(..)");
	}

//	@Around("execution(* com.lovemesomecoding.user.UserController.getUserByUid(..))")
//	public void beforeAndAfter(ProceedingJoinPoint proceedingJoinPoint) {
//		log.debug("beforeAndAfter(..)");
//
//		Object result = null;
//		try {
//			result = proceedingJoinPoint.proceed();
//		} catch (Throwable e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		log.debug("");
//	}
}
