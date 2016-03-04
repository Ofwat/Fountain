<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/xml; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" %>   

<%@page import="java.io.StringWriter,java.io.PrintWriter"%>
<%  //System.out.println(" \n\n\n\n\n\n>>>>>>>>>>>>>>>> XXXXXXXX <<<<<<<<<<<<<<<<<<<<<< processing error");
   
    response.setStatus(500);
    %>
<error>
	<type></type>
	<message></message>
	<trace><%
	out.println("<![CDATA[");
	StringWriter sw = new StringWriter();
	PrintWriter pw = new PrintWriter(sw);
	if (exception!=null) {
		exception.printStackTrace(pw);
	}
	out.print(sw);
	sw.close();
	pw.close();
	out.println("]]>");
	%>
	</trace>
	<%
	boolean handled = false;
	ErrorData ed = null;
	try{
		ed = pageContext.getErrorData();
	}catch(NullPointerException e){
		
	}
	if(ed != null){
        // Output this part in HTML just for fun
        %>
        	<errorData>
        <%

        // Output details about the HTTP error
        // (this should show error code 404, and the name of the missing page)
        out.println("ErrorCode: " + ed.getStatusCode() + "\n");
        out.println("URL: " + ed.getRequestURI() + "\n");
        // Error handled successfully, set a flag
        handled = true;		
	}	
	%>
	</errorData>
</error>