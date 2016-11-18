<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>
    <!-- Greeting user (Department, Teacher ot Student) -->
    <div>
        <c:if test="${userInfo.department != null}">
            <h3>${userInfo.department.name}</h3>
        </c:if>
        <c:if test="${userInfo.teacher != null}">
            <h3>Hello ${userInfo.teacher.name} ${userInfo.teacher.familyName}</h3>
        </c:if>
        <c:if test="${userInfo.student != null}">
            <h3>Hello ${userInfo.student.name} ${userInfo.student.familyName}</h3>
        </c:if>
    </div>

    <sec:authorize access="hasRole('ROLE_STUDENT')">
        <div>
            <a href="./MyGroup?userId=${userInfo.id}" name="toMyGroup">My Group</a>
            <br>
            <a href="./MySubjects?userId=${userInfo.id}" name="toMySubjects">My Subjects</a>
        </div>
    </sec:authorize>
    <sec:authorize access="hasAnyRole('ROLE_TEACHER', 'ROLE_DEPARTMENT')">
        <div>
            <a href="./Groups?userId=${userInfo.id}" name="toGroups">Groups</a>
        </div>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_DEPARTMENT')">
        <div>
            <a href="./Students?userId=${userInfo.id}" name="toStudents">Students</a>
            <br>
            <a href="./Teachers?userId=${userInfo.id}" name="toTeachers">Teachers</a>
        </div>
        <div>
            <br>
            <form action="./Add" method="POST">
                <select name="whatAdd">
                    <option value="GROUP">Group</option>
                    <option value="STUDENT">Student</option>
                    <option value="TEACHER">Teacher</option>
                </select>
                <input name="userId" type="hidden" value="${userInfo.id}">
                <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
                <input name="add" type="submit" value="Add"/>
            </form>
        </div>
    </sec:authorize>

    <div>
        <br>
        <a href="./ChangeUsername?userId=${userInfo.id}" name="changeLogin">Change login</a>
        <br>
        <a href="./ChangePassword?userId=${userInfo.id}" name="changePassword">Change password</a>
    </div>
    <div>
        <br>
        <!--a href="../logout" name="logout">Logout</a-->
        <form action="./j_spring_security_logout" method="POST">
            <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
            <input name="logout" type="submit" value="Logout"/>
        </form>
    </div>

</body>
</html>