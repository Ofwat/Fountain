<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>Fountain: Login</title>
	<link rel="stylesheet" type="text/css" href="/Fountain/css/reset-fonts-grids.css"/>
	<link type="text/css" rel="stylesheet" href="/Fountain/css/layout.css" />
</head>
<body>
<jsp:include page="banner.jsp" />
<div class="left">
<form method="POST" action="j_security_check">
	<input type="text" name="j_username">
	<input type="password" name="j_password">
	<button type="submit" value="Log in">Log in</button>
	<a href="http://resdev01:8008/fountainwiki/index.php/Main_Page" target="_blank"><img src="../../images/help.png" title="Help"></img></a>
</form>
</div>
        <jsp:include page="footer.jsp" />

</body>
</html>