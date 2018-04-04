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
                        <c:choose>
                            <c:when test="${empty user}">
                                <li class="active"><a href="login">login</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="courses">courses</a></li>
                                <li><a href="users">users</a></li>
                                <li class="active"><a href="logout">logout</a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </nav>
            </header>
        </div>
        <div class="formcontainer">
        <c:choose>
            <c:when test="${empty user}">

                <h2>Welcome to the [placeholder] school ${user.name}</h2>
                <form action="login" method="POST">
                    <p>Log in</p>
                    <label>Account name:</label>
                    <input type="text" name="account">
                    <label>Password:</label>
                    <input type="password" name="pass">
                    <input type="submit" value="Log in">
                    <%
                        if (request.getAttribute("message") != null) {
                    %>
                        ${message}
                    <%
                        }
                    %>
                </form>
                <form action="register" method="POST">
                    <p>Or if you not registered yet you can do it now</p>
                    <p>Register:</p>
                    <label>Name:</label>
                    <input type="text" name="name" required>
                    <label>e-mail:</label>
                    <input type="text" name = "mail" required>
                    <label>password:</label>
                    <input type="password" name="password" required>
                    <input type="radio" name="permission" value="mentor">
                    <input type="radio" name="permission" value="student" checked>
                    <input type="submit" value="Register">
                    <%
                        if (request.getAttribute("result") != null) {
                    %>
                        ${result}
                    <%
                        }
                    %>
                </form>
                </c:when>
                <c:otherwise>
                    <p>you are logged in as ${user.name}</p>
                </c:otherwise>

        </c:choose>
        </div>
    </div>
</body>
</html>