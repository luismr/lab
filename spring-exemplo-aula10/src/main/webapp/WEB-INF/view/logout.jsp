<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<% session.invalidate(); %>

<html>
	<head>
		<meta charset="utf-8">
		<title>Logout</title>
	</head> 
	<body>
		<h1>Logout efetuado com sucesso!</h1>
		<c:url value="/" var="root" />
		<a href="${root}">Voltar</a>
	</body>
</html>
