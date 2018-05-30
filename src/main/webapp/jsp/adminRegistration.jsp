<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "java2.businesslogic.registration.RegistrationResponse" %>
<%@ page import = "java2.businesslogic.ValidationError" %>
<html>
<head>
    <title>Admin registration page</title>
     <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
<body>

<h1>Admin registration page!</h1>

<% RegistrationResponse model = (RegistrationResponse) request.getAttribute("model"); %>
<% if(model != null) { %>
<% if(model.isSuccess()) { %>
<div><p class = "text-success">Registration is successful!</p></div>
<% } else { %>
<% for(ValidationError error : model.getErrors()) { %>
<div><p class = "text-danger">
<%=  error.getField() + " - " + error.getErrorMessage() %>
</p></div>
<% } %>
<% } %>
<% } %>

<div class="container">
<form class="form-horizontal" name="registrationForm" method="post" action="adminRegistration">
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
<label class="control-label"  for="name">Name:</label>
<div class="controls">
     <input class="input-xlarge" type="text" name="name" path="name"/>
</div>
</div>
<div class="control-group">
<label class="control-label"  for="email">E-mail:</label>
<div class="controls">
     <input class="input-xlarge" type="text" name="email" path="email"/>
</div>
</div>
<div class="control-group">
<div class="controls">
<input type="submit" value="Register!" />
</div>
</div>
</fieldset>
</form>
</div>

</body>
</html>