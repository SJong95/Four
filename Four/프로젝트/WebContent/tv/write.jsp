<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여기에 제목을 입력하십시오</title>
<script src="js/four.js"></script>
<style>
input[type="radio"]{
display: none;
}
.categorys input:checked + label{
background-color: #FFFFB0;
}
</style>
<link rel="stylesheet" href="css/fooo.css" type="text/css" media="print, projection, screen" />
</head>
<body>
<div style="margin:20px auto; width: 500px; height:500px; background-color: #eeeeee; padding:10px; position: relative; border-radius: 5px;">
<!-- 제목 부분 시작 -->
<div class="ui-tabs-nav ui-helper-reset  ui-widget-header ui-corner-all" style="width: 94.5%; height:27px;padding: 2px 12px 2px 12px;margin-right: 12px;">
<span style="float: left; margin: .1em 16px .1em 0; ">새로운 동영상 등록</span>
</div>
<!-- 제목 부분 끝 -->
<!-- form 시작 -->
<div style="margin: 2px 12px 2px 12px; font-size: 10pt; ">
<form name="writeForm" action="./fourWriteAction.tv" method="post">
<label style="font-size: 11pt;">카테고리</label>&nbsp;&nbsp;<span id="cate" style="color:red;">카테고리를 선택해 주세요.</span>
<div class="categorys"style="height:30px; padding: auto; position: relative; padding-top:10px;" class="btn-group">
<input type="radio" name="category" id="category" value="==" checked style="margin:10px" onchange="isCategory('category');">
<label for="category" class="ui-state-default ui-corner-all" style="margin: auto;padding: 9px 11px 9px 11px; font-size:11pt;">==</label>
<input type="radio" name="category" id="category1" value="게임" onchange="isCategory('category1');">
<label for="category1" class="ui-state-default ui-corner-all" style="padding: 10px 8px 10px 8px;font-size:10pt">게  임</label>
<input type="radio" name="category" id="category2" value="음악" onchange="isCategory('category2');">
<label for="category2" class="ui-state-default ui-corner-all" style="padding: 10px 8px 10px 8px;font-size:10pt">음  악</label>
<input type="radio" name="category" id="category3" value="스포츠" onchange="isCategory('category3');">
<label for="category3" class="ui-state-default ui-corner-all" style="padding: 10px 8px 10px 8px;font-size:10pt">스포츠</label>
<input type="radio" name="category" id="category4" value="유머" onchange="isCategory('category4');">
<label for="category4" class="ui-state-default ui-corner-all" style="padding: 10px 8px 10px 8px;font-size:10pt">유 머</label>
<input type="radio" name="category" id="category5" value="영화" onchange="isCategory('category5');">
<label for="category5" class="ui-state-default ui-corner-all" style="padding: 10px 8px 10px 8px;font-size:10pt">영  화</label>
<input type="radio" name="category" id="category6" value="동물" onchange="isCategory('category6');">
<label for="category6" class="ui-state-default ui-corner-all" style="padding: 10px 8px 10px 8px;font-size:10pt">동  물</label>
<input type="radio" name="category" id="category7" value="기타" onchange="isCategory('category7');">
<label for="category7" class="ui-state-default ui-corner-all" style="padding: 10px 8px 10px 8px;font-size:10pt">기  타</label>

</div>
<label for="link" style="font-size: 11pt;">링크</label>
<input type="text" name="link" id="link" class="text ui-widget-content ui-corner-all" required="required">
<label for="subject" style="font-size: 11pt;">제목</label>
<input type="text" name="subject" id="subject" class="text ui-widget-content ui-corner-all" required="required">
<label for="name" style="display:inline; font-size: 11pt;">이름</label>
<input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all" style="width:40%; display: inline;" required="required"><br>
<label for="pass" style="display:inline; font-size: 11pt;">비번</label>
<input type="password" name="pass" id="pass" class="text ui-widget-content ui-corner-all" style="width:40%; display: inline;" required="required"><br>
<label for="content" style="font-size: 11pt;">내용</label><br>
<textarea rows="10" cols="65" name="content" class="text ui-widget-content ui-corner-all" required="required"></textarea>
<br>
<br>
<div style="border-top: 1px solid #dddddd;"></div>
<div style="float: right;">
<br>
<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false" onclick="categoryCheck();" style="">
<span class="ui-button-text">등록</span>
</button>
<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false" onclick="javascript:history.back();" style="">
<span class="ui-button-text">취소</span>
</button>
</div>
<div style="margin-bottom: 5px"></div>

</form>
</div>
<!-- form 끝 -->
</div>



