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

    <style>
        .searchCard{

        }
    </style>
</head>
<body class="text-center">
    <jsp:include page="include/confirm.jsp" />
<main>
    <section class="py-5 text-center container" style="background-image: url('${root}/assets/img/intro_building.jpg')">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="fw-light">구해줘 홈즈!!</h1>
                <p class="lead text-dark">검색하고 싶은 지역을 선택해 주세요.!!</p>
                <p>
                    <input type="button" class="btn btn-primary my-2" onclick="searchApt()" value="아파트명 검색하기" />
                    <input type="button" class="btn btn-secondary my-2" onclick="searchArea()" value="지역주소로 검색하기" />
                </p>
            </div>
        </div>
        <div class="text-center" id="searchArea" style="display: none;">
            <div class="row justify-content-center py-1">
                <div class="col col-lg-6">
                    <div class="card mb-4 rounded-3 shadow-3">
                        <div class="card-header py-2">
                            <span class="fs-5">검색하기</span>
                        </div>
                        <div class="card-body">
                            <div class="row row-cols-md-3 justify-content-center py-md-1">
                                <div class="col-md-4 col-sm-12">
                                    <select class="form-select" id="sido" required>
                                        <option value="">시도 선택</option>
                                    </select>
                                </div>
                                <div class="col-md-4 col-sm-12">
                                    <select class="form-select" id="gugun" required>
                                        <option value="">구,군 선택</option>
                                    </select>
                                </div>
                                <div class="col-md-4 col-sm-12">
                                    <select class="form-select" id="dong" required>
                                        <option value="">동 선택</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row row-cols-md-3 justify-content-start py-md-1">
                                <div class="col-md-4 col-sm-12">
                                    <select class="form-select" id="year" required>
                                        <option value="">년도 선택</option>
                                    </select>
                                </div>
                                <div class="col-md-4 col-sm-12">
                                    <select class="form-select" id="month" required>
                                        <option value="">매매월선택</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row row-cols-4 justify-content-end py-md-1">
                                <div class="col-md-4 col-sm-12">
                                    <input type="button" class="btn btn-secondary" id="btn-search" value="검색하기" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="searchAPT" class="row justify-content-center py-lg-3">
            <div class="col-lg-6">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="아파트 검색">
                    <button type="submit" class="btn btn-secondary">search</button>
                </div>
            </div>
        </div>
    </section>

    <div class="album py-5 bg-light">
        <div class="container">
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                <div class="col">
                    <div class="card shadow-sm">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>

                        <div class="card-body">
                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                                </div>
                                <small class="text-muted">9 mins</small>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card shadow-sm">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>

                        <div class="card-body">
                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                                </div>
                                <small class="text-muted">9 mins</small>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card shadow-sm">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>

                        <div class="card-body">
                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                                </div>
                                <small class="text-muted">9 mins</small>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col">
                    <div class="card shadow-sm">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>

                        <div class="card-body">
                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                                </div>
                                <small class="text-muted">9 mins</small>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card shadow-sm">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>

                        <div class="card-body">
                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                                </div>
                                <small class="text-muted">9 mins</small>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card shadow-sm">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>

                        <div class="card-body">
                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                                </div>
                                <small class="text-muted">9 mins</small>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col">
                    <div class="card shadow-sm">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>

                        <div class="card-body">
                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                                </div>
                                <small class="text-muted">9 mins</small>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card shadow-sm">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>

                        <div class="card-body">
                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                                </div>
                                <small class="text-muted">9 mins</small>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card shadow-sm">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>

                        <div class="card-body">
                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                                </div>
                                <small class="text-muted">9 mins</small>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

    <jsp:include page="./include/footer.jsp" />
