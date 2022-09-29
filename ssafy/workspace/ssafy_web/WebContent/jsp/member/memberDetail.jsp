<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

	<%@ include file="../../include/header.jsp" %>
			<form id="form-detail" method="POST" action="">
				<input type="hidden" name="action" value="login">
				
                <div class="d-flex justify-content-center" style="margin-top: 30px">
                    <h1>구해줘 홈즈</h1>
                </div>

                <br>
				<hr>
                <br>
                <br> 
	                <div class="d-flex justify-content-center">
	                    <h1 class="h3 mb-3 fw-normal">회원정보</h1>
	                </div>

                <!-- 아이디 -->
                <div class="d-flex justify-content-center">
                    <div class="form-floating" id="id_input" style="width: 20%;">
                        <input 
                        type="text" 
                        class="form-control" 
                        id="memberId" 
                        name="memberId" 
                        placeholder="아이디" 
                        value="${memberId}"
                        disabled
                        >
                        <label for="memberId">아이디 </label>
                    </div>
                </div>
                
                <div 
                class=""
                id="idcheck-result"
                >
                </div>

                <!-- 이름 -->
                <div class="d-flex justify-content-center">
                    <div class="form-floating" id="name_input" style="width: 20%;">
                        <input 
                        type="text" 
                        class="form-control" 
                        id="name" 
                        name="name" 
                        placeholder="이름"
                        value="${name}"
                        disabled
                        >
                        <label for="name">이름 </label>
                    </div>
                </div>
                
                <!-- 전화번호 -->
                <div class="d-flex justify-content-center">
                    <div class="form-floating" id="mobile_input" style="width: 20%;">
                        <input 
                        type="text" 
                        class="form-control" 
                        id="mobile" 
                        name="mobile" 
                        placeholder="전화번호"
                        value="${mobile}"
                        disabled
                        >
                        <label for="mobile">전화번호 </label>
                    </div>
                </div>


                <div class="d-flex justify-content-center">
                <!-- 이메일 -->
                    <div class="form-floating" id="email_input" style="width: 20%;">
                        <input 
                        type="email" 
                        class="form-control" 
                        id="email" 
                        name="email" 
                        placeholder="이메일"
                        value="${email}"
                        disabled
                        >
                        <label for="email">이메일 </label>
                    </div>
                </div>
                    
                
                <!-- 나이 -->
               	<div class="d-flex justify-content-center">
                 	<div class="form-floating" id="age_input" style="width: 20%;">
                     	<input 
                     	type="text" 
                     	class="form-control" 
                     	id="age" 
                     	name="age" 
                     	placeholder="나이"
                     	value="${age}"
                     	disabled
                     	>
                     	<label for="age">나이 </label>
                 	</div>
             	</div>
             	
        	    <br>
        	    <div class="d-flex justify-content-center">
		            <!-- 홈으로 -->
		            <div class="d-flex justify-content-center">
			    		<button type="button" class="btn btn-primary" id="btn-home">홈으로</button>
		            </div>
	            </div>
            </form>
	
	<%@ include file="../../include/footer.jsp" %>
	<script>
	document.querySelector("#btn-home").addEventListener("click", function () {
          let form = document.querySelector("#form-detail");
          form.setAttribute("action", "${root}/controller?action=gohome");
          form.submit();
      });
	</script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>