<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>

<head>
<link href="css/query.css" type="text/css" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="pageCenter">
		<p />
		<form:form name="input" modelAttribute="context"
			action="/new_context_properties.html" method="post">
			<label for="contextname">DEFINE NEW CONTEXT</label>
			<p />
			<form:input class="inputColor" path="contextname" />
			<span id="contextnameErrors"><form:errors path="contextname" /></span>
			<p />
			<input class="normalButton" type="submit" value="NEXT">
		</form:form>
	</div>
</body>
</html>