<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP login</title>
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
              href="/webjars/bootstrap/4.1.0/css/bootstrap.min.css" />
    </head>
<body>

<h1>Login page!</h1>

<h2><%= request.getAttribute("model") %></h2>



<form name="loginForm" method="post" action="login">
<table>
   <tr>
     <td>Login:</td>
     <td><input type="text" name="login" path="login" /></td>
     <td><form:errors path="login" cssClass="red" /></td>
   </tr>
   <tr>
     <td>Password:</td>
     <td><input type="text" name="password" path="password"/></td>
     <td><form:errors path="password" cssClass="red" /></td>
   </tr>
</table>
<input type="submit" value="Login!" />
<input type="reset" />
</form>

<div class="container"><br/>
            <div class="alert alert-success">
                <a href="#" class="close" data-dismiss="alert"
                  aria-label="close">×</a>
                <strong>Success!</strong> It is working as we expected.
            </div>
        </div>

</body>
</html>