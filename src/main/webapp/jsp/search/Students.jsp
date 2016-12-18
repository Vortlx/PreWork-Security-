<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Students</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="../javascript/personValidation.js"></script>
    <style>
        .error{
            color: RED;
        }
    </style>
</head>
<body>
    <a href="welcome" name="back">Back</a>
    <form action="Students" method="POST" onsubmit="return personIncorrectNameValidation()">
        Name: <input name="name" id="personName" type="text">
        <span class="error" id="incorrName"></span>
        <br>
        Family name: <input name="familyName" id="personFamilyName" type="text">
        <span class="error" id="incorrFamilyName"></span>
        <br>
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
        <input name="send" type="submit" value="Find">
    </form>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Family Name</th>
            <th>Group</th>
            <th> </th>
        </tr>
        <c:forEach items="${students}" var="student">
           <tr>      
               <td>${student.name}</td>
               <td>${student.familyName}</td>
               <td>
                   ${student.group.name}
                   <br>
                   <a href="ChangeGroupPage?userId=${userId}&depId=${department.id}&studentId=${student.id}"
                      name="changeGroup">Change group</a>
               </td>
               <td>
                   <a href="DeleteStudent?studentId=${student.id}" name="deleteStudent">Delete</a>
               </td>
           </tr>
        </c:forEach>
    </table>
</body>
</html>