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
    <style>
        .error{
            color: RED;
        }
    </style>

    <script src="../javascript/validationPassword.js"></script>
</head>
<body>
    <a href="welcome" name="Back">Back</a>
    <form action="ChangePassword" method="POST" name="newPassword" onsubmit="return validPassword()">
        Enter your old password <input name="oldPassword" id="oldPassword" type="password"/>
        <span class="error" id="incorrOldPass"></span>
        <br>
        Enter your new password <input name="newPassword" id="newPassword" type="password"/>
        <span class="error" id="incorrNewPass"></span>
        <br>
        <input name="userId" type="hidden" value="${param.userId}"/>
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
        <input name="change" type="submit" value="Change"/>
    </form>
    <br>
    ${message}
</body>
</html>
