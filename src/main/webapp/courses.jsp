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
        <c:if test= "${user.permission}">
            <a href="courses?mode=new"><button>new course</button></a>
            <a href="assignment?mode=new"><button>new assignment</button></a>
        </c:if>
        <div class="container" id="courses">
            <ul class="list">
               <c:forEach var="course" items="${courses}">
                <li>
                    <c:out value="${course.name}"/>
                    <a href='courses?courseid=<c:out value="${course.id}"/>&mode=view'><button>view</button></a>
                    <c:if test= "${user.permission}">
                        <a href='courses?courseid=<c:out value="${course.id}"/>&mode=edit'><button>edit</button></a>
                        <a href='courses?courseid=<c:out value="${course.id}"/>&mode=delete'><button>delete</button></a>
                        <a href='courses?courseid=<c:out value="${course.id}"/>&mode=publish'><button>(un)publish</button></a>
                    </c:if>
                </li>
               </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>
