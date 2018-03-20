<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome!</title>
</head>
<body>

<h2>Welcome to the [placeholder] school</h2>

<p>Log in</p>
<form action="login" method="POST">
    Account name:<br>
    <input type="text" name="account">
    <br>
    Password:<br>
    <input type="password" name="pass">
    <br><br>
    <input type="submit" value="Log in">
    <%${message}%>
</form>

<p>Or if you not registered yet you can do it now</p>
<p>Register:</p>
<form action="RegisterServlet" method="POST">
    Name:<br>
    <input type="text" name="account">
    <br>
    e-mail:<br>
    <input type="text" name = "e-mail">
    <br>
    password:<br>
    <input type="password" name="password">
    <br><br>
    <input type="submit" value="Register">
</form>

</body>
</html>