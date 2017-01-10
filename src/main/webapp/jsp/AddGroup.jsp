<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp">
    <jsp:param name="userId" value="${param.userId}"/>
</jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Add Group</title>

    <link rel="stylesheet" type="text/css" href="../staticresources/css/formStyle.css"/>
    <link rel="stylesheet" type="text/css" href="../staticresources/css/marginForButtons.css"/>

    <script src="http://cdn.jsdelivr.net/jquery.validation/1.15.0/jquery.validate.min.js"></script>
    <script src="../staticresources/javascript/groupNameValidation.js"></script>
</head>
<body>
<div id="header"></div>
<div class="container upMargin">
    <form id="addGroup" name="addGroup" action="AddGroup" method="POST" class="form-horizontal">
        <div class="form-group">
            <div class="row">
                <label for="groupName" class="col-sm-2 control-label">Name:</label>
                <div class="col-sm-7">
                    <input name="groupName" id="groupName" class="form-control" type="text"/>
                </div>
                <div class="col-sm-3">
                    <span class="error" id="incorrGroupName"></span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <input id="userId" name="userId" type="hidden" value="${param.userId}"/>
            <input id="token" name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
            <div class="row">
                <div class="col-sm-offset-2 col-sm-1">
                    <input name="add" type="submit" class="btn btn-warning" value="Add">
                </div>
                <div id="back" class="col-sm-9">
                    <a href="welcome" class="btn btn-default" name="back">Cancel</a>
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