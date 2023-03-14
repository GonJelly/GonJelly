package com.example.google.config.oAuth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.google.exception.OAuthProcessingException;
import com.example.google.moduls.AuthProvider;
import com.example.google.moduls.MemberRole;
import com.example.google.moduls.Sample;
import com.example.google.moduls.SampleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private SampleRepository sampleRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		return process(userRequest, oAuth2User);
	}

	public OAuth2User process(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {

		AuthProvider authProvider = AuthProvider.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());
		OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(authProvider, oAuth2User.getAttributes());
		// 이메일이 없다면 예외처리
		if( oAuth2UserInfo.getEmail().isEmpty()) {
			throw new OAuthProcessingException("이메일을 찾을 수 없습니다. ( OAuth2 provider");
		}
		// DB에서 이메일을 이용해서 회원정보 가져오기
		Optional<Sample> sampleOpt = sampleRepository.findByEmail(oAuth2UserInfo.getEmail());
		Sample sample;
		// 회원이 가입되어 있는지 확인 ( 가입된 경우 )
		if( sampleOpt.isPresent() ) {
			sample = sampleOpt.get();
		} else {
			sample = createMember(oAuth2UserInfo, authProvider);
		}

		return CustomOAuthDetail.create(sample, oAuth2User.getAttributes());
	}

	private Sample createMember(OAuth2UserInfo userInfo, AuthProvider authProvider) {
		Sample sample = Sample.builder()
			.email(userInfo.getEmail())
			.role(MemberRole.USER)
			.authProvider(authProvider)
			.build();

		return sampleRepository.save(sample);
	}
}
