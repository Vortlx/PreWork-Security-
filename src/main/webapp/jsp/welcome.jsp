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
        <div class="container">
	        <div class="row">
	            <!-- Greeting user (Department, Teacher ot Student) -->
	            <div class="col-sm-12 alert alert-info" role="alert">
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
            </div>
            <div class="row">
	            <div class="col-sm-3">
		            <div id="content">
		                <sec:authorize access="hasRole('ROLE_STUDENT')">
		                    <div class="link">
		                        <a href="#" class="btn btn-default" name="toMyGroup" onclick="return showGroup(null, ${user.id})">
		                            <span>My Group</span>
		                        </a>
		                    </div>
		                    <div class="link">
		                        <a href="#" class="btn btn-default" name="toMySubjects" onclick="return showStudentSubjects(${user.id})">
		                            <span>My Subjects</span>
		                        </a>
		                    </div>
		                </sec:authorize>
		                <sec:authorize access="hasAnyRole('ROLE_TEACHER', 'ROLE_DEPARTMENT')">
		                    <div class="link">
		                        <a href="Groups?userId=${user.id}&page=1" class="btn btn-default" name="toGroups">
		                            <span>Groups</span>
		                        </a>
		                    </div>
		                </sec:authorize>
		                <sec:authorize access="hasRole('ROLE_DEPARTMENT')">
		                    <div class="link">
		                        <a href="search/Students.jsp?userId=${user.id}&page=1" class="btn btn-default" name="toStudents">
		                           <span>Students</span>
		                        </a>
		                    </div>
		                    <div class="link">
		                        <a href="search/Teachers.jsp?userId=${user.id}&page=1" class="btn btn-default" name="toTeachers">
		                           <span>Teachers</span>
		                        </a>
		                    </div>
		                    <div class="dropdown link">
		                      <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
		                        <span>Add</span>
		                        <div class="caret"></div>
		                      </button>
		                      <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
		                        <li><a href="add/AddGroup.jsp?userId=${user.id}"><span>Group</span></a></li>
		                        <li><a href="add/AddStudentPage?userId=${user.id}"><span>Student</span></a></li>
		                        <li><a href="add/AddTeacher.jsp?userId=${user.id}"><span>Teacher</span></a></li>
		                      </ul>
		                    </div>
		                </sec:authorize>
		                <div class="link">
		                    <a href="update/ChangePassword.jsp?userId=${user.id}" class="btn btn-default" name="changePassword">
		                       <span>Change password</span>
		                    </a>
		                </div>
		            </div>
		            <div id="logout">
		                <!--a href="../logout" name="logout">Logout</a-->
		                <form action="j_spring_security_logout" method="POST">
		                    <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
		                    <input name="logout" class="btn btn-danger" type="submit" value="Logout"/>
		                </form>
		            </div>
	            </div>
	            <div class="col-sm-9">
		            <div id="specifyGroup">
	                </div>
	            </div>
            </div>
        </div>
	</body>
</html>