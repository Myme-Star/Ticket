<%-- 
    Document   : cartCheckBox
    Created on : 2013/12/13, 18:33:23
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
        <div align="center">
                   <h1>${user}さんのカートの商品は以下の通りです。</h1>
      <c:forEach items="${kaijou}" var="pp">
                <h2>会場:${pp.name}</h2>
             </c:forEach>
    <c:forEach items="${data}" var="rec">
    <table border="1">
      <tr>
        <th>品名</th>
        <th>単価</th>
        <th>注文数</th>
        <th>金額</th>
    </tr>
  <tr>
    <td>${rec.name}</td>
    <td>${rec.tanka}</td>
    <td>${rec.kosuu}</td>
    <td>${rec.price}</td>
  </tr>
</table>
    </c:forEach>
                <h2>合計金額:${total}</h2>

        <form action="thankyou.jsp" method="post">
        <input type="submit" name="place" value="この内容で注文する" />
    </form>
    <form action="laneListFromDB.jsp" method="post">
        <input type="submit" name="place" value="注文をやり直す" />
    </form>
    </body>
</html>
