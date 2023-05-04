package com.ssafy.trip.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {

	private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	//Controller, Service, Dao의 모든 메소드가 실행되기 전 로그 찍기
	@Before(value = "execution(* com.ssafy.trip.controller.*.*(..))")
	public void loggingAdvice(JoinPoint jp) {
		logger.debug("메소드 선언부 : {}, 전달 파라미터 : {}", jp.getSignature(), Arrays.toString(jp.getArgs()));
	}
	
}
