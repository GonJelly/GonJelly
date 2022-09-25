package com.ssafy.member.model.service;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.dao.MemberDao;
import com.ssafy.member.model.dao.MemberDaoImpl;

import java.io.IOException;

public class MemberServiceImpl implements MemberService{

    // 싱글톤 적용
    private static MemberServiceImpl instance = new MemberServiceImpl();
    private MemberDao dao;

    private MemberServiceImpl() { dao = MemberDaoImpl.getInstance(); }
    public static MemberServiceImpl getInstance() {
        return instance;
    }
    // 아이디 체크
    @Override
    public int idCheck(String userid) throws Exception {
        return dao.idCheck(userid);
    }

    @Override
    public int joinMember(MemberDto member) throws Exception {
        return dao.joinMember(member);
    }

    // 특정회원 로그인 정보 가져오기
    @Override
    public MemberDto loginMember(String userid, String userpw) throws Exception {
        return dao.loginMember(userid,userpw);
    }
}
