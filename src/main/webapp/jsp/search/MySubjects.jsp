<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
	    <title>My Subjects</title>
	    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css"/>
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	        integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	        integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	    
	    <link rel="stylesheet" type="text/css" href="../staticresources/css/marginForButtons.css"/>
	    <link rel="stylesheet" type="text/css" href="../staticresources/css/formStyle.css"/>
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	    <script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
	    <script src="../staticresources/javascript/showSubjects.js"></script>
	</head>
	<body>
	    <div class="container upMargin">
	        <div class="row">
	            <div class="col-sm-7">
		            <div id="tableParent">
				        <sec:authorize access="hasRole('ROLE_STUDENT')">
				            <div class="link">
				                <a href="/" name="Back" class="btn btn-default" onclick="return hideInfo()">Hide</a>
				            </div>
				        </sec:authorize>
				        <sec:authorize access="hasRole('ROLE_DEPARTMENT')">
				            <div class="link">
				                <a href="Groups?userId=${userId}" class="btn btn-default" name="Back">Back</a>
				            </div>
				        </sec:authorize>
				        <table id="subjectList" class="table-bordered">
				            <thead>
				                <tr>
				                    <th colspan="4">Subjects</th>
				                </tr>
				                <tr>
				                    <th>Name</th>
				                    <th>Type</th>
				                    <th>Teacher</th>
				                    <sec:authorize access="hasRole('ROLE_DEPARTMENT')">
				                        <th> </th>
				                    </sec:authorize>
				                </tr>
				            </thead>
				            <tbody>
				                <c:forEach items="${subjects}" var="subject">
				                    <tr>
				                        <td>${subject.name}</td>
				                        <td>${subject.type}</td>
				                        <td>
				                                ${subject.teacher.name} ${subject.teacher.familyName}
				                        </td>
				                        <sec:authorize access="hasRole('ROLE_DEPARTMENT')">
				                            <td>
				                                <a href="DeleteSubject?groupId=${groupId}&subjectId=${subject.id}&userId=${userId}"
				                                   name="deleteSubject">Delete</a>
				                            </td>
				                        </sec:authorize>
				                    </tr>
				                </c:forEach>
				                <sec:authorize  access="hasRole('ROLE_DEPARTMENT')">
				                    <tr>
				                        <td colspan="3">
				                            <a href="/" name="addSubject" onclick="return showAddSubjects(${groupId}, ${userId})">Add</a>
				                        </td>
				                    </tr>
				                </sec:authorize>
				            </tbody>
				        </table>
				        <script>
				            $(document).ready(function(){
				                $("#subjectList").dataTable();
				            })
				        </script>
				    </div>
	            </div>
	            <div class="col-sm-3">
		            <div id="newSubject">
		            </div>
	            </div>
	        </div>
	    </div>
	</body>
</html>