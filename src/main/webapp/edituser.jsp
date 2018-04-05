<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>edit</title>
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
                        <li><a href="courses">courses</a></li>
                        <li class="active"><a href="users">users</a></li>
                        <li><a href="logout">logout</a></li>
                    </ul>
                </nav>
            </header>
        </div>
        <div class="container" id="user">
            <div class="editform">
                <form method="POST" action="edituser">
                    <h2>edit user</h2>
                    <input type="text" name="e-mail" value ="<c:out value="${user.email}"/>" required>
                    <input type="text" name="password" value ="<c:out value="${user.password}"/>" required>
                    <c:if test = "${user.permission == 'true'}">
                        <p>Role:
                        <select name="role" id="role">
                            <option value="student">Student</option>
                            <option value="mentor">Mentor</option>
                        </select><br></p>
                    </c:if>
                    <button class="button" type="submit" value="Save">Submit</button>
                </form>
            </ul>
        </div>
    </div>
</body>
</html>
