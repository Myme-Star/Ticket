<%-- 
    Document   : login
    Created on : 2013/12/09, 17:02:06
    Author     : 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <h1><font size="7" color="#9932CC">◢</font>フォーチュンチケットセンター会員様</h1>
    
    <form action="login.do" method="post" class="login">
        <table>
            <tr>
                <td>会員番号</td>
                <td><input type="text" name="user" size="16" value="" /></td>
            </tr>
            <tr>
                <td>パスワード</td>
                <td><input type="password" name="password" size="16" value="" /></td>
            </tr>
        </table> 
        <form action="login.do" method="post">
        <input type="submit" value="ログイン" />
        </form>
    </form>
</html>
