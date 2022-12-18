package com.ssafy.member.model.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.member.model.dto.Member;
import com.ssafy.member.model.service.MemberService;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/controller")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     MemberService service = new MemberService();

	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("action: " + action);
	
		String path = "/index.jsp";
		
		switch(action) {
		case "login":
			path = login(request, response);
			forward(request, response, path);
			break;
		case "logout":
			path = logout(request, response);
			forward(request, response, path);
			break;
		case "idcheck":
			System.out.println("idcheck!");
			int cnt = idCheck(request, response);
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out_id = response.getWriter();
			out_id.println(cnt);
			out_id.close();
			break;
		case "memberRegist":
			memberRegist(request, response);
			break;
		case "memberEdit":
			memberEdit(request, response);
			break;
		case "mvEdit":
			try {
				mvEdit(request, response);
				forward(request, response, "/member/memberEdit.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "findPw":
			String pw = findPw(request, response);
			PrintWriter out_pw = response.getWriter();
			out_pw.println(pw);
			break;
		case "memberDel":
			try {
				memberDel(request, response);
				forward(request, response, "/index.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case "memberDetail":
			try {
				mvEdit(request, response);
				forward(request, response, "/member/memberDetail.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case "gohome":
			path = "/index.jsp";
			redirect(request, response, path);
			break;
		}
	}

	
	
	
	private String memberDetail(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession();
		Member dto = (Member) session.getAttribute("user_session");
		Member new_dto = service.mvEdit(dto);
		
		request.setAttribute("memberId", new_dto.getMemberId());
		request.setAttribute("memberPw", new_dto.getMemberPw());
		request.setAttribute("name", new_dto.getName());
		request.setAttribute("mobile", new_dto.getMobile());
		request.setAttribute("email", new_dto.getEmail());
		request.setAttribute("age", new_dto.getAge());

		
		return null;
	}

	/**
	 * 로그인
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	protected String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		System.out.println(memberId + ", " + memberPw);
		Member dto = service.login(memberId, memberPw);
		
		System.out.println(dto);
		if (dto != null) {
			String saveId = request.getParameter("saveId");
			
			if("ok".equals(saveId)) {
				// 쿠키에 아이디 정보 저장!
				Cookie cookie = new Cookie("remeber_id", memberId);
				cookie.setMaxAge(60*3);
				cookie.setPath(request.getContextPath());
				response.addCookie(cookie);
				
			} else {
				Cookie[] cookies = request.getCookies();
				if(cookies != null) {
					for(Cookie cookie : cookies) {
						if(cookie.getName().equals("remeber_id")) {
							cookie.setMaxAge(0);
							cookie.setPath(request.getContextPath());
							
							response.addCookie(cookie);
							break;
						}
					}
				}
			}
			
			// 로그인 세션정보 저장
			HttpSession session = request.getSession();
			session.setAttribute("user_session", dto);
			return "/index.jsp";
			
		} else {
			request.setAttribute("msg", "아이디, 비밀번호를 확인해주세요!!");	
			return "/member/login.jsp";
		}
	}
	
	
	/**
	 * 회원정보 수정
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	protected void mvEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession();
		Member dto = (Member) session.getAttribute("user_session");
		Member new_dto = service.mvEdit(dto);
		
		request.setAttribute("memberId", new_dto.getMemberId());
		request.setAttribute("memberPw", new_dto.getMemberPw());
		request.setAttribute("name", new_dto.getName());
		request.setAttribute("mobile", new_dto.getMobile());
		request.setAttribute("email", new_dto.getEmail());
		request.setAttribute("age", new_dto.getAge());

		System.out.println(new_dto);
	}
	
	
	/**
	 * 로그아웃
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		System.out.println("로그아웃");
		return "/index.jsp";
	}
	

	
	/**
	 * 회원탈퇴
	 * @param request
	 * @param response
	 * @throws SQLException 
	 */
	private void memberDel(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession(false);
		Member member = (Member) session.getAttribute("user_session");
		session.invalidate();
		
		service.memberDel(member.getMemberId());
	}
	
	
	
	/**
	 * 아이디 중복확인
	 * @param request
	 * @param response
	 * @return
	 */
	private int idCheck(HttpServletRequest request, HttpServletResponse response) {
		String memberId = request.getParameter("memberId");
		System.out.println(memberId);
		try {
			int count = service.idCheck(memberId);
			return count;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 500;
	}
	
	
	/**
	 * 비밀번호 찾기
	 * @param request
	 * @param response
	 * @return
	 */
	private String findPw(HttpServletRequest request, HttpServletResponse response) {
		String memberId = request.getParameter("memberId");
		System.out.println(memberId + " in Controller");
		String memberPw = service.findPw(memberId);
		System.out.println(memberPw + " is password");
		return memberPw;
	}
	
	
	
	
	/**
	 * 회원등록
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void memberRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int age = 0;
		
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		
		try {
			age = Integer.parseInt(request.getParameter("age"));
		} catch(Exception e) {
			age = 0;
		}
		Member dto = new Member(memberId, memberPw, name, mobile, email, age);
		
		int rows = service.memberRegist(dto);
		String message = null;
		
		if (rows == 1) {
			response.sendRedirect("index.jsp");
		}
	}
	
	
	/**
	 * 회원등록
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void memberEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int age = 0;
		
		HttpSession session = request.getSession(false);
		Member member = (Member) session.getAttribute("user_session");
		
		String memberId = member.getMemberId();
		String memberPw = request.getParameter("memberPw");
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		
		try {
			age = Integer.parseInt(request.getParameter("age"));
		} catch(Exception e) {
			age = 0;
		}
		
		Member dto = new Member(memberId, memberPw, name, mobile, email, age);
		
		System.out.println("=======");
		System.out.println(dto);
		System.out.println("=======");
		
		int rows = service.memberEdit(dto);
		String message = null;
		
		if (rows == 1) {
			response.sendRedirect("index.jsp");
		}
	}
	

	protected void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
	
	protected void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + path);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
