package com.microservices.payroll.exception;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AsyncExecptionHandler implements AsyncUncaughtExceptionHandler{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
		log.error("\n****** handleUncaughtException ******");
		
		log.error("*  Class name: "+method.getDeclaringClass());
		log.error("*  Method name - "+method.getName());
		log.error("*  Exception message - "+throwable.getMessage());
		
		// mail message
//		StringBuilder message = new StringBuilder();
//		message.append("Class name: "+method.getDeclaringClass()+"\n");
//		message.append("Method name: "+method.getName()+"\n");
//		message.append("Exception message - "+throwable.getMessage()+"\n");
		
		
		for(Object param : obj){
			log.error("*  Param - "+param);
//			message.append("Param - "+param+"\n");
		}
		log.error("*************************************\n");
		
//		try {
//			mail.sendSimpleMail("fkaveinga@columbususa.com", "ttaps async uncaught exception", message.toString());
//		} catch (Exception e) {
//			log.info("Exception, send notification email or error -> "+e.getMessage());
//		}
//		
	}

}
