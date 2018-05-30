<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "java2.businesslogic.login.LoginResponse" %>
<%@ page import = "java2.businesslogic.ValidationError" %>
<html>
<head>
    <title>Admin menu page</title>
     <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
<body>

<h1>Welcome admin!</h1>

<div>
<a href="adminAnnouncements">Manage announcements</a>
</div>
<div>
<a href="adminUsers">Manage users</a>
</div>
<div>
<a href="logout">Logout</a>
</div>

</body>
</html>

