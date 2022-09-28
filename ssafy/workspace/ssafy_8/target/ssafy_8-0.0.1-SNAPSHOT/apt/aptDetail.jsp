<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Stylish Portfolio - Start Bootstrap Template</title>
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
</script>
<!-- nav -->
<%@ include file="/include/nav_session_nn.jsp"%>
<!-- Header-->
<header class="aptsearch d-flex align-items-center">
    <div class="container px-4 px-lg-5 text-center">
        <div class="row featurette align-items-center">
            <div class="col-md-7 vh-100 overflow-auto">
                <table class="table table-success" id="aptList">

                </table>
            </div>
            <div class="col-md-5">
                <div id="map" style="width:500px;height:400px;"></div>
            </div>
        </div>
    </div>
</header>

<!-- Footer-->
<%@ include file="/include/footer.jsp" %>

<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="${root}/js/scripts.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7de9f8e544cd2366ea5e0037853b3216&libraries=services"></script>
<script src="${root}/js/kakaoMap.js"></script>
</body>
</html>