<%@page import="vo.Board"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% ArrayList<Board> boardList = (ArrayList<Board>) request.getAttribute("boardList");
    ArrayList<Integer> countList = (ArrayList<Integer>) request.getAttribute("countList");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여기에 제목을 입력하십시오</title>
 <link rel="stylesheet" href="css/fooo.css" type="text/css" media="print, projection, screen" />
 <style>
 * {
margin:0;
padding: 0;
}
div{
font-size: 9pt;
}
 </style>
<script src="js/four.js"></script>
</head>
<body>
<section>
<div style="margin:auto; width: 750px; height:auto;">
<br>
<hr style="margin:2px 0px 5px 0px;">
<div style="display: inline;">
<img src="img/fourtv.jpg" width="300px" height="40px">
<form method="post" class="form-wrapper cf" id="searchVidio" style="margin: 20px auto;">
<input type="text" name="search" required placeholder="동영상 이름 입력">
<button type="submit">동영상 검색</button>
</form>
</div>
<hr style="margin:2px 0px 5px 0px;">
<br>
<table style="width: 100%; background-color: #eeeeee; padding:3px;">
<thead >
	<tr>
	<td class="ui-tabs-nav ui-helper-reset  ui-widget-header ui-corner-all" style="width: 100%; height:27px;">
	</td>
	</tr>
</thead>
<tbody style="">
<!-- 첫번째 시작 -->
<tr>
<td style="padding: 20px 16.8px 0px 16.8px;">
<div class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="font-size: 9pt; width:90px;">
<form>
<span class="ui-button-text" id="home_btn" onclick="javascript:location.href='four.tv'">처음으로</span>
</form>
</div>
<div class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="font-size: 9pt; width:100px;">
<form action="./fourWrite.tv" method="post" name="fourWrite">
<span class="ui-button-text" onclick="javascript:fourWrite.submit();">영상 등록</span>
</form>
</div>
<div style="float: right;">
<div style="font-size: 9pt; width:80px; margin-right: 1.32px;" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">
<span class="ui-button-text" id="home_btn" >◀ 이전</span>
</div>
<div style="font-size:9pt; width:30px;margin-right: 1.32px;"
class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-button-disabled ui-state-disabled">
<span class="ui-button-text">1</span>
</div>
<div style="font-size: 9pt; width:80px;" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">
<span class="ui-button-text" id="home_btn">다음 ▶</span>
</div>
</div>
</td>
</tr>
<!-- 첫번째 끝 -->
<!-- 구분선 -->
<!-- <tr>
<td style="padding: 0px 16.8px 0px 16.8px">
<hr style="margin:2px 0px 5px 0px;">
</td>
</tr> -->
<!-- 구분선 -->
<tr>
<td style="padding: 0 16.8px 0px 16.8px;">
<div style="border-top: 1px solid #87B1E2;">
<div style="margin-top: 5px"></div>
<form action="./four.tv" method="post" id="boardcategory">
<input type="hidden" id="category" name="category">
<div class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="font-size: 9pt; width:94px;">
<span class="ui-button-text" onclick="categoryList('게임')">게임</span>
</div>
<div class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="font-size: 9pt; width:94px;" >
<span class="ui-button-text" onclick="categoryList('음악')">음악</span>
</div>
<div class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="font-size: 9pt; width:94px;">
<span class="ui-button-text" onclick="categoryList('스포츠')">스포츠</span>
</div>
<div class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="font-size: 9pt; width:94px;">
<span class="ui-button-text" onclick="categoryList('유머')">유머</span>
</div>
<div class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="font-size: 9pt; width:94px;">
<span class="ui-button-text" onclick="categoryList('영화')">영화</span>
</div>
<div class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="font-size: 9pt; width:94px;">
<span class="ui-button-text" onclick="categoryList('동물')">동물</span>
</div>
<div class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="font-size: 9pt; width:94px;">
<span class="ui-button-text" onclick="categoryList('기타')">기타</span>
</div>
</form>
</div>
</td>
</tr>
<!-- 두번째 시작 -->
<tr>
<td style="padding: 0 16.8px 0px 16.8px;">
<div style="border-top: 1px solid #87B1E2;">
<div style="margin-top: 5px"></div>
<div class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="font-size: 9pt; width:100px;">
<span class="ui-button-text">좋아요 순</span>
</div>
<div class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="font-size: 9pt; width:100px;">
<span class="ui-button-text">등록 순</span>
</div>
<div class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="font-size: 9pt; width:100px;">
<span class="ui-button-text">댓글 순</span>
</div>
</div>
</td>
</tr>
<!-- 두번째 끝 -->
<!-- 세번째 시작 (for 사용 부분)-->

