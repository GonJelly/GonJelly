///////////////////////// select box 설정 (지역, 매매기간) /////////////////////////
let date = new Date();

window.onload = function () {
    let yearEl = document.querySelector("#year");
    let yearOpt = `<option value="">매매년도선택</option>`;
    let year = date.getFullYear();
    for (let i = year; i > year - 20; i--) {
        yearOpt += `<option value="${i}">${i}년</option>`;
    }
    yearEl.innerHTML = yearOpt;

    // 브라우저가 열리면 시도정보 얻기.
    sendRequest("sido", "*00000000");
};

document.querySelector("#year").addEventListener("change", function () {
    let month = date.getMonth() + 1;
    let monthEl = document.querySelector("#month");
    let monthOpt = `<option value="">매매월선택</option>`;
    let yearSel = document.querySelector("#year");
    let m = yearSel[yearSel.selectedIndex].value == date.getFullYear() ? month : 13;
    for (let i = 1; i < m; i++) {
        monthOpt += `<option value="${i < 10 ? "0" + i : i}">${i}월</option>`;
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

function sendRequest(selid, regcode) {
    const url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
    let params = "regcode_pattern=" + regcode + "&is_ignore_zero=true";
    fetch(`${url}?${params}`)
        .then((response) => response.json())
        .then((data) => addOption(selid, data));
}

function addOption(selid, data) {
    let opt = ``;
    initOption(selid);
    switch (selid) {
        case "sido":
            opt += `<option value="">시도선택</option>`;
            data.regcodes.forEach(function (regcode) {
                opt += `
              <option value="${regcode.code}">${regcode.name}</option>
              `;
            });
            break;
        case "gugun":
            opt += `<option value="">구군선택</option>`;
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
              <option value="${regcode.code}">${name}</option>
              `;
            });
            break;
        case "dong":
            opt += `<option value="">동선택</option>`;
            let idx = 2;
            data.regcodes.forEach(function (regcode) {
                if (regcode.name.split(" ").length != 3) idx = 3;
                opt += `
              <option value="${regcode.code}">${regcode.name.split(" ")[idx]}</option>
              `;
            });
    }
    document.querySelector(`#${selid}`).innerHTML = opt;
}

function initOption(selid) {
    let options = document.querySelector(`#${selid}`);
    options.length = 0;
    // let len = options.length;
    // for (let i = len - 1; i >= 0; i--) {
    //   options.remove(i);
    // }
}

function selectapt() {
    let url = 'http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev';
    // 선택된 년,월 시, 구, 군 정보 가져오기
    let year = document.getElementById("year");
    let month = document.getElementById("month");
    let gugun = document.querySelector("#gugun");
    let dong = document.querySelector('#dong');
    let fullDate = year.value + "" + month.value;
    console.log(fullDate);
    let regcode = gugun[gugun.selectedIndex].value.substr(0,5);
    console.log(regcode);
    // 서비스키 삽입하기
    let queryParams = encodeURIComponent("serviceKey") + "=" + "T35MiOIjU6pXC8h6yLXF2JSVHmSu7AiRQV34MshtSdkFr%2FB%2BoBw96PL3Pqw1SC24N260ppUGLyJdkRzXhbuBvA%3D%3D";
    // 지역코드 삽입하기
    queryParams += "&" + encodeURIComponent("LAWD_CD") + "=" + encodeURIComponent(regcode);
    // 페이지 번호
    queryParams += "&" + encodeURIComponent("pageNo") + "=" + encodeURIComponent("1");
    // 페이지 아이템 갯수
    queryParams += "&" + encodeURIComponent("numOfRows") + "=" + encodeURIComponent("5");
    queryParams += "&" + encodeURIComponent("DEAL_YMD") + "=" + encodeURIComponent(fullDate);

    fetch(`${url}?${queryParams}`)
        .then( (response) => response.text())
        .then( (data) => makeList(data));
}

function makeList(data) {
    document.getElementById("apt").setAttribute("style","display:");
    let tbody = document.getElementById('tbody');
    let parser = new DOMParser();
    let xml = parser.parseFromString(data,'application/xml');
    let gubun = document.getElementById("dong");
    let dongNm = gubun[gubun.selectedIndex].textContent;
    console.log(dongNm);
    // 테이블 초기화
    initTable();
    initDetail();
    // 테이블 생성 ( 아파트 매매 조회 )
    let apts = xml.querySelectorAll('item');
    apts.forEach( (apt) => {
        console.log(apt.querySelector("법정동").textContent);
        if( dongNm.trim() == apt.querySelector("법정동").textContent.trim() ) {
            // console.log("in");
            let parentEl = document.createElement('tr');
            parentEl.setAttribute("onclick",'javascript:detail(this);')

            let aptNm = document.createElement('td');
            aptNm.setAttribute("value",`${apt.querySelector("아파트").textContent}`);
            aptNm.appendChild(document.createTextNode(apt.querySelector("아파트").textContent));
            parentEl.appendChild(aptNm);

            let floor = document.createElement('td');
            floor.setAttribute("value",`${apt.querySelector("층").textContent}`);
            floor.appendChild(document.createTextNode(apt.querySelector("층").textContent));
            parentEl.appendChild(floor);

            let area = document.createElement('td');
            area.setAttribute("value",`${apt.querySelector("전용면적").textContent}`);
            area.appendChild(document.createTextNode(apt.querySelector("전용면적").textContent));
            parentEl.appendChild(area);

            let dong = document.createElement('td');
            dong.setAttribute("value",`${apt.querySelector("법정동").textContent}`);
            dong.appendChild(document.createTextNode(apt.querySelector("법정동").textContent));
            parentEl.appendChild(dong);

            let price = document.createElement('td');
            price.setAttribute("value",`${apt.querySelector("거래금액").textContent}`);
            price.appendChild(document.createTextNode(apt.querySelector("거래금액").textContent + "만원"));
            parentEl.appendChild(price);
            // 지번
            let hidden = document.createElement('input');
            hidden.setAttribute('type','hidden');
            hidden.setAttribute('value',apt.querySelector("지번").textContent);
            parentEl.appendChild(hidden);

            tbody.appendChild(parentEl);
        }
    });

}

function initTable() {
    let target = document.getElementById('tbody');
        target.textContent = '';
}

function initDetail() {
    let target = document.getElementById('mapDiv');
    target.textContent = '';
}

function login() {
    window.open("login.html", "pop01", "width=700, height=500, status=no, toolbar=no");
}

function regist() {
    window.open("register.html", "pop02", "width=700, height=540");
}

function logout(){
    let get_id = localStorage.key(0);
    let get_info = localStorage.getItem(get_id);
    let parsed_info = JSON.parse(get_info);

    parsed_info.status = 0;  // status to 0

    localStorage.setItem("test", JSON.stringify(parsed_info));
    alert("로그아웃을 성공헀습니다!")
    location.href="index.html";
}

function quit(){
    alert("회원탈퇴를 성공헀습니다!")
    localStorage.clear();
    location.href="index.html"
}

function info(){
    window.open("info.html", "pop03", "width=500, height=500");
}

function reference(){
    let get_id = localStorage.key(0);
    let get_info = localStorage.getItem(get_id);
    let parsed_info = JSON.parse(get_info);

    if (parsed_info.status == 0){
        alert("로그인 후에 사용해주세요!")
    }
}