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
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
String userName = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
    for(Cookie cookie : cookies){
        if(cookie.getName().equals("uname")) userName = cookie.getValue();
    }
}
%>
    <div class="wrapper">
        <div class="container" id="header">
            <header>
                <nav>
                    <ul>
                        <li><a href="home">home</a></li>
                        <li class="active"><a href="courses">courses</a></li>
                        <li><a href="students">students</a></li>
                        <%
                        if(userName == null) {
                        %>
                        <li><a href="login">login</a></li>
                        <%
                        } else {
                        %>
                        <li><a href="logout">logout</a></li>
                        <%
                        }
                        %>
                    </ul>
                </nav>
            </header>
        </div>
        <div class="container">
            <ul class="list">
                <h2>course</h2>
                <c:set var = "temp" scope = "session" value = "${course}"/>
                <li>Course name: <c:out value="${temp.name}"/></li>
                <li>Course description: <c:out value="${temp.desc}"/></li>
            </ul>
            <ul class="list">
                <h2>tasks</h2>
                <c:forEach var="task" items="${tasks}">
                <li>Task name: <c:out value="${task.title}"/></li>
                <li>Task description: <c:out value="${task.content}"/></li>
                </c:forEach>
             </ul>

        </div>
    </div>
</body>
</html>