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
    </div>
    <form>
        <input type="text" name="title" required>
        <textarea rows="25" cols="100" name="description"></textarea>
    </form>
</body>
</html>