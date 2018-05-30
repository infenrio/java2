<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "java2.domain.Announcement" %>
<%@ page import = "java.util.List" %>

<html>
<head>
    <title>Announcements of user</title>
     <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
<body>

<h1>Announcements of user!</h1>

<% List<Announcement> model = (List<Announcement>) request.getAttribute("model"); %>
<% if(model != null) { %>
<table class="table table-stripped">
<thead>
<tr>
<th scope="col">Category</th>
<th scope="col">Title</th>
<th scope="col">Description</th>
<th scope="col">Edit</th>
<th scope="col">Remove</th>
</tr>
</thead>
<tbody>
<% for(Announcement announcement : model) { %>
<tr>
<td><%= announcement.getCategory().getTitle().getTextEn() %></td>
<td><%= announcement.getTitle() %></td>
<td><%= announcement.getDescription() %></td>
<td><%= "<a class=\"btn btn-warning\" href=\"announcementEdit?id=" + announcement.getId() + "\">" %>
Edit</a></td>
<td><%= "<a class=\"btn btn-danger\" href=\"announcementRemove?id=" + announcement.getId() + "\">" %>
Remove</a></td>
</tr>
<% } %>
</tbody>
</table>
<% } %>
<div>
<a href="userMain" class="btn btn-success" style="margin-top:5px;">User home</a>
<div>
</body>
</html>