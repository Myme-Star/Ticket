<%-- 
    Document   : placeListFromDB
    Created on : 2013/12/10, 16:04:51
    Author     : 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
</head>
<body>
	<p>
	<h1>イベント会場一覧</h1>
	<div align="center">

		<h2>${user}さんの参加ご希望会場を選んでください</h2>

		<form action="set.do" method="get" class="set">
			<table class="menu">

				<c:forEach items="${data}" var="rec">
					<tr>
						<td><input type="radio" value=${rec.id } name="placeid" /></td>
						<td>${rec.place}</td>
					</tr>
				</c:forEach>

			</table>
			<input type="submit" name="set.do" value="レーン選択へ進む" />
		</form>
	</div>
	</p>
	<div align="center">
		<form action="getHistory.do" method="post">
			<input type="submit" name="history" value="購入履歴を見る" />
		</form>
		<form action="logout.do" method="post">
			<input type="submit" name="logout" value="ログアウト" />
		</form>
	</div>
</body>
</html>
