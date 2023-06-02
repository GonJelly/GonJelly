package com.example.google.moduls;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Sample {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long 			memberId;
	@Column(name = "email")
	private String 			email;
	private String 			birthday;
	@Column(name = "nickName")
	private String 			nickName;
	@Enumerated(EnumType.STRING)
	private MemberRole 		role;
	@Enumerated(EnumType.STRING)
	private AuthProvider	authProvider;
	private String 			createDate;
	private String 			updateDate;
	public Sample() {}

}
