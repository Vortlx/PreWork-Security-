<%--
  Created by IntelliJ IDEA.
  User: lebedevas
  Date: 14.11.16
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ChangeUsername</title>
</head>
<body>
<form action="./ChangeUsername" method="POST">
    Enter your password <input name="password" type="password"/>
    <br>
    Enter your new username <input name="username" type="text"/>
    <br>
    <input name="userID" type="hidden" value="${id}"/>
    <input name="change" type="submit" value="Change"/>
</form>
<br>
${message}
</body>
</html>
