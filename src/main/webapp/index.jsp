<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html ng-app="rootApp" ng-init="tweetnumber = ${tweetnumber}">
<head>
<title>lms</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://use.fontawesome.com/a1403016a7.js"></script>
</head>
<body ng-controller="siteController">
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
	<div class="container" id="news">
	
	</div>
</body>
</html>