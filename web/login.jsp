<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Form</title>
    <link href="resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="resources/css/common.css " type="text/css" rel="stylesheet">

</head>

<body>
<div class="container">
    <form action="Servlet" method="post" class="form-signin">
        <h2 class="form-signin-heading">Login or Register</h2>
        <input type="hidden" name="command" value="login"/>

        <div class="form-group">
            <input type="text" name="username" class="form-control" placeholder="username" autofocus="true"/>
            <input type="password" name="password" class="form-control" placeholder="password"/>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
            <h4 class="text-center"><a href="WEB-INF/registration.jsp">Create new account</a> </h4>
        </div>

    </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
</div>
</body>
</html>
