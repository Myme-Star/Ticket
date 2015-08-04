<%-- 
    Document   : laneListFromDB
    Created on : 2013/12/10, 16:48:40
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
	<h1>選択可能レーン一覧</h1>
	<div align="center">
		<h2>${user}さんのご希望レーンと枚数を打ってください</h2>
		<h2>会場：${kaijou}</h2>


		<form action="cart.do" method="get" class="cart">
			<table class="menu">
				<c:forEach items="${data}" var="rec">
					<tr>
						<td>${rec.name}</td>
						<td class="price">${rec.price}円</td>
						<td><input type="text" size="2" value="" name="kosuu" />枚</td>
					</tr>
				</c:forEach>

			</table>

			<input type="submit" name="cart.do" value="カートに入れ注文へ進む" />
		</form>

		<form action="placeListFromDB.jsp" method="post">
			<input type="submit" name="place" value="もどる" />
		</form>
	</div>
</body>
</html>
