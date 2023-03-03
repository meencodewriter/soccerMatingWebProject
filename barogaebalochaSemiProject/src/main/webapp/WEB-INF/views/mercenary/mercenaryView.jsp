<%@page import="semi.team.baro.mercenary.model.vo.Mercenary"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Mercenary mc = (Mercenary)request.getAttribute("mc");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<title>용병모집 상세보기</title>
<style>
    .tbl {
    	margin: 60px auto;
	    width: 80%;
	    border-spacing: 0px;
	    border-collapse: collapse;
    }
    .tbl th,.tbl td {
        padding: 10px;
        text-align: center;
    } 
    #mercenaryView th, #mercenaryView td{
        border: 1px solid #ccc;
    }
    #mercenaryView th{
        background-color: #181818;
        color: #fcfcfc;
    }
    .mercenary-content{
    	text-align: left;
    	min-height: 300px;
    }
    .back-link>.bs4{
    	padding: 5px;
    	font-size: 15px;
    	font-family: ns-bold;
    }
    /*
    .inputCommentBox ul{
    	width: 100%;
    	overflow: hidden;
    }
    .inputCommentBox li{
    	list-style-type: none;
    	float:left;
    }
    .inputCommentBox li>span{
    	font-size: 70px;
    	color: #ccc;
    }
    */
    .inputCommentBox{
		margin: 50px;
	}
	.inputCommentBox>form>ul{
		list-style-type: none;
		padding: 0;
		display: flex;
	}
	.inputCommentBox>form>ul>li:first-child{
		width: 10%;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	.inputCommentBox>form>ul>li:first-child>span{
		font-size: 70px;
		color: #ccc;
	}
	.inputCommentBox>form>ul>li:nth-child(2){
		width: 75%;
	}
	.inputCommentBox>form>ul>li:nth-child(2)>textarea{
		height: 95px;
		min-height: 95px;
	}
	.inputCommentBox>form>ul>li:last-child{
		width: 10%;
	}
	.view-link{
		width: 100%;
	}
	.view-link>a{
		text-align: center;
	}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="page-title">
			<h2>용병모집</h2>
			<hr>
		</div>
		<table class="tbl" id="mercenaryView">
			<tr>
				<th colspan="4"> 용병모집 </th>
			</tr>
			<tr>
				<th>지역</th>
				<td><%=mc.getLocation() %></td>
				<th>구장이름</th>
				<td colspan="3"><%=mc.getGroundName() %></td>
			</tr>
			<tr>
				<th>주소</th>
				<td colspan="3">서울시 영등포구</td>
			</tr>
			<tr>
				<th>경기일</th>
				<td colspan="3"><%=mc.getGameDate() %> [ <%=mc.getGameShowTime() %> ]</td>
				 
			</tr>
			<tr>
				<th>참가비</th>
				<td><%=mc.getMercenaryPay() %> 원</td>
				<th>실력</th>
				<%if(mc.getLevel() == 1){ %>
					<td>최상</td>
				<%}else if(mc.getLevel() == 2) {%>
					<td>상</td>
				<%}else if(mc.getLevel() == 3) {%>
					<td>중</td>
				<%}else if(mc.getLevel() == 4) {%>
					<td>하</td>
				<%}else if(mc.getLevel() == 5) {%>
					<td>최하</td>
				<%} %>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=mc.getMemberId() %></td>
				<th>작성일</th>
				<td style="color:#ccc;font-size:15px;"><%=mc.getRegDate() %></td>
			</tr>
			<tr>
				<th colspan="4">상세내용</th>
			</tr>
			<tr>
				<td colspan="4">
					<div class="mercenary-content">
						<%=mc.getMercenaryContent() %>
					</div>
				</td>
			</tr>
			<tr>
				<th colspan="4">
					<div class="back-link">
						
					</div>
				</th>
			</tr>	
		</table>
		<div class="view-link">
			<a href="/mercenaryList.do" class="btn2 bc1">목록으로</a>
		</div>
		<div class="inputCommentBox">
			<form action="/mercenaryRequestInsert.do" method="post">
				<ul>
					<li>
						<span class="material-symbols-outlined">account_circle</span>
					</li>
					<li>
						<input type="hidden" name="mercenaryNo" value="<%=mc.getMercenaryNo() %>">
						<input type="hidden" name="mcRequestNo" value="0">
						<textarea name="mcRequestContent" class="input-form"></textarea>
					</li>
					<li>
						<button type="submit" class="btn1 bc1 bs4">등록</button>
					</li>
				</ul>
			</form>
		</div>	
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>