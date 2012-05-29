<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

<link href="css/query.css" type="text/css" rel="stylesheet">
<link href="css/define_text.css" type="text/css" rel="stylesheet">

<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" src="js/jquery.dataSelector.js"></script>
<script type="text/javascript" src="js/define_text.js">
	
</script>

</head>
<body>

	<jsp:include page="header.jsp" />
	<div class="pageCenter">
		CONTEXT: ${contextname}
		<p />
		<span id="warnings"> Please enter your text: </span>

		<div id="selectedArea">
			<div id="removeSelected" class="squareBorder">
				<img id="trashImage" src="img/trash.png"
					alt="Drag into trash to delete" />
			</div>
			<div id="containerSelectedAreaList">
				<div class="info">Drag Items Here</div>
				<ul id="selectedAreaList" class="squareBorder">
				</ul>
			</div>
		</div>

		<div id="inputarea">
			<div id="splittedText" class="squareBorder"></div>
			<textarea class="inputColor" rows="10" cols="50"></textarea>
		</div>

		<input id="goToPhase1" class="normalButton" type="button"
			onClick="goToPhase1()" value="PREV" />
			
		<input id="prevButton" class="normalButton" type="button"
			onClick="location.href='/new_context_properties.html'" value="PREV" />
			
		<button id="splitButton" class="normalButton" type="button"
			onclick="splitText()">TEXT IS READY!</button>

		<div id="newContextForm">
			<form action="/save_new_text.html" method="post">
				<input type="hidden" id="contextName" name="contextName"/> 
				<input type="hidden" id="vocabularyList" name="vocabularyList"/> 
				<input type="hidden" id="selectedIndices" name="selectedIndices"/>
				<input type="hidden" id="meaningArray" name="meaningArray"/>

				<input class="normalButton" type="submit"
					onclick="collectInformation('${contextname}')" value="SAVE CONTEXT"/>
			</FORM>
		</div>
	</div>
</body>
</html>
