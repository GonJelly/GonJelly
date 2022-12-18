package com.ssafy.member.model.service;

import java.sql.SQLException;
import java.time.LocalDate;

import com.ssafy.member.model.dao.MemberDao;
import com.ssafy.member.model.dto.Member;

public class MemberService {
	MemberDao dao = MemberDao.getInstance();
    LocalDate now = LocalDate.now();

    public Member login(String memberId, String memberPw) {
		Member dto = dao.login(memberId, memberPw);
		System.out.println(dto);
		return dto;
	}
    
    
	
	public int memberRegist(Member dto) {
		// 시스템 추가 속성 설정
		dto.setEntryDate(now.toString()); // Java Utility.getCurrentDate()
		System.out.println(dto);
		return dao.memberRegist(dto);
	}
	
	public int memberEdit(Member dto) {
		return dao.memberEdit(dto);
	}



	public int idCheck(String memberId) throws SQLException {
		return dao.idCheck(memberId);
	}
	
	
	
	public String findPw(String memberId) {
		System.out.println(memberId + " in Service");
		return dao.findPw(memberId);
	}



	public Member mvEdit(Member dto) throws SQLException {
		return dao.mvEdit(dto);
	}



	public void memberDel(String memberId) throws SQLException {
		dao.memberDel(memberId);
	}
}
