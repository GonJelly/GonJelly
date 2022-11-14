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
            <button type="button" class="w-100 btn btn-lg btn-primary" onclick="login()">로그인</button>
            <hr class="my-4">
            <button type="button" class="w-100 py-2 mb-2 btn btn-outline-secondary rounded-3" type="submit">
                <svg class="bi me-1" width="16" height="16"><use xlink:href="#github"/></svg>
                아이디 찾기
            </button>
            <button type="button" class="w-100 py-2 mb-2 btn btn-outline-primary rounded-3" data-bs-toggle="modal" data-bs-target="#findPwdModal">
                <svg class="bi me-1" width="16" height="16"><use xlink:href="#facebook"/></svg>
                비밀번호 찾기
            </button>
            <button type="button" class="w-100 py-2 mb-2 btn btn-outline-dark rounded-3" onclick="movePage('registpage')">
                <svg class="bi me-1" width="16" height="16"><use xlink:href="#twitter"/></svg>
                회원가입
            </button>
        </form>
    </main>

    <jsp:include page="../include/footer.jsp" />

    <div class="modal fade" id="findPwdModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="findPwdModalLabel">비밀번호찾기</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="form-floating">
                        <input type="text" class="form-control" id="findName" name="findName" placeholder="name" required />
                        <label for="userId">name</label>
                    </div>
                    <div class="form-floating">
                        <input type="text" class="form-control" id="findId" name="findId" placeholder="ID" required />
                        <label for="userId">ID</label>
                    </div>
                    <div class="form-floating text-start" style="display:none;">
                        <span class="fs-6" style="color: red">정보가 일치하지 않습니다.</span>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" id="modal-close" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" id="findPwdBtn">찾기</button>
                </div>
            </div>
        </div>
    </div>

<script>

    let isUserid = false

    window.onload = () => {

        document.getElementById('findPwdBtn').addEventListener('click', function() {
            let userId = document.getElementById('findId').value;
            let userName = document.getElementById('findName').value;


            fetch(`${root}/user/searchPwd`,{
                method:'post',
                headers: {
                    "Content-type":"application/json",
                },
                body: JSON.stringify({
                    userId,
                    userName,
                }),
            }).then((response) => {
                if( response.status == 200 ) {
                    return response.json();
                }
            }).then((data) => {
                let body = document.querySelector('.modal-body');
                body.innerHTML = '<div id="message" class="form-floating text-start">' +
                    `<span class="fw-3">\${userId}님의 비밀번호는 [<span class="text-primary">\${data.newPass}</span>] 입니다.</span>` +
                    '</div>';
                this.setAttribute('style','display:none;');
            }).catch( (response => console.log(response)));


        });

        document.getElementById('findPwdModal').addEventListener('show.bs.modal', event => {
            let showbody = document.querySelector('.modal-body');

            showbody.innerHTML =
                '<div class="form-floating">' +
                '<input type="text" class="form-control" id="findName" name="findName" placeholder="name" required />' +
                '<label for="userId">name</label>' +
                '</div>' +
                '<div class="form-floating">' +
                '<input type="text" class="form-control" id="findId" name="findId" placeholder="ID" required />' +
                '<label for="userId">ID</label>' +
                '</div>';
            document.getElementById('findPwdBtn').setAttribute('style','');
        });
    }

    function login() {
        let userId = document.getElementById('userId').value;
        let userPwd = document.getElementById('userPwd').value;

        idCheck();

        if( userId == '' || userId == null ||
                userPwd == '' || userPwd == null || isUserid ) {
            document.querySelector('#failed').setAttribute('style','');
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

    function movePage(page) {
        console.log(page);
        location.href = `${root}/user/\${page}`;
    }



</script>

</body>
</html>