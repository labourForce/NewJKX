<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
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
    <form class="form-signin" method="POST" action="Servlet">
        <input type="hidden" name="command" value="statuschange">

<table>
    <c:forEach items="${requestScope.worklistproc}" var="list">
        <tr>
            <td><input type="radio" name="idWork" value="${list.id}"/>${list.toString()}</td>
        </tr>
    </c:forEach>
        <input type="radio" name="confirm" value="confirm"/><i>Confirm</i>
        <input type="radio" name="confirm" value="reject"/><i>Reject</i>
        <button class="btn btn-primary" type="submit">Submit</button>
        </form>
</table>

</div></body>
</html>
