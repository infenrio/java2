<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "java2.domain.Announcement" %>
<%@ page import = "java.util.List" %>

<html>
<head>
    <title>Announcements</title>
     <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
<body>

<h1>Announcements!</h1>

<% List<Announcement> model = (List<Announcement>) request.getAttribute("model"); %>
<% if(model != null) { %>
<table class="table table-stripped">
<thead>
<tr>
<th scope="col">Creator</th>
<th scope="col">Title</th>
<th scope="col">Description</th>
<th scope="col">E-mail</th>
</tr>
</thead>
<tbody>
<% for(Announcement announcement : model) { %>
<tr>
<td><%= announcement.getCreator().getName() %></td>
<td><%= announcement.getTitle() %></td>
<td><%= announcement.getDescription() %></td>
<td>
<%= "<a href=\"mailto:" + announcement.getCreator().getEmail() + "\">" %>
<%= announcement.getCreator().getEmail() %>
</a></td>
</tr>
<% } %>
</tbody>
</table>
<% } %>
<div>
<a href="announcementCategories?categoryId=0" class="btn btn-success" style="margin-top:5px;">Go Back</a>
<div>
</body>
</html>