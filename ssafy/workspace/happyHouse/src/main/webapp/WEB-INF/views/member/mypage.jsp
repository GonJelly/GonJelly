<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>
    <!-- 부트스트랩 가져오기 -->

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <link href="../assets/css/common.css" rel="stylesheet">

    <style>
        @media (max-width: 768px) {
            .profile {
                display: none;
            }
        }
    </style>
</head>
<body>
 <jsp:include page="../include/confirm.jsp" />
    <main class="w-100 m-auto" style="max-width: 800px">
        <div class="row justify-content-center g-3 align-items-">
            <div class="col-md-5 col-8 order-md-first py-5">
                <div class="row justify-content-end profile">
                    <div class="col-md-12 py-2">
                        <div class="card p-2 float-end" style="max-width: 200px">
                            <img src="../assets/img/user.png"/>
                            <h4 class="align-items-center mb-3">
                                <div class="text-primary text-center">프로필 사진</div>
                            </h4>
                        </div>
                    </div>
                    <div class="col-md-8">
                        <ul class="list-group mb-3">
                            <li class="list-group-item d-flex justify-content-between lh-sm">
                                <div>
                                    <h6 class="my-0">관심지역</h6>
                                    <small class="text-muted">서울시 종로</small>
                                </div>
                                <span class="text-muted">등록일자</span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between lh-sm">
                                <div>
                                    <h6 class="my-0">관심지역</h6>
                                    <small class="text-muted">서울시 종로</small>
                                </div>
                                <span class="text-muted">등록일자</span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between lh-sm">
                                <div>
                                    <h6 class="my-0">관심지역</h6>
                                    <small class="text-muted">서울시 종로</small>
                                </div>
                                <span class="text-muted">등록일자</span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between bg-light">
                                <div>
                                    <h6 class="my-0">관심지역</h6>
                                    <small class="text-muted">서울시 종로</small>
                                </div>
                                <span class="text-muted">등록일자</span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between">
                                <span>총 등록</span>
                                <strong>0건</strong>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-7 col-8 py-3">
                <h4 class="mb-3 text-center">내 정보</h4>
                <form>
                    <div class="row g-3">
                        <div class="col-12">
                            <label for="userId" class="form-label">ID</label>
                            <div class="input-group has-validation">
                                <input type="text" class="form-control" id="userId" name="userId" placeholder="ID" value="${userInfo.userId}" disabled>
                                <div class="invalid-feedback">
                                    Your username is required.
                                </div>
                            </div>
                        </div>

                        <div class="col-12">
                            <label for="userPwd" class="form-label">Passowrd</label>
                            <div class="input-group has-validation">
                                <input type="password" class="form-control" id="userPwd" name="userPwd" value="${userInfo.userPwd}" disabled="true">
                                <input type="button" class="input-group-text" value="수정" onclick="BtnModify(this)" />
                                <div class="invalid-feedback">
                                    Your username is required.
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-6">
                            <label for="firstName" class="form-label">First name</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="firstName" name="firstName" value="${userInfo.userName}" disabled="true">
                                <input type="button" class="input-group-text" value="수정" onclick="BtnModify(this)" />
                                <div class="invalid-feedback">
                                    Valid first name is required.
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-6">
                            <label for="lastName" class="form-label">Last name</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="lastName" name="lastName" value="${userInfo.userName}" disabled="true">
                                <input type="button" class="input-group-text" value="수정" onclick="BtnModify(this)" />
                                <div class="invalid-feedback">
                                    Valid last name is required.
                                </div>
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
                            <div class="input-group">
                                <input type="email" class="form-control" id="email" name="email" value="" disabled="true">
                                <input type="button" class="input-group-text" value="수정" onclick="BtnModify(this)" />
                                <div class="invalid-feedback">
                                    Please enter a valid email address for shipping updates.
                                </div>
                            </div>
                        </div>

                        <div class="col-12">
                            <label for="address" class="form-label">Address</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="address" name="address" value="${userInfo.address}" disabled="true">
                                <input type="button" class="input-group-text" value="수정" onclick="BtnModify(this)" />
                                <div class="invalid-feedback">
                                    Please enter your shipping address.
                                </div>
                            </div>
                        </div>

                        <div class="col-12">
                            <label for="phone" class="form-label">Phone <span class="text-muted">(Optional)</span></label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="phone" name="phone" value="${userInfo.phone}" disabled="true">
                                <input type="button" class="input-group-text" value="수정" onclick="BtnModify(this)" />
                            </div>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </main>
    <jsp:include page="../include/footer.jsp" />

 <script>

     function BtnModify(obj) {
         // 이전 엘레멘트인 input 요소를 찾기
         let inputEl = obj.previousElementSibling;
         // Input 속성에 disabled 속성이 참이면 없애고
         console.log(inputEl.getAttribute('disabled'));
         if( inputEl.getAttribute('disabled') ) {
             inputEl.removeAttribute('disabled');
         } else {
             // input 속성에 disabled 속성이 거짓이면 참으로 바꾸기
             inputEl.setAttribute('disabled',true);
         }
     }

 </script>
</body>
</html>