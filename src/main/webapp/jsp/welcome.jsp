<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Welcome</title>
    <script src="../javascript/GROG.js"></script>
</head>
<body>
    <!-- Greeting user (Department, Teacher ot Student) -->
    <div>
        <c:if test="${user.department != null}">
            <h3>${user.department.name}</h3>
        </c:if>
        <c:if test="${user.teacher != null}">
            <h3>Hello ${user.teacher.name} ${user.teacher.familyName}</h3>
        </c:if>
        <c:if test="${user.student != null}">
            <h3>Hello ${user.student.name} ${user.student.familyName}</h3>
        </c:if>
    </div>

    <sec:authorize access="hasRole('ROLE_STUDENT')">
        <div>
            <a href="MyGroup?userId=${user.id}" name="toMyGroup">My Group</a>
            <br>
            <a href="MySubjects?userId=${user.id}" name="toMySubjects">My Subjects</a>
        </div>
    </sec:authorize>
    <sec:authorize access="hasAnyRole('ROLE_TEACHER', 'ROLE_DEPARTMENT')">
        <div>
            <a href="Groups?userId=${user.id}" name="toGroups">Groups</a>
        </div>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_DEPARTMENT')">
        <div>
            <a href="Students?userId=${user.id}" name="toStudents">Students</a>
            <br>
            <a href="Teachers?userId=${user.id}" name="toTeachers">Teachers</a>
        </div>
        <div>
            <br>
            <form action="/" method="POST" name="add" onsubmit="addSwitcher()">
                <select name="whatAdd" id="whatAdd">
                    <option value="GROUP">Group</option>
                    <option value="STUDENT">Student</option>
                    <option value="TEACHER">Teacher</option>
                </select>
                <input name="userId" type="hidden" value="${user.id}">
                <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
                <input name="addButton" type="submit" value="Add"/>
            </form>
        </div>
    </sec:authorize>

    <div>
        <br>
        <!-- a href="ChangeUsername?userId=${user.id}" name="changeLogin">Change login</a-->
        <br>
        <a href="ChangePassword?userId=${user.id}" name="changePassword">Change password</a>
    </div>
    <div>
        <br>
        <!--a href="../logout" name="logout">Logout</a-->
        <form action="j_spring_security_logout" method="POST">
            <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
            <input name="logout" type="submit" value="Logout"/>
        </form>
    </div>
</body>
</html>