let date = new Date();

window.onload = function() {
    // 년도 셋팅
    let yearEL = document.querySelector('#year');
    let yearOpt = "<option id='' value=''>년도선택</option>";
    for(let i = date.getFullYear(); i > date.getFullYear() - 10; i--) {
        yearOpt += `<option id="" value=${i}>${i}년</option>`;
    }
    yearEL.innerHTML = yearOpt;
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

    if( this[this.selectedIndex].value ) {
        let code = this[this.selectedIndex].value;
        let url = `/ControllerApt?action=getGungu`;
        fetch(`${url}&sido=${code}`)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                let gungu = document.querySelector('#gugun');
                gungu.innerHTML = '';
                let gugunOpt = `<option id='' value=''>구,군[선택]</option>`;
                for(var obj in data) {
                    let key = obj;
                    if( key != '') {
                        gugunOpt += `<option id='' value='${data[key]}'>${key}</option>`
                    } else {
                        gugunOpt += `<option id='' value='${data[key]}'>[군,구 없음]</option>`
                    }
                }
                gungu.innerHTML = gugunOpt;
            });
    } else {
        initOption('gugun');
        initOption('dong');
    }
})

document.getElementById('gugun').addEventListener('change', function() {
    if( this[this.selectedIndex].value ) {
        let code = this[this.selectedIndex].value;
        let url = `/ControllerApt?action=getDong`;
        fetch(`${url}&gungu=${code}`)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                console.log(typeof data);
                console.log(data["eubmyundong_code"]);

                let dong = document.querySelector('#dong');
                dong.innerHTML = '';
                let dongOpt = `<option id='' value=''>동[선택]</option>`;
                    dongOpt += `<c:forEach var="dongs" items="${data}">`
                            +  `     <option id="" value="${dongs.eubmyundong_code}">${data}</option>`
                            +  `</c:forEach>`;
                dong.innerHTML = dongOpt;
            });
    } else {
        initOption('dong');
    }
})

function initOption(selid) {
    let opt = document.querySelector(`#${selid}`);
    opt.innerHTML = "";
}