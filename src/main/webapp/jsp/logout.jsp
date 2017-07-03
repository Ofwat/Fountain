<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
        <title>Fountain</title>
        <link type="text/css" rel="stylesheet" href="/Fountain/css/layout.css" />
    </head>
    <body>
    
    <% 
    session.invalidate();
    Cookie[] cookies = request.getCookies();
    Cookie sessionCookie = null;
	for(Cookie cookie : cookies){
		if(cookie.getName().equalsIgnoreCase("JSESSIONID")){
			sessionCookie = cookie;
			break;
		}
	}
	if(null != sessionCookie){
		sessionCookie.setMaxAge(0);
		response.addCookie(sessionCookie);
	}
%>
<jsp:include page="banner.jsp" />
<p class="logout">
Thank you for using Reservoir Share. 
<br/>
you can log back in <a href="/Fountain/jsp/protected/index.jsp">here</a>
</p>
    
    
        <jsp:include page="footer.jsp" />

        
    </body>
</html>