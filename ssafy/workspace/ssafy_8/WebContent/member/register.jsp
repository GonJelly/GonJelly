<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

			<%@ include file="/include/header.jsp" %>

			<form id="form-login" method="POST" action="">
				<input type="hidden" name="action" value="login">
				
                <div class="d-flex justify-content-center" style="margin-top: 30px">
                    <h1>구해줘 홈즈</h1>
                </div>

                <br>
				<hr>
                <br>
                <br> 
	                <div class="d-flex justify-content-center">
	                    <h1 class="h3 mb-3 fw-normal">회원가입</h1>
	                </div>

                <!-- 아이디 -->
                <div class="d-flex justify-content-center">
                    <div class="form-floating" id="id_input" style="width: 20%;">
                        <input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디">
                        <label for="floatingInput">* 아이디 </label>
                    </div>
                </div>
                
                <div 
                class=""
                id="idcheck-result"
                >
                </div>

                <!-- 비밀번호 -->
                <div class="d-flex justify-content-center">
                    <div class="form-floating" id="pw_input" style="width: 20%;">
                        <input type="password" class="form-control" id="memberPw" name="memberPw" placeholder="비밀번호">
                        <label for="floatingPassword">* 비밀번호 </label>
                    </div>
                </div>

                <!-- 이름 -->
                <div class="d-flex justify-content-center">
                    <div class="form-floating" id="email_input" style="width: 20%;">
                        <input type="text" class="form-control" id="name" name="name" placeholder="이름">
                        <label for="floatingEmail">* 이름 </label>
                    </div>
                </div>
                
                <!-- 전화번호 -->
                <div class="d-flex justify-content-center">
                    <div class="form-floating" id="email_input" style="width: 20%;">
                        <input type="text" class="form-control" id="mobile" name="mobile" placeholder="전화번호">
                        <label for="floatingEmail">* 전화번호 </label>
                    </div>
                </div>


                <div class="d-flex justify-content-center">
                <!-- 이메일 -->
                    <div class="form-floating" id="email_input" style="width: 20%;">
                        <input type="email" class="form-control" id="email" name="email" placeholder="이메일">
                        <label for="floatingEmail">* 이메일 </label>
                    </div>
                </div>
                    
                
                <!-- 나이 -->
               	<div class="d-flex justify-content-center">
                 	<div class="form-floating" id="age_input" style="width: 20%;">
                     	<input type="text" class="form-control" id="age" name="age" placeholder="나이">
                     	<label for="floatingInput">나이 </label>
                 	</div>
             	</div>
             	
        	    <br>
	            <!-- 회원가입 -->
	            <div class="d-flex justify-content-center">
		    		<button type="button" class="btn btn-primary" id="btn-join">회원가입</button>
	            </div>
	            
            </form>
          <%@ include file="/include/footer.jsp" %> 
    <script>
    
	    document.querySelector("#memberId").addEventListener("keyup", function () {
		     	 let memberId = this.value;
		    	 let resultDiv = document.querySelector("#idcheck-result");

		    	 if(memberId.length == 0) {
	      			 resultDiv.setAttribute("class", "");
		    		 resultDiv.textContent = "";
		    		 isUseId = true;
		    	 } else {
		    		fetch("${root}/controller?action=idcheck&memberId=" + memberId)
					.then(response => response.text())
					.then(data => {
		    			 if(data == 0) {
		    				 console.log(data);
		      			   resultDiv.setAttribute("class", "d-flex justify-content-center mb-3 text-primary");
		      		       resultDiv.textContent = memberId + "는 사용할 수 있습니다.";
		      		       isUseId = true;
		      			 } else {
		      			   resultDiv.setAttribute("class", "d-flex justify-content-center mb-3 text-danger");
		       		       resultDiv.textContent = memberId + "는 사용할 수 없습니다.";
	        		       isUseId = false;
		      			 }
					});
		    	 }
		      });
	    
	    document.querySelector("#btn-join").addEventListener("click", function () {
	        if (!document.querySelector("#memberId").value) {
	          alert("아이디를 입력해주세요!");
	          return;
	        } else if (!document.querySelector("#memberPw").value) {
	          alert("비밀번호를 입력해주세요!");
	          return;
	        } else if (!document.querySelector("#name").value) {
	          alert("이름을 입력해주세요!");
	          return;
	        } else if (!document.querySelector("#mobile").value) {
	          alert("전화번호를 입력해주세요!");
	          return;
	        } else if (!document.querySelector("#email").value) {
	          alert("이메일을 입력해주세요!");
	          return;
	        } else if (!isUseId) {
	          alert("중복아이디 입니다!");
	          return;
	        } else {
	          let form = document.querySelector("#form-login");
	          form.setAttribute("action", "${root}/controller?action=memberRegist");
	          form.submit();
	        }
	      });
	    
    </script>
    <script src="${root}/js/register.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>

</html>