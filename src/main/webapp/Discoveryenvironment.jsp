<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<!-- The HTML 4.01 Transitional DOCTYPE declaration-->
<!-- above set at the top of the file will set     -->
<!-- the browser's rendering engine into           -->
<!-- "Quirks Mode". Replacing this declaration     -->
<!-- with a "Standards Mode" doctype is supported, -->
<!-- but may lead to some differences in layout.   -->

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />   <!-- Remove this when GWT supports IE9 -->

<!--                                                               -->
<!-- Consider inlining CSS to reduce the number of requested files -->
<!--                                                               -->
<link type="text/css" rel="stylesheet" href="gxt/css/gxt-all.css">
<link type="text/css" rel="stylesheet" href="gxt/css/gxt-gray.css">
<link type="text/css" rel="stylesheet" href="Discoveryenvironment.css">
<link type="image/x-icon" rel="shortcut icon" href="images/favicon.ico">

<link type="text/css" rel="stylesheet" href="gxt-form-iplant.css">

<!-- set by i18n code -->
<title></title>

<!--                                           -->
<!-- This script loads your compiled module.   -->
<!-- If you add any GWT meta tags, they must   -->
<!-- be added before this line.                -->
<!--                                           -->


<script type="text/javascript" language="javascript"
	src="discoveryenvironment/discoveryenvironment.nocache.js"></script>
<!-- Google analytics -->
<script type="text/javascript">
  var _gaq = _gaq || [];

  _gaq.push(['_setAccount', 'UA-16039757-1']);

  _gaq.push(['_trackPageview']);
  (function() {

    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;

    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';

    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);

  })();

</script>
</head>

<!--                                           -->
<!-- The body can have arbitrary html, or      -->
<!-- you can leave the body empty if you want  -->
<!-- to create a completely dynamic UI.        -->
<!--                                           -->
<body>
<!-- 
	<div id="x-desktop">
		<dl id="x-shortcuts"></dl>
	</div>
 -->
	<!-- include for history support -->
	<iframe src="javascript:''" id="__gwt_historyFrame" 
		style="position: absolute; width: 0; height: 0; border: 0">
	</iframe>

</body>

</html>
