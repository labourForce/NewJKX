<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Work Form</title>
    <link href="../resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="../resources/css/common.css " type="text/css" rel="stylesheet">

</head>

<body>

<div class="container">
    <form id="logoutForm" method="POST" action="Servlet">
        <input type="hidden" name="command" value="logout"/>
        <h2 class="text-right"><a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
    </form>
</div>

<div class="container">
    <form action="Servlet" method="post" class="form-signin">
        <h2 class="form-signin-heading">Fill all fields below</h2>
        <input type="hidden" name="command" value="workform">

        <div class="form-group">
            <label for="select1">Type Of Works</label>
            <select id="select1" class="form-control" name="typeofwork" required>
                <option></option>
                <c:forEach items="${requestScope.listWork}" var="list">
                    <option name="scopeOfWork" value="${list.id}">${list.toString()}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="select2">Scope</label>
            <select id="select2" class="form-control" name="scope" required>
                <option></option>
                <c:forEach items="${requestScope.scopeWork}" var="list">
                    <option name="scopeOfWork" value="${list.id}">${list.toString()}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label>Day of begin</label>
            <input type="date" name="day" required class="form-control"/>
        </div>

        <div class="form-group">
            <label>Address</label>
            <input type="text" name="address" required class="form-control"/>

        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</div>
</body>
</html>

