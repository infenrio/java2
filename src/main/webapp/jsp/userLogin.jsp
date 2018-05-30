<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "java2.businesslogic.login.LoginResponse" %>
<%@ page import = "java2.businesslogic.ValidationError" %>
<html>
<head>
    <title>User login page</title>
     <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
<body>

<h1>User login page!</h1>

<% LoginResponse model = (LoginResponse) request.getAttribute("model"); %>
<% if(model != null) { %>
<%  for(ValidationError error : model.getErrors()) { %>
<div><p class = "text-danger">
<%=  error.getField() + " - " + error.getErrorMessage() %>
</p></div>
<% } %>
<% } %>

<div class="container">
<form class="form-horizontal" name="loginForm" method="post" action="userLogin">
<fieldset>
<div class="control-group">
<label class="control-label"  for="login">Login:</label>
<div class="controls">
     <input class="input-xlarge" type="text" name="login" path="login" />
</div>
</div>
<div class="control-group">
<label class="control-label"  for="password">Password:</label>
<div class="controls">
     <input class="input-xlarge" type="text" name="password" path="password"/>
</div>
</div>
<div class="control-group">
<div class="controls">
<input type="submit" value="Login!" class="btn btn-primary" />
</div>
</div>
</fieldset>
</form>
</div>
<div>
<a href="/java2" class="btn btn-success" style="margin-top:5px;">Home page</a>
<div>
</body>
</html>