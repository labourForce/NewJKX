<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="../resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="../resources/css/common.css " type="text/css" rel="stylesheet">
    <link href="../resources/css/mystaly.css " type="text/css" rel="stylesheet">
</head>
<body>
<div class="container">
    <form id="logoutForm" method="POST" action="Servlet">
        <input type="hidden" name="command" value="logout"/>
        <h2 class="text-right"><a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
    </form>
</div>

<table>
    <c:forEach items="${requestScope.worklist}" var="list">
        <tr>
            <td><input type="hidden" name="idWork" value="${list.id}"/>${list.toString()}</td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
