<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>login</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://use.fontawesome.com/a1403016a7.js"></script>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="container" id="header">
		<header>
			<nav>
				<ul>
				    <li><a href="home">home</a></li>
				    <li><a href="courses">courses</a></li>
				    <li><a href="students">students</a></li>
				    <li><a href="login">login</a></li>
				</ul>
			</nav>
		</header>
	</div>
	<h2>Welcome to the [placeholder] school</h2>
    <div class="formcontainer">
        <form action="login" method="POST">
            <p>Log in</p>
            <label>Account name:</label>
            <input type="text" name="account">
            <label>Password:</label>
            <input type="password" name="pass">
            <input type="submit" value="Log in">
            ${message}
        </form>
        <form action="RegisterServlet" method="POST">
            <p>Or if you not registered yet you can do it now</p>
            <p>Register:</p>
            <label>Name:</label>
            <input type="text" name="account">
            <label>e-mail:</label>
            <input type="text" name = "e-mail">
            <label>password:</label>
            <input type="password" name="password">
            <input type="submit" value="Register">
        </form>
    </div>
</body>
</html>