<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>courses - lms</title>
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
                        <li class="active"><a href="courses">courses</a></li>
                        <li><a href="users">users</a></li>
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
        <a href="courses?mode=new"><button>add new</button></a>
        <div class="container" id="courses">
            <ul class="list">
               <c:forEach var="course" items="${courses}">
                <li>
                    <c:out value="${course.name}"/>
                    <a href='courses?courseid=<c:out value="${course.id}"/>&mode=view'><button>view</button></a>
                    <a href='courses?courseid=<c:out value="${course.id}"/>&mode=edit'><button>edit</button></a>
                    <a href='courses?courseid=<c:out value="${course.id}"/>&mode=delete'><button>delete</button></a>
                </li>
               </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>