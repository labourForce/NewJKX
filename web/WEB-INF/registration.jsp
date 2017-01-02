<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Create new Account</title>
    <link href="../resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="../resources/css/common.css " type="text/css" rel="stylesheet">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>

<body>

<div class="container">

    <form action="Servlet" method="post" class="form-signin">
        <h2 class="form-signin-heading">Registration</h2>
        <input type="hidden" name="command" value="registration"/>

        <div class="form-group">
            <input type="text" name="username" required class="form-control" placeholder="username" autofocus="true"/>
            <input type="password" name="password" required class="form-control" placeholder="password"/>
            <input type="text" name="firstname" required class="form-control" placeholder="firstname"/>
            <input type="text" name="secondname" required class="form-control" placeholder="secondname"/>
            <input type="email" name="email" required class="form-control" placeholder="email"/>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>

        </div>

    </form>
</div>
</body>
</html>

