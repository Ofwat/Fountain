<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@page pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp"%>
<%@ taglib prefix="ofwat" uri="http://www.ofwat.gov.uk" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>

<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1; IE=8"/> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<!-- CSS Reset sheet, minified version, written by yahoo -->
		<link rel="stylesheet" type="text/css" href="/Fountain/css/reset-fonts-grids.css"/>
		<!-- 960.gs grid sheet -->
		<link rel="stylesheet" type="text/css" href="/Fountain/css/960grid.css"/>
		<link rel="stylesheet" type="text/css" href="/Fountain/css/layout.css"/>
	    <link type="text/css" rel="stylesheet" href="/Fountain/css/adhocReportLayout.css" media="screen"/>
		<!-- <link type="text/css" rel="stylesheet" href="/Fountain/css/bubble.css" media="screen"/> -->
		<link rel="stylesheet" href="<spring:theme code='colourscheme'/>" type="text/css" media="screen"/>
		<link type="text/css" rel="stylesheet" href="/Fountain/css/bootstrap.css" media="screen"/>
		<link type="text/css" rel="stylesheet" href="/Fountain/css/bootstrap-filterable.css" media="screen"/>
		<link type="text/css" rel="stylesheet" href="/Fountain/css/bootstrap-tagsinput.css" media="screen"/>
		<link href="/Fountain/css/pnotify.custom.min.css" media="all" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="/Fountain/css/font-awesome.css"  type="text/css"  media="all"/>
		<link rel="icon" type="image/gif" href="/Fountain/images/icon.gif"/>
		<style>
		

/*************************************
 * generic styling for ALS elements
 ************************************/

.als-container {
	position: relative;
	width: 100%;
	margin: 0px auto;
	z-index: 0;
}

.als-viewport {
	position: relative;
	overflow: hidden;
	margin: 0px auto;
}

.als-wrapper {
	position: relative;
	list-style: none;
}

.als-item {
	position: relative;
	display: block;
	text-align: center;
	cursor: pointer;
	float: left;
}

.als-prev, .als-next {
	position: absolute;
	cursor: pointer;
	clear: both;
}		
		
		
#demo3 {
	margin: 40px auto;
}

#demo3 .als-item {
	margin: 0px 5px;
	padding: 4px 0px;
	min-height: 120px;
	min-width: 100px;
	text-align: center;
}

#demo3 .als-item img {
	display: block;
	margin: 0 auto;
	vertical-align: middle;
}

#demo3 .als-prev, #demo3 .als-next {
	top: 40px;
}

#demo3 .als-prev {
	left: 200px;
}

#demo3 .als-next {
	right: 200px;
}		
		
		
		
		
			.fountainBox{
				/*border-top-left-radius: 10em;*/
				/*border-bottom-right-radius: 5em;*/
				border-bottom-left-radius: 1em;		
				border-top:5px;
				border-color: #000000;
				border-top: 4px solid;
				min-height: 250px !important;
				max-height: 250px !important;
				color: white;
				padding-left:16px;
				padding-right:16px;
				margin-bottom:20px;		
			}
		
			.blue{
				background-color: #409AD7;
				border-color: #40679b;
			}
			
			.green{
				background-color: #95B040;
				border-color: #719500;
			}
			
			.yellow{
				background-color: #F7BF40;
				border-color: #F4AA00;
			}

			.pink{
				background-color: #D740A2;
				border-color: #CA0083;
			}			
		
			resultsContainer {
				overflow-x: hidden;
			}
			html {
 			   overflow-y: scroll;
			}
						
			html.busy, html.busy * {
			  cursor: wait !important;
			}			
		
			.mostPopularHeader{
				background-color: #f9f9f9
			}
		
			/* div container containing the form  */
			#searchContainer {
				margin-left: auto;
    			margin-right: auto;
    			width: 60%;
				/*margin:20px;*/
			}
			

			#imageContainer{
				margin-right:auto;
				margin-left:auto;
				width: 332px;
			}
			#paginationContainer{
				display:none;
			}
			/* Clear floats */
			.fclear {clear:both}		
			

			.gsc-result-info {
			    text-align: left;	
			    color: #999;	
			    font-size: 12px;	
			    padding-left: 8px;	
			    margin: 10px 0 10px 0;	
			}
			.gs-result-title{
				text-decoration: underline;
			}
			.gs-result{
				padding-bottom: 1em;
			}
			.gs-result .gs-title, .gs-result .gs-title *{
			    color: #3083A3;
			    text-decoration: none;
			    /*font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;*/
			}
			.gs-result a.gs-visibleUrl, .gs-result .gs-visibleUrl {
			    color: #0052FF;
			    text-decoration: none;
			}
			.gs-result .gs-snippet {
			    font: 12px Tahoma, Geneva, sans-serif;
			}
			ul.gsc-results{
				list-style-type: none;
			}
			.gsc-results .gsc-cursor-box .gsc-cursor-page {
			    cursor: pointer;
			    color: #07AD00;
			    text-decoration: none;
			    margin-right: 5px;
			    display: inline;
			    border: 1px solid #DDD;
			    padding: 2px 5px 2px 5px;
			}
			.resultsTabs{
				margin-right:auto;
				margin-left:auto;
				width: 90em;
			}	
			.collapsibleList li{
			  width:400px;
			  list-style-image:url('/Fountain/images/button.png');
			  cursor:auto;
			}
			
			li.collapsibleListOpen{
			  list-style-image:url('/Fountain/images/button-open.png');
			  cursor:pointer;
			}
			
			li.collapsibleListClosed{
			  list-style-image:url('/Fountain/images/button-closed.png');
			  cursor:pointer;
			}			
			textarea {
  				resize: none;
  				overflow: auto;
			}
			span.searchExample {
			    font-family: "Courier New", Courier, monospace;
			}			
			
			li.selectedReport {
				background-color: #F0F0F0;
			}
			li.highlightedReport {
				/*background-color: #FAFAFA;*/
			}			
			li.shadow {
				width: 93%;
				margin-top: 5px;
				margin-bottom: 5px;
				margin-left: 10px;
				margin-right: 20px;
				border-bottom: 1px solid #ccc;
				padding: 5px;
				}
			
			li.shadow:hover {
				-moz-box-shadow: 0 0 5px rgba(0,0,0,0.5);
				-webkit-box-shadow: 0 0 5px rgba(0,0,0,0.5);
				box-shadow: 0 0 5px rgba(0,0,0,0.5);
				}			
			
			.btn-toolbar-no-margin {
				margin-top: 0px !important;
				margin-bottom: 0px !important;
			}
			.descriptionForm{
				margin-bottom:5px;
			}
			.btn-search-help{
				color:#9B9B9B
			}
			.gold {
				color: #FFD700;
			}
			.labelSelect{
			    float: right;
			    text-align: left;
			    font-weight: normal;
			    padding-right: 125px;
			    padding-top:5px;					
			}
			.searchSortOrderSelect {
				width:70px;		
			}
			
			select.btn-mini {
			    height: auto;
			    line-height: 14px;
			}
			
			/* this is optional (see below) */
			select.btn {
			    -webkit-appearance: button;
			       -moz-appearance: button;
			            appearance: button;
			    /*padding-right: 16px;*/
			}
			
			select.btn-mini + .caret {
			    margin-left: -20px;
			    margin-top: 9px;
			}			
			.searchPager{
				margin-left:25px;
			}
			
			#fountainUpdates ul{
				padding-right:3px;
			}
		</style>
		<!-- <link rel="stylesheet" href="<spring:theme code='colourscheme'/>" type="text/css" media="screen"/> -->
        <title>Fountain: Home</title>
    </head>
    
    <body class="">
	<div class="bannerContent"></div>
    
    <p></p>
    
    <div>
    
<div id="initialPageContent" style="display:none;">
<div class="container-fluid">
<div class='row-fluid'>
<div class="span9">
<h3 class='indexHead'>Welcome to Fountain</h3>
<div class="">
	<p>Welcome to the new release of Fountain. Please see the sidebar for what's new in this release.</p>
</div>   

<!-- 
<div class="als-container" id="demo3">
  <span class="als-prev"><img src="/Fountain/images/thin_left_arrow_333.png" alt="prev" title="previous" /></span>
  <div class="als-viewport">
    <ul class="als-wrapper">
      <li class="als-item">orange</li>
      <li class="als-item">apple</li>
      <li class="als-item">banana</li>
      <li class="als-item">blueberry</li> 
      <li class="als-item">watermelon</li>
      <li class="als-item">cherry</li>
      <li class="als-item">strawberry</li>
      <li class="als-item">avocado</li>
    </ul>
  </div>
  <span class="als-next"><img src="/Fountain/images/thin_right_arrow_333.png" alt="next" title="next" /></span>
</div>
-->
   <h4 class='indexHead'>Designing a report?</h4>
   <div><a href="/Fountain/jsp/protected/reportFlow.jsp" target="_blank">Click here</a> to view detailed step by step help on creating a report (Opens in new tab).</div>
   
   <h4 class='indexHead'>Company History</h4>
   <div>
   <a href="/Fountain/jsp/protected/companyHistory.jsp">Click here</a> to view company history since privatisation for all Fountain companies. 
   </div>

