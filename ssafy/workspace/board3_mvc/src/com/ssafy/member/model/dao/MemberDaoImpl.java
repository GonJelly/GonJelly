package com.ssafy.member.model.dao;

import com.ssafy.member.model.MemberDto;
import com.ssafy.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDaoImpl implements MemberDao {

    private static MemberDaoImpl instance = new MemberDaoImpl();
    private DBUtil db;

    private MemberDaoImpl() { db = DBUtil.getInstance(); }
    public static MemberDaoImpl getInstance() { return instance;}


    @Override
    public int idCheck(String userId) throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("select count(*) as cnt from members \n");
        sql.append("where user_id = ?");

        Connection conn = db.getConnection();
        PreparedStatement psmt = conn.prepareStatement(sql.toString());
        psmt.setString(1, userId);
        ResultSet rs = psmt.executeQuery();

        int cnt = 1;
        if( rs.next() ) {
            cnt -= rs.getInt("cnt");
        }
        db.close(conn,psmt,rs);
        return cnt;
    }

    @Override
    public int joinMember(MemberDto memberDto) throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("insert into members(user_name,user_id,user_password,email_id,email_domain,join_date) \n");
        sql.append("value (?,?,?,?,?,NOW())\n");

        Connection conn = db.getConnection();
        PreparedStatement psmt = conn.prepareStatement(sql.toString());

        int idx = 0;
        psmt.setString(++idx,memberDto.getUserName());
        psmt.setString(++idx,memberDto.getUserId());
        psmt.setString(++idx,memberDto.getUserPwd());
        psmt.setString(++idx,memberDto.getEmailId());
        psmt.setString(++idx,memberDto.getEmailDomain());

        int result = psmt.executeUpdate();
        return result;
    }

    @Override
    public MemberDto loginMember(String userId, String userPwd) throws SQLException {

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        MemberDto dto = null;

        int idx = 1;    // 삽입해야하는 값만큼 prepare의 갯수를 handling 할 수 있음
        StringBuilder sql = new StringBuilder();
        sql.append("Select * from members \n");
        sql.append("where user_id = ? and user_password = ?");

        conn = db.getConnection();
        psmt = conn.prepareStatement( sql.toString() );

        psmt.setString(idx++, userId);
        psmt.setString(idx++, userPwd);

        rs = psmt.executeQuery();
        // 유저의 정보는 only 1개여야하기 때문에 while보다는 if문으로 컨트롤러 해주자
        if( rs.next() ) {

            String id = rs.getString("user_id");
            String name = rs.getString("user_name");
            String pwd = rs.getString("user_password");
            String emailId = rs.getString("email_id");
            String emailDomain = rs.getString("email_domain");
            String joinDate = rs.getString("join_date");

            // 유저 도메인 ( 유저의 정보를 맵핑할 클래스 ) 생성
            dto = new MemberDto();

            dto.setUserId(id);
            dto.setUserName(name);
            dto.setUserPwd(pwd);
            dto.setEmailId(emailId);
            dto.setEmailDomain(emailDomain);
            dto.setJoinDate(joinDate);

        }
        db.close(conn,psmt,rs);
        return dto;

    }
}
