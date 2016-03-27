<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<title>CreateUser</title>
<style>.error {	color: red;}</style>
</head>
<body>
	<h1>Crear un usuario</h1>
	<form:form action="create-user" modelAttribute="user">
		<p>Name:
			<form:input path="username" placeholder="username" />
		</p>
		<p>Email:
			<form:input path="email" placeholder="email" />
		</p>
		<p>Password:
			<form:password path="password" placeholder="password" showPassword="true" />
		</p>
		<p><input type="submit" value="Crear"></p>
	</form:form>

	<a href="<c:url value="/home"/>">Home</a>

</body>
</html>