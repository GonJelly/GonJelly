<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HappyHouse</title>
    <!-- 부트스트랩 가져오기 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
    <link href="${root}/assets/css/common.css" rel="stylesheet">
    <c:if test="${cookie.saveId.value ne null}">
        <script>
            <c:set var="ckid" value="checked" />
            <c:set var="saveId" value='${cookie.saveId.value}' />
        </script>
    </c:if>
</head>
<body class="text-center">
<main class="form-signin w-100 m-auto">
    <form id="loginfrm" method="POST" action="">
        <h1 class="h3 mb-3 fw-normal">로그인</h1>
        <div class="form-floating">
            <input type="text" class="form-control" id="userId" name="userId" placeholder="아이디" value="${saveId}">
            <label for="userId">ID</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" id="userPwd" name="userPwd" placeholder="Password">
            <label for="userPwd">Password</label>
        </div>
        <div class="form-floating text-start" id="failed" style="display: none;">
            <span class="fs-6" style="color:red">로그인 정보가 정확하지 않습니다.</span>
        </div>
        <div class="checkbox mb-3">
            <label>
                <input type="checkbox" value="remember-me" id="ckId" name="ckId" ${ckid} > Remember me
            </label>
        </div>
        <button type="button" class="w-100 btn btn-lg btn-primary" onclick="login()">Sign in</button>
        <hr class="my-4">
        <h2 class="fs-5 fw-bold mb-3">Or use a third-party</h2>
        <button class="w-100 py-2 mb-2 btn btn-outline-dark rounded-3" type="submit">
            <svg class="bi me-1" width="16" height="16"><use xlink:href="#twitter"/></svg>
            Sign up with Twitter
        </button>
        <button class="w-100 py-2 mb-2 btn btn-outline-primary rounded-3" type="submit">
            <svg class="bi me-1" width="16" height="16"><use xlink:href="#facebook"/></svg>
            Sign up with Facebook
        </button>
        <button class="w-100 py-2 mb-2 btn btn-outline-secondary rounded-3" type="submit">
            <svg class="bi me-1" width="16" height="16"><use xlink:href="#github"/></svg>
            Sign up with GitHub
        </button>
    </form>
</main>

    <jsp:include page="../include/footer.jsp" />
<script>

    let isUserid = false

    function login() {
        let userId = document.getElementById('userId').value;
        let userPwd = document.getElementById('userPwd').value;

        idCheck();

        if( userId == '' || userId == null ||
                userPwd == '' || userPwd == null || isUserid ) {
            document.querySelector('#failed').setAttribute('style',';');
            return;
        } else {
            let form = document.getElementById('loginfrm');
            form.setAttribute('action', "/user/login");
            form.submit();
        }
    }

    function idCheck() {
        let userId = document.getElementById('userId').value;
        fetch(`/user/\${userId}`)
            .then( response => response.json() )
            .then(data => {
                console.log(data);
                // 아이디가 존재하는 경우 true 없으면 false
                if( data != 0 ) {
                    isUserid = true;
                } else {
                    isUserid = false;
                }
            });
    }
</script>

</body>
</html>