<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<title>CreateTraining</title>
<style>.error {	color: red;}</style>
</head>
<body>
	<h1>Crear un entrenamiento</h1>
	<form:form action="create-training" modelAttribute="trainingForm">
		<p>CourtId:
			<form:input path="courtId" placeholder="CourtId" />
		</p>
		<p>day of the year to start:
			<form:input path="startDayOfYear" placeholder="startDayOfYear" />
		</p>
		<p>day of the year to end:
			<form:input path="endDayOfYear" placeholder="endDayOfYear" />
		</p>
		<p>Trainer:
			<form:input path="trainer" placeholder="trainer" />
		</p>
		<p><input type="submit" value="Crear"></p>
	</form:form>

	<a href="<c:url value="/home"/>">Home</a>

</body>
</html>