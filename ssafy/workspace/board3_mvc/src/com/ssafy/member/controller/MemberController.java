package com.ssafy.member.controller;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;
import com.ssafy.member.model.service.MemberServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/user")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static MemberService service;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request,response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 파라미터 act에 있는 값으로 페이지를 어디로 갈지 분기주기
		String action = request.getParameter("act");
		service = MemberServiceImpl.getInstance();
		String path = "";
		switch( action ) {
			case "idCheck":
				idCheck(request,response);
				break;
			case "mvjoin":
				path = "user/join.jsp";
				redirect(request,response,path);
				break;
			case "join":
				path = join(request,response);
				forward(request,response,path);
				break;
			case "mvlogin":
				path = "user/login.jsp";
				redirect(request,response,path);
				break;
			case "login":
				path = login(request,response);
				forward(request,response,path);
				break;
			case "logout":
				path = logout(request,response);
				forward(request,response,path);
				break;
		}
	}

	protected String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userid = request.getParameter("userid");
		String userpwd = request.getParameter("userpwd");
		try {
			MemberDto member = service.loginMember(userid, userpwd);
			// 로그인의 성공
			if (member != null) {
				HttpSession session = request.getSession();
				session.setAttribute("userinfo", member);

				// 쿠키 저장 ( 아이디 정보 ) - start
				String saveid = request.getParameter("saveid");
				System.out.println(saveid);
				if ("ok".equals(saveid)) {
					Cookie cookie = new Cookie("ssafy_ck", member.getUserId());
					cookie.setPath(request.getContextPath());
					cookie.setMaxAge(60*60*24*365);
					response.addCookie(cookie);
				} else {
					// 아이디 저장이 체크되어 있지않다면 쿠키 제거
					Cookie[] cookies = request.getCookies();
					for (Cookie ck : cookies) {
						if (ck.getName().equals("ssafy_ck")) {
							ck.setMaxAge(0);
							response.addCookie(ck);
							break;
						}
					}
				}
				// 쿠기 저장 - end
			} else {
				request.setAttribute("msg","로그인 아이디와 비밀번호의 정보가 잘못되었습니다.");
				return "user/login.jsp";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "error/error.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error.jsp";
		}

		return "index.jsp";
	}

	protected String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		// 세션 제거
		if( session != null ) {
			session.removeAttribute("User");
			session.invalidate();
		}
		return "index.jsp";
	}

	protected String join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userName = request.getParameter("username");
		String userid = request.getParameter("userid");
		String userpwd = request.getParameter("userpwd");
		String emailid = request.getParameter("emailid");
		String emaildomain = request.getParameter("emaildomain");

		MemberDto member = new MemberDto();

		member.setUserName(userName);
		member.setUserId(userid);
		member.setUserPwd(userpwd);
		member.setEmailId(emailid);
		member.setEmailDomain(emaildomain);

		try {
			int cnt = service.joinMember(member);
			if( cnt == 0 ) {
				request.setAttribute("msg","입력된 정보가 잘못되었습니다.");
				return "user/join.jsp";
			}
		}	catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error.jsp";
		} finally {
			return "user/login.jsp";
		}
	}

	protected void idCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userid = request.getParameter("userid");
//		System.out.println(userid);
		int cnt = 1;
		try {
			cnt = service.idCheck(userid);
		} catch(SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
			return;
		}
//		System.out.println(cnt);
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(cnt);
		out.flush();
		out.close();

	}

	protected void forward(HttpServletRequest request, HttpServletResponse response,String path) throws ServletException, IOException {
		// forward로 페이지이동
		RequestDispatcher dist = request.getRequestDispatcher(path);
		dist.forward(request,response);

	}

	protected  void redirect(HttpServletRequest request, HttpServletResponse response,String path) throws ServletException, IOException {
		// redirect방식 이동
		response.sendRedirect(path);
	}

}
