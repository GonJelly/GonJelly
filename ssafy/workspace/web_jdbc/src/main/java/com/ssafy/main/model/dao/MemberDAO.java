package com.ssafy.main.model.dao;

import com.ssafy.main.model.Member;

import java.sql.SQLException;
import java.util.List;

public interface MemberDAO {

    Member getUser(String userid, String userPw) throws SQLException;
    Member idCheck(String userid) throws SQLException;
    List<String> getFollowing(String userid) throws SQLException;
    int addMember(Member user) throws SQLException;
    List<String> getFollower(Member user) throws SQLException;
    int modifiedMember(Member user) throws SQLException;
    int deleteMember(Member user) throws SQLException;

}
