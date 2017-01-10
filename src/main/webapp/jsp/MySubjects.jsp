<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
	    <title>My Subjects</title>

	    <link rel="stylesheet" type="text/css" href="../staticresources/css/marginForButtons.css"/>
	    <link rel="stylesheet" type="text/css" href="../staticresources/css/formStyle.css"/>

	    <script src="../staticresources/javascript/showSubjects.js"></script>
	</head>
	<body>
        <sec:authorize access="hasRole('ROLE_DEPARTMENT')">
			<jsp:include page="header.jsp">
				<jsp:param name="userId" value="${param.userId}"/>
			</jsp:include>
        </sec:authorize>
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
				                <a href="Groups.jsp?userId=${param.userId}" class="btn btn-default" name="Back">Back</a>
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
				                                <a href="DeleteSubject?groupId=${groupId}&subjectId=${subject.id}&userId=${userId}&page=${param.page}"
				                                   name="deleteSubject">Delete</a>
				                            </td>
				                        </sec:authorize>
				                    </tr>
				                </c:forEach>
				                <sec:authorize  access="hasRole('ROLE_DEPARTMENT')">
				                    <tr>
				                        <td colspan="3">
				                            <a href="#" name="addSubject" onclick="return showAddSubjects(${param.groupId}, ${param.userId}, 1)">Add</a>
				                        </td>
				                    </tr>
				                </sec:authorize>
				            </tbody>
				        </table>
				    </div>
	            </div>
	            <div class="col-sm-4">
		            <div id="newSubject">
                        <span class="error">${message}</span>
		            </div>
	            </div>
	        </div>
	        <div class="row paging">
				<div class="btn-toolbar">
					<div id="prevPage" class="btn-group">
						<div class="col-sm-1">
							<c:if test="${page > 1}">
								<a href="MySubjects?userId=${param.userId}&groupId=${param.groupId}&page=${page - 1}" class="btn btn-default">&lt;</a>
							</c:if>
						</div>
					</div>
					<div class="btn-group">
						<c:forEach var="i" begin="1" end="${maxPage}">
							<c:if test="${page == i}">
								<a href="MySubjects?groupId=${param.groupId}&userId=${param.userId}&page=${i}" class="btn btn-default active">${i}</a>
							</c:if>
							<c:if test="${page != i}">
								<a href="MySubjects?groupId=${param.groupId}&userId=${param.userId}&page=${i}" class="btn btn-default">${i}</a>
							</c:if>
						</c:forEach>
					</div>
					<div id="nextPage" class="btn-group">
						<div class="col-sm-1">
							<c:if test="${page < maxPage}">
								<a href="MySubjects?userId=${param.userId}&groupId=${param.groupId}&page=${page + 1}" class="btn btn-default">&gt;</a>
							</c:if>
						</div>
					</div>
				</div>
	        </div>
	    </div>
		<script>
            $(document).ready(function(){
                $("#subjectList").dataTable({
                    destroy: true,
                    bFilter : false,
                    bLengthChange: false,
                    paging: false,
                    info: false
                });
            });
		</script>
	</body>
</html>