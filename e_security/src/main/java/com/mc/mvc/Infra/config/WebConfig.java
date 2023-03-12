package com.mc.mvc.Infra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mc.mvc.Infra.Interceptor.BoardAuthInterceptor;
import com.mc.mvc.Infra.Interceptor.MemberAuthInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
	//권한 관리는 시큐리티가 하기 때문에 인터셉터 동작을 잠시 중단	
//		registry.addInterceptor(new MemberAuthInterceptor()).addPathPatterns("/member/**");
//		registry.addInterceptor(new BoardAuthInterceptor()).addPathPatterns("/board/**");;

	}
	
	

}
