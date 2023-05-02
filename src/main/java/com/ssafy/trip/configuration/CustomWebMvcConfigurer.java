package com.ssafy.trip.configuration;

import com.ssafy.trip.interceptor.ConfirmInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CustomWebMvcConfigurer extends WebMvcConfigurationSupport {

    // TODO : API 확인을 위하여 주석처리중 이후에 수정 예정
//	private final List<String> patterns = Arrays.asList("/board/*");

//	@Override
//	protected void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new ConfirmInterceptor())
//				.addPathPatterns(patterns);
//
//	}

}