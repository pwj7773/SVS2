
var mapContainer = document.getElementById('map'); // 지도를 표시할 div
var mapOption = {
	center: new kakao.maps.LatLng(37.551759, 126.991956), // 지도의 중심좌표
	level: 14 // 지도의 확대 레벨
};

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
var mapTypeControl = new kakao.maps.MapTypeControl(); // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT); // 지도에 컨트롤을 추가해야 지도위에 표시됩니다 (오른쪽 위)

var zoomControl = new kakao.maps.ZoomControl(); // 지도 확대 축소를 제어할 수 있는 줌 컨트롤을 생성합니다
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT); // 지도에 줌 컨트롤을 표시합니다 (오른쪽)

var geocoder = new kakao.maps.services.Geocoder(); // 주소-좌표 변환 객체를 생성합니다

var businessAddress = [
	/*[# th:each="b : ${business}"]*/
                /*[[${b.businessAddress}]]*/,
	/*[/]*/
];

var businessName = [
	/*[# th:each="b : ${business}"]*/
            /*[[${b.businessName}]]*/,
	/*[/]*/
];

var selectElement = document.getElementById('businessSelect');



var markers = []; // 마커를 저장하는 배열
var infowindows = []; // 인포윈도우를 저장하는 배열
for (var i = 0; i < businessAddress.length; i++) {
	(function(index) {
		var address = businessAddress[index];
		var name = businessName[index];

		// 주소로 좌표를 검색합니다
		geocoder.addressSearch(address, function(result, status) {
			// 정상적으로 검색이 완료됐으면
			if (status === kakao.maps.services.Status.OK) {
				var coords = new kakao.maps.LatLng(result[0].y, result[0].x); // 결과값으로 받은 위치를 마커로 표시합니다

				var marker = new kakao.maps.Marker({
					map: map,
					position: coords,
					clickable: true
				});

				// 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
				var iwContent = '<div style="width:150px;text-align:center;padding:6px 0;">' + name + '</div>';
				iwRemoveable = false;

				// 인포윈도우를 생성합니다
				var infowindow = new kakao.maps.InfoWindow({
					content: iwContent,
					removable: iwRemoveable
				});

				function closeInfoWindow() {
					for (var i = 0; i < infowindows.length; i++) {
						infowindows[i].close();

					}
				}

				// 마커에 클릭 이벤트를 등록합니다
				kakao.maps.event.addListener(marker, 'click', function() {
					closeInfoWindow(); // 이미 열려있는 인포윈도우가 있을 경우 닫습니다

					// 마커 위에 인포윈도우를 표시합니다
					infowindow.open(map, marker);

					// 생성된 인포윈도우를 배열에 추가합니다
					infowindows.push(infowindow);

					// 업체 선택과 업체 주소에 값을 채웁니다
					document.getElementById('businessNameInput').value = businessName[index];
					document.getElementById('businessAddressInput').value = businessAddress[index];

					selectElement.value = index.toString();
					// // 셀렉트 박스 선택 변경
					// var selectElement = document.getElementById('businessSelect');


				});

				selectElement.addEventListener('change', function() {
					var selectedIndex = selectElement.value;
					if (selectedIndex !== '') {
						var index = parseInt(selectedIndex);
						var selectedMarker = markers[index]; // 선택된 마커 가져오기
						var selectedInfowindow = infowindows[index]; // 선택된 인포윈도우 가져오기

						closeInfoWindow(); // 이미 열려있는 인포윈도우가 있을 경우 닫습니다

						// 마커 위에 인포윈도우를 표시합니다
						selectedInfowindow.open(map, selectedMarker);
						markers.push(marker); // 마커를 배열에 추가
						// 생성된 인포윈도우를 배열에 추가합니다
						infowindows.push(selectedInfowindow);

						// 업체 선택과 업체 주소에 값을 채웁니다
						document.getElementById('businessNameInput').value = businessName[index];
						document.getElementById('businessAddressInput').value = businessAddress[index];


					}
				});

			}
		});
	})(i);
}


$('#demo').daterangepicker({
	autoUpdateInput: false,
	"locale": {
		"format": "YYYY-MM-DD",
		"separator": " ~ ",
		"applyLabel": "확인",
		"cancelLabel": "취소",
		"fromLabel": "From",
		"toLabel": "To",
		"customRangeLabel": "Custom",
		"weekLabel": "W",
		"daysOfWeek": ["일", "월", "화", "수", "목", "금", "토"],
		"monthNames": ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
	},
	"startDate": new Date(),
	"endDate": new Date(),
	"drops": "auto"
}, function(start, end, label) {
	console.log('New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')');
});
$('input[name="demo"]').on('apply.daterangepicker', function(ev, picker) {
	$(this).val(picker.startDate.format('YYYY-MM-DD') + ' ~ ' + picker.endDate.format('YYYY-MM-DD'));
});

$('input[name="demo2"]').on('cancel.daterangepicker', function(ev, picker) {
	$(this).val('');
});