package com.ssafy.main.service;

import com.ssafy.main.model.Member;
import com.ssafy.main.model.dao.MemberDAO;
import com.ssafy.main.model.dao.MemberDAOImpl;

import java.util.List;

public class MemberServiceImpl implements MemberService{

    private static MemberServiceImpl instance = new MemberServiceImpl();
    private MemberDAO dao;
    private MemberServiceImpl() { dao = MemberDAOImpl.getInstance(); }
    public static MemberServiceImpl getInstance() { return instance; }

    public Member getUser(String userid, String userPw) throws Exception {
        return dao.getUser(userid,userPw);
    }

    @Override
    public int addMember(Member user) throws Exception {
        return dao.addMember(user);
    }

    @Override
    public Member idCheck(String userid) throws Exception {
        return dao.idCheck(userid);
    }

    @Override
    public List<String> getFollowing(String userid) throws Exception {
        return dao.getFollowing(userid);
    }

    @Override
    public List<String> getFollower(Member user) throws Exception {
        return dao.getFollower(user);
    }

    @Override
    public int modifiedMember(Member user) throws Exception {
        return dao.modifiedMember(user);
    }

    @Override
    public int deleteMember(Member user) throws Exception {
        return dao.deleteMember(user);
    }
}
