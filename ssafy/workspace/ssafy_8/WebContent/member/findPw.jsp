<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <title>비밀번호 찾기</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/sign-in/">



    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- Favicons -->



    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>


    <!-- Custom styles for this template -->
</head>
<body>
	<section>
		<form id="form-find" method="post" action="" >
			<input type="hidden" name="action" value="findPw">
			<div class="d-flex justify-content-center" style="margin-top: 30px">
            	<h1>비밀번호 찾기</h1>
            </div>
			
			<br>
			<hr>
			<br>
			
            <div class="d-flex justify-content-center">
            	<h6 class="h6 mb-3 fw-normal">비밀번호를 찾고자 하는 아이디를 입력해 주세요.</h6>
            </div>
            
            <!-- 아이디 -->
            <div class="d-flex justify-content-center">
                <div class="form-floating" id="id_input" style="width: 20%;">
                    <input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디">
                    <label for="floatingInput">* 아이디 </label>
                </div>
            </div>
            
            <br>
            <div 
            class=""
            id="findPw-result"
            >
            </div>
			
			<div class="d-flex justify-content-center">
				<!-- 찾기 -->
	            <div class="d-flex justify-content-center">
	    			<button type="button" class="btn btn-primary" id="btn-find" style="margin-right:30px">찾기</button>
	            </div>
	           	
	            <!-- 홈으로 -->
	            <div class="d-flex justify-content-center">
	    			<button type=button class="btn btn-primary" id="btn-home">홈으로</button>
	            </div>
			</div>

		</form>
	 <%@ include file="/include/footer.jsp" %> 
   	 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
   	 <script>
	    document.querySelector("#btn-find").addEventListener("click", function () {
	     	 let memberId = document.querySelector("#memberId").value;
	    	 let resultDiv = document.querySelector("#findPw-result");
			 console.log(memberId + "!!!");
    		fetch("${root}/controller?action=findPw&memberId=" + memberId)
			.then(response => response.text())
			.then(data => {
				console.log(data);
    			 if(data.trim() == 'fail') { // 아이디 찾기를 실패 했을 때,
    				console.log("실패");
      			   resultDiv.setAttribute("class", "d-flex justify-content-center mb-3 text-danger");
       		       resultDiv.textContent = "없는 아이디 입니다!! ";
      		       isUseId = false;
      			 } else {
      			   resultDiv.setAttribute("class", "d-flex justify-content-center mb-3 text-primary");
       		       resultDiv.textContent = "비밀번호는 " + data + " 입니다.";
       		       isUseId = true;
      			 }
			});
      	});
	    
	    document.querySelector("#btn-home").addEventListener("click", function () {
	          let form = document.querySelector("#form-find");
	          form.setAttribute("action", "${root}/controller?action=gohome");
	          form.submit();
	    });
   	 </script>
</body>