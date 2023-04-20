<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="model.Coordenadores"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lista</title>
<link rel="stylesheet" href="coordenador.css">
</head>

<body>
	<h1>Lista de Coordenadores</h1>
	<section class="area-coord">
		<c:forEach items="${listaCoordenadores}" var="coord">
			<div class="div-coordenador">
				<div class="nome-coordenador">${coord.nome}</div>
				<ul class="lista-cursos">
					<c:forEach items="${coord.cursos}" var="curso">
						<li>${curso.nome}</li>
					</c:forEach>
				</ul>
				<div class="horarios-coordenador">
					<c:forEach items="${coord.periodos}" var="periodo">
						${periodo.dia} (${periodo.horario}) <br>
					</c:forEach>
				</div>
				<a href="editar?id=${coord.id}">Editar</a>
				<a href="deletar?id=${coord.id}">Deletar</a>
			</div>
		</c:forEach>
	</section>
</body>
</html>