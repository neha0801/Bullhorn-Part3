<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bullhorn</title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<style>
body {
	font-family: "Bookman Old Style";
	color: black;
	background-color: #a6d2d2;
}

h1 {
	font-family: Edwardian Script ITC;
	font-size: 60px;
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
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="ServletViewPosts">View Posts</a></li>
					<li><a href="ServletProfile">${user_name}</a></li>
					<li><a href="LoginForm?logout=yes">Log out</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<form action="ServletBullhorn" method="post">
			<br> <br> <label style="font-size: 20px">Say
				something interesting:</label><br></br>
			<textarea rows='4' cols='50' name='post'></textarea>
			<br></br> <br> <input class="btn btn-primary" type='submit'
				value='Submit'></input><br> <br>
		</form>
		<form action="ServletSearchPost" method="post">
			<br> <br> <label style="font-size: 20px">Search Post</label><br></br>
			<input type="text" name='search'></input>
			<br></br> <br> <input class="btn btn-primary" type='submit'
				value='Search'></input><br> <br>
		</form>

	</div>
	${message}
</body>
</html>