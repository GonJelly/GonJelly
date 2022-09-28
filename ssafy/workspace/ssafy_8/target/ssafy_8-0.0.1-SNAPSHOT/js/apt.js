let date = new Date();

window.onload = function() {
    // 년도 셋팅
    let yearEL = document.querySelector('#year');
    let yearOpt = "<option id='' value=''>년도선택</option>";
    for(let i = date.getFullYear(); i > date.getFullYear() - 10; i--) {
        yearOpt += `<option id="" value=${i}>${i}년</option>`;
    }
    yearEL.innerHTML = yearOpt;
    // 시도 옵션 셋칭
    sendRequest('sido','*00000000')
}

document.getElementById('year').addEventListener('change', function() {
    initOption('month');
    let changeYear = document.getElementById('year').value;
    let month = document.getElementById('month');
    let opt = "<option id='' value=''> 월[선택]</option>";
    if( date.getFullYear() == changeYear ) {
        for(let i = date.getMonth(); i > 0; i--) {
            opt += `<option id="" value="${i < 10 ? '0' + i : i}">${i < 10 ? '0' + i : i}월</option>`;
        }
    } else {
        for(let i = 12; i > 0; i--) {
            opt += `<option id="" value="${i < 10 ? '0' + i : i}">${i < 10 ? '0' + i : i}월</option>`;
        }
    }
    month.innerHTML = opt
})
document.getElementById('sido').addEventListener('change', function() {

    if( this[this.selectedIndex].value) {
        let regcode = this[this.selectedIndex].value.substring(0,2) + "*00000";
        sendRequest('gugun',regcode);
    } else {
        initOption('gugun');
        initOption('dong');
    }
})

document.getElementById('gugun').addEventListener('change', function() {
    if( this[this.selectedIndex].value) {
        let regcode = this[this.selectedIndex].value.substring(0,5) + "*";
        sendRequest('dong',regcode);
    } else {
        initOption('dong');
    }
})

function sendRequest(selid, regcode) {
    const url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
    let params = "regcode_pattern=" + regcode + "&is_ignore_zero=true";
    fetch(`${url}?${params}`)
        .then((response) => response.json())
        .then((data) => addOption(selid, data));
}



function addOption(selid,data) {
    // console.log(data);
    initOption(selid);
    switch ( selid ) {
        case "sido":
            let sidoOpt = "<option id='' value='' >시도[선택] </option>";
            data.regcodes.forEach( function(regcode) {
                // console.log(regcode.name);
                sidoOpt += `<option id="" value="${regcode.code}">${regcode.name}</option>`;
            })
            let sidoEL = document.getElementById(`${selid}`);
            sidoEL.innerHTML = sidoOpt;
            break;
        case "gugun":
            // console.log(data);
            let sidos = document.getElementById('sido');
            let len = sidos[sidos.selectedIndex].textContent.length;
            let gugunOpt = "<option id='' value=''>구,군[선택]</option>";
            data.regcodes.forEach( function(regcode) {
                gugunOpt += `<option id='' value="${regcode.code}">${regcode.name.substring(len,regcode.name.length)}</option>`;
            })
            let gugunEL = document.getElementById(`${selid}`);
            gugunEL.innerHTML = gugunOpt;
            break;
        case "dong":
            let dongEL = document.getElementById(`${selid}`);
            let dongOpt = "<option id='' value=''>동[선택]</option>";
            idx = 2;
            data.regcodes.forEach(function(regcode) {
                if( regcode.name.split(" ").length != 3) {
                    ids = 3;
                }
                dongOpt += `<option id='' value='${regcode.code}'>${regcode.name.split(" ")[idx]}</option>`
            })
            dongEL.innerHTML = dongOpt;
            break;
    }
}

