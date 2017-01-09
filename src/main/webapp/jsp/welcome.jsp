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
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	        integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	        
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	    <script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
	    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

        
        <link rel="stylesheet" type="text/css" href="../staticresources/css/marginForButtons.css"/>
	    <script src="../staticresources/javascript/showGroup.js"></script>
	    <script src="../staticresources/javascript/showStudentSubjects.js"></script>
	</head>
	<body>
		<nav class="navbar navbar-default">
			<div class="container">
				<ul class="nav navbar-nav">
					<sec:authorize access="hasRole('ROLE_STUDENT')">
						<li>
							<a href="#" name="toMyGroup" onclick="return showGroupForStudent(${user.id}, 1)">
								<span class="header">My Group</span>
							</a>
						</li>
						<li>
							<a href="#" name="toMySubjects" onclick="return showStudentSubjects(${user.id}, 1)">
								<span class="header">My Subjects</span>
							</a>
						</li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_TEACHER', 'ROLE_DEPARTMENT')">
						<li>
							<a href="Groups?userId=${user.id}" name="toGroups">
								<span class="header">Groups</span>
							</a>
						</li>
					</sec:authorize>
					<sec:authorize access="hasRole('ROLE_DEPARTMENT')">
						<li>
							<a href="Students.jsp?userId=${user.id}" name="toStudents">
								<span class="header">Students</span>
							</a>
						</li>
						<li>
							<a href="Teachers.jsp?userId=${user.id}" name="toTeachers">
								<span class="header">Teachers</span>
							</a>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
							   aria-haspopup="true" aria-expanded="false"><span class="header">Add </span><span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="AddGroup.jsp?userId=${user.id}"><span class="header">Group</span></a></li>
								<li><a href="AddStudentPage?userId=${user.id}"><span class="header">Student</span></a></li>
								<li><a href="AddTeacher.jsp?userId=${user.id}"><span class="header">Teacher</span></a></li>
							</ul>
						</li>
					</sec:authorize>
					<li>
						<a href="ChangePassword.jsp?userId=${user.id}" name="changePassword">
							<span class="header">Change password</span>
						</a>
					</li>
					<%--<li>--%>
						<%--<a href="../logout" name="logout">Logout</a>--%>
					<%--</li>--%>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li style="margin-left: 20px">
						<!-- Greeting user (Department, Teacher or Student) -->
						<c:if test="${user.department != null}">
							<h3 class="navbar-text">${user.department.name}</h3>
						</c:if>
						<c:if test="${user.teacher != null}">
							<h3 class="navbar-text">Hello ${user.teacher.name} ${user.teacher.familyName}</h3>
						</c:if>
						<c:if test="${user.student != null}">
							<h3 class="navbar-text">Hello ${user.student.name} ${user.student.familyName}</h3>
						</c:if>
					</li>
					<li>
						<form action="j_spring_security_logout" method="POST">
							<div class="form-group">
								<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
								<input name="logout" class="btn btn-danger navbar-btn" type="submit" value="Logout"/>
							</div>
						</form>
					</li>
				</ul>
			</div>
		</nav>
            <%--<div class="row">--%>
	            <%--<div class="col-sm-3">--%>
		            <%----%>
	            <%--</div>--%>
            <%--</div>--%>
			<div class="row">
				<div class="col-sm-9">
					<div id="specifyGroup">
					</div>
				</div>
			</div>
        </div>
	</body>
</html>