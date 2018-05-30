<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "java2.businesslogic.announcementcreation.AnnouncementCreationResponse" %>
<%@ page import = "java2.businesslogic.ValidationError" %>
<%@ page import = "java.util.*" %>
<html>
<head>
    <title>Announcement creation</title>
     <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
<body>

<h1>Create announcement!</h1>

<% HashMap<String, Object> model = (HashMap<String, Object>) request.getAttribute("model"); %>
<% AnnouncementCreationResponse announcementResponse = (AnnouncementCreationResponse) model.get("response"); %>
<% if(announcementResponse != null) { %>
<% if(announcementResponse.isSuccess()) { %>
<div><p class = "text-success">Announcement created successfully!</p>
<a href="userAnnouncements">Check your announcements</a></div>
<% } else { %>
<% for(ValidationError error : announcementResponse.getErrors()) { %>
<div><p class = "text-danger">
<%=  error.getField() + " - " + error.getErrorMessage() %>
</p></div>
<% } %>
<% } %>
<% } %>

<div class="container">
<form class="form-horizontal" name="registrationForm" method="post" action="announcementCreate">
<fieldset>
</div>
<div class="control-group">
<label class="control-label"  for="category">Category:</label>
<div class="controls">
     <input class="input-xlarge" type="text" name="category" path="category" value="<%= model.get("categoryId") %>" readonly/>
</div>
</div>
<div class="control-group">
<label class="control-label"  for="title">Title:</label>
<div class="controls">
     <input class="input-xlarge" type="text" name="title" path="title"/>
</div>
</div>
<div class="control-group">
<label class="control-label"  for="description">Description:</label>
<div class="controls">
     <input class="input-xlarge" type="text" name="description" path="description"/>
</div>
</div>
<div class="control-group">
<div class="controls">
<input type="submit" value="Create announcement!" class="btn btn-primary" />
</div>
</div>
</fieldset>
</form>
</div>
<div>
<a href="userMain" class="btn btn-success" style="margin-top:5px;">User home</a>
<div>
</body>
</html>