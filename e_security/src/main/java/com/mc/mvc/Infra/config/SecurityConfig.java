package com.mc.mvc.Infra.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.mc.mvc.Infra.config.security.AuthFailureHandler;
import com.mc.mvc.Infra.config.security.AuthSuccessHandler;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity //Spring Security의 기본 설정 대신 사용자가 커즈터마이징한 설정을 시큐리티에 적용하기 위한 
@AllArgsConstructor

public class SecurityConfig {
	private AuthSuccessHandler authSuccessHandler;
	private AuthFailureHandler authFailureHandler;
	private DataSource dataSource;
	private final UserDetailsService userDetailsService;
	
	@Bean
	public PersistentTokenRepository tokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		return tokenRepository;
	}
	
	
	@Bean                                                            
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests()
			.antMatchers(HttpMethod.GET, "/").permitAll()
			.antMatchers(HttpMethod.GET, "/member/signup", "/member/checkId","/member/signupimpl/**").permitAll()
			.antMatchers(HttpMethod.POST, "/member/signup").permitAll()
			.antMatchers(HttpMethod.POST, "/mail").permitAll()
			.antMatchers(HttpMethod.GET, "/board/list", "/board/detail","/board/download").permitAll()
			.antMatchers(HttpMethod.GET, "/admin").hasAuthority("ROLE_ADMIN")
			.anyRequest().authenticated();
		
		http.formLogin()
			.loginProcessingUrl("/member/login")
			.loginPage("/member/login")
			.usernameParameter("userId")
//			.successHandler(authSuccessHandler)
//			.failureHandler(authFailureHandler)
			.permitAll();
		
		http.logout()
			.logoutUrl("/member/logout")
			.logoutSuccessUrl("/member/login");
		http.rememberMe()
			.userDetailsService(userDetailsService)
			.tokenRepository(tokenRepository());
		
		//csrf: post요청일 때 수행해야 하는 csrf토큰 검증을 off
		//http.csrf().disable();
		http.csrf().ignoringAntMatchers("/mail");
		return http.build();
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring().antMatchers("/css/**","/js/**","/img/**","/icon/**")
		.requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}
	
	
}
