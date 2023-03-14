package com.example.google.moduls;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum MemberRole {

	ADMIN("ROLE_ADMIN","admin"),
	USER("ROLE_USER","user");

	MemberRole(String role, String name) {
		this.role = role;
		this.name = name;
	}

	private final String role;
	private final String name;

}
