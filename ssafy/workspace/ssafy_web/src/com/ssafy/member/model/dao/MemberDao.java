package com.ssafy.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.member.model.dto.Member;
import com.ssafy.sample.util.DBUtil;

public class MemberDao {
	// DBUtil 객체 인스턴
	private DBUtil dbutil = DBUtil.getInstance();
	
	// ============ SingleTon ==================
	private static MemberDao instance = new MemberDao();
	private MemberDao() {
	}
	public static MemberDao getInstance() {
		return instance;
	}
	// ============ SingleTon ==================

	
	
	/**
	 * 비밀번호 찾기
	 * @param memberId 아이디
	 * @return memberPw, 미존재시 ""
	 */
	public String findPw(String memberId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String validate = "fail";
		
		try {
			// 2.
			conn = dbutil.getConnection();
			
			// 3. SQL 실행 통로 개설 : 전용통로, 통로 객체 생성시에 sql 구문을 지정
			String sql = 
					"select memberPw from member where memberId=?";
			
			pstmt = conn.prepareStatement(sql);
			
			// 전용통로의 ?에 매핑되는 값을 설정
			pstmt.setString(1, memberId);
			
			
			rs = pstmt.executeQuery();
			System.out.println(memberId + " in Dao");

			// SQL 실행 결과처리
			if (rs.next()) {
				System.out.println("결과처리!");
				validate = rs.getString("member_pw");
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Factory 자원해제 위임
			dbutil.close(conn, pstmt, rs);
		}
		
		return validate;
	}
	
	/**
	 * 로그인
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @return 로그인 회원정보, 미존재시 null
	 */
	public Member login(String memberId, String memberPw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 2.
			conn = dbutil.getConnection();
			
			// 3. SQL 실행 통로 개설 : 전용통로, 통로 객체 생성시에 sql 구문을 지정
			String sql = "select * from member where memberId=? and memberPw=?";
			pstmt = conn.prepareStatement(sql);
			
			// 전용통로의 ?에 매핑되는 값을 설정
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			
			rs = pstmt.executeQuery();
			// SQL 실행 결과처리
			if (rs.next()) {
				return new Member(memberId, memberPw);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Factory 자원해제 위임
			dbutil.close(conn, pstmt, rs);
		}
		
		return null;
	}
	
	
	
	/**
	 * 회원가입
	 * @param Member dto
	 * @return 
	 */
	public int memberRegist(Member dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dbutil.getConnection();
			
			// SQL 실행 통로 개설 : 전용통로, 통로 객체 생성시에 sql 구문을 지정
			String sql = "insert into member values(?, ?, ?, ?, ?, ?, now())";
			pstmt = conn.prepareStatement(sql);
			
			// 전용통로의 ?에 매핑되는 값을 설정
			pstmt.setString(1, dto.getMemberId());
			pstmt.setString(2, dto.getMemberPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getMobile());
			pstmt.setString(5, dto.getEmail());
			pstmt.setInt(6, dto.getAge());

			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Factory 자원해제 위임
			dbutil.close(conn, pstmt);
		}
		return 0;
	}
	
	
	
	/**
	 * 회원정보 변경
	 * @param Member dto
	 * @return 
	 */
	public int memberEdit(Member dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dbutil.getConnection();
			
			// SQL 실행 통로 개설 : 전용통로, 통로 객체 생성시에 sql 구문을 지정
			StringBuilder sql = new StringBuilder();
			sql.append("update member set \n");
			sql.append("memberId = ?, \n");
			sql.append("memberPw = ?, \n");
			sql.append("name = ?, \n");
			sql.append("mobile = ?, \n");
			sql.append("email = ?, \n");
			sql.append("age = ? \n");
			sql.append("where memberId = ?");
			
			pstmt = conn.prepareStatement(sql.toString());

			// 전용통로의 ?에 매핑되는 값을 설정
			pstmt.setString(1, dto.getMemberId());
			pstmt.setString(2, dto.getMemberPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getMobile());
			pstmt.setString(5, dto.getEmail());
			pstmt.setInt(6, dto.getAge());
			pstmt.setString(7, dto.getMemberId());

			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Factory 자원해제 위임
			dbutil.close(conn, pstmt);
		}
		return 0;
	}
	
	
	
	/**
	 * 아이디 중복체크
	 * @param memberId
	 * @return cnt
	 * @throws SQLException
	 */
	public int idCheck(String memberId) throws SQLException {
		int cnt = 1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbutil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select count(memberId) \n");
			sql.append("from member \n");
			sql.append("where memberId=?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			rs.next();
			
			cnt = rs.getInt("count(memberId)");
			
		} finally {
			dbutil.close(rs, pstmt, conn);
		}
		return cnt;
	}
	
	
	/**
	 * 회원수정
	 * @param dto
	 * @return
	 * @throws SQLException 
	 */
	public Member mvEdit(Member dto) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member new_dto = null;
		try {
			conn = dbutil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select * \n");
			sql.append("from member \n");
			sql.append("where memberId=?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getMemberId());
			
			rs = pstmt.executeQuery();
			rs.next();
			
			String memberId = rs.getString("memberId");
			String memberPw = rs.getString("memberPw");
			String name = rs.getString("name");
			String mobile = rs.getString("mobile");
			String email = rs.getString("email");
			int age = rs.getInt("age");
			String entryDate = rs.getString("entry_Date");
			
			new_dto = new Member(memberId, memberPw, name, mobile, email, age, entryDate);
		} finally {
			dbutil.close(rs, pstmt, conn);
		}
		
		return new_dto;
	}
	
	public void memberDel(String memberId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dbutil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("delete \n");
			sql.append("from member \n");
			sql.append("where memberId=?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, memberId);

			
			pstmt.executeUpdate();
			
		} finally {
			dbutil.close(pstmt, conn);
		}
	}
}
