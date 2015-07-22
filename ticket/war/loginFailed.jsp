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
    <h1>フォーチュンチケットセンター会員様</h1>
    
    <from action="login.do" method="post" class="login">
        <table>
            <tr>
                <td>会員番号またはパスワードに誤りがあります。<BR>もう一度ログインしてください</td>
            </tr>
        </table>
        <td><a href="login.jsp">もう一度ログインする</a></td>
    </from>
</html>