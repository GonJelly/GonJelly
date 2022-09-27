package com.ssafy.main.service;

import com.ssafy.main.model.Member;

import java.util.List;

public interface MemberService {

    Member getUser(String userid, String userPw) throws Exception;
    Member idCheck(String userid) throws Exception;
    int addMember(Member user) throws Exception;
    List<String> getFollowing(String userid) throws Exception;
    List<String> getFollower(Member user) throws Exception;
    int modifiedMember(Member user) throws Exception;
    int deleteMember(Member user) throws Exception;
}
