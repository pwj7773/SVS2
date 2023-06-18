$(document).ready(function() {
	loadReply(1);

	$("#replyText2").hide();

});

function insertReply() {
	// 댓글 정보 가져오기(댓글 내용)
	let replyText = $("#replyText").val();
	let boardNum = $("#boardNum").val();
	// ajax로 댓글 정보 보내주면 되겠다
	$.ajax({
		url: "/insertReply",
		method: "post",
		data: { "replyText": replyText, "boardNum": boardNum },
		success: function() {
			$("#replyText").val(""); //입력창 초기화
			loadReply();
		}
	});

}// end of insertReply

//댓글 목록 가져오는 ajax 호출하는 함수
function loadReply(page) {
	// 글 번호 가져오기
	let boardNum = $("#boardNum").val();	// hidden 태그에 달린 글 번호
	$.ajax({
		url: "/boards/" + boardNum + "/replies?page=" + page,
		method: "GET",
		success: function(data) {
			// data : List<Reply>
			let replyDiv = "";
			// 반복 $.each(반복할 뭉탱이, 뭉탱이로 할일)
			$.each(data.content, function(index, item) {
				replyDiv += "<div class='container center'>";
				//hidden 태그에 들어있는 session 정보 가져오기
				let login = $("#login").val();
				replyDiv += '<div class="card">';
				replyDiv += '<div class="card-header d-flex justify-content-between align-items-center">';
				replyDiv += '<div class="d-flex align-items-center">';
				replyDiv += '<img src="https://dummyimage.com/50x50/ced4da/6c757d.jpg">';
				replyDiv += '<div class="mx-3">';
				replyDiv += "<h5>" + item.userId + "</h5>";
				replyDiv += '<font size="2">' + item.inputDate + "</font>";
				replyDiv += "</div>";
				replyDiv += "</div>";
				if (item.userId === login) {
					replyDiv += '<div class="btn-group btn-group-sm">';
					replyDiv += "<a class='btn btn-outline-primary' href = 'javascript:getOneReply(" + item.id + ");'>수정</a>";
					replyDiv += "<a class='btn btn-outline-primary' href = 'javascript:deleteReply(" + item.id + ");'>삭제</a>";
					replyDiv += "</div>";
				}
				replyDiv += "</div>";
				replyDiv += '<div class="card-body">' + item.replyText;
				replyDiv += "</div>";
				replyDiv += "</div>";
				replyDiv += "</div>";
				replyDiv += "<br>";

			});

			// reList라는 id를 가진 요소에 replyTalbe에 있는 HTML 문자열을 추가하겠음
			$("#reList").html(replyDiv);


			// Create HTML for pagination
			let startPage = Math.floor(data.number / 5) * 5;
			let endPage = (startPage + 4) < data.totalPages ? (startPage + 4) : (data.totalPages - 1);
			let pageDiv = `
    <div style="padding: 10px;" ${data.totalPages > 0 ? '' : 'hidden'}>
        <ul class="pagination justify-content-center">
            <li class="page-item"><a class="page-link" href="javascript:loadReply(${data.number})"><span aria-hidden="true">&laquo;</span></a></li>
`;

			for (let counter = startPage; counter <= endPage; counter++) {
				pageDiv += `<li class="page-item"><a class="page-link" href="javascript:loadReply(${counter + 1})">${counter + 1}</a></li>`;
			}

			pageDiv += `
            <li class="page-item" ${data.number < data.totalPages - 1 ? '' : 'hidden'}><a class="page-link" href="javascript:loadReply(${data.number + 2})"><span aria-hidden="true">&raquo;</span></a></li>
        </ul>
    </div>
`;

			// Set the HTML for pagination
			$("#paging").html(pageDiv);


		}
	});
}

function getOneReply(num) {
	// 해봅시다
	// 지금까지 한 ajax 함수 참고해서 컨트롤러에서
	// /getOneReply 라는 url 호출하면 댓글 정보 돌려주는 메서드 만들어보세요

	$.ajax({

		url: "/getOneReply",
		method: "post",
		data: { "replyNum": num },
		success: function(reply) {

			// 얘는 입력창				얘는 객체에 들어있느 댓글내용
			// 댓글 달기 버튼을 숨기고
			$("#replyText").hide();
			// 댓글 수정 버튼은 보여줘야함
			$("#replyText2").show();
			$("#replyText2").focus();
			$("#replyText2").val(reply.replyText);

			// 숨겨진 selectedReply에 value를 글번호로 설정
			$("#selectedReply").val(reply.id);

		}
	});
}

function updateReply() {
	//새로운 내용 가져오기
	let newReply = $("#replyText2").val();
	let rNum = $("#selectedReply").val();
	$.ajax({
		url: "/updateReply",
		method: "post",
		data: { "replyText": newReply, "replyNum": rNum },
		success: function() {
			loadReply();
			$("#replyText2").hide();
			$("#replyText2").val("");
			$("#replyText").show();
		}

	});
}
// 댓글 삭제 링크를 클릭했을 때 파라미터에 입력된 댓글번호를 num이라는 이름으로 가져옴
function deleteReply(rNum) {

	if (confirm("삭제 하시겠습니까?")) {
		$.ajax({
			url: "/deleteReply",
			method: "post",
			data: { "replyNum": rNum },
			success: function() {

				loadReply();
			}
		});
	}
}