<h4 class='indexHead'>Table Guidance</h4>
<div class="accordion" id="accordion2">
   <div class="accordion-group">
      <div class="accordion-heading">
         <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
         <i class="fa fa-map"></i>&nbsp; Annual Collections 
         </a>
      </div>
      <div id="collapseOne" class="accordion-body collapse">
         <div class="accordion-inner">
            <table class="table table-striped">
               <tr>
                  <th>Year</th>
                  <th>Notes</th>
               <tr>
               <tr>
                  <td>June Return ICS (2007)</td>
                  <td>
                  	<div>Data returned by companies existing in 2007 through the June Return information capture system.</div> 
                  	 All the data is in outturn prices. 
                  	 The tables consist of:
                     <ul>
	                     <li>Key information in A to E;</li>
	                     <li>Services data in 1 to 17;</li>
	                     <li>Financial data in 18 to 39;</li>
	                     <li>Health and safety data in 41.</li>
                     </ul>
                     <em>SOME JR TABLES WERE CONFIDENTIAL AND NOT FOR THE PUBLIC DOMAIN.  FOR EXAMPLE,
                     <ul>
	                     <li>Table 17b</li>
	                     <li>Land sales info</li>
	                     <li>Transfer pricing info</li>
                     </ul>
                     </em>
                     <b>For further information click <a href="https://bubble.live.sharepoint.ofwat.net/WaterCompanyInformation/Reporting%20Requirements/Forms/Document%20Set/docsethomepage.aspx?ID=311&FolderCTID=0x0120D520001CF013BF68ED3340B575DAF29B6D653B&List=887b4490-1a72-4ff6-bfcd-b0bded526511&RootFolder=%2FWaterCompanyInformation%2FReporting%20Requirements%2FJune%20Return%202007%20Reporting%20Requirements">here.</a></b>
                  </td>
               </tr>
               <tr>
                  <td>June Return ICS (2008)</td>
                  <td>
                  	<div>Data returned by companies existing in 2008 through the June Return information capture system.</div> 
                  	 All the data is in outturn prices. 
                  	 The tables consist of:
                     <ul>
	                     <li>Key information in A to E;</li>
	                     <li>Services data in 1 to 17;</li>
	                     <li>Financial data in 18 to 39;</li>
	                     <li>Health and safety data in 41.</li>
                     </ul>
                     <em>SOME JR TABLES WERE CONFIDENTIAL AND NOT FOR THE PUBLIC DOMAIN.  FOR EXAMPLE,
                     <ul>
	                     <li>Table 17b</li>
	                     <li>Land sales info</li>
	                     <li>Transfer pricing info</li>
                     </ul>
                     </em>
                     <b>For further information click <a href="https://bubble.live.sharepoint.ofwat.net/WaterCompanyInformation/Reporting%20Requirements/Forms/Document%20Set/docsethomepage.aspx?ID=238&FolderCTID=0x0120D520001CF013BF68ED3340B575DAF29B6D653B&List=887b4490-1a72-4ff6-bfcd-b0bded526511&RootFolder=%2FWaterCompanyInformation%2FReporting%20Requirements%2FJune%20Return%202008%20Reporting%20Requirements">here.</a></b>
                  </td>
               </tr>
               <tr>
                  <td>June Return ICS (2009)</td>
                                    <td>
                  	<div>Data returned by companies existing in 2009 through the June Return information capture system.</div> 
                  	 All the data is in outturn prices. 
                  	 The tables consist of:
                     <ul>
	                     <li>Key information in A to E;</li>
	                     <li>Services data in 1 to 17;</li>
	                     <li>Financial data in 18 to 39;</li>
	                     <li>Health and safety data in 41.</li>
                     </ul>
                     <em>SOME JR TABLES WERE CONFIDENTIAL AND NOT FOR THE PUBLIC DOMAIN.  FOR EXAMPLE,
                     <ul>
	                     <li>Table 17b</li>
	                     <li>Land sales info</li>
	                     <li>Transfer pricing info</li>
                     </ul>
                     </em>
                     <b>For further information click <a href="https://bubble.live.sharepoint.ofwat.net/WaterCompanyInformation/Reporting%20Requirements/Forms/Document%20Set/docsethomepage.aspx?ID=163&FolderCTID=0x0120D520001CF013BF68ED3340B575DAF29B6D653B&List=887b4490-1a72-4ff6-bfcd-b0bded526511&RootFolder=%2FWaterCompanyInformation%2FReporting%20Requirements%2FJune%20Return%202009%20Reporting%20Requirements">here.</a></b>
                  </td>
               </tr>
               <tr>
                  <td>June Return ICS (2010)</td>
                  <td>
                  	<div>Data returned by companies existing in 2010 through the June Return information capture system.</div> 
                  	 All the data is in outturn prices. 
                  	 The tables consist of:
                     <ul>
	                     <li>Key information in A to E;</li>
	                     <li>Services data in 1 to 17;</li>
	                     <li>Financial data in 18 to 39;</li>
	                     <li>Health and safety data in 41.</li>
                     </ul>
                     <em>SOME JR TABLES WERE CONFIDENTIAL AND NOT FOR THE PUBLIC DOMAIN.  FOR EXAMPLE,
                     <ul>
	                     <li>Table 17b</li>
	                     <li>Land sales info</li>
	                     <li>Transfer pricing info</li>
                     </ul>
                     </em>
                     <b>For further information click <a href="https://bubble.live.sharepoint.ofwat.net/WaterCompanyInformation/Reporting%20Requirements/Forms/Document%20Set/docsethomepage.aspx?ID=76&FolderCTID=0x0120D520001CF013BF68ED3340B575DAF29B6D653B&List=887b4490-1a72-4ff6-bfcd-b0bded526511&RootFolder=%2FWaterCompanyInformation%2FReporting%20Requirements%2FJune%20Return%202010%20Reporting%20Requirements">here.</a></b>
                  </td>
               </tr>
               <tr>
                  <td>June Return ICS (2011)</td>
                  <td>
                  	<div>Data returned by companies existing in 2011 through the June Return information capture system.</div> 
                  	 All the data is in outturn prices. 
                  	 The tables consist of:
                     <ul>
	                     <li>Key information in A to E;</li>
	                     <li>Services data in 1 to 17;</li>
	                     <li>Financial data in 18 to 39;</li>
	                     <li>Health and safety data in 41.</li>
                     </ul>
                     <em>SOME JR TABLES WERE CONFIDENTIAL AND NOT FOR THE PUBLIC DOMAIN.  FOR EXAMPLE,
                     <ul>
	                     <li>Table 17b</li>
	                     <li>Land sales info</li>
	                     <li>Transfer pricing info</li>
                     </ul>
                     </em>
                    <b>For further information click <a href="https://bubble.live.sharepoint.ofwat.net/WaterCompanyInformation/Reporting%20Requirements/Forms/Document%20Set/docsethomepage.aspx?ID=1&FolderCTID=0x0120D520001CF013BF68ED3340B575DAF29B6D653B&List=887b4490-1a72-4ff6-bfcd-b0bded526511&RootFolder=%2FWaterCompanyInformation%2FReporting%20Requirements%2FJune%20Return%202011%20Reporting%20Requirements">here.</a></b>
                  </td>
               </tr>
            </table>
         </div>
      </div>
   </div>
   <div class="accordion-group">
      <div class="accordion-heading">
         <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
         <i class="fa fa-map"></i>&nbsp;Price Reviews 
         </a>
      </div>
      <div id="collapseTwo" class="accordion-body collapse">
         <div class="accordion-inner">
         	<table class="table table-striped">
         		<tr><th>Model Name</th><th>Description</th></tr>
         		<tr><td>Cost Base 2008</td><td><em><b>Cost Base 2008</b></em> information is a PR09 related submission that we required from companies in April 2008. It contained companies' <em><b>initial</b></em> cost base standard costs estimates for 2007-08 and % expenditure projections for AMP5. We used this information to derive average efficiency assumptions which we used in setting draft baseline levels of capital expenditure for the new capital expenditure incentive scheme (CIS). We published a report in December 2008 containing these draft baselines.</td></tr>
         		<tr><td>Draft Business Plan 2008</td><td>Each water company has provided to Ofwat a draft business plan for the period 2010 to 2015.
The draft business plans set out company proposals - what they will achieve in terms of services and the improvements they will provide - in light of the current information available to them.  The draft business plan tables are available on Fountain.<br/>
We used these draft plans to expose the potential impact of the key issues that needed to be resolved over the remainder of the review. The information was also used as input into the customer research. The results of this research, the draft business plan information, views garnered from the ensuing debate, and advice from the regulators contributed to the key decisions taken during this part of the review. 
         		</td></tr>
         		<tr><td>Revised Final Business Plan 2009</td><td>Each water company has provided to Ofwat a final business plan for the period 2010 to 2015. This provides the company's final view on its future price limits. They set out proposals for meeting its objectives (what it will achieve in terms of services and the improvements it will provide) for 2010-11 to 2014-15 including its expenditure needs, financing requirements and the implications for its price limits and average bills for the period 2010-15.<br/>
The RFBP tables are available on Fountain revised for any changes agreed with the companies from their business plans.
         		</td></tr>
         	</table>
         </div>
      </div>
   </div>
   <div class="accordion-group">
      <div class="accordion-heading">
         <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseThree">
         <i class="fa fa-map"></i>&nbsp;Small Company Returns (Navs) 
         </a>
      </div>
      <div id="collapseThree" class="accordion-body collapse">
         <div class="accordion-inner">
            <table class="table table-striped">
            	<tr><th>Model Name</th><th>Reporting Year</th><th>Description</th></tr>
            	<tr>
            		<td>NAV2011-ICS</td>
            		<td>2010-11</td>
            		<td><p>Information notice '<a href="http://webarchive.nationalarchives.gov.uk/20150624091829/http://ofwat.gov.uk/regulating/gud_rag_contregacc_306.pdf">IN 11/03: Regulatory accounts 2010-11: </a><a href="">Reporting requirements –
						RAG 3.06</a>' informs way in which we expect the companies to prepare their regulatory information for 2010-11.</p> 
						<p>
						All appointed water and sewerage companies are required to submit regulatory accounting information annually. Condition F of each company's licence requires this information, and the Regulatory Accounting Guidelines specify the format of the information. 
						</p>
						<p>
						In RD 07/09: 'Regulatory accounts information' we introduced three levels of regulatory accounting information requirements to ensure that the financial information we receive is appropriate to the size of the company.
						</p>
						<p>
						Small companies submit their return using pro forma tables as set out in appendix 1 and 2 of RD 07/09.
						</p>            		
            		</td>
            	</tr>
            	<tr>
            		<td>NAV 2011-12</td>
            		<td>2011-12</td>
            		<td>
            		<p>
						Information notice '<a href="http://webarchive.nationalarchives.gov.uk/20150624091829/http://www.ofwat.gov.uk/publications/rags/prs_in1201rag.pdf">IN12/01 Regulatory accounts reporting requirements for 2011-12 and onwards</a>' sets out the way in which we expect the companies to prepare their regulatory information for 2011-12.  
						</p>
						<p>
						All appointed water and sewerage companies are required to submit regulatory accounting information annually. Condition F of each company's licence requires this information, and the Regulatory Accounting Guidelines specify the format of the information. 
						</p>
						<p>
						In RD 07/09: 'Regulatory accounts information' we introduced three levels of regulatory accounting information requirements to ensure that the financial information we receive is appropriate to the size of the company.
						</p>
						<p>
						Small companies submit their return using pro forma tables as set out in appendix 1 and 2 of RD 07/09.
						</p>            		
            		</td>
            	</tr>
            	<tr>
            		<td>NAV 2013</td>
            		<td>2012-1013</td>
            		<td><p>
						Information notice '<a href="http://webarchive.nationalarchives.gov.uk/20150624091829/http://www.ofwat.gov.uk/publications/rags/prs_in1301rag.pdf">IN13/01 Revised regulatory accounting guidelines and regulatory accounts reporting requirements for 2012-13 and onwards</a>' sets out the way in which we expect the companies to prepare their regulatory information for 2012-13.  
						</p>
						<p>
						Small companies submit their return using pro forma tables as set out in <a href="http://webarchive.nationalarchives.gov.uk/20150624091829/http://www.ofwat.gov.uk/publications/rags/gud_rag_3contregacc_307.pdf">RAG 3.07</a> and in line with the line definitions as set out in <a href="http://webarchive.nationalarchives.gov.uk/20150624091829/http://www.ofwat.gov.uk/publications/rags/gud_rag_4opcosts_404.pdf">RAG 4.04</a>.
						</p>
            		</td>
            	</tr>
            	<tr>
            		<td>NAV 2014</td>
            		<td>2013-14</td>
            		<td>
						<p>
						Information notice '<a href="http://webarchive.nationalarchives.gov.uk/20150624091829/http://www.ofwat.gov.uk/publications/rags/prs_in1405reportingreqs.pdf">IN14/05 Expectations for company reporting 2013-14 – regulatory accounts</a>, accounting separation and performance information' sets out the way in which we expect the companies to prepare their regulatory information for 2013-14.  
						</p>
						<p>
						Small companies submit their return using pro forma tables as set out in <a href="http://webarchive.nationalarchives.gov.uk/20150624091829/http://www.ofwat.gov.uk/publications/rags/gud_rag_3contregacc_307.pdf">RAG 3.07</a> and in line with the line definitions as set out in <a href="http://webarchive.nationalarchives.gov.uk/20150624091829/http://www.ofwat.gov.uk/publications/rags/gud_rag_4opcosts_404.pdf">RAG 4.04</a>.
						</p>            			
            		</td>
            	</tr>
            	<tr>
            		<td>Small Company Return 2014-15</td>
            		<td>2014-15</td>
            		<td>
						<p>
						Information notice '<a href="http://webarchive.nationalarchives.gov.uk/20150624091829/http://www.ofwat.gov.uk/regulating/compliance/prs_in1502regacc.pdf">IN15/02 Expectations for company reporting 2014-15 – regulatory accounts</a>, accounting separation and performance information' sets out the way in which we expect the companies to prepare their regulatory information for 2014-15.  
						</p>
						<p>
						Small companies submit their return using pro forma tables as set out in RAG 3.07 and in line with the line definitions as set out in RAG 4.04.
						</p>            			            		
            		</td>
            	</tr>
            </table>
         </div>
      </div>
   </div>
   <div class="accordion-group">
      <div class="accordion-heading">
         <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseFour">
         <i class="fa fa-map"></i>&nbsp;Prinicipal Statements 
         </a>
      </div>
      <div id="collapseFour" class="accordion-body collapse">
         <div class="accordion-inner">
            <table class="table table-striped">
            	<tr><th>Model Name</th><th>Charging Year</th><th>Description</th></tr>
            	<tr>
            		<td>PS 2013-14</td><td>2013-14</td><td>
            			<p>
            			Information notice '<a href="http://webarchive.nationalarchives.gov.uk/20150624091829/https://www.ofwat.gov.uk/regulating/charges/prs_in1211chargesapproval.pdf">IN12/11 Approval of charges 2013-14</a>' sets out the process and the timetable for the approval of each company's charges scheme for 2013-14. 
            			</p>
						<p>
						Each appointed water company sends this annual return to us in January. It sets out the company's standard charges for the forthcoming charging year (1 April to 31 March), as well as other information that enables the calculation of the increase in that company’s overall average charges for the forthcoming charging year. The increase in the overall average charge must not exceed the company's price limit for that charging year.
						</p>
						<p>
						For the 2013-14 charging year, companies submitted their principal statement information to us using an Excel-based information capture system (ICS). The ICS contained all the necessary formulas that enabled the basket item ratios and the weighted average charges increase (WACI) to be calculated in accordance with the definitions in licence condition B. But it did not include any prior year charges data.
						</p>
            		</td>
            	</tr>
            	<tr>
            		<td>PS 2014-15</td><td>2014-15</td><td>
						<p>
						Information notice '<a href="http://webarchive.nationalarchives.gov.uk/20150624091829/https:/www.ofwat.gov.uk/regulating/charges/prs_in1312approvalprocess.pdf">IN13/12 Approval of charges 2014-15</a>' sets out the process and the timetable for the approval of each company's charges scheme for 2014-15. 
						</p>
						<p>
						Each appointed water company sends this annual return to us in January. It sets out the company's standard charges for the forthcoming charging year (1 April to 31 March), as well as other information that enables the calculation of the increase in that company’s overall average charges for the forthcoming charging year. The increase in the overall average charge must not exceed the company's price limit for that charging year.
						</p>
            		</td>
            	</tr>            	
            </table>
         </div>
      </div>
   </div>
   

   <!-- <div class="accordion-group">
      <div class="accordion-heading">
      <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseFive">
      Feedback
      </a>
      </div>
      <div id="collapseThree" class="accordion-body collapse">
      <div class="accordion-inner">
      Anim pariatur cliche...
      </div>
      </div>
      </div> -->				  
