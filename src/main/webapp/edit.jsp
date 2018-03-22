<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>student</title>
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
            <div class="editormenu">
                <button onlick="document.execCommand('bold');"><i class="fa fa-bold"></i>
                <button onlick="document.execCommand('italic');"><i class="fa fa-italic"></i>
                <button onlick="document.execCommand('insertParagraph');"><i class="fa fa-paragraph"></i>
                <button onlick="document.execCommand('justifyCenter');"><i class="fa fa-justify"></i>
            </div>
            <form method="POST" action="newcourse">
                <input type="text" name="title" required>
                <textarea rows="15" cols="50" name="description"></textarea>
                <button type="submit">Create</button></td>
            </form>
        </div>
    </div>
</body>
</html>