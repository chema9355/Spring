<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<title>Registration court fail</title>
</head>

<body>
	<H1>Creación de pista fallida</H1>

	<h3>Se ha producido un error en el registro (${error})</h3>

	<p><a href="<c:url value='/court-list' />">Ir a Lista de pistas</a></p>


</body>
</html>