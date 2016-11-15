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
        <c:if test="${userInfo.departments.size() > 0}">
            <c:forEach items="${userInfo.departments}" var="department">
                <h3>${department.name}</h3>
            </c:forEach>
        </c:if>
        <c:if test="${userInfo.teachers.size() > 0}">
            <c:forEach items="${userInfo.teachers}" var="teacher">
                <h3>Hello ${teacher.name} ${teacher.familyName}</h3>
            </c:forEach>
        </c:if>
        <c:if test="${userInfo.students.size() > 0}">
            <c:forEach items="${userInfo.students}" var="studnet">
                <h3>Hello ${studnet.name} ${studnet.familyName}</h3>
            </c:forEach>
        </c:if>
    </div>

    <sec:authorize access="hasRole('ROLE_STUDENT')">
        <div>
            <a href="./search/MyGroup?userId=${userInfo.id}" name="toMyGroup">My Group</a>
            <br>
            <a href="./search/MySubjects?userId=${userInfo.id}" name="toMySubjects">My Subjects</a>
        </div>
    </sec:authorize>
    <sec:authorize access="hasAnyRole('ROLE_TEACHER', 'ROLE_DEPARTMENT')">
        <div>
            <a href="./search/Groups?userId=${userInfo.id}" name="toGroups">Groups</a>
        </div>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_DEPARTMENT')">
        <div>
            <a href="./search/Students?userId=${userInfo.id}" name="toStudents">Students</a>
            <br>
            <a href="./search/Teachers?userId=${userInfo.id}" name="toTeachers">Teachers</a>
        </div>
        <div>
            <br>
            <form action="./add/Add" method="POST">
                <select name="whatAdd">
                    <option value="GROUP">Group</option>
                    <option value="STUDENT">Student</option>
                    <option value="TEACHER">Teacher</option>
                </select>
                <input name="userId" type="hidden" value="${userInfo.id}">
                <input name="add" type="submit" value="Add"/>
            </form>
        </div>
    </sec:authorize>

    <div>
        <br>
        <a href="./update/ChangeUsername.jsp?userId=${userInfo.id}" name="changeLogin">Change login</a>
        <br>
        <a href="./update/ChangePassword.jsp?userId=${userInfo.id}" name="changePassword">Change password</a>
    </div>

</body>
</html>