<%if(boardList != null){ %>
<%for(int i=0;i<boardList.size();i++){ %>
<tr>
<td style="padding: 0 16.8px 0px 16.8px;" id="ll_main">
<div style="position: relative; height:90px; width: 100%; border-top: 1px solid #87B1E2;" class="linkbox">
<div style="margin-top: 5px"></div>
<div style="position:absolute; left:0px; text-align: center;">
<form>
<img class="good_btn" src="img/good.gif">
</form>
<span><%=boardList.get(i).getBOARD_LIKE() %></span>
<form>
<img class="bad_btn" src="img/bad.gif">
</form>
</div>
<form action="./fourDetailAction.tv" method="post" id="boardDetail">
<input type="hidden" name="BOARD_NUM" id="BOARD_NUM_DETAIL">
<div style="position:absolute; left:80px; height:80px; text-align: center; overflow: hidden; cursor: pointer;">
<img class="idd" src="<%=boardList.get(i).getBOARD_LINK_IMAGE() %>" width="120" height="90" onclick="detail(<%=boardList.get(i).getBOARD_NUM() %>)" >
</div></form>
<div style="position:absolute; left:210px; width:70%; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
<a style="text-decoration:none;" target="_blank" href="<%=boardList.get(i).getBOARD_LINK()%>">
<span style="color:#2955BC; font-size:14pt; font-family:dotum; text-decoration:none;">
<b><%=boardList.get(i).getBOARD_SUBJECT() %></b><!-- 글 제목 -->
</span>
<img style="border:0px;" src="img/new.png">
<br>
<span style="color:#BBB;">
<font style="color:#D0D; font-size:10pt;"><%=boardList.get(i).getBOARD_LINK_TITLE() %></font>
<!-- 영상의 원본 제목 (불러오는 법 못찾으면 없앰) -->
</span>
</a>
<br>
<span><%=boardList.get(i).getBOARD_NAME() %> / <%=boardList.get(i).getBOARD_DATE() %> / <%=boardList.get(i).getBOARD_LINK_NAME() %></span>
</div>
<div style="position:absolute; left:210px; bottom:8px;">
<span class="comments ui-widget-content ui-corner-all" style="padding:3px; font-family:gulim; cursor:pointer;">[ <%=countList.get(i) %> ]개의 댓글</span>
</div>
<div style="position:absolute; left:300px; bottom:8px;">
<!-- <button class="ui-corner-all"style=" border: 1px solid #dddddd; background: #ffffff 50% top repeat-x; color: #333333;">
삭제</button> -->
<form action="./fourDeleteAction.tv" method="post" name="boardDelete" id="boardDelete">
<input type="hidden" name="BOARD_NUM" id="BOARD_NUM_DELETE">
<input type="hidden" name="PASS_DEL" id="PASS_DEL">
 <!-- <span class="comments ui-widget-content ui-corner-all" style="padding:3px; font-family:gulim; cursor:pointer;" onclick="boardDel(<%=boardList.get(i).getBOARD_NUM() %>)">삭제</span> --> 
<a class="comments ui-widget-content ui-corner-all" style="padding:3px; font-family:gulim; cursor:pointer; text-decoration: none;" href="javascript:boardDel(<%=boardList.get(i).getBOARD_NUM() %>)">삭제</a>
</form>
</div>
<div style="position:absolute; right:10px; bottom:8px; ">
<img src="img/siren.JPG" width="13px" height="13px" style="margin-left: 9px;"/>
<form action="" method="post" id="boardReport">
<input type="hidden" name="BOARD_NUM" id="BOARD_NUM_REPORT">
<span class="comments ui-widget-content ui-corner-all" style="background-color:#FFA7A7; padding:3px; font-family:gulim; cursor:pointer;" onclick="boardRep(<%=boardList.get(i).getBOARD_NUM() %>)">신고</span> 
</form>
</div>
</div>
</td>
</tr>
<%}} %>
<!-- 세번째 끝 -->

</tbody>
<tfoot>
<tr>
<td style="padding-bottom: 12px;">
</td>
</tr>

</tfoot>
</table>
</div>

<section>
<table>

</table>
<!-- <input type="text" name="link" id="link" class="text ui-widget-content ui-corner-all" value="http://">
 -->
</section>
</section>
</body>
</html>