</div>
</div>
  

   <div class="span3">
   <div class='fountainBox blue'>
      <h4 class='indexHead'><i class="fa fa-asterisk fa-large"></i>&nbsp;What's New In Fountain</h4>
      <h5 id="fountainRelease">Release: </h5>
      <div id="fountainUpdates" style="overflow-y: scroll; height:150px; overflow-x: hidden">
		
      </div>
   </div>


   
   <div class='fountainBox green'>
      <h4 class='indexHead'><i class="fa fa-question fa-large"></i>&nbsp;FAQ's and How To guides</h4>
      <ul class="fa-ul">
         <li><i class="fa-li fa fa-tint"></i><a target="_blank" href="/Fountain/jsp/protected/reportFlow.jsp">How to create a report</a></li>
         <li><i class="fa-li fa fa-tint"></i><a href="https://bubble.live.sharepoint.ofwat.net/Teams/WorkingGroups/FountainWiki/Shared%20Documents/FountainRunsAndCheckpoints.pdf">What are runs and checkpoints?</a></li>
         
         <li><i class="fa-li fa fa-tint"></i><a href="https://bubble.live.sharepoint.ofwat.net/Teams/WorkingGroups/FountainWiki/SiteAssets/SitePages/Home/HowToSearch.pdf">How to search</a></li>
         <li><i class="fa-li fa fa-tint"></i><a href="https://bubble.live.sharepoint.ofwat.net/Teams/WorkingGroups/FountainWiki/Shared%20Documents/HowToViewTables.pdf">How to view tables from data collection</a></li>
         <li><i class="fa-li fa fa-tint"></i><a href="https://bubble.live.sharepoint.ofwat.net/Teams/WorkingGroups/FountainWiki/Shared%20Documents/ExportToExcel.pdf">How to export data to Excel</a></li>
      </ul>
   </div>
   <div class='fountainBox yellow'>
      <h4 class='indexHead'><i class="fa fa-bug fa-large"></i>&nbsp;Raise An Issue</h4>
      <div>If you have any issues, queries or suggestions on how we could improve Fountain please complete as much of the Bubble form on the
      link below.
      <a href="https://bubble.live.sharepoint.ofwat.net/Teams/CorporateServices/IMT/Lists/FountainIssues/NewForm.aspx?Source=https%3A%2F%2Fbubble%2Elive%2Esharepoint%2Eofwat%2Enet%2FTeams%2FCorporateServices%2FIMT%2FLists%2FFountainIssues%2FAllItems%2Easpx&RootFolder"><img alt="Fountain Feedback" src="/Fountain/images/bubble_feedback.png"></img></a>
      </div>
   </div>
   <!-- 
   <div class='fountainBox pink'>
      <h4 class='indexHead'>...</h4>
   </div>
   -->   
   </div>
</div>
</div>
</div>
</div>

    
    <div class="container_12"> <!-- main container, currently using 1 x grid_12 -->
        	<div id="imageContainer">
		<img src="/Fountain/images/banner_logo_lrg.png" style="height:80px; padding-bottom: 5px;">
	</div>   
   
   <div id="searchContainer">
		<form id="searchForm">
			<!--
			<input id="field" name="field" type="text" placeholder="Search..."/>
			-->
			<!-- <div id="delete"><span id="x">x</span></div> -->
			<!-- <input id="searchButton" name="searchButton" type="button" /> -->
			<div class="input-append">
			  <input class="span4" id="field" name="field" type="text" placeholder="Search...">
			  <button id="searchButton" class="btn btn-primary " type="button">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="icon-search icon-white"></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
			  <button id="favoriteButton" class="btn"><i class="fa fa-star-o"></i></button>
			  <a href="#myModal" role="button" class="btn btn-search-help" data-toggle="modal">&nbsp;&nbsp;<i class="fa fa-question"></i>&nbsp;&nbsp;</a>
			  <button id="showSelected" class="btn" disabled="disabled"><i class="fa fa-angle-double-down"></i></button>
			</div>
			
			
						
			<label class="checkbox inline">
			  <input type="checkbox" id="showDeleted" value="option1">Include deleted?</input>
			</label>
			<label class="checkbox inline">
			  <input type="checkbox" id="showMyDataOnly" value="option2">My data only?</input>
			</label>
			<label class="checkbox inline">
			  <input type="checkbox" id="sortByDate" value="option3">Sort by date?</input>
			</label>
			<label class="inline labelSelect">
				<select class="btn btn-mini searchSortOrderSelect" disabled="disabled" id="sortOrder">
					<option value="desc">Desc.</option>
  					<option value="asc">Asc.</option>
				</select>
				<!-- <span class="caret"></span> -->
			</label>			
			<!-- 
			<label class="checkbox inline">
			  <input type="checkbox" id="inlineCheckbox3" value="option3"></input>
			</label>
			-->  			
		</form>
	</div>	    	
    	
    	<div class="grid_4 push_8" id="response"></div>
	    
    	<div class='clear'>&nbsp;</div>
    	
		<div id="reportsContainer" class='grid_12'></div>
		<div class='clear'>&nbsp;</div>
        <div id="allContent">
   </div>
   
   <div class="container_12">
   </div>
   
   
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">Search Help</h3>
  </div>
  <div class="modal-body">


  	<h4>Basic Search</h4>
    <p>Search on free text string e.g enter 'water' to bring back all reports that contain title, description, or items with names or descriptions that contain the string 'water'</p>
    <p>The following fields can be explicitly searched
    	<ul>
    		<li><span class="searchExample">id</span></li>
    		<li><span class="searchExample">name</span></li>
    		<li><span class="searchExample">user</span></li>
    		<li><span class="searchExample">last_modified</span></li>
    		<li><span class="searchExample">items</span></li>
    	</ul>
    	by using the field prefix on the search term e.g: <span class="searchExample">name:water AND user:joe.bloggs</span> will return all items that were created by the user joe.bloggs and contain hthe string water in the name field.
    </p>
  	<h4>Combining Search Terms</h4>
    <p>Search terms can be combined using the 'AND', 'OR' and 'NOT' operators. These must be entered upper case as in the following examples;</p>
    <p>
    	<ul>
    		<li><span class="searchExample">water AND lead</span> will find all items that contain the string water and lead in any fields.</li>
    		<li><span class="searchExample">water OR lead</span> will find all items that contain the string water or lead in any fields.</li>
    		<li><span class="searchExample">water AND NOT lead</span> will find all items that contain the string water and don't contain the string lead in any fields.</li>
    	</ul>
    </p>
  	<h4>Searching Date Ranges</h4>
    <p>Date ranges can be searched by using the following syntax:
		<span class="searchExample">last_modified:[01/01/2012 TO 31/12/2012]</span>    
	Note that this will only return reports. Items and Tables do not contain a date field. 
    </p>    
  	<h4>Wildcard Operators</h4>
    <p>Wildcard operators can be used to widen search terms, e.g the following string would find all items that start with the string BN: <span class="searchExample">BN*</span></p>
	
	 <h4>Contraction</h4>
	 <p>When a command uses an operator (AND, OR, AND NOT) to impose a restriction on the same field (id, name, user, last_modified, items), the command can be contracted. For example, the command:
		<span class="searchExample">name:*pipe* OR name:*water*</span>
	 </p>
	 <p>
	Can be contracted in:
	<span class="searchExample">name:(*pipe* OR *water*)</span>
	</p>
	<p>
	The use of the brackets is imperative. Indeed, the following command will not return the same results:
	<span class="searchExample">name:*pipe* OR *water*</span>
	</p>
	<p>
	The first two command search all reports which contains in their name either the word "pipe" or "water" (or both). The last command search all reports which contain the word "pipe" in their name or correspond to the general criteria "water".
	Several operators (AND, AND NOT) - same field	Searches are not limited to the use of a single operator (AND, AND NOT). The following search is also valid:
	<span class="searchExample">name: *water* AND name: *waste* AND name: *volume*</span>
	</p>
	<p>
	This search will return reports which contain the words "water", "waste" and "volume" in their name.
	Note that the contracted form also works:
	<span class="searchExample">name:(*water* AND *waste* AND *volume*)</span>
	</p>
	<p>
	Several operators (OR) - same field	When using several "OR" operators, only the contracted form works:
	<span class="searchExample">name:(*water* OR *waste* OR *volume*)</span>
	Several operators - two fields	If we are looking for a report which conatins "water" in the title and has been created by harry.test or larry.test, the following command can be used:
	<span class="searchExample">name: *water* AND (user:harry.test OR user:larry.test)</span>
	The contracted form also works in this case:
	<span class="searchExample">name: *Water* AND user:(harry.test OR larry.test)</span>
	</p>
	<p> 
	Note: The following command does not work because the operator "OR" can only be used in his contracted form when several operators are used:
	<span class="searchExample">name:(*watersure* OR *pipe*) OR user:*harry.test*</span>
	This command will return all the reports created by harry.test. It does not take into account the first part of the command. The same is true for the following command:
	<span class="searchExample">(user:*pursall* AND name:*report*) OR name:*watersure*</span>
	</p>
	
	<h4>Exclude part of a subset</h4>
	<p>If we want to display all reports which contains the word "water" but not "wasterwater" in their name, the following command should be used:
	<span class="searchExample">name: (*water* AND NOT *wastewater*)</span>
	</p>
	<p>
	Note that the reports containing the words "water" and "wastewater" will not be displayed.
	</p>
	<p>
	It also works with several operators:
	<span class="searchExample">name: (*water* AND NOT *wastewater* AND NOT *watersure*)</span>
	In the last case, the reports containing the word "water" in their name are displayed except for the reports with the words "wastewater" or "watersure" in their name.
	</p>


   
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
  </div>
</div>		

</div>
 
 <div id="form_notice" style="display: none;">
	<form class="pf-form pform_custom" action="" method="post">
		<div class="pf-element pf-heading">
			<h3>Selected Reports</h3>
			<p>Please review the reports and select an action.</p>
		</div>
		<div class="pf-element">
			<label>
				<select multiple="multiple" style="width: 300px;" class="pf-field" name="selectedReportsSelect" id="selectedReportsSelect">
				</select>
			</label>
		</div>
		<div class="pf-element pf-buttons pf-centered">
			<a class="btn btn-warning" id="btnMakeReadOnly" href="#"><i class="icon-lock icon-white"></i>Make Read-only</a>
			<a class="btn btn-danger" id="btnDelete" href="#"><i class="icon-trash icon-white"></i>Delete</a>
			<a class="btn" id="btnSelectAll" href="#">Select All</a>
			<a class="btn" id="btnDeselectAll" href="#">Deselect All</a>
			<!-- 
				<input class="pf-button btn btn-primary" type="submit" name="submit" value="Submit"/>
				<input class="pf-button btn btn-default" type="button" name="cancel" value="Cancel"/>
			-->
		</div>
	</form>
</div>
 
<div id="modalConfirmSaveSearch" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button> -->
    <h3 id="myModalLabel"><i class="fa fa-star gold"></i> Add to my searches:</h3>
  </div>
  <div class="modal-body">
  <fieldset>
    <!-- <legend>Legend</legend>  -->
    <div class="control-group">
    	<div class="controls">
		    <label>Please enter a name to refer to this search:</label>
		    <input type="text" class="input-xlarge" placeholder="Search name…"/>
    	</div>
    </div>
    <!-- <span class="help-block">Example block-level help text here.</span> -->
  </fieldset>
  </div>
  <div class="modal-footer">
    <button id="btnSaveSearch" class="btn btn-primary" data-dismiss="modal" aria-hidden="true">Save</button>
    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
  </div>
</div>	
   
 
<div class="resultsTabs">

<ul class="nav nav-tabs">
  <!-- <li><a href="#whatsNew" data-toggle="tab">News</a></li> -->
  <li><a href="#report" data-toggle="tab" data-searchtype="report">Reports</a></li>
  <li><a href="#item" data-toggle="tab" data-searchtype="item">Items</a></li>
  <li><a href="#table" data-toggle="tab" data-searchtype="table">Tables</a></li>
  <li><a href="#trending" data-toggle="tab" data-searchtype="trending">Trending</a></li>
  <li><a href="#myReports" data-toggle="tab" data-searchtype="myReports">My Reports</a></li>
  <li><a href="#mySearches" data-toggle="tab" data-searchtype="mySearches">My Searches</a></li>
