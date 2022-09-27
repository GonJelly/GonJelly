<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
      
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
      crossorigin="anonymous"
    />
    <link href="${root}/assets/css/app.css" rel="stylesheet" />
    <title>구해줘 홈즈!</title>
  </head>
  <body>
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="my-3 py-3 shadow-sm bg-light text-center">
            <mark>공지사항</mark>
          </h2>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
          <div class="row align-self-center mb-2">
            <div class="col-md-7 offset-3">
              <form class="d-flex" id="form-search" action="">
                <select
                  class="form-select form-select-sm ms-5 me-1 w-50"
                  name="key"
                  aria-label="검색조건"
                >
                  <option value="" selected>검색조건</option>
                  <option value="subject">제목</option>
                  <option value="userid">작성자</option>
                </select>
                <div class="input-group input-group-sm">
                  <input type="text" class="form-control" name="word" placeholder="검색어..." />
                  <button id="btn-search" class="btn btn-dark" type="button">검색</button>
                </div>
              </form>
            </div>
          </div>
          <table class="table table-hover">
            <thead>
              <tr class="text-center">
                <th scope="col">글번호</th>
                <th scope="col">제목</th>
                <th scope="col">작성자</th>
                <th scope="col">조회수</th>
                <th scope="col">작성일</th>
              </tr>
            </thead>
            <tbody>
           		<tr class="text-center">
	                <th scope="row">2</th>
	                <td class="text-start">
	                  <a
	                    href="#"
	                    class="article-title link-dark"
	                    data-no="2"
	                    style="text-decoration: none"
	                  >
	                    	이 홈페이지는 곧 폐쇠할 예정입니다.
	                  </a>
	                </td>
	                <td>test</td>
	                <td>1</td>
	                <td>2022-09-23</td>
              	</tr>
              	
         		<tr class="text-center">
	                <th scope="row">1</th>
	                <td class="text-start">
	                  <a
	                    href="#"
	                    class="article-title link-dark"
	                    data-no="1"
	                    style="text-decoration: none"
	                  >
	                    	안녕하세요. 공지사항입니다.
	                  </a>
	                </td>
	                <td>test</td>
	                <td>2</td>
	                <td>2022-09-22</td>
              	</tr>
            </tbody>
          </table>
        </div>
        <div class="row">
          <ul class="pagination justify-content-center">
            <li class="page-item">
              <a class="page-link" href="#">이전</a>
            </li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item active">
              <a class="page-link" href="#">2</a>
            </li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item"><a class="page-link" href="#">4</a></li>
            <li class="page-item"><a class="page-link" href="#">5</a></li>
            <li class="page-item"><a class="page-link" href="#">다음</a></li>
            <li class=""><a class="page-link" href="ssafy_8//index.jsp">홈으로</a></li>
          </ul>
        </div>
      </div>
    </div>
    <form id="form-no-param" method="get" action="${root}/board">
      <input type="hidden" id="act" name="act" value="view">
      <input type="hidden" id="pgno" name="pgno" value="${param.pgno}">
      <input type="hidden" id="key" name="key" value="${param.key}">
      <input type="hidden" id="word" name="word" value="${param.word}">
      <input type="hidden" id="articleno" name="articleno" value="">
    </form>
    <script>
      let titles = document.querySelectorAll(".article-title");
      titles.forEach(function (title) {
        title.addEventListener("click", function () {
       	 	document.querySelector("#articleno").value = this.getAttribute("data-no");
       	 	document.querySelector("#form-no-param").submit();
        });
      });

      document.querySelector("#btn-mv-register").addEventListener("click", function () {
        location.href = "${root}/board";
      });
      
      document.querySelector("#btn-search").addEventListener("click", function () {
    	  let form = document.querySelector("#form-search");
          form.setAttribute("action", "${root}/board");
          form.submit();
      });
    </script>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
      crossorigin="anonymous"
    ></script>
    <br>
    <hr>
    <!-- <button  type="submit" id="sing_in">Sign in</button> -->
    <p class="mt-5 mb-3 text-muted">&copy; 2022-09-07</p>
  </body>
</html>
