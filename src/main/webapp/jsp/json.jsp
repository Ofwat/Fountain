



<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
        <title>Reservoir Share prototype</title>
        <link type="text/css" rel="stylesheet" href="./tag.css"/>
    </head>
    <body onload="loadXMLDoc('rest-services/model', parseModels)">
    <jsp:include page="banner.jsp" />
    
    
<style type="text/css">
<!--
div.scroll {
height: 400px;
width: 600px;
overflow: scroll;
border: 1px solid #999;
background-color: #eee;
padding: 8px;
float:none;
}
-->
</style>

         <script type="text/javascript">
	var xmlhttp;
	
	function loadXMLDoc(url, handler)
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
			  xmlhttp.onreadystatechange=handler;
			  xmlhttp.open("GET",url,true);
			  xmlhttp.setRequestHeader("accept", "application/json");
			  xmlhttp.send(null);
		  }
		else
		  {
		  	alert("Your browser does not support XMLHTTP.");
		  }
	}

	function parseModels()
	{
		var txt;
		if(xmlhttp.readyState!=4) return;
		if(xmlhttp.status!=200)
		  {
		 	 alert("Problem retrieving XML data");
		  	return;
		  }
		
		txt="<table border='1'>";
		var modelArray = JSON.parse(xmlhttp.responseText);
		
		for (i=0;i<modelArray.length;i++)
		  {
			  txt=txt + "<tr>";			  
		    {
		    try
		      {
		      	txt=txt + "<td><button onClick='loadXMLDoc(\"" + modelArray[i].uri + "\" , parseTables)'/>" + modelArray[i].name + "</td>";
		      }
		    catch (er)
		      {
			      alert(er);
		     	txt=txt + "<td> " + er +"</td>";
		      }
		    }
		  txt=txt + "</tr>";
		  }
	
		txt=txt + "</table>";
		document.getElementById('models').innerHTML=txt;
		
	}

	function parseTables(){

		var txt;
		if(xmlhttp.readyState!=4) return;
		if(xmlhttp.status!=200)
		  {
		 	 alert("Problem retrieving XML data");
		  	return;
		  }
		
		txt="<table border='1'>";

		var model = JSON.parse(xmlhttp.responseText);
		
			  txt=txt + "<tr>";
			  varName=model.name;
			  varTableLinks = model.tableLinks
			  varCode=model.code;

			  try
		      {

		      	txt=txt + "<td>" + varCode + "</td>";
		      	txt=txt + "<td>" + varName + "</td>";
		      }
		      catch (er)
		      {
			      alert(er);
		     	 txt=txt + "<td> " + er +"</td>";
		      }
		    txt=txt + "</tr>";
		    for(j=0; j<varTableLinks.length;j++){
		    	txt=txt + "<tr>";
		    	varTableName = varTableLinks[j].name;
		    	varTableUri = varTableLinks[j].uri;
		    	try
			      {
			      	txt=txt + "<td>" + varTableName + "</td>";
			      	txt=txt + "<td><a href='javascript:loadXMLDoc(\"" +  varTableUri + "\", parseDataTable)'>  click to open </a></td>";
			      }
			      catch (er)
			      {
				      alert(er);
			     	 txt=txt + "<td> " + er +"</td>";
			      }
			      txt=txt + "</tr>";
		    }

		txt=txt + "</table>";
		document.getElementById('tables').innerHTML=txt;		
	}

	function parseDataTable(){
		var txt;
		if(xmlhttp.readyState!=4) return;
		if(xmlhttp.status!=200)
		  {
		 	 alert("Problem retrieving XML data");
		  	return;
		  }
		txt="<table border='1'>";
		txt = txt+"<tr><td> Item </td> <td> Year </td><td> Value </td></tr>";

		varCollection = JSON.parse(xmlhttp.responseText);


		for(j=0; j<varCollection.length;j++){
	    	txt=txt + "<tr>";
	    	varItemName = varCollection[j].item.name;
	    	varYearName = varCollection[j].year.name; 
	    	varValue = varCollection[j].value;

	    	try
		      {
		      	txt=txt + "<td>" + varItemName + "</td>";
		      	txt=txt + "<td>" + varYearName + "</td>";
		      	txt=txt + "<td>"+ varValue  +"</td>";
		      }
		      catch (er)
		      {
			      alert(er);
		     	 txt=txt + "<td> " + er +"</td>";
		      }
		      txt=txt + "</tr>";
	    }

		txt=txt + "</table>";
		document.getElementById('data').innerHTML=txt;		
		return false;
		
	}
	
</script>   

  
        
        <br/><br/>
        
        
        <!-- master table for main body layout -->
        
        
<table border='2'>
<tr><td>


        
        <div id="models">
	
</div>

</td>
<td rowspan='2'>



<div  id="data" class="scroll">


</div>

</td>
</tr>
<tr>
<td>


<br/>
<div id="tables">
</div>


</td>
</tr>
</table>
        <jsp:include page="footer.jsp" />
        
    </body>
</html>
