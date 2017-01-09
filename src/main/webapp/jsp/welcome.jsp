<%--
  Created by IntelliJ IDEA.
  User: lebedevas
  Date: 09.01.2017
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header.jsp">
    <jsp:param name="userId" value="${user.id}"/>
</jsp:include>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
    <h1>
        Hello
    </h1>
</body>
</html>
