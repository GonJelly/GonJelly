var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
var options = {
    //지도를 생성할 때 필요한 기본 옵션
    center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
    // draggable: false, // 지도를 생성할때 지도 이동 및 확대/축소를 막으려면 draggable: false 옵션을 추가하세요
    level: 5, //지도의 레벨(확대, 축소 정도)
};
var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
// 아파트 마커들 저장
var markers = [];

function makePinPoint(data) {
    // 생성된 마커제거하기
    setMarkers(null);

    if( data.length == 0 ) { return; }

    // 아파트정보가 1개면 지도 움직이지 못하도록 고정
    if( data.length == 1) {
        map.setDraggable(false);
        map.setZoomable(false);
    }

    else {
        map.setDraggable(true);
        map.setZoomable(true);
    }

    data.forEach(async (house, idx) => {
        let dong = house.dong;
        let jibun = house.jibun;
        let title = house.apartmentName;

        let geocoder = new kakao.maps.services.Geocoder();
        geocoder.addressSearch(`${dong} ${jibun}`, function (result, status) {
            if (status === kakao.maps.services.Status.OK) {
                let coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                let marker = new kakao.maps.Marker({
                    map: map,
                    position: coords,
                    title,
                });

                markers.push(marker);

                if (idx == 0) {
                    map.setCenter(coords);
                }
            }
        });
    });
}

function initTable() {
    let tbody = document.querySelector("#aptlist");
    let len = tbody.rows.length;
    for (let i = len - 1; i >= 0; i--) {
        tbody.deleteRow(i);
    }
}

function setMarkers(map) {
    for (let i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
    }
}