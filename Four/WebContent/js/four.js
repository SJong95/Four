/**
 * 
 */
/* main.jsp */
 function detail(board_num) { // 이미지 클릭시 상세정보 보기
	 document.getElementById("BOARD_NUM_DETAIL").value = board_num;
	 document.getElementById("boardDetail").submit();
	}
 function boardDel(board_num) { // 영상 삭제 시 비밀번호 입력 창 팝업 위함
	document.getElementById("PASS_DEL").value=prompt("비밀번호를 입력해주세요.");
	if(document.getElementById("PASS_DEL").value!=0){
	document.getElementById("BOARD_NUM_DELETE").value = board_num;
	document.getElementById("boardDelete").submit();
		}
	}
 function categoryList(board_category){ // 카테고리 클릭 시 처리 함수
		document.getElementById("category").value=board_category;
		if(document.getElementById("cateAlign").value!="null"){
			document.getElementById("cateAlign").value="null";
		}
		if(document.getElementById("cateSearch").value!="null"){
			document.getElementById("cateSearch").value="null";
		}
		document.getElementById("boardcategory").submit();
	}
 function likeGood(board_num) { // 좋아요 클릭시 처리 함수
	 document.getElementById("board_num_good").value = board_num;
	 document.getElementById("board_good").submit();
}
 function likeBad(board_num) {	// 싫어요 클릭시 처리 함수
	 document.getElementById("board_num_bad").value = board_num;
	 document.getElementById("board_bad").submit();
}
 function boardReport(board_num) {	// 신고 클릭시 처리 함수
	 document.getElementById("board_num_report").value = board_num;
	 document.getElementById("board_report").submit();
}
 function pageNext(page) {	// 페이지 다음 클릭시 처리 함수
	 document.getElementById("page_next").value=page+1;
	 document.getElementById("board_next").submit();
}
 function pageAgo(page) {	// 페이지 이전 클릭시 처리 함수
	 document.getElementById("page_ago").value=page-1;
	 document.getElementById("board_ago").submit();
}
 function likeAlign() { // 좋아요 순 클릭시 처리 함수
	 document.getElementById("page_like_align").value="like";
	 document.getElementById("board_like_align").submit();
}
 function replyAlign() { // 댓글 순 클릭시 처리 함수
	 document.getElementById("page_reply_align").value="reply";
	 document.getElementById("board_reply_align").submit();
}
 function regiAlign() { // 등록 순 클릭시 처리 함수
	 document.getElementById("page_regi_align").value="regi";
	 document.getElementById("board_regi_align").submit();
}
 
/* main.jsp */
/* write.jsp */
function categoryCheck() { // 영상 등록시 카테고리 선택 여부 확인 함수
	var arr = document.getElementsByName("category"), check="";
	 for(var i=0;i<arr.length;i++){ // radio 갯수 만큼 반복
		if(arr[i].checked){ // 카테고리 체크된 값을 저장
			check = arr[i].value;
		}
	}
	if(check=="=="){ // 카테고리 선택안 된 경우
		alert("카테고리를 선택해 주세요.")
	}
}
function isCategory(cate_id) { // 영상 등록 페이지에서 카테고리 선택 안했을 시 확인 함수
	if(document.getElementById(cate_id).value=="=="){ // 카테고리 선택 안된 경우
		document.getElementById("cate").innerHTML = "카테고리를 선택해 주세요."; // 해당 문자를 추가
		document.getElementById("cate").style.color="red"; // 해당 문자를 빨간색으로 강조
	}else{ // 카테고리 선택된 경우 선택 문구 삭제
		document.getElementById("cate").innerHTML = "";
	}
}
/* write.jsp */
/* view.jsp */
function recommentW(i) { // 댓글의 답글 작성란 보이기
	var html;
	var j = i+100; // 댓글의 번호와 구분하기 위함
	if(document.getElementById(i).style.display=="none"){// 답글 작성란이 비활성화 된경우
		document.getElementById(i).style.display="block"; // 답글 작성란 활성화
		html="<div style='margin-bottom:8px;'><div style='font-size: 9pt; padding-left: 25px;'><label for='comname'>이름: </label><input type='text' name='comname' style='width: 15%; height:14px; border-radius: 5px;' class='ui-corner-all ui-widget-content'>&nbsp;<label for='compass'>비번: </label><input type='password' name='compass' style='width: 15%; height:14px; border-radius: 5px;' class='ui-corner-all ui-widget-content'></div><div style='font-size: 9pt; padding-left: 25px;margin-top:5px; display: inline;'><label for='comcontent'>내용: </label><input type='text' name='comcontent' style='width: 80%; height:14px; border-radius: 5px;' class='ui-corner-all ui-widget-content'></div><div style='font-size: 8pt; float: right; margin-top: 1px;'><button type='submit' class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only' role='button' aria-disabled='false' style=' width:60px; height:20px; padding: 0 3px 0 3px;'><span>답글 등록</span></button></div></div>";
		html+="<input type='hidden' name='REBOARD_NUM' id='RECOMMANET_NUM'>";
		html+="<input type='hidden' name='BOARD_NUM' id='BOARD_NUM'>";
		document.getElementById(j).innerHTML = html; // 답글 작성 form안에 작성해줌
	}else{	// 답글 작성란이 활성화 된 경우
		document.getElementById(i).style.display="none";
			// 답글 작성란 비활성화
	}
}
function recommentWrite(reboard_num, board_num){ // 댓글 등록시 값을 저장 하기 위한 함수
	document.getElementById("BOARD_NUM").value=board_num;
	document.getElementById('RECOMMANET_NUM').value=reboard_num;
}
function commentdel(reboard_num) { // 댓글 삭제 시 비밀번호 입력 창 팝업을 위한 함수
	document.getElementById("REBOARD_PASS").value=prompt("비밀번호를 입력해주세요.");
	if(document.getElementById("REBOARD_PASS").value!=0){
	document.getElementById("REBOARD_NUM").value = reboard_num;
	document.getElementById("commentDelete").submit();
		}
}
/* view.jsp */