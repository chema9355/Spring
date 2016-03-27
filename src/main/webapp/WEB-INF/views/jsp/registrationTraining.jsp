<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<title>Training registration</title>
</head>

<body>
	<H1>Resultado creacion de entrenamiento</H1>

	<h3>(${result})</h3>

	<p><a href="<c:url value='/training-list' />">Ir a Lista de entrenamientos</a></p>


</body>
</html>