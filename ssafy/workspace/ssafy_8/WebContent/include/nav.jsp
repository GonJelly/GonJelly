<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Navigation-->
<a class="menu-toggle rounded" href="#"><i class="fas fa-bars"></i></a>
<nav id="sidebar-wrapper">

	<c:if test="${user_session != null}">
        <ul class="sidebar-nav">
        <li class="sidebar-brand"><a href="#page-top">구해줘 홈즈!</a></li>
        <li class="sidebar-nav-item nav-fixed"><a href="${root}/controller?action=logout">로그아웃</a></li>
        <li class="sidebar-nav-item nav-fixed"><a href="${root}/member/findPw.jsp">비밀번호 찾기</a></li>
        <li class="sidebar-nav-item nav-fixed"><a href="${root}/controller?action=mvEdit">회원정보 수정</a></li>
    	<li class="sidebar-nav-item nav-fixed"><a href="${root}/controller?action=memberDetail">회원정보 상세</a></li>
	    <li class="sidebar-nav-item nav-fixed"><a href="${root}/ControllerApt?action=mvsearch">아파트 정보 조회</a></li>
    	<li class="sidebar-nav-item nav-fixed"><a href="${root}/board/list.jsp">공지사항</a></li>
    	<li class="sidebar-nav-item nav-fixed"><a href="${root}/controller?action=memberDel">회원탈퇴</a></li>
    </ul>
	</c:if>

    <c:if test="${user_session == null}">
	    <ul class="sidebar-nav">
	        <li class="sidebar-brand"><a href="#page-top">구해줘 홈즈!</a></li>
	        <li class="sidebar-nav-item"><a href="${root}/member/register.jsp">회원가입</a></li>
	        <li class="sidebar-nav-item nav-fixed"><a href="${root}/member/login.jsp">로그인</a></li>
	    </ul>
   	</c:if>
</nav>