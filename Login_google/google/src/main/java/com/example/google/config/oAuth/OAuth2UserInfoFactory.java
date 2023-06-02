package com.example.google.config.oAuth;

import java.util.Map;

import com.example.google.moduls.AuthProvider;

public class OAuth2UserInfoFactory {
	public static OAuth2UserInfo getOAuth2UserInfo(AuthProvider authProvider, Map<String, Object> attributes) {
		switch (authProvider) {
			case GOOGLE:
				return new GoogleOAuth2UserInfo(attributes);
			default:
				return null;
		}
	}
}
