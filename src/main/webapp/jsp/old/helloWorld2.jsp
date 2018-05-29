<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP page</title>
    <link rel='stylesheet' href='webjars/bootstrap/4.1.0/css/bootstrap.min.css'>
</head>
<body>

<h1>Hello from JSP file!</h1>

<h2><%= request.getAttribute("model") %></h2>

<h3>${model.role}</h3>
<h3>${model.userId}</h3>

</body>
</html>