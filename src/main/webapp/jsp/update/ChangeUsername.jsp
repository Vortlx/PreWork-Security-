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
    <a href="welcome" name="Back">Back</a>
	<form action="ChangeUsername" method="POST">
	    Enter your password <input name="password" type="password"/>
	    <br>
	    Enter your new username <input name="username" type="text"/>
	    <br>
	    <input name="userId" type="hidden" value="${param.userId}"/>
		<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
	    <input name="change" type="submit" value="Change"/>
	</form>
	<br>
	${message}
</body>
</html>
