<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "java2.domain.AnnouncementCategory" %>
<%@ page import = "java.util.List" %>

<html>
<head>
    <title>Select category for announcement</title>
     <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
<body>

<h1>Select category for announcement!</h1>

<% List<AnnouncementCategory> model = (List<AnnouncementCategory>) request.getAttribute("model"); %>
<% if(model != null) { %>
<% for(AnnouncementCategory category : model) { %>
<div><p>
<%= "<a href=\"announcementCreate?categoryId=" + category.getId() + "\">" %>
Create announcement in category "<%= category.getTitle().getTextEn() %>"</a>
(<%= category.getDescription().getTextEn() %>)
</br>
<%= "<a href=\"announcementPickCategory?categoryId=" + category.getId() + "\">" %>
Check subcategories</a>
</p></div>
<% } %>
<% } %>
<div>
<a href="javascript:history.back()">Go Back</a>
<div>
</body>
</html>