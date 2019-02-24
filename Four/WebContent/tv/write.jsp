<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="img/logo.jpg">
<title>FOUR.TV</title>
<script src="js/four.js?v=3"></script>
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
<input type="text" name="link" id="link" class="text ui-widget-content ui-corner-all" required>
<label for="subject" style="font-size: 11pt;">제목</label>
<input type="text" name="subject" id="subject" class="text ui-widget-content ui-corner-all" required="required">
<label for="name" style="display:inline; font-size: 11pt;">이름</label>
<input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all" style="width:40%; display: inline;" required="required"><br>
<label for="pass" style="display:inline; font-size: 11pt;">비번</label>
<input type="password" name="pass" id="pass" class="text ui-widget-content ui-corner-all" style="width:40%; display: inline;" required="required"><br>
<label for="content" style="font-size: 11pt;">내용</label><br>
<textarea rows="10" cols="65" name="content" class="text ui-widget-content ui-corner-all" required="required"  style="resize: none;"></textarea>
<br>
<br>
<div style="border-top: 1px solid #dddddd;"></div>
<div style="float: right;">
<br>
<input type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false" onclick="categoryCheck();" value="등록">
<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false" onclick="javascript:history.back();" style="">
<span class="ui-button-text">취소</span>
</button>
</div>
<div style="margin-bottom: 5px"></div>
</form>
</div>
<!-- form 끝 -->
</div>
</body>
</html>