<!-- 포우꺼
<div class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-resizable" tabindex="-1" role="dialog" aria-labelledby="ui-dialog-title-dialog-form" style="display: block; z-index: 1002; outline: 0px; position: absolute; height: auto; width: 600px; top: 319px; left: 167px;">
<div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
<span class="ui-dialog-title" id="ui-dialog-title-dialog-form">새로운 링크 등록</span>
<a href="#" class="ui-dialog-titlebar-close ui-corner-all" role="button">
<span class="ui-icon ui-icon-closethick">close</span>
</a>
</div>
<div id="dialog-form" class="ui-dialog-content ui-widget-content" scrolltop="0" scrollleft="0" style="width: auto; min-height: 0px; height: 423.28px;">

	<span class="validateTips"></span>
	<form action="./linklist.php">
	<fieldset>
		<label for="subtype">분류 <span style="font-size:9pt; color:#F33;">( 필수 입력 - 자동분류를 위해 반드시 분류를 선택해 주세요. )</span></label>
		<div id="subtype_radio" class="ui-buttonset">
						<input type="radio" id="subtype_radio1" value="1" name="subtype_radio" checked="checked" class="ui-helper-hidden-accessible"><label for="subtype_radio1" class="ui-state-active ui-button ui-widget ui-state-default ui-button-text-only ui-corner-left" aria-pressed="true" role="button" aria-disabled="false"><span class="ui-button-text">--</span></label>			<input type="radio" id="subtype_radio2" value="2" name="subtype_radio" class="ui-helper-hidden-accessible"><label for="subtype_radio2" aria-pressed="false" class="ui-button ui-widget ui-state-default ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">LoL</span></label>			<input type="radio" id="subtype_radio5" value="5" name="subtype_radio" class="ui-helper-hidden-accessible"><label for="subtype_radio5" aria-pressed="false" class="ui-button ui-widget ui-state-default ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">게임</span></label>			<input type="radio" id="subtype_radio10" value="10" name="subtype_radio" class="ui-helper-hidden-accessible"><label for="subtype_radio10" aria-pressed="false" class="ui-button ui-widget ui-state-default ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">배그</span></label>			<input type="radio" id="subtype_radio9" value="9" name="subtype_radio" class="ui-helper-hidden-accessible"><label for="subtype_radio9" aria-pressed="false" class="ui-button ui-widget ui-state-default ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">오버워치</span></label>			<input type="radio" id="subtype_radio3" value="3" name="subtype_radio" class="ui-helper-hidden-accessible"><label for="subtype_radio3" aria-pressed="false" class="ui-button ui-widget ui-state-default ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">유머</span></label>			<input type="radio" id="subtype_radio4" value="4" name="subtype_radio" class="ui-helper-hidden-accessible"><label for="subtype_radio4" aria-pressed="false" class="ui-button ui-widget ui-state-default ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">음악</span></label>			<input type="radio" id="subtype_radio6" value="6" name="subtype_radio" class="ui-helper-hidden-accessible"><label for="subtype_radio6" aria-pressed="false" class="ui-button ui-widget ui-state-default ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">감동</span></label>			<input type="radio" id="subtype_radio12" value="12" name="subtype_radio" class="ui-helper-hidden-accessible"><label for="subtype_radio12" aria-pressed="false" class="ui-button ui-widget ui-state-default ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">동물</span></label>			<input type="radio" id="subtype_radio11" value="11" name="subtype_radio" class="ui-helper-hidden-accessible"><label for="subtype_radio11" aria-pressed="false" class="ui-button ui-widget ui-state-default ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">스포츠</span></label>			<input type="radio" id="subtype_radio7" value="7" name="subtype_radio" class="ui-helper-hidden-accessible"><label for="subtype_radio7" aria-pressed="false" class="ui-button ui-widget ui-state-default ui-button-text-only ui-corner-right" role="button" aria-disabled="false"><span class="ui-button-text">기타</span></label>
		</div>
		<div style="clear:both; margin-bottom:4px;"></div>
		<label for="link">링크 <span style="font-size:9pt; color:#F33;">( 필수 입력 )</span></label>
		<input type="text" name="link" id="link" class="text ui-widget-content ui-corner-all" value="http://">
		<label for="description">제목 <span style="font-size:9pt; color:#3A3;">( 생략 가능 - 생략 시 페이지 제목으로 자동생성 )</span></label>
		<input type="text" name="desc" id="desc" class="text ui-widget-content ui-corner-all">
		<label for="name" style="display:inline;">이름</label>
		<input type="text" name="name" value="" id="name" class="text ui-widget-content ui-corner-all" style="width:40%; display:inline;"><span style="font-size:9pt; color:#3A3;"> ( 생략 가능 - 생략 시 익명으로 글쓰기 )</span><br>
		<label for="pass" style="display:inline;">암호</label>
		<input type="text" name="pass" id="pass" class="text ui-widget-content ui-corner-all" style="width:40%; display:inline;"><span style="font-size:9pt; color:#3A3;"> ( 생략 가능 - 생략 시 IP로 자동인증 )</span>
		<img id="link_captcha" style="border: 1px solid #000; margin-right: 15px" src="./si/show.php?sid=0.2847104932421407" alt="CAPTCHA Image" align="left">
		<input id="captcha_link" type="text" name="captcha" class="text ui-widget-content ui-corner-all" style="width:106px; display:inline;"><span style="font-size:9pt; color:#F33;"> ( 필수 입력: 정답은? )</span> <span class="reload_captcha ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="font-size:9pt;" onclick="reload_captcha('link_captcha'); this.blur(); return false;" role="button"><span class="ui-button-text">문제 다시 받기</span></span>
		<div style="clear:both; margin-bottom:4px;"></div>
		<span>※ 애니메이션 등의 저작권물을 등록할 경우 사이버 수사대의 협조 요청에<br> &nbsp;따라 IP 정보 등이 참고자료로 제공될 수 있습니다.</span>
	</fieldset>
	</form>
</div>
<div class="ui-resizable-handle ui-resizable-n">
</div>
<div class="ui-resizable-handle ui-resizable-e">
</div>
<div class="ui-resizable-handle ui-resizable-s">
</div>
<div class="ui-resizable-handle ui-resizable-w">
</div>
<div class="ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se ui-icon-grip-diagonal-se" style="z-index: 1001;">
</div>
<div class="ui-resizable-handle ui-resizable-sw" style="z-index: 1002;">
</div>
<div class="ui-resizable-handle ui-resizable-ne" style="z-index: 1003;">
</div>
<div class="ui-resizable-handle ui-resizable-nw" style="z-index: 1004;">
</div>
<div class="ui-dialog-buttonpane ui-widget-content ui-helper-clearfix">
<div class="ui-dialog-buttonset">
<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false">
<span class="ui-button-text">등록</span>
</button>
<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false">
<span class="ui-button-text">취소</span></button>
</div>
</div>
</div>
 -->
</body>
</html>