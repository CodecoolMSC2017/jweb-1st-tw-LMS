<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>course</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://use.fontawesome.com/a1403016a7.js"></script>
<link href="puruttya.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div class="wrapper">
        <div class="container" id="header">
            <header>
                <nav>
                    <ul>
                        <li><img src="logo.png"></li>
                        <li><a href="home">home</a></li>
                        <li class="active"><a href="courses">courses</a></li>
                        <li><a href="users">users</a></li>
                        <li><a href="logout">logout</a></li>
                    </ul>
                </nav>
            </header>
        </div>
        <div class="container">
            <ul class="list">
                <h2>Task</h2>
                <c:set var = "temp" scope = "session" value = "${course}"/>
                <li>Task name: <c:out value="${temp.name}"/></li>
                <li>Task description: <c:out value="${temp.desc}"/></li>
            </ul>
        </div>
    </div>
</body>
</html>