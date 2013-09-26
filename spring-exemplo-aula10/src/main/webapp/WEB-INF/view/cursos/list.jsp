<!DOCTYPE html>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 
           uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<html>
	<head>
		<meta charset="utf-8">
		<title>Cursos</title>
	</head> 

	<c:url value="/cursos/edit" var="urlEdit" />
	<c:url value="/cursos/delete" var="urlDelete" />
	<c:url value="/cursos/add" var="urlAdd" />
			
	<body>
		<h2>Cursos</h2>
		<hr>
		<table>
			<tr>
				<td>Id</td>
				<td>Nome</td>
				<td>Matriculas</td>
				<td>Ações</td>
			</tr>
			<c:forEach items="${cursos}" var="curso">
				<tr>
					<td>${curso.id}</td>
					<td>${curso.nome}</td>
					<td>
						<c:set var="matriculas" value="${curso.matriculas}"/>
						<c:set var="alunos" value="${fn:length(matriculas)}" />					
						<c:out value="${alunos}" />
					</td>
					<td>
						<a href="${urlEdit}/${curso.id}">Editar</a>
						&nbsp;
						<a href="${urlDelete}/${curso.id}">Excluir</a>
					</td>
				</tr>			
			</c:forEach>
			<tr>
				<td colspan="4">
					<a href="${urlAdd}">Incluir</a>
				</td>
			</tr>
		</table>
	</body>
</html>
