package com.ssafy.member.model.service;

import com.ssafy.member.model.Member;

import java.util.Map;

public interface MemberService {

    // 회원정보 조회하기
    Member memberInfo(Member member) throws Exception;
    // 회원이 있는지 확인
    int idCheck(String userId) throws Exception;
    // 회원가입
    void addMember(Member member) throws Exception;
    // 회원정보 변경
    int updateMember(Member member) throws Exception;
    // 비밀번호 찾기
    String searchPwd(Member member) throws Exception;
    // 비밀번호 변경
    void updatePass(Map<String,Object> newPass) throws Exception;
    // 회원정보 삭제하기
    int deleteMember(String userId) throws Exception;
}
