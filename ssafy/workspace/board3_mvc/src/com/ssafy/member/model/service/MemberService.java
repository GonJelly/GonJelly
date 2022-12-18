package com.ssafy.member.model.service;

import com.ssafy.member.model.MemberDto;

import java.io.IOException;

public interface MemberService {

	int idCheck(String userid) throws Exception;
	int joinMember(MemberDto member) throws Exception;
	MemberDto loginMember(String userid, String userpwd) throws Exception;
	
}
