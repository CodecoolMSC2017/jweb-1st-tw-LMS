<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>logout</title>
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
                        <li><a href="users">users</a></li>
                        <%
                        if(userName == null) {
                        %>
                        <li class="active"><a href="login">login</a></li>
                        <%
                        } else {
                        %>
                        <li class="active"><a href="logout">logout</a></li>
                        <%
                        }
                        %>
                    </ul>
                </nav>
            </header>
        </div>
        <%
        if(userName != null) {
        %>
        <div class="formcontainer">
            <form action="logout" method="POST">
                <p>Log out</p>
                <input type="submit" value="Log out">
                <%
                    if (request.getAttribute("message") != null) {
                %>
                    ${message}
                <%
                    }
                %>
            </form>
            <%
            } else {
            %>
                <p>you are not logged in</p>
            <%
            }
            %>
        </div>
    </div>
</body>
</html>