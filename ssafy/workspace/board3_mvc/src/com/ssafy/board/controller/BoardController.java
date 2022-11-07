package com.ssafy.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.board.model.service.BoardServiceImpl;
import com.ssafy.member.model.MemberDto;
import com.ssafy.Util.ParameterCheck;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardService boardService;
	private Map<String, String> map;
	
	public void init() {
		boardService = BoardServiceImpl.getBoardService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		
		String path = "/index.jsp";
		if("list".equals(act)) {
			path = list(request, response);
			forward(request, response, path);
		} else if("mvwrite".equals(act)) {
			path = "/board/write.jsp";
			redirect(request, response, path);
		} else if("write".equals(act)) {
			path = write(request, response);
			redirect(request, response, path);
		} else if("view".equals(act)) {
			path = view(request, response);
			forward(request, response, path);
		} else if("mvmodify".equals(act)) {
			path = mvModify(request, response);
			forward(request, response, path);
		} else if("modify".equals(act)) {
			path = modify(request, response);
			forward(request, response, path);
		} else if("delete".equals(act)) {
			path = delete(request, response);
			redirect(request, response, path);
		} else {
			redirect(request, response,path);
		}
	}
	
	private void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
	
	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

	private String list(HttpServletRequest request, HttpServletResponse response) {

		Map<String,String> map = new HashMap<>();

		map.put("pageNo",request.getParameter("pgno"));
		map.put("key",request.getParameter("key"));
		map.put("word",request.getParameter("word"));

		try {
			List<BoardDto> list = boardService.listArticle(map);
			request.setAttribute("articles",list);
			return request.getContextPath() + "board/list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error.jsp";
		}
	}

	private String write(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession(false);
		MemberDto user = new MemberDto();
		if( session != null ) {
			user = (MemberDto) session.getAttribute("userinfo");
		}
		try {
			String userid = user.getUserId();
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");

			BoardDto boardDto = new BoardDto();

			boardDto.setUserId(userid);
			boardDto.setSubject(subject);
			boardDto.setContent(content);
			int result = 0;
			for(int i = 0; i < 20; i++) {
				result = boardService.writeArticle(boardDto);
			}
			if ( result > 0) {
				System.out.println("정상적으로 입력되었습니다.");
				return request.getContextPath() + "/board?act=list";
			} else {
				System.out.println("글작성이 실패");
				return request.getContextPath() + "/board/act=mvwrite";
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return "error/error.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("글작성 중 오류가 발생하였습니다.");
			return "error/error.jsp";
		}
	}
	
	private String view(HttpServletRequest request, HttpServletResponse response) {

		int articleNo = Integer.parseInt(request.getParameter("articleNo"));

		try {
			BoardDto board = boardService.getArticle(articleNo);
			request.setAttribute("article",board);
			return request.getContextPath() + "/board/view.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
			return "error/error.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error.jsp";
		}
	}

	private String mvModify(HttpServletRequest request, HttpServletResponse response) {
		return "";
	}

	private String modify(HttpServletRequest request, HttpServletResponse response) {
		return "";
	}

	private String delete(HttpServletRequest request, HttpServletResponse response) {
		return "";
	}
	
}
