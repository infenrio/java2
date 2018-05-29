<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "java2.businesslogic.announcementediting.AnnouncementEditingResponse" %>
<%@ page import = "java2.businesslogic.ValidationError" %>
<%@ page import = "java2.domain.Announcement" %>
<%@ page import = "java.util.*" %>
<html>
<head>
    <title>Announcement editing</title>
     <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
<body>

<h1>Edit announcement!</h1>

<% HashMap<String, Object> model = (HashMap<String, Object>) request.getAttribute("model"); %>
<% AnnouncementEditingResponse announcementResponse = (AnnouncementEditingResponse) model.get("response"); %>
<% if(announcementResponse != null) { %>
<% if(announcementResponse.isSuccess()) { %>
<div><p class = "text-success">Announcement edited successfully!</p>
<a href="userAnnouncements">Check your announcements</a>
</div>
<% } else { %>
<% for(ValidationError error : announcementResponse.getErrors()) { %>
<div><p class = "text-danger">
<%=  error.getField() + " - " + error.getErrorMessage() %>
</p></div>
<% } %>
<% } %>
<% } %>

<% Announcement announcement = (Announcement) model.get("announcement"); %>
<div class="container">
<form class="form-horizontal" name="registrationForm" method="post" action="announcementEdit">
<fieldset>
</div>
<div class="control-group">
<label class="control-label"  for="id">Id:</label>
<div class="controls">
     <input class="input-xlarge" type="text" name="id" path="id" value="<%= announcement.getId() %>" readonly/>
</div>
</div>
<div class="control-group">
<label class="control-label"  for="title">Title:</label>
<div class="controls">
     <input class="input-xlarge" type="text" name="title" path="title" value="<%= announcement.getTitle() %>"/>
</div>
</div>
<div class="control-group">
<label class="control-label"  for="description">Description:</label>
<div class="controls">
     <input class="input-xlarge" type="text" name="description" path="description" value="<%= announcement.getDescription() %>"/>
</div>
</div>
<div class="control-group">
<div class="controls">
<input type="submit" value="Edit announcement!" />
</div>
</div>
</fieldset>
</form>
</div>

</body>
</html>