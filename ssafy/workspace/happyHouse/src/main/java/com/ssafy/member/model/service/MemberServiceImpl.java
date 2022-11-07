package com.ssafy.member.model.service;

import com.ssafy.member.model.Member;
import com.ssafy.member.model.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberMapper memberMapper;
    @Override
    public Member memberInfo(Member member) throws Exception {
        return memberMapper.selectMember(member);
    }

    @Override
    public int idCheck(String userId) throws Exception {
        return memberMapper.idCheck(userId);
    }
}
