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
<th scope="col">Login</th>
<th scope="col">Title</th>
<th scope="col">Description</th>
<th scope="col">Status</th>
<th scope="col">Action</th>
</tr>
</thead>
<tbody>
<% for(Announcement announcement : model) { %>
<tr>
<td><%= announcement.getCreator().getLogin() %></td>
<td><%= announcement.getTitle() %></td>
<td><%= announcement.getDescription() %></td>
<td><%= announcement.getState().getTitle().getTextEn() %></td>
<td>
<%= "<a href=\"adminAnnouncements?login=" + announcement.getCreator().getLogin() + "&id=" + announcement.getId() + "\">" %>
Ban announcement</a>
</td>
</tr>
<% } %>
</tbody>
</table>
<% } %>
<div>
<a href="javascript:history.back()">Go Back</a>
<div>
</body>
</html>