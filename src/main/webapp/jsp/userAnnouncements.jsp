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
<% for(Announcement announcement : model) { %>
<div>
<h3><%= announcement.getTitle() %></h3>
<p><%= announcement.getDescription() %></p>
<%= "<a href=\"announcementEdit?id=" + announcement.getId() + "\">" %>
Edit</a>
<%= "<a href=\"announcementRemove?id=" + announcement.getId() + "\">" %>
Remove</a>
</div>
<% } %>
<% } %>
<div>
<a href="javascript:history.back()">Go Back</a>
<div>
</body>
</html>