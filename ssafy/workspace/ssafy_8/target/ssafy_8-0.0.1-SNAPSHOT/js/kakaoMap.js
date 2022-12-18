/* 카카오 맵을 만들어주는 부분 */

function detail(obj) {

    let url = 'http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev';

    // 선택된 년,월 시, 구, 군 정보 가져오기
    let year = document.getElementById("year");
    let month = document.getElementById("month");
    let gugun = document.querySelector("#gugun");
    let dong = document.querySelector('#dong');
    let fullDate = year.value + "" + month.value;
    let regcode = gugun[gugun.selectedIndex].value.substr(0,5);
    // 서비스키 삽입하기
    let queryParams = encodeURIComponent("serviceKey") + "=" + "T35MiOIjU6pXC8h6yLXF2JSVHmSu7AiRQV34MshtSdkFr%2FB%2BoBw96PL3Pqw1SC24N260ppUGLyJdkRzXhbuBvA%3D%3D";
    // 지역코드 삽입하기
    queryParams += "&" + encodeURIComponent("LAWD_CD") + "=" + encodeURIComponent(regcode);
    // 페이지 번호
    queryParams += "&" + encodeURIComponent("pageNo") + "=" + encodeURIComponent("1");
    // 페이지 아이템 갯수
    queryParams += "&" + encodeURIComponent("numOfRows") + "=" + encodeURIComponent("5");
    queryParams += "&" + encodeURIComponent("DEAL_YMD") + "=" + encodeURIComponent(fullDate);
    // row 데이터 넘어오는지 확인
    // console.log(obj);
    let info = [];
    for( i = 0; i < obj.children.length; i++) {
        info[i] = obj.children.item(i).textContent.trim();
        console.log(info[i]);
    }
    fetch(`${url}?${queryParams}`)
        .then(  (response) => response.text() )
        .then( (data) => {
            let parser = new DOMParser();
            let xml = parser.parseFromString(data,'application/xml');
            let apts = xml.querySelectorAll('item');

            apts.forEach( (apt) => {
                // 지번 / 아파트명 / 층이 같은 아파트의 상세정보를 확인
                if( apt.querySelector("지번").textContent.trim() == obj.querySelector("input[type=hidden]").value.trim()
                    && apt.querySelector("아파트").textContent.trim() == info[0]
                    && apt.querySelector("층").textContent.trim() == info[1]) {

                    let sido = document.getElementById('sido');
                    let sidoText = sido[sido.selectedIndex].textContent;

                    let gugun = document.getElementById('gugun');
                    let gugunText = gugun[gugun.selectedIndex].textContent;
                    console.log(apt.querySelector("도로명").textContent);
                    let target = sidoText +" " +
                                    gugunText +" "+
                                        apt.querySelector("도로명").textContent.trim() + " " +
                                            apt.querySelector("지번").textContent.trim();

                    makeMap(target,apt);
                    return false;
                }
            });
            // makeMap(apts)
        });
}

function makeMap(address,apt) {

    // 테이블 가리기
    let table = document.getElementById('apt');
    table.setAttribute('style','display:none;');
    // 지도 뛰우기
    let mapDiv = document.getElementById('mapDiv');
    mapDiv.style.display = '';
    // 아파트 상세 설명
    let aptDetail = document.createElement("div");
    aptDetail.setAttribute('class','col-2 col-sm-3');
    aptDetail.setAttribute('id','aptMapDetail');

    console.log(aptDetail);
    let mapDetail = document.createElement('div');
    mapDetail.setAttribute('class','col-2 col-sm-3')
    mapDetail.setAttribute('id','aptMapDetail')
    // 아파트 지도
    let mapComponent = document.createElement('div');
    mapComponent.setAttribute('id','map');
    mapComponent.setAttribute('style','width: 100%; height: 100%;');

    let contents = "<div class='text-start'>" + `아파트 이름 : ${apt.querySelector("아파트").textContent}` + "<br>" +
        `주소 : ${address}` + "<br>" +
        `거래 금액 : ${apt.querySelector("거래금액").textContent}` + "<br>" +
        "</div>";

    // 좌표찍기
    let mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };
    // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
    var map = new kakao.maps.Map(mapComponent, mapOption);
    // 주소-좌표 변환 객체를 생성합니다
    var geocoder = new kakao.maps.services.Geocoder();

    // 주소로 좌표를 검색합니다
    geocoder.addressSearch(address, function(result, status) {

        // 정상적으로 검색이 완료됐으면
        if (status === kakao.maps.services.Status.OK) {

            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            // 결과값으로 받은 위치를 마커로 표시합니다
            var marker = {
                map: map,
                position: coords
            };

            // 인포윈도우로 장소에 대한 설명을 표시합니다
            var infowindow = new kakao.maps.InfoWindow({
                content: '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
            });
            infowindow.open(mapComponent, marker);

            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            map.setCenter(coords);
        }
    });

    console.log('ok');
    aptDetail.innerHTML = contents;
    // aptDetail.appendChild(document.createTextNode(contents));
    mapDetail.appendChild(mapComponent);
    mapDiv.appendChild(aptDetail);
    mapDiv.appendChild(mapDetail);
}