function initOption(selid) {
    let opt = document.querySelector(`#${selid}`);
    opt.innerHTML = "";
}
/*
document.getElementById('searchApt').addEventListener('click',function() {

    let url = "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";

    let year = document.getElementById('year').value;   // 년도
    let month = document.getElementById('month').value; // 월
    let DEAL_YMD = year + "" + month;

    let gugun = document.getElementById('gugun');
    let regcode = gugun[gugun.selectedIndex].value.substring(0,5);
    console.log(regcode);
    console.log(DEAL_YMD);
    //pageNo=1&numOfRows=10&LAWD_CD=11110&DEAL_YMD=201512
    let serviceKey = "T35MiOIjU6pXC8h6yLXF2JSVHmSu7AiRQV34MshtSdkFr%2FB%2BoBw96PL3Pqw1SC24N260ppUGLyJdkRzXhbuBvA%3D%3D";
    let param = encodeURIComponent("serviceKey") + "=" + serviceKey + "&";

    let pageNo = 1;
    let numOfRows = 50;

    param += encodeURIComponent("pageNo") + "=" + encodeURIComponent(pageNo) + "&"
        + encodeURIComponent("numOfRows") + "=" + encodeURIComponent(numOfRows) + "&"
        + encodeURIComponent("LAWD_CD") + "=" + encodeURIComponent(regcode) + "&"
        + encodeURIComponent("DEAL_YMD") + "=" + encodeURIComponent(DEAL_YMD);
    console.log(param);

    fetch(`${url}?${param}`)
        .then(response => response.text())
        .then(data => searchApt(data));
})

function searchApt(data) {
    // XML파싱
    let parser = new DOMParser();
    let xml = parser.parseFromString(data,"application/xml");
    let apts = xml.querySelectorAll("item");
    // 선택한 법정동
    let dong = document.getElementById('dong');
    let area = dong[dong.selectedIndex].textContent.trim();
    console.log(area);
    console.log(dong);
    // console.log(apts);

    let tbody = document.getElementById('tblApt').children.item(1);
    let tr = ``;
    // console.log(tbody);
    tbody.innerHTML = "";
    let idx = 1;
    apts.forEach(function(apt) {
        let aptNm = apt.querySelector('아파트').textContent.trim();
        let floor = apt.querySelector('층').textContent.trim();
        let size = apt.querySelector('전용면적').textContent.trim();
        let dg = apt.querySelector('법정동').textContent.trim();
        let price = apt.querySelector('거래금액').textContent.trim();
        let gibun = apt.querySelector(`지번`).textContent.trim();
        // console.log(floor);
        // console.log(size);
        console.log(area == dg);
        // 전체 검색
        if( area == '동[선택]') {
            tr += `<tr class="mh-10" onclick="aptDetail(this)">`
                + `<th>${idx}</th>`
                + `<td>${aptNm}</td>`
                + `<td>${floor}</td>`
                + `<td>${size}</td>`
                + `<td>${dg}</td>`
                + `<td>${price}</td>`
                + `<input type="hidden" id="gibun" value="${gibun}" />`
                + '</tr>';
            idx += 1;
        }
        // 동 검색
        else if( area == dg ) {
            tr += '<tr class="mh-10" onclick="aptDetail(this)">'
                + `<th>${idx}</th>`
                + `<td>${aptNm}</td>`
                + `<td>${floor}</td>`
                + `<td>${size}</td>`
                + `<td>${dg}</td>`
                + `<td>${price}</td>`
                + `<input type="hidden" id="gibun" value="${gibun}" />`
                + '</tr>';
            idx += 1;
        }
        console.log(dg);
    });

    tbody.innerHTML = tr;
}
*/

