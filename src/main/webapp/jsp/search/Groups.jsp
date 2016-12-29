<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
	<head>
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
	    <script src="../staticresources/javascript/showGroup.js"></script>
	</head>
	<body>
	    <div id="tableParent" class="container upMargin">
		    <div class="row">
		        <div class="col-sm-6">
			        <div class="link">
			            <a href="welcome" class="btn btn-default" name="back">Back</a>
			        </div>
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
			                                <a href="MySubjects?userId=${userId}&groupId=${group.id}&page=1" name="subjects">Subjects</a>
			                            </td>
			                            <td>
			                                <a href="DeleteGroup?userId=${userId}&groupId=${group.id}&page=${param.page}" name="deleteGroup">Delete</a>
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
							<c:if test="${param.page != 1}">
								<a href="Groups?userId=${param.userId}&page=${param.page - 1}" class="btn btn-default">&lt;</a>
							</c:if>
						</div>
					</div>
					<div class="btn-group">
                        <c:forEach var="i" begin="1" end="${maxPage}">
                            <a href="Groups?userId=${param.userId}&page=${i}" class="btn btn-default">${i}</a>
                        </c:forEach>
					</div>
					<div class="btn-group">
						<div class="col-sm-1">
							<c:if test="${param.page!= maxPage}">
								<a href="Groups?userId=${param.userId}&page=${param.page + 1}" class="btn btn-default">&gt;</a>
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
            })
        </script>
	</body>
</html>