</ul>
   
   
<div class="tab-content">

   <!-- 
  <div class="tab-pane" id="whatsNew">
	<h4 class="indexHead">What's New In Fountain</h4>
	<a href="https://bubble.live.sharepoint.ofwat.net/Teams/WorkingGroups/FountainWiki/SitePages/Latest%20Updates.aspx" target="_blank">Latest Updates</a><br/>
  </div>
  -->

  <div class="tab-pane" id="report">
   	<div class="resultsContainer">
   		<div class="resultsCount gsc-result-info"></div>
		<ul class="resultsList gsc-results">
			<div class="progress progress-striped active hidden">
			  <div class="bar" style="width: 0%;"></div>
			</div>			
		</ul>
		<div class="searchPager pagination"></div>
 	</div>
 </div>
 
 <div class="tab-pane" id="item">
   	<div class="resultsContainer">
   		<div class="resultsCount gsc-result-info"></div>
		<ul class="resultsList gsc-results">
			<div class="progress progress-striped active hidden">
			  <div class="bar" style="width: 0%;"></div>
			</div>			
		</ul>
		<div class="searchPager pagination"></div>
	</div> 	
 </div>
 
 <div class="tab-pane" id="table">
   	<div class="resultsContainer">
   		<div class="resultsCount gsc-result-info"></div>
		<ul class="resultsList gsc-results">
			<div class="progress progress-striped active hidden">
			  <div class="bar" style="width: 0%;"></div>
			</div>			
		</ul>
		<div class="searchPager pagination"></div>
	</div> 	
 </div> 
 
 <div class="tab-pane" id="trending">
 	<div id="trendingResults">
		<table id='tblReports' class='table table-bordered table-condensed'>
			<tbody>
				<!-- <tr><th>Report Name</th><th>Last Modified Date</th><th></th></tr> -->
				<tr><th colspan="3" class="mostPopularHeader">Most Popular Today</th></tr>
			</tbody>
			<tbody id="popularToday">
			</tbody>
			<tbody>
				<tr><th colspan="3" class="mostPopularHeader">Most Popular This Week</th></tr>
			</tbody>
			<tbody id="popularWeek">
			</tbody>
			<tbody>
				<tr><th colspan="3" class="mostPopularHeader">Most Popular This Month</th></tr>
			</tbody>			
			<tbody id="popularMonth">
			</tbody>
			<tbody>
				<tr><th colspan="3" class="mostPopularHeader">Most Popular This Year</th></tr>
			</tbody>
			<tbody id="popularYear">
			</tbody>									
		</table> 		
 	</div>	
 </div>
 <div class="tab-pane" id="myReports">
   	<div class="resultsContainer">
   		<div class="resultsCount gsc-result-info"></div>
		<ul class="resultsList gsc-results">
			<div class="progress progress-striped active hidden">
			  <div class="bar" style="width: 0%;"></div>
			</div>			
		</ul>
		<div class="searchPager pagination"></div>
	</div>  
 </div>
  <div class="tab-pane" id="mySearches">
		<table id='tblSearches' class='table table-bordered table-condensed'>
			<tbody>
				<!-- <tr><th>Report Name</th><th>Last Modified Date</th><th></th></tr> -->
				<tr><th colspan="6" class="userFavorites mostPopularHeader">My Searches</th></tr>
				<tr><th>Search Name</th><th>Search String</th><th>Include Deleted?</th><th>My Data Only?</th><th>Date</th><th></th></tr>
			</tbody>
			<tbody id="userFavorites">
			</tbody>
			<tbody>
				<tr><th colspan="6" class="userHistory mostPopularHeader">My Search History</th></tr>
				<tr><th></th><th>Search String</th><th>Include Deleted?</th><th>My Data Only?</th><th>Date</th><th></th></tr>
			</tbody>
			<tbody id="userHistory">
			</tbody>
			<tbody>
				<tr><th colspan="6" class="allHistory mostPopularHeader">All User Search History</th></tr>
				<tr><th></th><th>Search String</th><th>Include Deleted?</th><th>My Data Only?</th><th>Date</th><th></th></tr>
			</tbody>
			<tbody id="allHistory">
			</tbody>
		</table> 	
 </div>
</div>
</div>  

   <div id="fade" class="dark_overlay"></div>   
   <p/>
   	<script type="text/javascript" src="/Fountain/js/dojo/dojo.js" ></script>
	<script type="text/javascript" src="../../js/ofwat/ofwat.js"></script>
	<script type="text/javascript" src="../../js/json2.js"></script>
    <script type="text/javascript" src="../../js/ofwat/rest.js"></script>
    <script type="text/javascript" src="../../js/ofwat/referenceSelection.js"></script> 
    <script type="text/javascript" src="../../js/ofwat/reportSearch.js"></script>
    <script type="text/javascript" src="../../js/ofwat/reportPage.js"></script>
    <!-- <script type="text/javascript" src="../../js/ofwat/ofwatPager.js"></script> -->
    <script type="text/javascript" src="../../js/ofwat/reportCompanyRunSelection.js"></script>
    <script type="text/javascript" src="../../js/ofwat/updates.js"></script>
    <script type="text/javascript" src="../../js/jquery/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="../../js/bootstrap.js"></script>
    <script type="text/javascript" src="../../js/jquery.bootpag.min.js"></script>
    <script type="text/javascript" src="../../js/jquery.dataTables.js"></script>       
    <script type="text/javascript" src="../../js/jquery.filterable.js"></script>
    <script type="text/javascript" src="../../js/bootstrap-editable.min.js"></script>
    <script type="text/javascript" src="../../js/CollapsibleLists.js"></script>
    <script type="text/javascript" src="../../js/pnotify.custom.min.js"></script>
    <script type="text/javascript" src="../../js/bootstrap-tagsinput.js"></script>  
	<script type="text/javascript" src="../../js/handlebars/handlebars.js"></script>
	<script type="text/javascript" src="../../js/moment.js"></script>
	<!-- <script type="text/javascript" src="../../js/jquery.als-1.7.min.js"></script> -->
    <script type="text/javascript">
	    //Handlebars.registerHelper('inRole', ofwat.checkUserInRole); 
		//ofwat.loadTemplate(dojo.query(".bannerContent")[0], ofwat.template.banner);
		//helpPage="My Reports";
		//ofwat.wikiPage();
    </script>

