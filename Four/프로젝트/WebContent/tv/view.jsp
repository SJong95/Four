<%@page import="vo.ReBoard"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% Board board = (Board)request.getAttribute("board");
       ArrayList<ReBoard> reboard = (ArrayList<ReBoard>)request.getAttribute("commentList");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여기에 제목을 입력하십시오</title>
<link rel="stylesheet" href="css/fooo.css" type="text/css" media="print, projection, screen" />
<script src="js/four.js"></script>
</head>
<body>
<div style="margin:20px auto; width: 750px; height:auto; background-color: #eeeeee; padding:10px; position: relative; border-radius: 5px;">
<div class="ui-tabs-nav ui-helper-reset  ui-widget-header ui-corner-all" style="width: 715px; height:35px;padding: 2px 12px 2px 12px; margin: 0 5px 0 5px;">
<span style="float: left; margin: .3em 16px .5em 0; font-size: 15pt;"><%=board.getBOARD_SUBJECT() %></span>
<span style="float: right; margin: .3em 16px .5em 0; font-size: 15pt;"><%=board.getBOARD_DATE() %></span>
</div>

<div style="margin: 2px 12px 2px 12px; ">
<div style="margin: 5px 0 5px 0;">
<iframe width="726px;" height="408px;" src="<%=board.getBOARD_LINK_VIDEO() %>"
frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe><!-- 주소 값 바꿔줘야됨 -->
</div>
<div style="margin: 10px 0 10px 0; display: inline;font-size: 11pt;">
<span style="color: ;" ><%=board.getBOARD_NAME() %></span><!-- 글 색 정하기 -->
</div>
<div style="float: right;">
<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false" onclick="javascript:window.open('<%=board.getBOARD_LINK() %>')" style="height: 25px; font-size: 9pt;">
<span class="ui-button-text">원본 영상</span>
<!-- <a href="<%=board.getBOARD_LINK()%>">원본 영상</a> -->
</button>
</div><br>
<div style="margin: 10px 0 10px 0; font-size: 11pt;">
<span>내용</span>
</div>
<div style="width:726px;height:auto; border-bottom: 1px solid #dddddd; border-top: 1px solid #dddddd; padding: 5px 0 5px 0; margin:0px 0 5px 0; font-size: 9pt;">
<span style=""><%=board.getBOARD_CONTENT() %></span></div>
<!-- 댓글 작성 불러오기 시작 -->
<div style="margin: 10px 0 10px 0; font-size: 11pt;">
<span>댓글</span>
</div>
<div style="width:726px;height:auto; border-bottom: 1px solid #dddddd; border-top: 1px dashed #dddddd; padding: 5px 0 5px 0; margin:0px 0 5px 0;">
<div style="width: 100%; font-size: 9pt;">
<!-- for 시작 -->
<%if(reboard != null){ %>
<%for(int i =0; i<reboard.size();i++){ %>
<form action="./fourCommentDelete.tv" method="post" id="commentDelete"<%if(reboard.get(i).getREBOARD_RE_SEQ()!=0){%>style="margin-left: 40px;"<%}%>>
<div style="height:auto; border-bottom: 1px solid #bbbbbb; border-top: 1px solid #bbbbbb; padding: 3px 0 3px 0;">
<span style="padding: 3px; color: #77D;"><%=reboard.get(i).getREBOARD_NAME() %></span>&nbsp;
<span style="color: gray;"><%=reboard.get(i).getREBOARD_DATE() %></span>
<%if(reboard.get(i).getREBOARD_RE_SEQ()==0){ %>
<div style="display: inline;">
<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false" onclick="recommentW(<%=i %>)" style=" width:20px; ">
<span style="padding: 1px 3px;">R</span>
</button></div>
<%} %>
<div style="float: right;"><!-- for문 사용시 포지션 앱솔루트로 해서 bottom이나 top값 변경으로 바꿔주기  변경시 태그: <div style="position: absolute; right: 23px; top:641px;">-->
<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false" onclick="commentdel(<%=reboard.get(i).getREBOARD_NUM()%>)" style=" width:20px; ">
<span style="padding: 1px 3px;">X</span>
</button>
<input type="hidden" name="REBOARD_NUM" id="REBOARD_NUM">
<input type="hidden" name="REBOARD_PASS" id="REBOARD_PASS">
<input type="hidden" name="BOARD_NUM" value="<%=reboard.get(i).getBOARD_NUM() %>">
</div>
</div>
<div style="height:auto; padding: 3px 0 3px 0; overflow: hidden; text-overflow: clip; white-space: nowrap;" >
<span style="padding: 10px;">&nbsp;<%=reboard.get(i).getREBOARD_CONTENT() %></span>
</div>
</form>
<div id="<%=i %>" style="display: none;">
<form action="fourReComment.tv" method="post" name="reCommentWrite" id="<%=i+100%>" onsubmit="recommentWrite(<%=reboard.get(i).getREBOARD_NUM()%>, <%=reboard.get(i).getBOARD_NUM()%>);">

</form>
</div>
<%}} %>
<!-- for 끝 -->
</div>
</div>
<!-- 댓글 작성 불러오기 끝 -->
<!-- 댓글 작성 란 시작 -->
<div>
<form action="fourComment.tv" method="post" name="commentWrite">
<div style="font-size: 9pt; padding-left: 12px;">
<label for="comname">이름: </label>
<input type="text" name="comname" style="width: 15%; height:14px; border-radius: 5px;" class="ui-corner-all ui-widget-content">&nbsp;
<label for="compass">비번: </label>
<input type="text" name="compass" style="width: 15%; height:14px; border-radius: 5px;" class="ui-corner-all ui-widget-content">
</div>
<div style="font-size: 9pt; padding-left: 12px;margin-top:5px; display: inline;">
<label for="compass">내용: </label>
<input type="text" name="comcontent" style="width: 70%; height:14px; border-radius: 5px;" class="ui-corner-all ui-widget-content">
<input type="hidden" name="BOARD_NUM" value="<%=board.getBOARD_NUM()%>">
</div>
<div style="font-size: 8pt; float: right; margin-top: 1px; margin-right: 5px;">
<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false" onclick="javascript:location.href='four.tv';" style=" width:60px; height:20px; padding: 0 3px 0 3px;">
<span>목록</span>
</button></div>
<div style="font-size: 8pt; float: right; margin-top: 1px;">
<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false" onclick="javascript:commentWrite.submit();" style=" width:60px; height:20px; padding: 0 2px 0 2px;">
<span>댓글 등록</span>
</button></div>

</form>
</div>
<!-- 댓글 작성 란 끝 -->
</div>
</div>
</body>
</html>