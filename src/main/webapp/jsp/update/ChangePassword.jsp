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
    <title>ChangePassword</title>
</head>
<body>
    <form action="./ChangePassword" method="POST">
        Enter your old password <input name="oldPassword" type="password"/>
        <br>
        Enter your new password <input name="newPassword" type="password"/>
        <br>
        <input name="userID" type="hidden" value="${id}"/>
        <input name="change" type="submit" value="Change"/>
    </form>
    <a href="../welcome" name="Back">Back</a>
    <br>
    ${message}
</body>
</html>