<script type="text/javascript"> 
    var startRow = 0;
    var PAGE_INCREMENT = 20;
    
    var endRow = startRow + PAGE_INCREMENT;
    
    //We need the username in the client. 
    userName = "<%if (null != request.getRemoteUser()){
    	String userName = request.getRemoteUser();
    	userName = request.getRemoteUser().substring(userName.lastIndexOf('\\')+1, userName.length());
    	out.print(userName);
    }%>";
    
    /*
    <ofwat:userRole role="OFWAT\Fountain.Editors">
    	isAdmin = true;
    </ofwat:userRole>
    */	
    
    //var currentPage = 0;
    var maxPages = 0;
  
    //Holds a ref to the selected reports display.
	var notice
    var maxStartRow;
	var searchType = "report";
	var SELECTED_REPORT_STYLE = "selectedReport";
	var activeTab = null;
	var pageInfo;
	var globalReset = false;
	var selectedReports;
	var getUpdateList;
	
	function getReleases(){
		//Call the function to get all the releases from update.js. 
		//Render in the update div. 
		ofwat.viewRelease.getLatestRelease(function(release){
			//Render a list in the updateDiv? 
			if(release != null){
				$("#fountainRelease").html("Release: " + release.releaseName);
				var $FountainUpdatesContainer = $("#fountainUpdates");
				var updates = "<ul>";
				var i = 0;
				for(i=0;i<release.updates.length;i++){
					var u = release.updates[i];
					updates = updates + "<li><i class='fa-li fa fa-tint'></i><a href='" + u.externalLink + "'>" + u.title + "</a>"; 
					updates = updates + "<div>" + u.description + "</div>";
					updates = updates + "</li>";
				}		
				updates = updates + "</ul>";
				$FountainUpdatesContainer.html(updates);
			}
		})
	};
	
	
	/**
		Object to manage the reports that are currently selected. 
		Takse callback as parameters to trigger UI actions (Or whatever). 
	**/
	function SelectedReports(addcallback, removecallback, emptyCallback) {
		this.removecallback = removecallback;
		this.addcallback = addcallback;
		this.emptyCallback = emptyCallback;
		
		this.removeAll = function(){
			for(var i=0;i<this.reportInfo.length;i++){
				removecallback(this.reportInfo[i].name, this.reportInfo[i].id, this.reportInfo[i].node);
			}		
			this.reportInfo = [];
			emptyCallback();
		}
		
		this.remove = function(ri){
			var index = -1;
			for(var i=0;i<this.reportInfo.length;i++){
				if(this.reportInfo[i].id == ri.id){
					index = i;							
				}
			}
			if (index > -1) {
				this.reportInfo.splice(index, 1);
			}
			removecallback(ri.name, ri.id, ri.node);
			//If it's empty.
			if(this.reportInfo.length == 0){
				emptyCallback();
			}
		};
		
		this.add = function(ri){
			//Need to check reportInfo.id for duplicates.
			var add = true;
			for(var i=0;i<this.reportInfo.length;i++){
				if(this.reportInfo[i].id == ri.id){
					add = false;
				}
			}
			if(add){
				this.reportInfo.push(ri);
				addcallback(ri.name, ri.id, ri.node);
			}else{
				console.log("Didn't add report to selected reports.");
			}
		};
		this.contains = function(reportId){
			for(var i=0;i<this.reportInfo.length;i++){
				if(this.reportInfo[i].id == reportId){
					return true;
				}
			}			
			return false;
		}
		this.processed = false;
		this.reportInfo = [];
	}

	/**
		Simple object to store the search state of each of the tabs. 	
	**/
	var searchState = {
			"report":{
				"pager":null,
				"startRow":0,
				"endRow":0,
				//"currentPage":0,
				"reset":true
			},
			"item":{
				"pager":null,
				"startRow":0,
				"endRow":0,
				//"currentPage":0,
				"reset":true
			},
			"table":{
				"pager":null,
				"startRow":0,
				"endRow":0,				
				//"currentPage":0,
				"reset":true
			},
			"myReports":{
				"pager":null,
				"startRow":0,
				"endRow":0,				
				//"currentPage":0,
				"reset":true				
			}
	};
    
	$(document).ready(function() {
		
		/*
		$("#demo3").als({
			visible_items: 4,
			scrolling_items: 2,
			orientation: "horizontal",
			circular: "yes",
			autoscroll: "yes",
			interval: 4000
		});
		*/
		
		helpPage="My Reports";
		ofwat.wikiPage();		
		
	    <ofwat:userRole role="OFWAT\Fountain.Editors">
			isAdmin = true;
		</ofwat:userRole>		
		
		
	    Handlebars.registerHelper('inRole', ofwat.checkUserInRole); 
		ofwat.loadTemplate(dojo.query(".bannerContent")[0], ofwat.template.banner);
		helpPage="My Reports";
		ofwat.wikiPage();		
		
		getReleases();
		/*
		getUpdateList = function(){
			$.get("/Fountain/js/WhatsNew.json", function( data ) {
				//Show the updates
				console.log(data);
			}).fail(function() {
				//Set a fail message
				console.log("Failed");
			});				
		}
		*/
			
		/**
			Remove the selected highlight from all items. 
		**/
		function clearSelection(){
			$(".selectable99").each(function(i, item){
				$(item).removeClass(SELECTED_REPORT_STYLE);
			});
		}
		
		/**
			Helper function to grab all the items for a report by re-running the search for a particular item and 
			then rendering them all (This was introduced to make IE render reports with lots of items faster).
		**/
		function getItems(event){

			//set the cursor to hourglass.	
			$('html').addClass("busy");
			
			var reportId = $($(event.currentTarget).find(".reportItemList:first")).data('reportid'); 
			var showDeleted = $("#showDeleted").is(':checked');
			if(showDeleted){
				var qs = "/Fountain/rest-services/search?type=" + reportId + "&start=0&query=id:" + reportId;
			}else{
				var qs = "/Fountain/rest-services/search?type=" + reportId + "&start=0&query=id:" + reportId + " AND deleted:false";
			}
					
					
			//add spinner
			var itemListNode = $("#item_list_" + reportId);
			//itemListNode.append('<img id="theImg" src="../../images/ajax-loader.gif" />')
			itemListNode.append('<div>Loading...<div/>')
					
			$.get( qs, function( data ) {
				var result = data.hits.hits[0];
				itemListNode.empty();
				var itemNodes = []
				$.each(result._source.items, function(i, item) {
					//item_list_' + result._source.id + '
					var itemNode = $('<li><div class="item"><span style="display:inline-block; vertical-align:middle;"><div class="gs-title" style="float:left">' + item.code + '</div><div class="gs-snippet" style="float:left; padding-top:3px;">' + item.description + '</div></span></div><span class="clear"></span></li>');
					itemNodes.push(itemNode);
				});
				itemListNode.append(itemNodes);
				//Set cursor back to normal.
				$('html').removeClass("busy");
			}).fail(function() {
				//Set cursor back to normal.
				$('html').removeClass("busy");
			});		
		}		
		
		
		/**
			Trap the enter key being pressed on the search box and run the search. 
		**/
		$("#field").keypress(function(e){
			  if(e.which === 13){
			        e.preventDefault();
			        e.stopPropagation();
			        globalReset = true;
			        getResults(true, 0);
			   }
			});		
		
		
		/**
			????????????????????????????
		**/
		$("#showHelp").click(function(){	
		});
		

		
		$('.nav-tabs a:first').tab('show');
		$('.nav-tabs a:first').click()
		//Hack to make the first tab the active tab. 
		//activeTab = $('.nav-tabs a:first')
		activeTab = $('#' + $('.nav-tabs a:first').data("searchtype"));
		searchType = "report"
		
	    var $resultsList = $($(activeTab).find(".resultsList")[0]); 
		$resultsList.append("<div class='tab-pane' id='whatsNew'></div>");
		$initialPageContent = $("#initialPageContent");
		$("#whatsNew").append($initialPageContent);
		$initialPageContent.show();
		//$("#initialPageContent").appendTo("#report");
		/**
			If text input field value is not empty show the "X" button
		**/
		$("#field").keyup(function() {
			$("#x").fadeIn();
			if ($.trim($("#field").val()) == "") {
				$("#x").fadeOut();
			}
		});
		
		/**
			on click of "X", delete input field value and hide "X"
		**/
		$("#x").click(function() {
			$("#field").val("");
			$(this).hide();
		});
		
		/**
			Handler for tab switching.
			Slightly more complex than it needs to be - because we are starting search and paging when changing tabs...
		**/
		$('a[data-toggle="tab"]').on('shown', function (e) {
			activeTab = $("#"+ $(e.target).data("searchtype"));
			searchType = ($(e.target).data("searchtype"));
			$("#searchForm :input").attr("disabled", false);
			$("#searchForm .btn").attr("disabled", false);
			if(searchType == "myReports"){
				$("#showMyDataOnly").prop("checked",true);
				//append the correct params to qs? - get the user.
				var reset = searchState[searchType].reset;
				var startRes = searchState[searchType].startRow;						
				getResults(reset, startRes);
			}  
			else if(searchType == "trending"){
				//We need to establish the tab
				$("#showMyDataOnly").prop("checked",false);
				//Disable out the search field. 
				$("#searchForm :input").attr("disabled", true);
				$("#searchForm .btn").attr("disabled", true);
				showTrending();
			}
			else if(searchType == "mySearches"){
				$("#showMyDataOnly").prop("checked",false);
				//Disable out the search field. 
				$("#searchForm :input").attr("disabled", true);
				$("#searchForm .btn").attr("disabled", true);
				showHistory();
			}else{
				$("#showMyDataOnly").prop("checked",false);
				var searchStr = $("#field").val();
				if(searchStr != null && searchStr != ""){
					var reset = searchState[searchType].reset;
					var startRes = searchState[searchType].startRow;
				  	getResults(reset, startRes);
				}
			}
		})		
		
		
		function getUserFavoriteSearches(successCallback, errorCallback){
			var url = '/Fountain/rest-services/search/favorite/'
			makeCall(url, successCallback, errorCallback);
		}
		
		function getUserLastSearches(successCallback, errorCallback){
			var url = '/Fountain/rest-services/search/history?user=true'
			makeCall(url, successCallback, errorCallback);
		}
		
		function getAllLastSearches(successCallback, errorCallback){
			var url = '/Fountain/rest-services/search/history/'
			makeCall(url, successCallback, errorCallback);
		}
		
		function makeCall(url, successCallback, errorCallback){
			$.ajax({
				url:url,
				type:'GET',
   			 	contentType: "application/json",
   			    success: function(result){
   			    	if(successCallback != null){
   			    		successCallback(result);
   			    	}
   			    },
			    error: function(jqXHR, textStatus, errorThrown){
					if(errorCallback != null){
						errorCallback(jqXHR, textStatus, errorThrown);
					}				       					    	
			    }
			});			
		}
		
		/**
			Function to delete the reports that are selected in the 'SelectedReports' object instance. 
		**/
		function deleteReports(reportIds){
			console.log("deleting reports");
			var reportIdStr = '';
			for(var i=0;i<reportIds.length;i++){
				reportIdStr = reportIdStr + reportIds[i] + ',';	
			}
						
			$.ajax({
			    url: '/Fountain/rest-services/report/' + reportIdStr + '/bulk',
			    type: 'DELETE',
			    success: function(result) {
			        //Remove the select notification.
			        if(notice){
			        	notice.remove();
			        }				        
			        
			        if(selectedReports){
			        	selectedReports.removeAll();
			        }
		
			        //Show a success notice. 
			        new PNotify({
			            title: 'Success',
			            text: 'Successfully deleted all reports.',
			            type: 'success'
			        });	
			        clearSelection();
			    },
			    error: function(jqXHR, textStatus, errorThrown){
			    	var errorReports = jqXHR.responseJSON;
			        if(notice){
			        	notice.remove();
			        }				    	
			    	//Build error string. 
			    	var errorText = '<ul>';
			    	for(var i=0;i<errorReports.length;i++){
			    		errorText = errorText + '<li>Report id:' + errorReports[i].id + ', message:' + errorReports[i].message + '</li>'
			    	}
			    	errorText = errorText + '</ul>';
			    	//Show error notification.
			    	new PNotify({
			    	    title: 'Unable to delete all reports.',
			    	    text: errorText,
			    	    type: 'error'
			    	});			 			    	
			    }
			});
		}
		
		/**
			Function to mark reports read only that are selected in the 'SelectedReports' object instance. 
		**/		
		function markReportsReadOnly(reportIds){
			console.log("making reports readonly");
			var reportIdStr = '';
			for(var i=0;i<reportIds.length;i++){
				reportIdStr = reportIdStr + reportIds[i] + ',';	
			}
						
			$.ajax({
			    url: '/Fountain/rest-services/report/readOnly/' + reportIdStr + '?readOnly=true',
			    type: 'POST',
			    success: function(result) {
			        //Remove the select notification.
			        if(notice){
			        	notice.remove();
			        }	
			        
			        if(selectedReports){
			        	selectedReports.removeAll();
			        }
		
			        //Show a success notice. 
			        new PNotify({
			            title: 'Success',
			            text: 'Successfully updated all reports.',
			            type: 'success'
			        });		
			        clearSelection();
			    },
			    error: function(jqXHR, textStatus, errorThrown){
			    	var errorReports = jqXHR.responseJSON;
			        if(notice){
			        	notice.remove();
			        }				    	
			    	//Build error string. 
			    	var errorText = '<ul>';
			    	for(var i=0;i<errorReports.length;i++){
			    		errorText = errorText + '<li>Report id:' + errorReports[i].id + ', message:' + errorReports[i].message + '</li>'
			    	}
			    	errorText = errorText + '</ul>';
			    	//Show error notification.
			    	new PNotify({
			    	    title: 'Unable to update all reports.',
			    	    text: errorText,
			    	    type: 'error'
			    	});			    	
			    }
			});			
		}
		
		/***
			Show the history reports in the appropriate tab. 
		***/
		function showHistory(){
			//Ok get and render
			//user favourite searches.
			getUserFavoriteSearches(function(result){
				//display the users favourite searches
				console.log("Showing User favorites");
				appendHistory(result, $("#userFavorites"));
				addDeleteSearchHandlers();
			});
			//User last searches
			getUserLastSearches(function(result){
				//display the users last searches
				console.log("Showing User last searches");
				appendHistory(result, $("#userHistory"));
			});
			
			//all last searches.
			getAllLastSearches(function(result){
				//Display all last searches. 
				console.log("Showing All last searches");
				appendHistory(result, $("#allHistory"));
			});			
		}
		/**
			Helper function to add handlers to delete search History item.
		**/
		function addDeleteSearchHandlers(){
			$(".deleteSearch").click(function(event){
				event.preventDefault();
				event.stopPropagation();
			 	$.ajax({
	   			    url: "/Fountain/rest-services/search/history/" + $(event.currentTarget).data("searchid"),
	   			    type: 'DELETE',
	   			 	contentType: "application/json",
	   			    success: function(result){
	   			    	$(event.currentTarget).parents("tr").remove();
	   			    },
				    error: function(jqXHR, textStatus, errorThrown){
										    	
				    }
	   			});
			});
		}
		
		/**
			Helper function to get a param from the querystring. 
		**/
		function getParameterByName(name) {
		    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
		    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
		        results = regex.exec(location.search);
		    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
		}		
		
		function appendHistory(data, $node){
			var txt = '';
			var chkBoxChecked = '<td><input type="checkbox" disabled="disabled" checked="checked"></input></td>';
			var chkBoxNotChecked = '<td><input type="checkbox" disabled="disabled"></input></td>';
			$node.empty();
			for (var i=0;i<data.length;i++)
			{
				var currentSearch = data[i];
				if($node.attr("id") == "userFavorites"){
					txt += "<tr><td><a href='index.jsp?searchId="+ currentSearch.id +"'>" + currentSearch.searchName + "</a></td>";
				}else{
					txt += "<tr><td><a href='index.jsp?searchId="+ currentSearch.id +"'></a></td>";
				}
				txt += "<td><a href='index.jsp?searchId="+ currentSearch.id +"'>" + currentSearch.searchString + "</a></td>";
				if(currentSearch.includeDeleted){
					txt += chkBoxChecked;	
				}else{
					txt += chkBoxNotChecked;
				}
				if(currentSearch.myDataOnly){
					txt += chkBoxChecked;	
				}else{
					txt += chkBoxNotChecked;
				}		
				txt += "<td>" + currentSearch.createdAt + "</td>";
				if($node.attr("id") == "userFavorites"){
					txt += "<td><a class='btn btn-small deleteSearch' data-searchid='" + currentSearch.id + "' href='#'><i class='fa fa-trash'></i></a></td></tr>";					
				}else{
					txt += "<td></td></tr>";
				}
				
			}
			//Append the txt.
			$node.append(txt);			
			
		}
		
		
		/**
			Show the trending reports data in he appropriate tab.
		**/
		function showTrending(){
				
				$.get("/Fountain/rest-services/report/trending/DAY", function( reports ) {
					console.log("DAY");
					console.log(reports.reverse());
					appendReports(reports.reverse(), $("#popularToday"));
				});							
				$.get("/Fountain/rest-services/report/trending/WEEK", function( reports ) {
					console.log("WEEK");
					console.log(reports.reverse());
					appendReports(reports.reverse(), $("#popularWeek"));
				});			
				$.get("/Fountain/rest-services/report/trending/MONTH", function( reports ) {
					console.log("MONTH");
					console.log(reports.reverse());
					appendReports(reports.reverse(), $("#popularMonth"));
				});			
				$.get("/Fountain/rest-services/report/trending/YEAR", function( reports ) {
					console.log("YEAR");
					console.log(reports.reverse());
					appendReports(reports.reverse(), $("#popularYear"));
				});							
		};
		
		/**
			Append a list of reports(Data in JSON format rendered from ES) to the passed JQ node.
		**/
		function appendReports(reports, $node){
			var txt = '';
			$node.empty();
			for (var i=0;i<reports.length;i++)
			{
				var currentReport = reports[i];
				var currentReportName = ofwat.escapeHTML(currentReport.name);
				txt += "<tr><td><a href='javascript:ofwat.reportPage.selectReport(\"reportDisplay.page?reportId=" + currentReport.id + "\")'>" + currentReportName + "</a>";
				txt +="</td>";
				txt +="<td>"+currentReport.lastModified+"</td>";
				txt = txt + "<td>";
				txt += "<a href='javascript:ofwat.reportPage.selectReport(\"reportDisplay.page?reportId=" + currentReport.id + "\")' title='View "+currentReportName+"'/><img border='0' src='../../images/table.png' alt='View report'/></a>&nbsp;";
				txt += "<a href='javascript:ofwat.reportPage.populateReport(\"/Fountain/rest-services/report/\", \"" + currentReport.id +"\")' title='Download "+currentReportName+" as spreadsheet'/><img border='0' src='../../images/page_excel.png' alt='Export report to Excel'/></a>&nbsp;";
					
				if (currentReport.redesign) {
					txt = txt + "<a name='editReport' title='Redesign "+ currentReportName+"' href='adhocReport.jsp?reportId="+currentReport.id+"' alt='Edit "+currentReportName+"'><img border='0' src='../../images/pencil.png'/></a>&nbsp;";
				}
					// Delete and publish activities only available for your own team's reports
				if (ofwat.reportPage.isMyReport(currentReport)) {
					// publish/unpublish is not relevant for the general fountain users group.
					if (currentReport.team != "Universal") {
						if (!currentReport.publicReport){
							// it's not public so allow it to be published
							txt = txt + "<a name='publishReport' title='Publish "+ currentReportName+"' href='javascript:ofwat.reportPage.publishReport(\""+currentReport.id+"\", \""+currentReportName+"\")' alt='Publish "+currentReportName+"'><img border='0' src='../../images/world_go.png'/></a>&nbsp;";
						} else {
							// show the unpublish button
							txt = txt + "<a name='hideReport' title='Make "+ currentReportName+" no longer public' href='javascript:ofwat.reportPage.hideReport(\""+currentReport.id+"\", \""+currentReportName+"\")' alt='Hide "+currentReportName+"'><img border='0' src='../../images/arrow_undo.png'/></a>&nbsp;";
						}
					}
					txt = txt + "<a name='deleteReport' title='Delete "+ currentReportName+"' href='javascript:ofwat.reportPage.deleteReport(\""+currentReport.link.uri+"\",\""+currentReportName+"\")' alt='Delete "+currentReportName+"'><img border='0' src='../../images/bin_closed.png'/></a>&nbsp;";
				}
				txt = txt + "</td></tr>";						
			}
			//Append the txt.
			$node.append(txt);			
		}
		
		/**
			Get the results for the querystring and the passed parameters. 
			reset - Whether to reset the search results display?? TODO - not sure about this.
			passedStartRow - The start row to return data from in the ES results. 
			queryString - The querystring text froim the user. 
		**/
		function getResults(reset, passedStartRow, queryString){
			var query = $("#field").val();
			//get the classes relative to the activeTab!
			var $resultsList = $($(activeTab).find(".resultsList")[0]); 
			var $resultsCount = $($(activeTab).find(".resultsCount")[0]);
			var $resultsContainer = $($(activeTab).find(".resultsContainer")[0]);

			$resultsList.empty();
			$resultsCount.empty();	
			
			$("#errorMessage").remove();

			
			var showDeleted = $("#showDeleted").is(':checked');
			var showMyDataOnly = $("#showMyDataOnly").is(':checked');
			var sortByDate = $("#sortByDate").is(':checked');

			if(searchType =="report" || searchType == "myReports"){
				if(!showDeleted){
					if(query == ""){
						query = query + "deleted:false";
					}else{
						query = query + " AND deleted:false";
					}
				}
			}
			if(showMyDataOnly){
				if(query == ""){
					query = query + "user:" + userName;
				}else{
					query = query + " AND user:" + userName;	
				}
			}	
			
			//Show the progress bar set to 10%
			$resultsList.append('<img id="theImg" src="../../images/ajax-loader.gif" />')
			
			var qs = '';
			
			//Ok the querystring is null when we click on a tab with no search e.g. 'My Reports'
			if(queryString == null){
				qs = "/Fountain/rest-services/search?type=" + searchType + "&start=" + passedStartRow + "&query=" + encodeURIComponent(query) + "&deleted=" + showDeleted + "&showMyDataOnly=" + showMyDataOnly;
			}else{				
				qs = "/Fountain/rest-services/search?type=" + searchType + "&start=" + passedStartRow + "&query=" + encodeURIComponent(queryString) + "&deleted=" + showDeleted + "&showMyDataOnly=" + showMyDataOnly;
			}
			
			if(sortByDate){
				qs = qs + "&sort=lastModified:" + $("#sortOrder").val()
			}else{
				qs = qs + "&sort="
			}
			
			
			//Do the GET to get results. 
			$.get( qs, function( data ) {
				
				//Record this search in the search history.
				saveSearch(false);
				
				
				//console.log(data);
				//clear the results div
				if(globalReset == true){
					if(searchState[searchType].pager != null){
						//searchState[searchType].pager.reset();
						searchState[searchType].pager.children().remove();
					}
					searchState.report.reset = true;
					searchState.item.reset = true;
					searchState.table.reset = true;
					searchState.report.startRow = 0;
					searchState.item.startRow = 0;
					searchState.table.startRow = 0;
					searchState.report.endRow = PAGE_INCREMENT;
					searchState.item.endRow = PAGE_INCREMENT;
					searchState.table.endRow = PAGE_INCREMENT;					
					
					//startRow = 0;
					//endRow = startRow + PAGE_INCREMENT;
				}else{
					if(searchState[searchType].pager == null){
						searchState[searchType].startRow = 0;
					}else{
						searchState[searchType].startRow = passedStartRow;//searchState[searchType].startRow + PAGE_INCREMENT; //What about if we go back - need to set it to the clicked value....
					}
					searchState[searchType].endRow = searchState[searchType].startRow + PAGE_INCREMENT;
				}
				
				
				var pager = null;
				activeTab.find(".searchPager").hide();
				
				

						if(data.hits.total <= 0){
							$("#theImg").remove();
							$resultsList.append('<li>Found 0 results!</li>');
						}else{
							//populate with a list of the report titles and links to the reports. 
							var results = [];
							
							
							/**
								If we are searching for items
							**/
							if(searchType == "item"){
							
								$.each(data.hits.hits, function(i, result) {
							        
									var reportString = '<ul class="collapsibleList"><li>Reports<ul class="collapsibleList">';
								
									$.each(result._source.reports, function(i, report) {
										reportString = reportString + '<li><div class="report"><a class="gs-title" href="/Fountain/jsp/protected/reportDisplay.page?reportId=' + report.id + '">' + report.name + '</a></div></li>';
									});
									reportString = reportString + '</ul></li></ul>';
									
									if(result._source.reports.length == 0){
										reportString = '';
									}
									
									var tableString = '<ul class="collapsibleList"><li>Tables<ul class="collapsibleList">';
									
									$.each(result._source.tables, function(i, table) {
										tableString = tableString + '<li><div class="report"><a class="gs-title" href="#">' + table.name + '</a></div>';
										tableString = tableString + '<div class="report"><a class="gs-title" href="#">' + table.model_name + ' - ' + table.model_code +'</a></div></li>';
									});
									tableString = tableString + '</ul></li></ul>';
									
									if(result._source.tables.length == 0){
										tableString = '';
									}
									
									
									results.push('<li class="gs-result">' +
							        	'<span class="gs-result-title">' + result._source.code + '</span>' + 
							        	'<div><div class="gs-title" style="float:left">Unit: ' + result._source.unit + '</div>' +
							        	'<div class="gs-snippet" style="float:left; padding-left: 1em;">' + result._source.description + '</div><span class="clear"></span></div>' +	
							        	reportString + ' ' + tableString  + '</li>');
				       		    });//close each()
				       		    
				       		    //render the pager here
				       		    if(searchState.item.reset || globalReset){
				       		    	//searchState.item.pager = new Pager($resultsContainer.find('.searchPager'), true, PAGE_INCREMENT, data.hits.total, PAGE_INCREMENT, function(passedSR){startRow=passedSR;getResults(false,passedSR);});
				       		    	//pager = searchState.item.pager;
				       		    	
				       		    	 var pNode = $($(activeTab).find(".resultsContainer")[0]).find('.searchPager');
				       		    	 var _this = this;
				       		    	 pNode.bootpag({total: Math.ceil(data.hits.total/PAGE_INCREMENT), maxVisible: PAGE_INCREMENT, page: 1, firstLastUse: true, first: '&larr;', last: '&rarr;',}).on("page", function(event, num){
				       		    		searchState.item.startRow = (num-1)*PAGE_INCREMENT;
				       		    		getResults(false, (num-1)*PAGE_INCREMENT);
				       		    	 });
				       		    	searchState.item.pager = pNode; 
				       		    	searchState.item.reset = false;
				       		    }
							/**
								If we are searching for reports...
							**/				       		    
							}else if (searchType =="report"){
								$resultsList.hide();
								$.each(data.hits.hits, function(i, result) {
									var reportDescriptionText = result._source.description;
									if(reportDescriptionText == null){
										reportDescriptionText = "No description available.";
									}
									var description = "";
									//if they are an admin or the report owner then let them add a desc. 
									if(result._source.user == userName || isAdmin){ //userGroup Check required to see if current user is admin...
										console.log("Creating input form for description");
										//description = '<textarea placeholder="Please enter a description for this report." style="width:95%" rows="2"></textarea>';
										if(result._source.description == null){
											description = '<form class="form-inline descriptionForm" data-reportid="' + result._source.id + '" style="display:none;"><div class="control-group"><div class="controls"><textarea placeholder="Please enter a description." style="width:50%; float:left; line-height:15px;" rows="1"></textarea><div class="btn-toolbar btn-toolbar-no-margin"><div class="btn-group"><button class="saveDescription btn btn-small"><i class="fa fa-save"></i></button><button class="btn btn-small hideDescForm"><i class="fa fa-close"></i></button></div></div></div></div></form>';
										}else{
											description = '<form class="form-inline descriptionForm" data-reportid="' + result._source.id + '" style="display:none;"><textarea placeholder="Please enter a description." style="width:50%; float:left; line-height:15px;" rows="1">' + reportDescriptionText + '</textarea><div class="btn-toolbar btn-toolbar-no-margin"><div class="btn-group"><button class="saveDescription btn btn-small"><i class="fa fa-save"></i></button></button><button class="btn btn-small hideDescForm"><i class="fa fa-close"></i></button></div></div></form>';
										}
										description = description + "<div class='descriptionEdit' data-reportid='" + result._source.id + "'>" + reportDescriptionText + "</div>";
									}else{
										description = '<div>' + reportDescriptionText + '</div>';
									}
												
															
									var tagList = '';
									if(result._source.tags){
										for(var i=0;i<result._source.tags.length;i++){
											tagList += result._source.tags[i] + ',';
										}
										//taglist = result._source.tags;
									}
									
									//description = description + '<div style="float:right"><input data-reportid="' + result._source.id + '" type="text" value="' + tagList + '" class="tagsInput" data-role="tagsinput"></input></div>';
									//Create the report node.
									
									var selected = '';
									if((selectedReports != null) && (selectedReports.contains(result._source.id))){
										selected = SELECTED_REPORT_STYLE;
									}
									
									var reportNode = '<li class="reportResult gs-result selectable99 shadow ' + selected + '" data-reportid="' + result._source.id + '" data-reportname="' + result._source.name + '">' +
						        		'<div style="float:left"><a class="gs-result-title" href="/Fountain/jsp/protected/reportDisplay.page?reportId=' + result._source.id + '">' + result._source.name + '</a></div><div class="gs-title" style="float:right">' + new Date(result._source.lastModified).toUTCString() + '</div><span class="clear"></span>' + 
						        		'<div><div class="gs-title" style="float:left">Created By: ' + result._source.user + '</div><div class="gs-title" style="float:right; width:150px">'; 

				        			
				        			reportNode = reportNode + '<div style="float:right; color:#333333">' + 
				        				'<div class="btn-toolbar btn-toolbar-no-margin">' + 
				        			  	'<div class="btn-group">'; 
				        			  	
				        			  	if(result._source.deleted){
				        			  		reportNode = reportNode + '<a data-toggle="tooltip" title="This report is marked as deleted." class="btn btn-small" href="#"><i class="fa fa-trash-o"></i></a>'
				        			  	}				        			  	
				        			  	if(result._source.readOnly){
				        			  		reportNode = reportNode + '<a data-toggle="tooltip" title="This report is marked read only." class="btn btn-small disabled" href="#"><i class="fa fa-lock"></i></a>'
				        			  	}else{
				        			  		reportNode = reportNode + '<a data-toggle="tooltip" title="Unlock this report." class="btn btn-small disabled" href="#"><i class="fa fa-unlock"></i></a>'
				        			  	}
				        			    reportNode = reportNode + '<a data-toggle="tooltip" title="View report." class="btn btn-small reportAction" data-action="view" href="#"><i class="fa fa-table"></i></a>' +
				        			    '<a data-toggle="tooltip" title="Export report to Excel." class="btn btn-small reportAction" data-action="export" href="#"><i class="fa fa-file-excel-o"></i></a>' +
				        			    '<a data-toggle="tooltip" title="Edit this report." class="btn btn-small reportAction" data-action="edit" href="#"><i class="fa fa-pencil"></i></a>' +
				        			    '<a data-toggle="tooltip" title="Share this report (Coming Soon!)." class="btn btn-small reportAction" data-action="share" href="#"><i class="fa fa-share-alt"></i></a>' +
				        			  	'</div>' + 
				        				'</div>' + 
				        				'</div></div><span class="clear"></span></div>'				        				
				        			
				        			reportNode = reportNode + '<div><div class="gs-snippet" id="">' + description + '</div><ul class="collapsibleList"><li data-reportid="' + result._source.id + '" data-reportname="' + result._source.name + '" class="reportItemList">Items<ul class="" id="item_list_' + result._source.id + '"></ul></li></ul></div></li>'; 
									
									reportNode = $(reportNode);
									$resultsList.append(reportNode);
									reportNode.click(getItems);
									
									
									
				       		    });// close each()		
				       		    
				       		    //Add the handlers for the buttons each time they are rendered. 
				       			$(".reportAction").click(function(event){
				       				event.preventDefault();
				       				event.stopPropagation();			
				       				var action = $(event.currentTarget).data("action");
				       				var reportId = $(event.currentTarget).parents('li').data('reportid');
				       				switch(action){
				       				case "view":
				       					//Navigate to the report. 
				       					window.location.href = "/Fountain/jsp/protected/reportDisplay.page?reportId=" + reportId;
				       				break;
				       				case "export":
				       					//Navigate to excel export.
				       					ofwat.reportPage.populateReport("/Fountain/rest-services/report/", reportId);
				       				break;
				       				case "share":
				       					//Show coming soon notice.
				       				break;
				       				case "edit":
				       					//Navigate to adhoc report.
				       					window.location.href = "adhocReport.jsp?reportId=" + reportId;
				       				break;
				       				}
				       			});				       		    
				       		    
				       		    //this isnt being reset!!!!
				       		    if(searchState.report.reset || globalReset){
				       		    	//searchState.report.pager = new Pager($resultsContainer.find('.searchPager'), true, PAGE_INCREMENT, data.hits.total, PAGE_INCREMENT, function(passedSR){startRow=passedStartRow;getResults(false, passedSR);});
				       		    	//pager = searchState.report.pager;
				       		    	
				       		    	 var pNode = $($(activeTab).find(".resultsContainer")[0]).find('.searchPager');
				       		    	 var _this = this;
				       		    	 pNode.bootpag({total: Math.ceil(data.hits.total/PAGE_INCREMENT), maxVisible: PAGE_INCREMENT, page: 1, firstLastUse: true, first: '&larr;', last: '&rarr;',}).on("page", function(event, num){
				       		    		searchState.report.startRow = (num-1)*PAGE_INCREMENT;
				       		    		getResults(false, (num-1)*PAGE_INCREMENT);
				       		    	 });
				       		    	searchState.report.pager = pNode; 
				       		    	searchState.report.reset = false;
				       		    }		
				       		    
				       		 	// TAGGING
				       		 	// We need to add the tag data too...
				       		 	//
				       		 	/*
				       		    $('.tagsInput').tagsinput('refresh');
				       		 	$('.tagsInput').on('beforeItemAdd', function(event){
				       		 		alert("added item " + $(event.currentTarget).attr('data-reportid'));
				       		 	});
				       		 	$('.tagsInput').on('beforeItemRemove', function(event){
				       		 		alert("remove item " + $(event.currentTarget).attr('data-reportid'));
				       		 	});				       		 	
				       		 	*/
				       		 	
				       		 	$('[data-toggle="tooltip"]').tooltip(); 
							/**
								If we are searching for tables...
							**/
							}else if (searchType =="table"){
								console.log("searchType table");
								$.each(data.hits.hits, function(i, result) {
									//console.log(result);
									
									var itemString = '<ul class="collapsibleList"><li>Items<ul class="collapsibleList">';
									
									$.each(result._source.items, function(i, item) {
										itemString = itemString + '<li><div class="item"><span style="display:inline-block; vertical-align:middle;"><div class="gs-title" style="float:left">' + item.code + '</div><div class="gs-snippet" style="float:left; padding-top:3px;">' + item.description + '</div></span></div><span class="clear"></span></li>';
									});
									
									itemString = itemString + '</ul></li></ul>';
									
									var url = '/Fountain/jsp/protected/tables.jsp?modelcode=' + encodeURIComponent(result._source.model_id + '#' + result._source.model_code) + '&tablename=' + encodeURIComponent(result._source.name)
									results.push('<li class="gs-result"><a class="gs-result-title tableResult" data-tablename="' + result._source.name + '" data-modelcode="' + result._source.model_id + '#' + result._source.model_code +'"  href="' + url + '">' + result._source.name + '</a><div><div class="gs-title" style="float:left">Model Name: ' + result._source.model_name + '</div><div class="gs-title" style="float:left">&nbspModel Code:' + result._source.model_code + '</div><span class="clear"></span></div>' + itemString + '</li>');
									
					       		    if(searchState.table.reset || globalReset){
					       		    	//searchState.table.pager = new Pager($resultsContainer.find('.searchPager'), true, PAGE_INCREMENT, data.hits.total, PAGE_INCREMENT, function(passedSR){startRow=passedStartRow;getResults(false, passedSR);});
					       		    	//pager = searchState.table.pager;
				       		    	 	var pNode = $($(activeTab).find(".resultsContainer")[0]).find('.searchPager');
				       		    	 	var _this = this;
				       		    	 	pNode.bootpag({total: Math.ceil(data.hits.total/PAGE_INCREMENT), maxVisible: PAGE_INCREMENT, page: 1, firstLastUse: true, first: '&larr;', last: '&rarr;',}).on("page", function(event, num){
				       		    	 		searchState.table.startRow = (num-1)*PAGE_INCREMENT;				       		    	 		
					       		    		getResults(false, (num-1)*PAGE_INCREMENT);
					       		    	 });
					       		    	searchState.table.pager = pNode; 
					       		    	searchState.table.reset = false;
					       		    }										
									
								});
							/**
								We are displaying 'my reports'.
							**/
							}else if (searchType =="myReports"){
								
								$resultsList.hide();
								$.each(data.hits.hits, function(i, result) {
									var reportDescriptionText = result._source.description;
									if(reportDescriptionText == null){
										reportDescriptionText = "No description available.";
									}
									var description = "";
									//if they are an admin or the report owner then let them add a desc. 
									if(result._source.user == userName || isAdmin){ //userGroup Check required to see if current user is admin...
										console.log("Creating input form for description");
										//description = '<textarea placeholder="Please enter a description for this report." style="width:95%" rows="2"></textarea>';
										if(result._source.description == null){
											description = '<form class="form-inline descriptionForm" data-reportid="' + result._source.id + '" style="display:none;"><div class="control-group"><div class="controls"><textarea placeholder="Please enter a description." style="width:50%; float:left; line-height:15px;" rows="1"></textarea><div class="btn-toolbar btn-toolbar-no-margin"><div class="btn-group"><button class="saveDescription btn btn-small"><i class="fa fa-save"></i></button><button class="btn btn-small hideDescForm"><i class="fa fa-close"></i></button></div></div></div></div></form>';
										}else{
											description = '<form class="form-inline descriptionForm" data-reportid="' + result._source.id + '" style="display:none;"><textarea placeholder="Please enter a description." style="width:50%; float:left; line-height:15px;" rows="1">' + reportDescriptionText + '</textarea><div class="btn-toolbar btn-toolbar-no-margin"><div class="btn-group"><button class="saveDescription btn btn-small"><i class="fa fa-save"></i></button></button><button class="btn btn-small hideDescForm"><i class="fa fa-close"></i></button></div></div></form>';
										}
										description = description + "<div class='descriptionEdit' data-reportid='" + result._source.id + "'>" + reportDescriptionText + "</div>";
									}else{
										description = '<div>' + reportDescriptionText + '</div>';
									}
																		
									//Create the report node.
									
									var selected = '';
									if((selectedReports != null) && (selectedReports.contains(result._source.id))){
										selected = SELECTED_REPORT_STYLE;
									}
									
									var reportNode = '<li class="reportResult gs-result selectable99 shadow ' + selected + '" data-reportid="' + result._source.id + '" data-reportname="' + result._source.name + '">' +
						        		'<div style="float:left"><a class="gs-result-title" href="/Fountain/jsp/protected/reportDisplay.page?reportId=' + result._source.id + '">' + result._source.name + '</a></div><div class="gs-title" style="float:right">' + new Date(result._source.lastModified).toUTCString() + '</div><span class="clear"></span>' + 
						        		'<div><div class="gs-title" style="float:left">Created By: ' + result._source.user + '</div><div class="gs-title" style="float:right; width:150px">'; 

				        			
				        			reportNode = reportNode + '<div style="float:right; color:#333333">' + 
			        				'<div class="btn-toolbar btn-toolbar-no-margin">' + 
			        			  	'<div class="btn-group">'; 
			        			  	
			        			  	if(result._source.deleted){
			        			  		reportNode = reportNode + '<a data-toggle="tooltip" title="This report is marked as deleted." class="btn btn-small" href="#"><i class="fa fa-trash-o"></i></a>'
			        			  	}				        			  	
			        			  	if(result._source.readOnly){
			        			  		reportNode = reportNode + '<a data-toggle="tooltip" title="This report is marked read only." class="btn btn-small disabled" href="#"><i class="fa fa-lock"></i></a>'
			        			  	}else{
			        			  		reportNode = reportNode + '<a data-toggle="tooltip" title="Unlock this report." class="btn btn-small disabled" href="#"><i class="fa fa-unlock"></i></a>'
			        			  	}
			        			    reportNode = reportNode + '<a data-toggle="tooltip" title="View report." class="btn btn-small reportAction" data-action="view" href="#"><i class="fa fa-table"></i></a>' +
			        			    '<a data-toggle="tooltip" title="Export report to Excel." class="btn btn-small reportAction" data-action="export" href="#"><i class="fa fa-file-excel-o"></i></a>' +
			        			    '<a data-toggle="tooltip" title="Edit this report." class="btn btn-small reportAction" data-action="edit" href="#"><i class="fa fa-pencil"></i></a>' +
			        			    '<a data-toggle="tooltip" title="Share this report (Coming Soon!)." class="btn btn-small reportAction" data-action="share" href="#"><i class="fa fa-share-alt"></i></a>' +
			        			  	'</div>' + 
			        				'</div>' + 
			        				'</div></div><span class="clear"></span></div>'			        				
				        			
				        			reportNode = reportNode + '<div class="gs-snippet" id="">' + description + '</div><ul class="collapsibleList"><li data-reportid="' + result._source.id + '" data-reportname="' + result._source.name + '" class="reportItemList">Items<ul class="" id="item_list_' + result._source.id + '"></ul></li></ul></li>'; 
									
									reportNode = $(reportNode);
									$resultsList.append(reportNode);
									reportNode.click(getItems);								
				       		    });// close each()		
				       		    
				       		    //this isnt being reset!!!!
				       		    if(searchState.myReports.reset || globalReset){
				       		    	//searchState.myReports.pager = new Pager($resultsContainer.find('.searchPager'), true, PAGE_INCREMENT, data.hits.total, PAGE_INCREMENT, function(passedSR){startRow=passedStartRow;getResults(false, passedSR);});
				       		    	//pager = searchState.myReports.pager;
				       		    	 var pNode = $($(activeTab).find(".resultsContainer")[0]).find('.searchPager');
				       		    	 var _this = this;
				       		    	 pNode.bootpag({total: Math.ceil(data.hits.total/PAGE_INCREMENT), maxVisible: PAGE_INCREMENT, page: 1, firstLastUse: true, first: '&larr;', last: '&rarr;',}).on("page", function(event, num){
				       		    		searchState.myReports.startRow = (num-1)*PAGE_INCREMENT; 
				       		    		getResults(false, (num-1)*PAGE_INCREMENT);
				       		    		
				       		    	 });
				       		    	searchState.myReports.pager = pNode; 	       		    	
				       		    	searchState.myReports.reset = false;
				       		    }	
								/*
								$.each(data.hits.hits, function(i, result) {

									var description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla tempor efficitur sagittis. Proin ac diam a erat auctor interdum. Ut congue quis magna et vehicula. Sed fermentum non neque sit amet gravida. Duis placerat mi lobortis quam bibendum pulvinar. Aenean id ultricies lectus, a pellentesque mauris. Pellentesque magna est, aliquam quis ex in, dignissim laoreet nulla. Mauris pulvinar auctor velit, et ornare risus consequat quis. Mauris volutpat nec quam non luctus. Maecenas nec quam ...";
									if(result._source.description == null){
										console.log("Creating input form for description");
										//description = '<textarea placeholder="Please enter a description for this report." style="width:95%" rows="2"></textarea>';
										//description = '<textarea placeholder="No description available." style="width:95%" rows="2" disabled></textarea>';
										description = '<div>No description available.</div>';
									}
									
									var itemString = '<ul class="collapsibleList"><li>Items<ul class="collapsibleList">';
								
									$.each(result._source.items, function(i, item) {
										itemString = itemString + '<li><div class="item"><span style="display:inline-block; vertical-align:middle;"><div class="gs-title" style="float:left">' + item.code + '</div><div class="gs-snippet" style="float:left">' + item.description + '</div></span></div><span class="clear"></span></li>';
									});
									
									itemString = itemString + '</ul></li></ul>';
									
									results.push('<li class="gs-result">' +
							        	'<a class="gs-result-title" href="/Fountain/jsp/protected/reportDisplay.page?reportId=' + result._source.id + '">' + result._source.name + '</a>' + 
							        	'<div><div class="gs-title" style="float:left">Created By: ' + result._source.user + '</div><div class="gs-title" style="float:right">' + result._source.lastModified + '</div><span class="clear"></span></div>' +
							        	'<div class="gs-snippet">' + description + '</div>' +	
							        	itemString + '</li>');
				       		    });// close each()		
				       		    
				       		    //this isnt being reset!!!!
				       		    if(searchState.myReports.reset){
				       		    	searchState.myReports.pager = new Pager($resultsContainer.find('.searchPager'), true, PAGE_INCREMENT, data.hits.total, PAGE_INCREMENT, function(passedSR){startRow=passedStartRow;getResults(false, passedSR);});
				       		    	pager = searchState.myReports.pager;
				       		    	searchState.myReports.reset = false;
				       		    }
				       		    */
				       			$(".reportAction").click(function(event){
				       				event.preventDefault();
				       				event.stopPropagation();			
				       				var action = $(event.currentTarget).data("action");
				       				var reportId = $(event.currentTarget).parents('li').data('reportid');
				       				switch(action){
				       				case "view":
				       					//Navigate to the report. 
				       					window.location.href = "/Fountain/jsp/protected/reportDisplay.page?reportId=" + reportId;
				       				break;
				       				case "export":
				       					//Navigate to excel export.
				       					ofwat.reportPage.populateReport("/Fountain/rest-services/report/", reportId);
				       				break;
				       				case "share":
				       					//Show coming soon notice.
				       				break;
				       				case "edit":
				       					//Navigate to adhoc report.
				       					window.location.href = "adhocReport.jsp?reportId=" + reportId;
				       				break;
				       				}
				       			});					       		    
				       		    
							}
		       		    		       		 		
							$resultsList.append( results.join('') );	
		       		    
							searchState[searchType].endRow = searchState[searchType].startRow+PAGE_INCREMENT;
			       		 	if(searchState[searchType].endRow > data.hits.total){
			       		 	searchState[searchType].endRow = data.hits.total;
			       		 	}
		
			       		 	$resultsCount.empty();
			       		 	$resultsCount.append("Fetched results "+ searchState[searchType].startRow + "-" + searchState[searchType].endRow + " of " + data.hits.total + " in " + data.took + "ms.");
			       		          		    
			       		 	CollapsibleLists.apply();
			       		 	$("#theImg").remove();
			       		 	
			       		 	$(".hideDescForm").click(function(event){
			       		 		//Get the next sibling and show it...
			       		 		$(event.currentTarget).parents("form").hide()
			       		 		$(event.currentTarget).parents("form").next().show()
			       		 		event.stopPropagation();
			       		 	});
			       		 	
			       		 	
			       		 	$(".saveDescription").click(function(event){
			       		 		//Get the description text if it's not null and send it with ajax POST.
			       		 		var txt = $(event.currentTarget).parents("form").find("textarea").val();
			       		 		var $txtArea = $(event.currentTarget).parents("form").find("textarea");
			       		 		var reportId = $(event.currentTarget).parents("form").data('reportid');
			       		 		event.stopPropagation();
			       		 		if((txt == null) || (txt == '')){
			       		 			//Highlight the description and focus.
	       					        new PNotify({
	       					            title: 'Error',
	       					            text: "Please enter a description.",
	       					            type: 'error'
	       					        });					       					    			       		 			
			       		 			$txtArea.parents(".control-group").addClass("error");
			       		 		}else{
			       		 			var encodedtxt = encodeURI(txt);
			       		 			
			       		 			//Set the save icon to spinner
			       		 			$saveDescriptionIcon = $(".saveDescription").find('i');
			       		 			$saveDescriptionIcon.removeClass('fa-save').addClass('fa-cog fa-spin');
			       		 			
			       		 			
			       		 			$.ajax({
			       						//URL encode txt!?			       					
			       					    url: '/Fountain/rest-services/report/description/' + reportId + '?description=' + encodedtxt,
			       					    type: 'POST',
			       					    success: function(result) {
			       					        //Remove the select notification.
			       					        //Show a success notice. 
			       					        new PNotify({
			       					            title: 'Success',
			       					            text: 'Successfully updated reports description.',
			       					            type: 'success'
			       					        });
			       					        
			       					        //update the description in the page. 
			       					        $(event.currentTarget).parents("form").next().html(txt);
			       					        
			       					        //hide the desc form.
			       		      		 		$(event.currentTarget).parents("form").hide();
						       		 		$(event.currentTarget).parents("form").next().show();
						       		 		event.stopPropagation();
						       		 		
						       		 		//reset the save icon,
					       		 			$saveDescriptionIcon.removeClass('fa-cog fa-spin').addClass('fa-save');

			       					        
			       					    },
			       					    error: function(jqXHR, textStatus, errorThrown){
			       					    	//reset the save icon,
			       					        new PNotify({
			       					            title: 'Error',
			       					            text: "Couldn't update report description.",
			       					            type: 'error'
			       					        });				
			       					     	$saveDescriptionIcon.removeClass('fa-cog fa-spin').addClass('fa-save');
			       					    }
			       					});
			       		 		}
			       		 		
			       		 	});
			       		 	
			       		 	/*
			       		 	$(".descriptionForm").each(function(i, item){
			       		 		$(item).submit(function(e) {
			       		    		e.preventDefault();
			       				});			       		 	
		       		 		});
			       		 	*/
			       		 	
			       		 	$(".descriptionForm").submit(function(e){
			       		    		e.preventDefault();
		       		 		});			       		 	
			       		 	
			       		 	$(".descriptionEdit").click(function(event){
			       		 		var $node = $(event.currentTarget);
			       		 		$node.hide();
			       		 		$node.prev().show();
			       		 		$($node.prev().find("textarea")[0]).focus();
			       		 	});
							
							$(".selectable99").mouseover(function(event){
			       		 		//$(event.currentTarget).addClass('highlightedReport');
			       		 	});
							$(".selectable99").mouseout(function(event){
			       		 		//$(event.currentTarget).removeClass('highlightedReport');
			       		 	});
							
							$(".selectable99").click(function(event){
								if(selectedReports==null){
									//Create  anew selected reports object and add handlers to remove them form the notify. 
									//First param addCallback, second param remove callback. 
									selectedReports = new SelectedReports(
									//add callback
									function(reportName, reportId, $node){
										console.log("Appending a new report select..." + reportName + ":" + reportId);
										$node.addClass(SELECTED_REPORT_STYLE);
										var newOption = $("<option />");
										notice.get().find('#selectedReportsSelect').append(newOption);
										newOption.val(reportId); 
										newOption.dblclick(function(event){
											var option = event.currentTarget;
											var reportId = $(option).val();
											console.log(reportId);
											doSearch('id:' + reportId);
										})
										newOption.html(reportName);
										$("#showSelected").prop('disabled', false);
									}, 
									//remove callback
									function(reportName, reportId, $node){
										//$("#selectedReportsSelect").append('<option value=1>My option</option>');
										notice.get().find('#selectedReportsSelect option[value="' + reportId +'"]').remove();
										$node.removeClass(SELECTED_REPORT_STYLE);
									},
									//empty callback. 
									function(){
										notice.remove();
										$("#showSelected").find("i").removeClass("fa-angle-double-up").addClass("fa-angle-double-down");
										clearSelection();
										//Disable the show button. 
										$("#showSelected").prop('disabled', true);
									});
								}
								if (notice == null){
									notice = new PNotify({
									    text: $('#form_notice').html(),
									    icon: false,
									    width: 'auto',
									    hide: false,
									    styling: "bootstrap2",
									    auto_display:false,
									    buttons: {
									        closer: false,
									        sticker: false
									    },
									    insert_brs: false
									});
									notice.get().find("#btnMakeReadOnly").click(function(){
										var selectArr = [];
										notice.get().find('#selectedReportsSelect option').each(function() {
										    selectArr.push($(this).val());
										});
										//Make the Ajax call.
										markReportsReadOnly(selectArr);
									});		
									notice.get().find("#btnDelete").click(function(){
										var selectArr = [];
										notice.get().find('#selectedReportsSelect option').each(function() {
										    selectArr.push($(this).val());
										});
										//Make the ajax call.
										deleteReports(selectArr);
									});										
									notice.get().find('form.pf-form').on('click', '[name=cancel]', function() {
									    notice.remove();
									});
									notice.get().find("#btnSelectAll").click(function(){
										//Get all the rendered report ids and add them to selectedReports.
										var reportListItems = $(".reportResult");
										$.each(reportListItems, function(i, reportLi) {
											var $li = $(reportLi);
											var reportInfo = {"id":$li.data("reportid"), "name":$li.data("reportname"), "node":$li};
											selectedReports.add(reportInfo);
										});
									});		
									notice.get().find("#btnDeselectAll").click(function(){
										selectedReports.removeAll();
									});									
								}else{
									//If it's not visible, show it...
									//if(notice.state != "open"){
									//	notice.open();
									//}			
								}						
								
			       		 		var $selectedReportLi = $(event.currentTarget);
			       		 		var $itemLi = $($selectedReportLi.find(".reportItemList:first"));
			       		 		
			       		 		var reportName = $itemLi.data("reportname");	       		 		
			       		 		var reportId = $itemLi.data("reportid");	
		       		 			var reportInfo = {"id":reportId, "name":reportName, "node":$selectedReportLi}; 
			       		 		
			       		 		//If we have already selected then remove!
			       		 		
			       		 		if($selectedReportLi.hasClass(SELECTED_REPORT_STYLE)){
			       		 			selectedReports.remove(reportInfo);
			       		 		}else{
			       		 			//Add the report to the list. 
			       		 			selectedReports.add(reportInfo);
			       		 		}
			       		 		
			       		 	});							
							
			       		 	//WE may also need to add an on-click that changes the checkbox....
			       		 	$resultsList.show();
			       		    
			       		 	
			       		 	//render the pager.
			       		 	if(pager != null){
		       		 			pager.prepare();
		       		 			
			       		 	}
			       		    activeTab.find(".searchPager").show();
			       		 	globalReset = false;
			       		 	
					}					
			}).fail(function() {
				//show the failure message and flash the search box red.
				$("#theImg").remove();
				activeTab.append('<div id="errorMessage" class="alert alert-error"><button type="button" class="close" data-dismiss="alert">×</button>'
			              		+ '<strong>Oh snap!</strong> Change a few things up and try submitting again.'
			            		+ '</div>');
				
				activeTab.find(".searchPager").hide();
			});			
		}		

		$("#showSelected").click(function(event){
			event.preventDefault();
			event.stopPropagation();		
			if(notice.state != "open"){
				$("#showSelected").find("i").removeClass("fa-angle-double-down").addClass("fa-angle-double-up");
				notice.open();
			}else{
				$("#showSelected").find("i").removeClass("fa-angle-double-up").addClass("fa-angle-double-down");
				notice.remove();
			}
		});
		
		$("#favoriteButton").click(function(event){
			//Ok get all the search params and then ask for a title or something...
			event.preventDefault();
			event.stopPropagation();
			$('#modalConfirmSaveSearch').modal('show');
		});			
		
		//Check there is a title and save the search. 
		$("#btnSaveSearch").click(function(event){
			event.preventDefault();
			event.stopPropagation();
			console.log("Adding search to favorites.");
			var searchName = $("#modalConfirmSaveSearch").find("input").val();
			if(searchName == "" || searchName == null){
				//Put focus back on the input and ask them to enter a search name.
				
			}else{
				//Save the search.
				saveSearch(true, searchName, function(result) {
				        //Show a success notice. 
				        new PNotify({
				            title: 'Success',
				            text: 'Added search to favorites.',
				            type: 'success'
				        });		
				        //Close the window. 
				        $("#modalConfirmSaveSearch").modal('hide');
				    }, function(jqXHR, textStatus, errorThrown){
   				        new PNotify({
   				            title: 'Error',
   				            text: "Couldn't save search",
   				            type: 'error'
   				        });					       					    	
   				    });
			}
		});			
			
		//When the search button is clicked do a get to the /search resource passing a parameter query in the url.
		$("#searchButton").click(function(){
			globalReset = true;
			getResults(true, 0);
		});
		
		//Helper function to do a search. 
		function doSearch(searchStr){
			$("#field").val("");
			$("#field").val(searchStr);
			getResults(true, 0);
		}
		
		/**
		* Save a users search or a random search history result. 
		**/
		function saveSearch(userFavorite, searchName, successCallback, failureCallback){
			
			var searchString = $("#field").val();
			var includeDeleted = $("#showDeleted").prop('checked');
			var myDataOnly = $("#showMyDataOnly").prop('checked');
			var sortByDate = $("#sortByDate").prop('checked');
			var sortOrder = $("#sortOrder").val();
			var bodyJson = {
					"searchString": searchString,
					"includeDeleted": includeDeleted,
					"myDataOnly": myDataOnly,
					"searchName": searchName,
					"sortByDate": sortByDate,
					"sortOrder": sortOrder,
					"searchType": searchType
			};
			var url = '/Fountain/rest-services/search/history/'
			if(userFavorite){
				url = '/Fountain/rest-services/search/favorite/'
			}
			
		 	$.ajax({
   				//URL encode txt!?			       					
   			    url: url,
   			    type: 'POST',
   			 	processData:false,
   			 	data: JSON.stringify(bodyJson),
   			 	contentType: "application/json",
   			    success: function(result){
   			    	if(successCallback != null){
   			    		successCallback(result);
   			    	}
   			    },
			    error: function(jqXHR, textStatus, errorThrown){
					if(errorCallback != null){
						errorCallback(jqXHR, textStatus, errorThrown);
					}				       					    	
			    }
   			});				
		}

		
		//Add a click handler to enable the sortOrder select. 
		$("#sortByDate").change(function(event){
			if($(event.currentTarget).prop('checked')){
				//Make the search sort order not disabled. 
				$("#sortOrder").prop('disabled', false);
			}else{
				$("#sortOrder").prop('disabled', true);
			}
		});
		
		
		
		//Set up the query - run when the page is loaded.  
		if((getParameterByName('searchId') != null) && (getParameterByName('searchId') != "")){
			console.log("Getting a saved search.");
		 	$.ajax({
   				//URL encode txt!?			       					
   			    url: "/Fountain/rest-services/search/history/" + getParameterByName('searchId'),
   			    type: 'GET',
   			 	contentType: "application/json",
   			    success: function(result){
   					$("#field").val(result.searchString);
   					$("#showDeleted").prop('checked', result.includeDeleted);
   					$("#showMyDataOnly").prop('checked', result.myDataOnly);
   					$("#sortByDate").prop('checked', result.sortByDate);
   					if(result.sortByDate){
   						$("sortOrder").val(result.sortOrder);
   						$("#sortOrder").prop('disabled', false);
   					}
   					
   					//Set the active tab. 
   					var searchType = result.searchType;
   					if((searchType != "") && (searchType != null)){
   						$(".nav-tabs").find("[data-searchtype='" + searchType + "']").click();
   					}
   					
   					//Run the search. 
   					$("#searchButton").click();
   			    },
			    error: function(jqXHR, textStatus, errorThrown){
			    	
			    }
   			});				
		}
		
		
		
		
	});
//<![CDATA[
    //dojo.require("dijit.Tooltip");
//]]>
</script>
<jsp:include page="../footer.jsp" />
</body>
</html>