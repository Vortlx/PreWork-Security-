<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
	    <title>Add Subject</title>
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	        integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	        integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	    <link rel="stylesheet" type="text/css" href="../staticresources/css/formStyle.css"/>
	    <link rel="stylesheet" type="text/css" href="../staticresources/css/marginForButtons.css"/>
	    
	    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	</head>
	<body>
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
	                        <a href="#" name="back" class="btn btn-default" onclick="return hideAddSubject()">Hide</a>
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