<script>

    var item = [];
    let date = new Date();

    // 페이지 로드시 년도 셋팅
    window.onload = function () {
        
        let yearEl = document.querySelector('#year');
        let yearOpt = '<option value="">년도 선택</option>';
        let year = date.getFullYear();

        for( let i = year; i > year - 8; i--) {
        	yearOpt += `<option value="\${i}">\${i}년</option>`;
        }

        yearEl.innerHTML = yearOpt;

        // 시도 정보 받아오기
        sendRequest('sido','*00000000');
    }

    function sendRequest(selid, regcode) {
        const url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
        let params = "regcode_pattern=" + regcode + "&is_ignore_zero=true";
        fetch(`\${url}?\${params}`)
            .then((response) => response.json())
            .then((data) => addOption(selid, data));
    }

    function addOption(selid, data) {
    	console.log(data);
        let opt = ``;
        initOption(selid);
        switch (selid) {
            case "sido":
                // opt += `<option value="">시도 선택</option>`;
                data.regcodes.forEach(function (regcode) {
                    opt += `<option value="\${regcode.code}">\${regcode.name}</option>`;
                });
                break;
            case "gugun":
                // opt += `<option value="">구군 선택</option>`;
                for (let i = 0; i < data.regcodes.length; i++) {
                    if (i != data.regcodes.length - 1) {
                        if (
                            data.regcodes[i].name.split(" ")[1] == data.regcodes[i + 1].name.split(" ")[1] &&
                            data.regcodes[i].name.split(" ").length !=
                                data.regcodes[i + 1].name.split(" ").length
                        ) {
                            data.regcodes.splice(i, 1);
                            i--;
                        }
                    }
                }
                let name = "";
                data.regcodes.forEach(function (regcode) {
                    if (regcode.name.split(" ").length == 2) name = regcode.name.split(" ")[1];
                    else name = regcode.name.split(" ")[1] + " " + regcode.name.split(" ")[2];
                    opt += `
            <option value="\${regcode.code}">\${name}</option>
            `;
                });
                break;
            case "dong":
                // opt += `<option value="">동 선택</option>`;
                let idx = 2;
                data.regcodes.forEach(function (regcode) {
                    if (regcode.name.split(" ").length != 3) idx = 3;
                    opt += `
            <option value="\${regcode.code}">\${regcode.name.split(" ")[idx]}</option>
            `;
                    item.push({code: regcode.code, name: regcode.name.split(" ")[idx], type: "dong"});
                });
        }
        document.querySelector(`#\${selid}`).innerHTML += opt;
    }

    function initOption(selid) {
        let opt = ``;
        let options = document.querySelector(`#\${selid}`);
        options.length = 0;
        // 시도 , 구군 , 동 선택 기본
        switch (selid) {
            case "sido":
                opt += `<option value="">시도 선택</option>`;
                break;
            case "gugun":
                opt += `<option value="">구군 선택</option>`;
                break;
            case "dong":
                opt += `<option value="">동 선택</option>`;
                break;
        }

        options.innerHTML = opt;
    }

    // 클릭 이벤트 처리 ( 년도 변경 )
    document.querySelector("#year").addEventListener("change", function () {
        let month = date.getMonth() + 1;
        let monthEl = document.querySelector("#month");
        let monthOpt = `<option value="">매매월선택</option>`;
        let yearSel = document.querySelector("#year");
        let m = yearSel[yearSel.selectedIndex].value == date.getFullYear() ? month : 13;
        for (let i = 1; i < m; i++) {
            monthOpt += `<option value="\${i < 10 ? "0" + i : i}">\${i}월</option>`;
        }
        monthEl.innerHTML = monthOpt;
    });

    // 시도가 바뀌면 구군정보 얻기.
    document.querySelector("#sido").addEventListener("change", function () {
        if (this[this.selectedIndex].value) {
            let regcode = this[this.selectedIndex].value.substr(0, 2) + "*00000";
            sendRequest("gugun", regcode);
        } else {
            initOption("gugun");
            initOption("dong");
        }
    });

    // 구군이 바뀌면 동정보 얻기.
    document.querySelector("#gugun").addEventListener("change", function () {
        if (this[this.selectedIndex].value) {
            let regcode = this[this.selectedIndex].value.substr(0, 5) + "*";
            sendRequest("dong", regcode);
        } else {
            initOption("dong");
        }
    });
    
    // 아파트 지역으로 검색하는 경우
    function searchArea() {
        let main = document.querySelector('#searchArea');
        let style = main.style.display;

        if( style == 'none' ) {
            main.setAttribute('style','');
            document.querySelector('#searchAPT').setAttribute('style','display:none;');
        }
    }
    // 아파트명으로 검색할 경우
    function searchApt() {
        let main = document.querySelector('#searchAPT');
        let style = main.style.display;

        if( style == 'none' ) {
            main.setAttribute('style','');
            document.querySelector('#searchArea').setAttribute('style','display:none;');
        }
    }

</script>
</body>
</html>