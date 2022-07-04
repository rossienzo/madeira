<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    
	    <link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" >
		<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">

	    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/style/reset.css">
	    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/style/style.css">
	    
		<title>Madeira OO</title>
	</head>
	<body>
		<div class="wrapper">
			<header>
				<div class="container">
					<h1><a href="/madeira">Madeira OO</a></h1>
				</div>
			</header>
			<main id="page-login">
				<div class="container">
					<form id="form-login" action="/madeira/ServletLogin" method="post">
						<h3>Entrar</h3>
						<label for="name">Nome</label>
						<input id="name" type="text" name="name">	

						<label for="password">Senha</label>
					 	<input type="password" name="password">
						 
					 	<input type="submit" value="Entrar">
						
						<c:if test="${ msg.text != null}">
							<p class="msg ${ msg.type }"> ${ msg.text } </p>
						</c:if>
					</form>
					
					<form id="form-create" action="/madeira/ServletCreate" method="post">
						<h3>Registra-se</h3>
						<label for="name">Nome</label>
						<input id="name" type="text" name="name" value="${ cliente.nome }">	
						
						<label for="phone">Telefone</label>
						<input id="phone" type="text" name="phone" value="${ cliente.telefone }">
						
						<label for="password">Senha</label>
					 	<input id="password" type="password" name="password" value="${ cliente.senha }">
						 
					 	<input type="submit" value="Enviar">
						
						
						<c:if test="${ msg2.text != null }">
							<p class="msg ${ msg2.type }">${ msg2.text }</p>
						</c:if>
					</form>
				</div>
			</main>
		</div>
	</body>
</html>

