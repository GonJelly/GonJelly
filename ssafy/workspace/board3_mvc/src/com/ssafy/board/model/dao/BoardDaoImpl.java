package com.ssafy.board.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssafy.board.model.BoardDto;
import com.ssafy.Util.DBUtil;

public class BoardDaoImpl implements BoardDao {

	private static BoardDao boardDao = new BoardDaoImpl();
	private DBUtil dbUtil;
	
	private BoardDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}

	public static BoardDao getBoardDao() {
		return boardDao;
	}
	
	@Override
	public int writeArticle(BoardDto boardDto) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int idx = 0;
		int result = 0;

		StringBuilder sql = new StringBuilder();
		sql.append("insert into board( user_id, subject, content, hit, register_time) \n");
		sql.append("value ( ?, ?, ?, 0, NOW() )");

		conn = dbUtil.getConnection();
		pstmt = conn.prepareStatement(sql.toString());

		pstmt.setString(++idx,boardDto.getUserId());
		pstmt.setString(++idx,boardDto.getSubject());
		pstmt.setString(++idx,boardDto.getContent());

		result = pstmt.executeUpdate();
		dbUtil.close(pstmt,conn);
		return result;
	}

	@Override
	public List<BoardDto> listArticle(Map<String, String> map) throws SQLException {

		List<BoardDto> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from board \n");
		sql.append("order by register_time desc,article_no desc \n");
		sql.append("limit " + 20*(Integer.parseInt(map.get("pageNo")) - 1) + ",20");

		Connection conn = dbUtil.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql.toString());

		while ( rs.next() ) {

			BoardDto board = new BoardDto();
			board.setArticleNo(rs.getInt("article_no"));
			board.setUserId(rs.getString("user_id"));
			board.setSubject(rs.getString("subject"));
			board.setContent(rs.getString("content"));
			board.setHit(rs.getInt("hit"));
			board.setRegisterTime(rs.getString("register_time"));

			list.add(board);

		}

		dbUtil.close(rs,stmt,conn);
		return list;
	}

	@Override
	public int totalArticleCount(Map<String, String> map) throws SQLException {
		return 0;
	}

	@Override
	public BoardDto getArticle(int articleNo) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("select * from board \n");
		sql.append("where article_no=?");

		BoardDto dto = new BoardDto();
		Connection conn = dbUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		pstmt.setInt(1,articleNo);

		ResultSet rs = pstmt.executeQuery();
		if( rs.next() ) {

			dto.setArticleNo(rs.getInt("article_no"));
			dto.setUserId(rs.getString("user_id"));
			dto.setSubject(rs.getString("subject"));
			dto.setContent(rs.getString("content"));
			dto.setHit(rs.getInt("hit"));
			dto.setRegisterTime(rs.getString("register_time"));

		}

		dbUtil.close(rs,pstmt,conn);
		return dto;
	}

	@Override
	public void updateHit(int articleNo) throws SQLException {
		
	}

	@Override
	public void modifyArticle(BoardDto boardDto) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("update board set \n");
		sql.append("content=?\n");
		sql.append("register_time=now()\n");
		sql.append("where article_no=? \n");

		int idx = 1;
		Connection conn = dbUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		pstmt.setString(idx++,boardDto.getContent());
		pstmt.setInt(idx++,boardDto.getArticleNo());

		int result = pstmt.executeUpdate();
		dbUtil.close(pstmt,conn);

	}

	@Override
	public void deleteArticle(int articleNo) throws SQLException {
		
	}

}
