<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Welcome</title>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
        integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        
    <script src="../staticresources/javascript/addSwitcher.js"></script>
    <script src="../staticresources/javascript/showGroup.js"></script>
    <script src="../staticresources/javascript/showStudentSubjects.js"></script>
</head>
<body>
    <div style="float: left; margin-right: 50px">
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
                <a href="#" class="btn btn-warning" name="toMyGroup" onclick="return showGroup(null, ${user.id})">My Group</a>
                <br>
                <a href="#" class="btn btn-warning" name="toMySubjects" onclick="return showStudentSubjects(${user.id})">My Subjects</a>
            </div>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_TEACHER', 'ROLE_DEPARTMENT')">
            <div>
                <a href="Groups?userId=${user.id}" class="btn btn-warning" name="toGroups">Groups</a>
            </div>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_DEPARTMENT')">
            <div>
                <a href="search/Students.jsp?userId=${user.id}" class="btn btn-warning" name="toStudents">Students</a>
                <br>
                <a href="search/Teachers.jsp?userId=${user.id}" class="btn btn-warning" name="toTeachers">Teachers</a>
            </div>
            <div>
                <br>
                <form action="#" method="POST" name="add" onsubmit="addSwitcher(${user.id})">
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
            <a href="ChangePassword?userId=${user.id}" class="btn btn-warning" name="changePassword">Change password</a>
        </div>
        <div>
            <br>
            <!--a href="../logout" name="logout">Logout</a-->
            <form action="j_spring_security_logout" method="POST">
                <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
                <input name="logout" class="btn btn-danger" type="submit" value="Logout"/>
            </form>
        </div>
    </div>
    <div id="specifyGroup">
    </div>
</body>
</html>