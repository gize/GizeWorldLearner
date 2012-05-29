<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link href="css/query.css" type="text/css" rel="stylesheet">
<link href="css/quiz.css" type="text/css" rel="stylesheet">

<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" src="js/jquery.dataSelector.js"></script>
<script type="text/javascript" src="js/quiz.js">	</script>

</head>
<body>

	<jsp:include page="header.jsp" />

	<div class="pageCenter">
		QUIZ: ${context}
		<p>
		<div id="quiz" class="squareBorder">
			<%
		List<String> vocabularyList = (List<String>)request.getAttribute("vocabularyList");
		List<String> meaningList = (List<String>)request.getAttribute("meaningList");
		List<Integer> selectedIndices = (List<Integer>)request.getAttribute("selectedIndices");
		%>
			<% for(int i=0, index=0; i<vocabularyList.size(); i++) { %>
			<% if( index < selectedIndices.size() &&  selectedIndices.get(index)==i ){ %>
			<% index++; %>
			<input type="hidden" id=<%="selectedIndices"+i%> name="selectedIndices"
				value="<%= vocabularyList.get(i) %>"> 
			<input id=<%="inputfield"+i%>
				class="inputtext inputColor" type="text" size="15"  title="<%= meaningList.get(i) %>"/>

			<a class="hint normalButton" onclick="getNextLetter('<%= i %>')">?</a>
				
			<% } else {  %>
			<%= vocabularyList.get(i) %>
			<% } //endelse %>

			<% }//endfor %>


		</div>
		<div id="meaningSpan">
			<span>MEANING:</span>
		</div>
		<div id="meaning" class="squareBorder">
		</div>
	</div>

</body>
</html>