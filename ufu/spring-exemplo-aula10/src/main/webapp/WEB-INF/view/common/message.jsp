<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
    
<html>
	<head>
		<meta charset="utf-8">
		<title>Mensagem</title>
	</head>
	<c:url var="backUrl" value="${back}" /> 
	<body>
		<c:if test="${msg.type == 'INFO'}"><h2>Sucesso!</h2></c:if>
		<c:if test="${msg.type == 'ERROR'}"><h2>Erro!</h2></c:if>
		<hr>
		<ul>
			<li>${msg.body}</li>
		</ul>
		<a href="${backUrl}">Voltar</a>
	</body>
</html>
