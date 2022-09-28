<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<c:if test="${cookie.remeber_id.value ne null}">
	<c:set var="id_ck" value="checked"></c:set>
	<c:set var="sv_id" value="${cookie.remeber_id.value}"></c:set>
</c:if>
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
                    <h1 class="h3 mb-3 fw-normal">로그인</h1>
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
                        value="${sv_id}"
                        >
                        <label for="floatingInput">아이디 </label>
                    </div>  
                </div>


                <!-- 비밀번호 -->
                <div class="d-flex justify-content-center">
                    <div class="form-floating" id="pw_input" style="width: 20%;">
                        <input 
                        type="password" 
                        class="form-control" 
                        id="memberPw" 
                        name="memberPw"
                        placeholder="비밀번호">
                        <label for="floatingPassword">비밀번호 </label>
                    </div>
                </div>
            	
       			<div class="d-flex justify-content-end" style="width: 60%">
		        	<input
		                class="form-check-input"
		                type="checkbox"
		                value="ok"
		                id="saveId"
		                name="saveId"
 		                ${id_ck}
			         />
		        	<label class="form-check-label" for="saveId">&nbsp&nbsp아이디저장</label>
            	</div>
            	
            	<div class="d-flex justify-content-center text-danger mb-2">${msg}</div>
       	        <br>

            	
       	       	<!-- 로그인 -->
	            <div class="d-flex justify-content-center">
	            	<div class="form-floating" id="login" style="width: 20%;">
	        	        <button type="button" id="btn-login" class="w-100 btn btn-lg btn-primary">
		                	로그인
		            	</button> 
	            	</div>
	            </div>
            </form>
          <%@ include file="/include/footer.jsp" %> 

    
    <!-- 반응형 -->
    <script>
	    document.querySelector("#btn-login").addEventListener("click", function () {
	       	console.log("!!!");
	    	if (!document.querySelector("#memberId").value) {
	          alert("아이디를 입력해주세요.");
	          return;
	        } else if (!document.querySelector("#memberPw").value) {
	          alert("비밀번호를 입력해주세요.");
	          return;
	        } else {
	          let form = document.querySelector("#form-login");
	          form.setAttribute("action", "${root}/controller?action=login");
	          form.submit();
	        }
	      });
	   
    </script>
    <script src="js/login.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>

