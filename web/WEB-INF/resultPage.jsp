<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="../resources/css/common.css " type="text/css" rel="stylesheet">
    <link href="../resources/css/mystaly.css " type="text/css" rel="stylesheet">
</head>

<body>
<center><h1>Complite!</h1></center>

</div>
<table>
    <tr>
        <th colspan="2">Castomer name</th>
        <th>Type of work</th>
        <th>Scope</th>
        <th>Date of begin</th>
        <th>Address</th>
        <th>Status</th>
    </tr>
    <tr>
        <td><%=request.getAttribute("castomer")%></td>
        <td></td>
        <td><%=request.getAttribute("typeofwork")%></td>
        <td><%=request.getAttribute("scopeofwork")%></td>
        <td><%=request.getAttribute("date")%></td>
        <td><%=request.getAttribute("address")%></td>
        <td><%=request.getAttribute("status")%></td>
    </tr>
</table>

<div class="container">
    <form id="useraccountForm" method="POST" action="Servlet">
        <input type="hidden" name="command" value="useraccount"/>
        <h2 class="text-center"><a onclick="document.forms['useraccountForm'].submit()">My account</a></h2>
    </form>
</div>

<div class="container">
    <form id="logoutForm" method="POST" action="Servlet">
        <input type="hidden" name="command" value="logout"/>
        <h2 class="text-center"><a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
    </form>
</div>

</body>
</html>
