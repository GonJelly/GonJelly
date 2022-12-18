<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <!-- 부트스트랩 가져오기 -->
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <link href="../assets/css/common.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="../include/confirm.jsp" />
<main class="form-signin w-100 m-auto">
    <form class="needs-validation" method="POST" novalidate>
        <div class="row g-3">
            <div class="col-12">
                <label for="userId" class="form-label">ID</label>
                <div class="input-group has-validation">
                    <input type="text" class="form-control" id="userId" name="userId" placeholder="ID" required>
                </div>
                <div id="messageckId"></div>
            </div>

            <div class="col-12">
                <label for="userPwd" class="form-label">Passowrd</label>
                <div class="input-group has-validation">
                    <input type="password" class="form-control" id="userPwd" name="userPwd" placeholder="password" required>
                    <div class="invalid-feedback">
                        Your password is required.
                    </div>
                </div>
                <div id="messageckPw"></div>
            </div>

            <div class="col-12">
                <label for="confirmPwd" class="form-label">Passowrd confrim</label>
                <div class="input-group has-validation">
                    <input type="password" class="form-control" id="confirmPwd" placeholder="passoword confirm">
                </div>
            </div>

            <div class="col-sm-6">
                <label for="firstName" class="form-label">First name</label>
                <input type="text" class="form-control" id="firstName" name="firstName" placeholder="" value="" required>
                <div class="invalid-feedback">
                    Valid first name is required.
                </div>
            </div>

            <div class="col-sm-6">
                <label for="lastName" class="form-label">Last name</label>
                <input type="text" class="form-control" id="lastName" name="lastName" placeholder="" value="" required>
                <div class="invalid-feedback">
                    Valid last name is required.
                </div>
            </div>
            <!--
            <div class="col-12">
              <label for="username" class="form-label">Username</label>
              <div class="input-group has-validation">
                <span class="input-group-text">@</span>
                <input type="text" class="form-control" id="username" placeholder="Username" required>
              <div class="invalid-feedback">
                  Your username is required.
                </div>
              </div>
            </div>
            -->
            <div class="col-12">
                <label for="email" class="form-label">Email <span class="text-muted">(Optional)</span></label>
                <input type="email" class="form-control" id="email" name="email" placeholder="you@example.com">
            </div>

            <div class="col-12">
                <label for="address" class="form-label">Address</label>
                <input type="text" class="form-control" id="address" name="address" placeholder="1234 Main St" required>
                <div class="invalid-feedback">
                    Please enter your shipping address.
                </div>
            </div>

            <div class="col-12">
                <label for="phone" class="form-label">Phone <span class="text-muted">(Optional)</span></label>
                <input type="text" class="form-control" id="phone" name="phone" placeholder="-(하이픈) 없이 작성">
            </div>
        </div>
        <hr class="my-4">
        <button class="w-100 btn btn-primary btn-lg" onclick="regist()">회원가입</button>
    </form>
</main>

    <jsp:include page="../include/footer.jsp" />

    <script>

        let idcheck = false;
        let pwcheck = false;

        // 중복 아이디 체크
        document.querySelector('#userId').addEventListener('change', function() {
            let userId = this.value;
            if ( userId.length < 5 || userId.length > 16) {
                let msg = document.querySelector('#messageckId');
                msg.innerHTML = '<span class="fw-5 text-danger">5자리이상 16자리이하로 입력하셔야합니다..</span>';
                idcheck = false;
            } else {
                fetch(`/user/\${userId}`)
                    .then( response => response.json() )
                    .then(data => {
                        // 아이디가 존재하는 경우 true 없으면 false
                        if( data != 0 ) {
                            let msg = document.querySelector('#messageckId');
                            msg.innerHTML = '<span class="fw-5 text-danger">사용불가한 아이디 입니다.</span>';
                            idcheck = false;
                        } else {
                            let msg = document.querySelector('#messageckId');
                            msg.innerHTML = '<span class="fw-5 text-primary">사용가능한 아이디 입니다.</span>';
                            idcheck = true;
                        }
                    });
            }
        });

        function regist() {

            let form = document.querySelector('.needs-validation');
            // 아이디 유효성 체크
            if( !idcheck ) {
                alert('사용가능하지 않는 아이디 입니다.');
                document.querySelector('#userId').focus();
                return;
            }
            // 비밀번호 유효성 체크
            if( !pwcheck ) {
                alert('비밀번호가 일치한지 확인해주세요.');
                document.querySelector('#userPwd').focus();
                return;
            }

            //
            form.setAttribute('action',`${root}/user/regist`);
            form.submit();
        };

        document.querySelector('#confirmPwd').addEventListener('change', function() {
           let password = document.getElementById('userPwd').value;
           let msgEl = document.getElementById('messageckPw');
           if( this.value == password ) {
               let msg = '<span class="fw-5 text-primary"> 비밀번호가 일치합니다. </span>';
               msgEl.innerHTML = msg;
               pwcheck = true;
           } else {
               let msg = '<span class="fw-5 text-danger"> 비밀번호가 일치하지않습니다. </span>';
               msgEl.innerHTML = msg;
               pwcheck = false;
           }
        });

        document.querySelector('.needs-validation').addEventListener('submit', event => {
            let form = document.querySelector('.needs-validation');
            if( !form.checkValidity() ) {
                event.preventDefault();
                event.stopPropagation();
                return;
            }
            form.classList.add('was-validated');
        }, false);
    </script>
</body>
</html>
