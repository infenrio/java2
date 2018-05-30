<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "java2.domain.User" %>
<%@ page import = "java.util.List" %>

<html>
<head>
    <title>Users</title>
     <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
<body>

<h1>Users!</h1>

<% List<User> model = (List<User>) request.getAttribute("model"); %>
<% if(model != null) { %>
<table class="table table-stripped">
<thead>
<tr>
<th scope="col">Login</th>
<th scope="col">Name</th>
<th scope="col">Email</th>
<th scope="col">Status</th>
<th scope="col">Action</th>
</tr>
</thead>
<tbody>
<% for(User user : model) { %>
<tr>
<td><%= user.getLogin() %></td>
<td><%= user.getName() %></td>
<td><%= user.getEmail() %></td>
<td><%= user.getState().getTitle().getTextEn() %></td>
<td>
<%= "<a href=\"adminUsers?login=" + user.getLogin() + "\">" %>
Ban user</a>
</td>
</tr>
<% } %>
</tbody>
</table>
<% } %>
<div>
<a href="adminMain" class="btn btn-success" style="margin-top:5px;">Admin home</a>
<div>
</body>
</html>