package com.ssafy.main.model.dao;

import com.ssafy.main.model.Member;
import com.ssafy.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO{

    private static MemberDAOImpl instance = new MemberDAOImpl();
    private DBUtil db;
    private MemberDAOImpl() { db = DBUtil.getInstance(); }
    public static MemberDAOImpl getInstance() { return instance; }

    public Member getUser(String userid, String userPw) throws SQLException {

        Member user = new Member();
        StringBuilder sql = new StringBuilder();
        sql.append("select userid, userpw, name, email \n");
        sql.append("from user \n");
        sql.append("where userid = ? and \n");
        sql.append("      userpw = ? \n");

        Connection conn = db.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());

        pstmt.setString(1,userid);
        pstmt.setString(2,userPw);

        ResultSet rs = pstmt.executeQuery();
        if( rs.next() ) {

            String id = rs.getString("userid");
            String pw = rs.getString("userpw");
            String name = rs.getString("name");
            String email = rs.getString("email");

            user.setUserid(id);
            user.setUserPw(pw);
            user.setName(name);
            user.setEmail(email);

//            System.out.println(user);
        } else {
            user = null;
        }

        db.close(rs,pstmt,conn);
        return user;
    }

    public Member idCheck(String userid) throws SQLException {

        Member user = new Member();
        StringBuilder sql = new StringBuilder();
        sql.append("select userid, userpw, name, email \n");
        sql.append("from user \n");
        sql.append("where userid = ? \n");

        Connection conn = db.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());

        pstmt.setString(1,userid);

        ResultSet rs = pstmt.executeQuery();
        if( rs.next() ) {

            String id = rs.getString("userid");
            String pw = rs.getString("userpw");
            String name = rs.getString("name");
            String email = rs.getString("email");

            user.setUserid(id);
            user.setUserPw(pw);
            user.setName(name);
            user.setEmail(email);

//            System.out.println(user);
        } else {
            user = null;
        }

        db.close(rs,pstmt,conn);
        return user;
    }

    @Override
    public List<String> getFollowing(String userid) throws SQLException {

        List<String> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("select following \n");
        sql.append("from follow \n");
        sql.append("where follower = ? ");

        Connection conn = db.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());

        pstmt.setString(1,userid);

        ResultSet rs = pstmt.executeQuery();
        while ( rs.next() ) {
            String following = rs.getString("following");
            list.add(following);
        }
        db.close(rs,pstmt,conn);
        return list;
    }

    @Override
    public List<String> getFollower(Member user) throws SQLException {

        List<String> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("select follower \n");
        sql.append("from follow \n");
        sql.append("where following = ? ");

        Connection conn = db.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());

        pstmt.setString(1,user.getUserid());

        ResultSet rs = pstmt.executeQuery();
        while ( rs.next() ) {
            String following = rs.getString("follower");
            list.add(following);
        }
        db.close(rs,pstmt,conn);
        return list;
    }

    @Override
    public int addMember(Member user) throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("insert into user(userid, userpw, name, email) \n");
        sql.append("value (?, ?, ?, ?) ");

        int idx = 0;
        Connection conn = db.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());

        pstmt.setString(++idx, user.getUserid());
        pstmt.setString(++idx, user.getUserPw());
        pstmt.setString(++idx, user.getName());
        pstmt.setString(++idx, user.getEmail());

        int result = pstmt.executeUpdate();

        db.close(pstmt,conn);
        return result;
    }

    @Override
    public int modifiedMember(Member user) throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("update user \n");
        sql.append("set userpw = ?,\n");
        sql.append("    name = ?,\n");
        sql.append("    email = ?\n");
        sql.append("where userid = ?");

        int idx = 0;
        Connection conn = db.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        System.out.println(user);
        pstmt.setString(++idx, user.getUserPw());
        pstmt.setString(++idx, user.getName());
        pstmt.setString(++idx, user.getEmail());
        pstmt.setString(++idx, user.getUserid());

        int result = pstmt.executeUpdate();
        db.close(pstmt,conn);
        return result;
    }

    @Override
    public int deleteMember(Member user) throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("delete from user \n");
        sql.append("where userid = ?");

        int idx = 0;
        Connection conn = db.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
//        System.out.println(user);
        pstmt.setString(++idx, user.getUserid());

        int result = pstmt.executeUpdate();
        db.close(pstmt,conn);
        return result;
    }
}
