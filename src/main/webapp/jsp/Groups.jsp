<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header.jsp">
	<jsp:param name="user.id" value="${param.userId}"/>
</jsp:include>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	    <title>Groups</title>
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css"/>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	        integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	        integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	    
	    <link rel="stylesheet" type="text/css" href="../staticresources/css/formStyle.css"/>
	    <link rel="stylesheet" type="text/css" href="../staticresources/css/marginForButtons.css"/>
	    
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
            
	    <script src="../staticresources/javascript/showGroup.js"></script>
	</head>
	<body>
        <div id="header"></div>
	    <div id="tableParent" class="container upMargin">
		    <div class="row">
		        <div class="col-sm-6">
			        <table id="tableGroups" class="table-bordered">
			            <thead>
			                <tr>
			                    <th>Name</th>
			                    <sec:authorize access="hasRole('ROLE_DEPARTMENT')">
			                        <th> </th>
			                        <th> </th>
			                    </sec:authorize>
			                </tr>
			            </thead>
			            <tbody>
			                <c:forEach items="${groups}" var="group">
			                    <tr>
			                        <td>
			                            <a href="#" name="toGroups"
			                               onclick="return showGroup(${group.id}, ${param.userId}, 1)">${group.name}</a>
			                        </td>
			                        <sec:authorize access="hasRole('ROLE_DEPARTMENT')">
			                            <td>
			                                <a href="MySubjects?userId=${userId}&groupId=${group.id}" name="subjects">Subjects</a>
			                            </td>
			                            <td>
			                                <a href="DeleteGroup?userId=${userId}&groupId=${group.id}&page=${page}" name="deleteGroup">Delete</a>
			                            </td>
			                        </sec:authorize>
			                    </tr>
			                </c:forEach>
			            </tbody>
			        </table>
		        </div>
		        <div class="col-sm-6">
		            <div id="specifyGroup">
		            </div>
		        </div>
		    </div>
		    <div class="row paging">
				<div class="btn-toolbar">
					<div class="btn-group">
						<div class="col-sm-1">
							<c:if test="${page > 1}">
								<a href="Groups?userId=${param.userId}&page=${page - 1}" class="btn btn-default">&lt;</a>
							</c:if>
						</div>
					</div>
					<div class="btn-group">
                        <c:forEach var="i" begin="1" end="${maxPage}">
							<c:if test="${page == i}">
								<a href="Groups?userId=${param.userId}&page=${i}" class="btn btn-default active">${i}</a>
							</c:if>
							<c:if test="${page != i}">
								<a href="Groups?userId=${param.userId}&page=${i}" class="btn btn-default">${i}</a>
							</c:if>
                        </c:forEach>
					</div>
					<div class="btn-group">
						<div class="col-sm-1">
							<c:if test="${page < maxPage}">
								<a href="Groups?userId=${param.userId}&page=${page + 1}" class="btn btn-default">&gt;</a>
							</c:if>
						</div>
					</div>
				</div>
		    </div>
	    </div>
        <script>
            $(document).ready(function(){
                $("#tableGroups").dataTable({
                    bFilter : false,
                    bLengthChange: false,
                    paging: false,
                    info: false,
                    columnDefs: [{
                        targets: [1, 2],
                        searcheable: false,
                        orderable: false
                    }]
                });
            });
        </script>
	</body>
</html>