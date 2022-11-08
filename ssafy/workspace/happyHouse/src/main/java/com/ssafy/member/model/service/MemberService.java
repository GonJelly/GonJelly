package com.ssafy.member.model.service;

import com.ssafy.member.model.Member;

public interface MemberService {

    // 회원정보 조회하기
    Member memberInfo(Member member) throws Exception;
    // 회원이 있는지 확인
    int idCheck(String userId) throws Exception;
    // 회원가입
    void addMember(Member member) throws Exception;
}
