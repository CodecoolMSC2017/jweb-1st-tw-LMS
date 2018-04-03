<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>lms</title>
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
                        <li class="active"><a href="home">home</a></li>
                        <li><a href="courses">courses</a></li>
                        <li><a href="users">users</a></li>
                        <c:if test="${empty user}">
                            <li><a href="login">login</a></li>
                        <li><a href="logout">logout</a></li>
                        </c:if>
                    </ul>
                </nav>
            </header>
        </div>
        <div class="container" id="news">
            <h1>NEWS</h1>
            <img src="puruttya.jpg">
            <p>Welcome to Puruttya WebTeacher</p>
            <c:if test="${not empty user}">

                <p>Aggmá egy cigit "${userName}"</p>
            </c:if>
            <p>Here you can find trick and tips to be the best scriptkiddie in town. Download Kali linux use its tools. Hack your girlfriends facebook. And so much more.</p>
        </div>
    </div>
</body>
</html>
