
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
        <title>Reservoir Share prototype</title>
        <link type="text/css" rel="stylesheet" href="./tag.css"/>
    </head>
    <body>
    <jsp:include page="banner.jsp" />
    

    

  
        
        <br/><br/>
        
        <script type="text/javascript">
	var xmlhttp;
	
	function loadXMLDoc(url)
	{
		xmlhttp=null;
		if (window.XMLHttpRequest)
		  {// code for IE7, Firefox, Mozilla, etc.
		 	 xmlhttp=new XMLHttpRequest();
		  }
		else if (window.ActiveXObject)
		  {// code for IE5, IE6
		 	 xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		if (xmlhttp!=null)
		  {
			  xmlhttp.onreadystatechange=onResponse;
			  xmlhttp.open("GET",url,true);
			  xmlhttp.send(null);
		  }
		else
		  {
		  	alert("Your browser does not support XMLHTTP.");
		  }
	}
        
        
	function onResponse()
	{
		var txt;
		if(xmlhttp.readyState!=4) return;
		if(xmlhttp.status!=200)
		  {
		 	 alert("Problem retrieving XML data");
		  	return;
		  }
		
		txt="<table border='1'>";
		x=xmlhttp.responseXML.documentElement.getElementsByTagName("company");
		for (i=0;i<x.length;i++)
		  {
			  txt=txt + "<tr>";
			  xx=x[i].getElementsByTagName("name");
		    {
		    try
		      {
		      	txt=txt + "<td>" + xx[0].firstChild.nodeValue + "</td>";
		      }
		    catch (er)
		      {
		     	txt=txt + "<td> </td>";
		      }
		    }
		  	xx=x[i].getElementsByTagName("code");
		    {
		    try
		      {
		      	txt=txt + "<td>" + xx[0].firstChild.nodeValue + "</td>";
		      }
		    catch (er)
		      {
		      	txt=txt + "<td> </td>";
		      }
		    }
		  txt=txt + "</tr>";
		  }
		  
		  x=xmlhttp.responseXML.documentElement.getElementsByTagName("data");
		  for (i=0;i<x.length;i++)
		  {	  
		  	txt=txt + "<tr>";
		  	
		  	 xx=x[i].getElementsByTagName("id");

	 	    try
	 	      {
	 	      txt=txt + "<td>" + xx[0].firstChild.nodeValue + "</td>";
	 	      }
	 	    catch (er)
	 	      {
	 	      txt=txt + "<td> </td>";
	 	      }
         }
         
          xx=x[i].getElementsByTagName("value");
	 	{
	 	    try
	 	      {
	 	      txt=txt + "<td>" + xx[0].firstChild.nodeValue + "</td>";
	 	      }
	 	    catch (er)
	 	      {
	 	      txt=txt + "<td> </td>";
	 	      }
         }
         txt=txt + "</tr>";
		  
	
		txt=txt + "</table>";
		document.getElementById('companies').innerHTML=txt;
	}

</script>
        
        <div id="companies">
	<button onclick="loadXMLDoc('rest-services/table/1')">Company Details</button>
</div>
        
        <jsp:include page="footer.jsp" />
        
    </body>
</html>