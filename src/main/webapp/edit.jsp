<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>edit course - lms</title>
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
            <div class="editform">
                <c:set var = "m" scope = "session" value = "${mode}"/>
                <c:choose>
                <c:when test="${m.equals('new')}">
                <form method="POST" action="newcourse">
                    <h2>newcourse</h2>
                    <input type="text" name="title" value="course name"required>
                    <textarea rows="15" cols="50" name="description">course description</textarea>
                    <button type="submit">save</button>
                </form>
                </c:when>
                <c:otherwise>
                <form method="POST" action="savecourse">
                    <h2>edit course</h2>
                    <c:set var = "temp" scope = "session" value = "${course}"/>
                    <input type="text" name="title" value ="<c:out value="${temp.name}"/>" required>
                    <textarea rows="15" cols="50" name="description"><c:out value="${temp.desc}"/></textarea>
                    <c:set var="id" value="${course.id}" scope="session"/>
                    <c:set var="act" value="${course.active}" scope="session"/>
                    <button type="submit">save</button>
                </form>
                </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</body>
</html>