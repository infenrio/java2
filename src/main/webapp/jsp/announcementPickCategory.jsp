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
<table class="table table-stripped">
<thead>
<tr>
<th scope="col">Title</th>
<th scope="col">Description</th>
<th scope="col">Vīrsraksts</th>
<th scope="col">Apraksts</th>
<th scope="col">Заголовок</th>
<th scope="col">Описание</th>
<th scope="col">Check subcategories</th>
<th scope="col">Create announcement</th>
</tr>
</thead>
<tbody>
<% for(AnnouncementCategory category : model) { %>
<tr>
<td><%= category.getTitle().getTextEn() %></td>
<td><%= category.getDescription().getTextEn() %></td>
<td><%= category.getTitle().getTextLv() %></td>
<td><%= category.getDescription().getTextLv() %></td>
<td><%= category.getTitle().getTextRu() %></td>
<td><%= category.getDescription().getTextRu() %></td>
<td><%= "<a class=\"btn btn-info\" href=\"announcementPickCategory?categoryId=" + category.getId() + "\">" %>
Check subcategories</a></td>
<td><%= "<a class=\"btn btn-success\" href=\"announcementCreate?categoryId=" + category.getId() + "\">" %>
Create announcement</a></td>
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