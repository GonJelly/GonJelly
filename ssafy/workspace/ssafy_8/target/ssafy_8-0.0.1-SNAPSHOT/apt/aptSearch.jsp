<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" scope="session"/>
<c:set var="test" value="나는 jsp 변수입니다."/>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>아파트 조</title>
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    <!-- Simple line icons-->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/simple-line-icons/2.5.5/css/simple-line-icons.min.css" rel="stylesheet" />
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="${root}/css/styles.css" rel="stylesheet" />
    <link href="${root}/css/apt.css" rel="stylesheet" />
</head>
<body id="page-top">
<script>
    flag = 0;
    // console.log(``)
    console.log("<c:out value="${root}" />");
</script>
<!-- nav -->
<%@ include file="/include/nav_session_nn.jsp"%>
<!-- Header-->
<header class="aptsearch d-flex align-items-center">
    <div class="container vh-100 gy-0 px-4 px-lg-5 text-center">
        <div class="row mb-3 gx-0 gy-0 justify-content-evenly text-center" id="header">
            <div class="col col-md-auto">
                <select name="year" id="year">
                    <option value="">년도[선택]</option>
                </select>
            </div>
            <div class="col col-md-auto">
                <select name="month" id="month">
                    <option value="">월[선택]</option>
                </select>
            </div>
            <div class="col col-md-auto">
                <select name="sido" id="sido">
                    <option value="">시,도[선택]</option>
                </select>
            </div>
            <div class="col col-md-auto">
                <select name="gugun" id="gugun">
                    <option value="">구,군[선택]</option>
                </select>
            </div>
            <div class="col col-md-auto">
                <select name="dong" id="dong">
                    <option value="">동[선택]</option>
                </select>
            </div>
            <div class="col col-md-3">
                <div class="row row-cols-md-2 justify-content-between">
                    <input type="button" class="btn btn-danger" id="searchApt" value="아파트 상세 조회">
                    <input type="button" class="btn btn-success" id="saveApt" value="아파트 정보 저장">
                </div>
            </div>
        </div>
        <div class="row row-cols-1 h-75 overflow-scroll">
            <table class="table table-striped" id="tblApt">
                <thead>
                <tr>
                    <th>번호</th>
                    <td>아파트명</td>
                    <td>층</td>
                    <td>전용면적</td>
                    <td>법정동</td>
                    <td>가격</td>
                </tr>
                </thead>

                <tbody>
                </tbody>
            </table>
        </div>
    </div>
</header>

<!-- Footer-->
<%@ include file="/include/footer.jsp" %>

<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="${root}/js/scripts.js"></script>
<script src="${root}/js/apt.js"></script>
<script>
    document.getElementById('searchApt').addEventListener('click',function() {

        ischeckbox();

        let year = document.getElementById('year').value;   // 년도
        let month = document.getElementById('month').value; // 월
        let DEAL_YMD = year + "" + month;

        let gugun = document.getElementById('gugun');
        let regcode = gugun[gugun.selectedIndex].value.substring(0,5);

        fetch('${root}/ControllerApt?act=search',{
            method : "POST",
            body : {
                DEAL_YMD: DEAL_YMD,
                regcode : regcode
            }
        })
            .then(response => response.json())
            .then(data => console.log(data));
    })

    function ischeckbox() {
        let year = document.getElementById('year');
        if( year[year.selectedIndex].value == '' ) {
            alert('년도를 선택해주세요.');
            return;
        }
        let month = document.getElementById('month');
        if( month[month.selectedIndex].value == '' ) {
            alert('월을 선택해주세요.');
            return;
        }
        let sido = document.getElementById('sido');
        if( sido[sido.selectedIndex].value == '') {
            alert('시도를 선택해주세요.');
            return;
        }
        let gugun = document.getElementById('gugun');
        if( gugun[gugun.selectedIndex].value == '') {
            alert('구,군을 선택해주세요.');
            return;
        }
    }
</script>
</body>
</html>
