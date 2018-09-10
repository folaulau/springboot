package com.kaveinga.audit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.kaveinga.user.User;

@Aspect
public class UserAop {
	
//	@Around("execution(* com.kaveinga.user.UserServiceImp.create(com.kaveinga.user.User)) && args(ob)")
//	public void log(final ProceedingJoinPoint jp, User ob) {
//		System.out.println("USERAOP, USER: "+ob.toJson());
//		System.out.println("\n\n");
//	}
}
