package com.example.google.config;

import org.apache.catalina.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.google.config.jwt.JwtAccessDeniedHandler;
import com.example.google.config.jwt.JwtAuthenticationEntryPoint;
import com.example.google.config.jwt.JwtAuthenticationFilter;
import com.example.google.config.jwt.JwtTokenProvider;
import com.example.google.config.oAuth.CookieAuthorizationRequestRepository;
import com.example.google.config.oAuth.CustomOAuth2UserService;
import com.example.google.config.oAuth.OAuth2AuthenticationFailureHandler;
import com.example.google.config.oAuth.OAuth2AuthenticationSuccessHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final CustomOAuth2UserService customOAuth2UserService;
	private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;
	private final OAuth2AuthenticationSuccessHandler authenticationSuccessHandler;
	private final OAuth2AuthenticationFailureHandler authenticationFailureHandler;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
			.authorizeHttpRequests()
			.antMatchers("h2-console/**").permitAll()
			.mvcMatchers("/", "/login", "/oauth2/**").permitAll();

		http.httpBasic().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.formLogin().disable()
			.oauth2Login()
			.authorizationEndpoint()
			.baseUri("/oauth2/authorization")
			.authorizationRequestRepository(cookieAuthorizationRequestRepository)
			.and()
			.redirectionEndpoint()
			.baseUri("/*/oauth2/code/*")

			.and()
			.userInfoEndpoint()
			.userService(customOAuth2UserService)

			.and()
			.successHandler(authenticationSuccessHandler)
			.failureHandler(authenticationFailureHandler);

		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		http.exceptionHandling()
			.authenticationEntryPoint(jwtAuthenticationEntryPoint)
			.accessDeniedHandler(jwtAccessDeniedHandler);

		return http.build();
	}

}
