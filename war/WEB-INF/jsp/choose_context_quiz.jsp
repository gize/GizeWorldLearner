<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<link href="css/query.css" type="text/css" rel="stylesheet">

<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" src="js/jquery.dataSelector.js"></script>
<script type="text/javascript" src="js/choose_context_quiz.js">	</script>

</head>
<body>


	<jsp:include page="header.jsp" />

	<div class="pageCenter">
		<form name="input" action="/quiz.html" method="get">
			<label for="contextname">CHOOSE CONTEXT</label>
			<p />
			<select id="context" class="normalSize inputColor" name="context"></select>
			<p />
			<input class="normalButton" type="button" onClick="location.href='/choose_mode.html'" value="PREV"/>
			<input class="normalButton" type="submit" value="NEXT"/>
		</FORM>
	</div>
</body>
</html>