<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "java2.businesslogic.login.LoginResponse" %>
<%@ page import = "java2.businesslogic.ValidationError" %>
<html>
<head>
    <title>Admin login page</title>
     <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
<body>

<h1>Admin login page!</h1>

<% LoginResponse model = (LoginResponse) request.getAttribute("model"); %>
<% if(model != null) { %>
<%  for(ValidationError error : model.getErrors()) { %>
<div><p class = "text-danger">
<%=  error.getField() + " - " + error.getErrorMessage() %>
</p></div>
<% } %>
<% } %>

<div class="container">
<form name="loginForm" method="post" action="adminLogin">
<div>Login:
     <input type="text" name="login" path="login" />
</div>
<div>Password:
     <input type="text" name="password" path="password"/>
</div>
<div>
<input type="submit" value="Login!" />
<input type="reset" />
</div>
</form>
</div>

</body>
</html>