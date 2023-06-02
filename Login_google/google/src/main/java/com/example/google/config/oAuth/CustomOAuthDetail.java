package com.example.google.config.oAuth;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.example.google.moduls.Sample;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CustomOAuthDetail implements UserDetails, OAuth2User {

	private long id;
	private String email;
	private Collection<? extends GrantedAuthority> authorities;
	private Map<String, Object> attributes;
	public CustomOAuthDetail(Long id, String email, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.email = email;
		this.authorities = authorities;
	}

	public static CustomOAuthDetail create(Sample sample) {
		Collection<? extends GrantedAuthority> authorities =
			Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));

		return new CustomOAuthDetail(
			sample.getMemberId(),
			sample.getEmail(),
			authorities
		);
	}

	public static CustomOAuthDetail create(Sample sample, Map<String, Object> attributes) {
		CustomOAuthDetail userDetails = CustomOAuthDetail.create(sample);
		userDetails.setAttributes(attributes);
		return userDetails;
	}

	public void setAttributes(Map<String, Object> attribuutes) {
		this.attributes = attribuutes;
	}
	@Override
	public <A> A getAttribute(String name) {
		return OAuth2User.super.getAttribute(name);
	}

	@Override
	public Map<String, Object> getAttributes() {
		return this.attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getName() {
		return String.valueOf(id);
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

}
