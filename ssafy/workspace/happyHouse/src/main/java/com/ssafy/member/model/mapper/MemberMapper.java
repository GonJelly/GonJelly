package com.ssafy.member.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.member.model.Member;

public interface MemberMapper {
	
	// 회원점보를 조회할 경우
	List<Member> listMember() throws SQLException;
	// 로그인 및 특정 유저의 정보가 필요할 경우
	Member selectMember(String id) throws SQLException;
	// id가 중복되어 있는지 검사
	int isChekc(String id) throws SQLException;
	// 회원정보 등록
	void insertMember(Member member) throws SQLException;
	// 회원정보 삭제
	void deleteMember(Member member) throws SQLException;
	
}
