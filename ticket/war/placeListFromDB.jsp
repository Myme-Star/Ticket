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
           <h1>イベント会場一覧</h1>
        <div align="center">

      <h2>${user}さんの参加ご希望会場を選んでください</h2>


       <form action="set.do" method="get" class="set">
          <table class="menu">
              <c:forEach items="${data}" var="rec">
                  <tr>
                      <td><input type="checkbox" value=${rec.id} name="placeid"/></td>
                      <td>${rec.id}</td>
                      <td>${rec.name}</td>
                      <td class="price">${rec.price} </td>
                  </tr>
              </c:forEach>

          </table>          
              <input type="submit" name="set.do" value="レーン選択へ進む" />
           </form>

    <form action="logout.do" method="post">
        <input type="submit" name="logout" value="ログアウト" />
    </form>
    </body>
</html>
