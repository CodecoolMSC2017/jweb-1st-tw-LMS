<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>user</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://use.fontawesome.com/a1403016a7.js"></script>
<link href="puruttya.css" rel="stylesheet" type="text/css">
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
                        <li><img src="logo.png"></li>
                        <li><a href="home">home</a></li>
                        <li><a href="courses">courses</a></li>
                        <li class="active"><a href="users">users</a></li>
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
        <div class="container" id="user">
            <ul class="user">
                <c:set var = "temp" scope = "session" value = "${user}"/>
                <li>id: <c:out value="${temp.id}"/></li>
                <li>name: <c:out value="${temp.name}"/></li>
                <li>email: <c:out value="${temp.email}"/></li>
                <c:set var = "role" scope = "session" value = "${user.permission}"/>
                <c:choose>
                <c:when test="${role != 'false'}">
                <li>role: mentor</li>
                </c:when>
                <c:otherwise>
                <li>role: student</li>
                </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</body>
</html>