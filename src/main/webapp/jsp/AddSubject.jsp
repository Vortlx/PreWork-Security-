<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
	    <title>Add Subject</title>

	    <link rel="stylesheet" type="text/css" href="../staticresources/css/formStyle.css"/>
	    <link rel="stylesheet" type="text/css" href="../staticresources/css/marginForButtons.css"/>

	</head>
	<body>
        <div id="header"></div>
		<div class="container upMargin">
			<form action="AddSubject" method="POST" class="form-horizontal">
	            <div class="row">
	                <div class="form-group">
	                    <label for="subjectName" class="col-sm-2 control-label">Subject:</label>
	                    <div class="col-sm-3">
		                    <select name="subjectName" id="subjectName" class="form-control">
				                <c:forEach items="${subjects}" var="subject">
				                    <option value="${subject.name}">${subject.name}</option>
				                </c:forEach>
				            </select>
	                    </div>
	                </div>
	            </div>
	            <div class="row">
	                <div class="form-group">
		                <div class="col-sm-offset-2 col-sm-2">
		                    <select name="subjectType" class="form-control">
		                        <option value="LECTURE">Lecture</option>
		                        <option value="PRACTICE">Practice</option>
		                    </select>
		                </div>
	                </div>
	            </div>
	            <div class="row">
	                <div class="form-group">
		                <input name="groupId" type="hidden" value="${param.groupId}"/>
			            <input name="userId" type="hidden" value="${param.userId}"/>
			            <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
			            <div class="col-sm-offset-2 col-sm-1">
	                        <input name="add" type="submit" class="btn btn-warning" value="Add">
			            </div>
			            <div class="col-sm-9">
	                        <a href="#" name="back" class="btn btn-default" onclick="return hideAddSubject()">Cancel</a>
			            </div>
	                </div>
	            </div>
		    </form>
		    <div class="row">
		       <div class="col-sm-10">
		           <span class="error">${message}</span>
		       </div>
		    </div>
	    </div>
	</body>
</html>