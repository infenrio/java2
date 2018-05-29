<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "java2.domain.AnnouncementCategory" %>
<%@ page import = "java.util.List" %>

<html>
<head>
    <title>User registration page</title>
     <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
<body>

<h1>Announcement categories!</h1>

<% List<AnnouncementCategory> model = (List<AnnouncementCategory>) request.getAttribute("model"); %>
<% if(model != null) { %>
<% for(AnnouncementCategory category : model) { %>
<div><p>
<%= "<a href=\"announcementCategories?categoryId=" + category.getId() + "\">" %>
<%= category.getTitle().getTextEn() %></a>
(<%= category.getDescription().getTextEn() %>)
<%= "<a href=\"announcementList?categoryId=" + category.getId() + "\">" %>
Show announcements of category</a>
</p></div>
<% } %>
<% } %>
<div>
<a href="javascript:history.back()">Go Back</a>
<div>
</body>
</html>