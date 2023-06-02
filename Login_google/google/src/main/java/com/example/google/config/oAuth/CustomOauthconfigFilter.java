package com.example.google.config.oAuth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;

public class CustomOauthconfigFilter extends OAuth2LoginAuthenticationFilter {

	public CustomOauthconfigFilter(ClientRegistrationRepository clientRegistrationRepository,
		OAuth2AuthorizedClientService authorizedClientService) {
		super(clientRegistrationRepository, authorizedClientService);
	}

	public CustomOauthconfigFilter(ClientRegistrationRepository clientRegistrationRepository,
		OAuth2AuthorizedClientService authorizedClientService, String filterProcessesUrl) {
		super(clientRegistrationRepository, authorizedClientService, filterProcessesUrl);
	}

	public CustomOauthconfigFilter(ClientRegistrationRepository clientRegistrationRepository,
		OAuth2AuthorizedClientRepository authorizedClientRepository, String filterProcessesUrl) {
		super(clientRegistrationRepository, authorizedClientRepository, filterProcessesUrl);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws
		AuthenticationException {
		return super.attemptAuthentication(request, response);
	}
}
