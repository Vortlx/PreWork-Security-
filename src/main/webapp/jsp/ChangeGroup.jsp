<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
	    <title>Add Group</title>

	    <link rel="stylesheet" type="text/css" href="../staticresources/css/formStyle.css"/>
	    <link rel="stylesheet" type="text/css" href="../staticresources/css/marginForButtons.css"/>
	</head>
	<body>
        <div id="header"></div>
	    <div class="container upMargin">
		    <form action="ChangeGroup" method="POST" class="form-horizontal">
		        <div class="row">
		            <div class="form-group">
		                <label for="newGroupId" class="col-sm-2 control-label">Group Name:</label>
		                <div class="col-sm-2">
		                    <select name="newGroupId" id="newGroupId" class="form-control">
		                        <c:forEach items="${groups}" var="group">
		                            <option value="${group.id}">${group.name}</option>
		                        </c:forEach>
		                    </select>
		                </div>
		            </div>
		        </div>
		        <div class="row">
		            <div class="form-group">
		                <input name="studentId" type="hidden" value="${studentId}">
		                <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
		                <div class="col-sm-offset-2 col-sm-1">
		                   <input name="add" type="submit" class="btn btn-warning" value="Change">
		                </div>
		                <div class="col-sm-9">
		                    <a href="/" name="back" class="btn btn-default" onclick="return hideFrom()">Cancel</a>
		                </div>
		            </div>
		        </div>
		    </form>
		    <div class="row">
		        <div class="col-sm-10">
		            <span class="error">${requestScope.message}</span>
		        </div>
		    </div>
	    </div>
	</body>
</html>