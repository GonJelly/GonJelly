package com.example.google.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		// 토큰에서 'Bearer '부분을 제외하고 가져오기
		String token = parseBearerToken(request);
		// 토큰이 유혀성 검사 ( 토큰이 존재하는지 , 토큰의 가용성 검사 )
		if (StringUtils.hasText(token) && jwtTokenProvider.vailableToken(token)) {
			// 토큰의 사용자 정보를 저장하고 있는 Authentication 객체 가져오기
			Authentication authentication = jwtTokenProvider.getAuthentication(token);
			// Authentication 객체를 저장하는 구간
			SecurityContextHolder.getContext().setAuthentication(authentication);
			log.debug(authentication.getName() + "의 인증정보 저장");
		} else {
			log.debug("유효한 JWT 토큰이 없습니다.");
		}
		filterChain.doFilter(request, response);
	}

	private String parseBearerToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authentication");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}
}
