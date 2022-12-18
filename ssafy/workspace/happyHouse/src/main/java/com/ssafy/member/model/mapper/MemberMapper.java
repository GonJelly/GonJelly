package com.ssafy.member.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.member.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

@Mapper
public interface MemberMapper {
	
	// 회원점보를 조회할 경우
//	List<Member> listMember() throws SQLException;
	// 로그인 및 특정 유저의 정보가 필요할 경우
	Member selectMember(Member member) throws SQLException;
	// id가 중복되어 있는지 검사
	int idCheck(String id) throws SQLException;
	// 회원정보 등록
	void insertMember(Member member) throws SQLException;
	// 회원정보 삭제
//	void deleteMember(Member member) throws SQLException;
	// 비밀번호 찾기
	String searchPwd(Member member) throws SQLException;
	// 패스워드 변경
	void updatePass(Map<String,Object> map) throws SQLException;
	// 회원정보 수정하기
	int updateMember(Member member) throws SQLException;
	// 회원정보 삭제하기
	int deleteMember(String userId) throws SQLException;
}