/*
function aptDetail(obj) {
    console.log(obj);
    let year = document.getElementById('year').value;
    let month = document.getElementById('month').value;

    let date = year + "" + month;

    let sido = document.getElementById('sido');
    let gugun = document.getElementById('gugun');
    let regcode = gugun[gugun.selectedIndex].value.substring(0,5);
    let sidoNm = sido[sido.selectedIndex].textContent;
    // let dongNm = dong[dong.selectedIndex].textContent.trim();
    let dongNm = obj.getElementsByTagName('td').item(3).textContent;
    let floor = obj.getElementsByTagName('td').item(1).textContent;
    let gibun = obj.getElementsByTagName('input').item(0).value;

    let address = sidoNm + " " + dongNm + " " + gibun;
    console.log(regcode);

    console.log(address);

    window.open(`./aptmodal.jsp?mode=2&regcode=${regcode}&date=${date}&address=${address}&floor=${floor}`,'_blank','toolbar=no,scrollbars=no,resizable=no,top=300px,left=300px,width=800px,height=500px');
    // location.href = `./aptDetail.jsp?address=${address}`;
}
*/
/*
    document.getElementById('saveApt').addEventListener('click', function() {

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

        let url = "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";

        let DEAL_YMD = year.value + "" + month.value;
        let regcode = gugun[gugun.selectedIndex].value.substring(0,5);

        let serviceKey = "T35MiOIjU6pXC8h6yLXF2JSVHmSu7AiRQV34MshtSdkFr%2FB%2BoBw96PL3Pqw1SC24N260ppUGLyJdkRzXhbuBvA%3D%3D";
        let param = encodeURIComponent("serviceKey") + "=" + serviceKey + "&";

        let pageNo = 1;
        let numOfRows = 50;

        param += encodeURIComponent("pageNo") + "=" + encodeURIComponent(pageNo) + "&"
            + encodeURIComponent("numOfRows") + "=" + encodeURIComponent(numOfRows) + "&"
            + encodeURIComponent("LAWD_CD") + "=" + encodeURIComponent(regcode) + "&"
            + encodeURIComponent("DEAL_YMD") + "=" + encodeURIComponent(DEAL_YMD);
        // console.log(param);

        fetch(`\${url}?\${param}`)
            .then(response => response.text())
            .then(data => save(data));
    })

    function save(data){

        let parser = new DOMParser();
        let xml = parser.parseFromString(data,"application/xml");
        let apts = xml.querySelectorAll('item');
        console.log("${test}");
        apts.forEach(function(apt) {

            let year = apt.querySelector('년').textContent.trim();
            let month = apt.querySelector('월').textContent.trim();

            let Code = apt.querySelector('일련번호').textContent.trim();
            let LAWD_CD = apt.querySelector('지역코드').textContent.trim();
            let DEAL_YMD = year + "" + (month.length > 1 ? month : `0`+month);
            let Deal_Amount = apt.querySelector('거래금액').textContent.trim();
            let regcode = apt.querySelector('법정동시군구코드').textContent.trim();
            let Eubmyudong = apt.querySelector('법정동읍면동코드').textContent.trim();
            let dong = apt.querySelector('법정동').textContent.trim();
            let AptName = apt.querySelector('아파트').textContent.trim();
            let AreaExUse = apt.querySelector('전용면적').textContent.trim();
            let Jibun = apt.querySelector('지번').textContent.trim();
            let Floor = apt.querySelector('층').textContent.trim();
            let Build_Year = apt.querySelector('건축년도').textContent.trim();
            let Read_Name = apt.querySelector('도로명').textContent.trim();
            let Bonbun = apt.querySelector('도로명건물본번호코드').textContent.trim();
            let Bubun = apt.querySelector('도로명건물부번호코드').textContent.trim();

            fetch(`${root}/ControllerApt?act=info`,{
                headers : "content-type : application/json",
                method : "POST",
                body : JSON.stringify({
                    Code : Code,
                    LAWD_CD : LAWD_CD,
                    DEAL_YMD : DEAL_YMD,
                    Deal_Amount : Deal_Amount,
                    regcode : regcode,
                    Eubmyudong : Eubmyudong,
                    dong : dong,
                    AptName : AptName,
                    AreaExUse : AreaExUse,
                    Jibun : Jibun,
                    Floor : Floor,
                    Build_Year : Build_Year,
                    Read_Name : Read_Name,
                    Bonbun : Bonbun,
                    Bubun : Bubun
                })
            })
                .then(response => response.text())
                .then(data => console.log("1완료"));
        });


    }
*/
