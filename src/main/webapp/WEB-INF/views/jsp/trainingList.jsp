<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<title>Paddle Trainings</title>
</head>

<body>
    <H1>Listado de entrenamientos</H1>
	<table border="1">
		<thead>
			<tr>
				<th>Id</th>
				<th>Court</th>
				<th>Trainer</th>
				<th>#</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${trainingList}" var="training">
				<tr>
					<td>${training.id}</td>
					<td>${training.court}</td>
					<td>${user.trainer}</td>
					<td><a href="<c:url value='/delete-training/${training.id}' />">delete</a></td>
					<td><a href="<c:url value='/show-reserves/${training.id}' />">show reserves</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p><a href="<c:url value='/create-training'/>">Create Training</a></p>
	<p><a href="<c:url value='/home'/>">Home</a></p>


</body>
</html>