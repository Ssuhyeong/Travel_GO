package com.ssafy.trip.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@EnableWebMvc
//@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer {

    // TODO : API 확인을 위하여 주석처리중 이후에 수정 예정
//	private final List<String> patterns = Arrays.asList("/board/*");

//	@Override
//	protected void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new ConfirmInterceptor())
//				.addPathPatterns(patterns);
//
//	}

}