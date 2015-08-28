<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/additional-methods.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/additional-methods.min.js"></script>
<title>User Profile</title>
<style>
body {
	font-family: "Bookman Old Style";
	color: black;
	background-color: #a6d2d2;
	font-size: 15px;
}
h1{
	font-family: Edwardian Script ITC;;
	font-size: 70px;
}
</style>
</head>
<body>
	<nav class="navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" style="color: red">BULLHORN BLOG</a>
			</div>
			    <div>
			    <% String guest = (String) session.getAttribute("guestUser");
			    if(guest==null)
					guest="no";
			      if(!guest.equalsIgnoreCase("yes")){%>			    
				      <ul class="nav navbar-nav">
				        <li ><a href="UserAccount.jsp">Home</a></li>
				        <li><a href="ServletViewPosts">View Posts</a></li>
				        <li class="active"><a href="ServletProfile">${user_name}</a></li>		        
				        <li><a href="LoginForm?logout=yes">Log out</a></li>
				      </ul>
				<%}%>
		    </div>
		</div>
	</nav>
	${userData}
		<%  guest = (String) session.getAttribute("guestUser");
		if(guest==null)
			guest="no";
		if(guest.equalsIgnoreCase("yes")){%>	
			    <a href="ServletViewPosts?guestUser=yes" class="btn btn-danger">Go back</a> <br><br>
			    <%}else%>
			     <a href="ServletViewPosts?guestUser=no" class="btn btn-danger">Go back</a> <br><br>
</body>
</html>