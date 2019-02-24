/**
 * 
 */
/* main.jsp */
 function detail(board_num) {
	 document.getElementById("BOARD_NUM_DETAIL").value = board_num;
	 document.getElementById("boardDetail").submit();
	}
 function boardDel(board_num) {
	document.getElementById("PASS_DEL").value=prompt("비밀번호를 입력해주세요.");
	if(document.getElementById("PASS_DEL").value!=0){
	//alert("프롬프트 성공");
	//alert(document.getElementById("PASS_DEL").value);
	document.getElementById("BOARD_NUM_DELETE").value = board_num;
	//alert("넘버값 성공");
	//alert(document.getElementById("BOARD_NUM").value);
	document.getElementById("boardDelete").submit();
		}
	}
 function categoryList(board_category){
		document.getElementById("category").value=board_category;
		document.getElementById("boardcategory").submit();
	}
/* main.jsp */
/* write.jsp */
function categoryCheck() {
	var arr = document.getElementsByName("category"), check="";
	 for(var i=0;i<arr.length;i++){
		if(arr[i].checked){
			check = arr[i].value;
		}
	}
	if(check!="=="){
		document.writeForm.submit();
	}else{
		alert("카테고리를 선택해 주세요.")
	} 
}
function isCategory(cate_id) {
	if(document.getElementById(cate_id).value=="=="){
		document.getElementById("cate").innerHTML = "카테고리를 선택해 주세요.";
		document.getElementById("cate").style.color="red";
	}else{
		document.getElementById("cate").innerHTML = "";
		//document.getElementById("cate").style.color="red";
	}
}
/* write.jsp */
/* view.jsp */
function recommentW(i) {
	var html;
	var j = i+100;
	if(document.getElementById(i).style.display=="none"){
		document.getElementById(i).style.display="block";
		html="<div style='margin-bottom:8px;'><div style='font-size: 9pt; padding-left: 25px;'><label for='comname'>이름: </label><input type='text' name='comname' style='width: 15%; height:14px; border-radius: 5px;' class='ui-corner-all ui-widget-content'>&nbsp;<label for='compass'>비번: </label><input type='password' name='compass' style='width: 15%; height:14px; border-radius: 5px;' class='ui-corner-all ui-widget-content'></div><div style='font-size: 9pt; padding-left: 25px;margin-top:5px; display: inline;'><label for='comcontent'>내용: </label><input type='text' name='comcontent' style='width: 80%; height:14px; border-radius: 5px;' class='ui-corner-all ui-widget-content'></div><div style='font-size: 8pt; float: right; margin-top: 1px;'><button type='submit' class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only' role='button' aria-disabled='false' style=' width:60px; height:20px; padding: 0 3px 0 3px;'><span>답글 등록</span></button></div></div>";
		html+="<input type='hidden' name='REBOARD_NUM' id='RECOMMANET_NUM'>";
		html+="<input type='hidden' name='BOARD_NUM' id='BOARD_NUM'>";
		document.getElementById(j).innerHTML = html;
	}else{
		document.getElementById(i).style.display="none";
	}
	
}
function recommentWrite(reboard_num, board_num){
	alert(reboard_num);
	document.getElementById("BOARD_NUM").value=board_num;
	document.getElementById('RECOMMANET_NUM').value=reboard_num;
}
function commentdel(reboard_num) {
	document.getElementById("REBOARD_PASS").value=prompt("비밀번호를 입력해주세요.");
	if(document.getElementById("REBOARD_PASS").value!=0){
	//alert("프롬프트 성공");
	//alert(document.getElementById("PASS_DEL").value);
	document.getElementById("REBOARD_NUM").value = reboard_num;
	//alert("넘버값 성공");
	//alert(document.getElementById("BOARD_NUM").value);
	document.getElementById("commentDelete").submit();
		}
}
/* view.jsp */