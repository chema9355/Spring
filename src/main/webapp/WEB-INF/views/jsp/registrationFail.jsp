<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<title>Registration training fail</title>
</head>

<body>
	<H1>Creación de entrenamiento fallida</H1>

	<h3>Se ha producido un error en el registro (${error})</h3>

	<p><a href="<c:url value='/user-list' />">Ir a Lista de usuarios</a></p>


</body>
</html>