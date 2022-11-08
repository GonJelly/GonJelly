<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<header class="p-3 text-bg-dark">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"/></svg>
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="${root}/" class="nav-link px-2 text-secondary">아파트 정보 조회</a></li>
                <li><a href="#" class="nav-link px-2 text-white">관심지역</a></li>
                <li><a href="#" class="nav-link px-2 text-white">게시판</a></li>
                <li><a href="#" class="nav-link px-2 text-white">고객센터</a></li>
            </ul>

            <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search">
                <input type="search" class="form-control form-control-dark text-bg-dark" placeholder="Search..." aria-label="Search">
            </form>

            <c:choose>
                <c:when test="${empty userInfo}">
                    <div class="text-end">
                        <button type="button" class="btn btn-outline-light me-2" onclick="javascript:location.href = `${root}/user/loginpage`">로그인</button>
                        <button type="button" class="btn btn-warning" onclick="javascript:location.href = `${root}/user/registpage`">회원가입</button>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="dropdown text-end">
                        <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="${root}/assets/img/user.png" alt="mdo" width="32" height="32" class="rounded-circle">
                        </a>
                        <ul class="dropdown-menu text-small">
                            <li><a class="dropdown-item" href="#">마이페이지</a></li>
                            <li><a class="dropdown-item" href="#">관심지역</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="${root}/user/logout">로그아웃</a></li>
                        </ul>
                    </div>
                </c:otherwise>
            </c:choose>

        </div>
    </div>
</header>
