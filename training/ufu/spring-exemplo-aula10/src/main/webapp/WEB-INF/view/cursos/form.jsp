<!DOCTYPE html>

<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 
           uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" 
		   uri="http://www.springframework.org/tags"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<html>
	<head>
		<meta charset="utf-8">
		<title>Cursos</title>
	</head>
	 
	<c:url var="backUrl" value="/cursos/list" />
	<c:url var="actionUrl" value="/cursos/save"/>
	 
	<c:if test="${action == 'add'}">
		<c:set var="actionLabel" value="Incluir" />
	</c:if> 
	<c:if test="${action == 'edit'}">
		<c:set var="actionLabel" value="Editar" />
	</c:if> 
	<c:if test="${action == 'delete'}">
		<c:set var="actionLabel" value="Excluir" />
	</c:if>

	<script type="text/javascript">
		function back() {
			document.location.href="${backUrl}";
		}	
	</script>
	 
	<body>
		<h2>Cursos :: ${actionLabel}</h2>
		<hr>
		<form method="post" action="${actionUrl}/${action}">
			<table>
				<tr>
					<td>Id:</td>
					<td><input name="id" type="text" value="${curso.id}" ${action != 'add' ? 'readonly' : '' }/></td>
				</tr>
				<tr>
					<td>Nome:</td>
					<td><input name="nome" type="text" value="${curso.nome}" ${action == 'delete' ? 'readonly' : '' }/></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="${actionLabel}">
						<input type="button" value="Cancelar" onclick="javascript:back();">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
