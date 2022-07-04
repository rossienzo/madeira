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
		<div id="body-image"></div>
		<div class="wrapper">
			<header>
				<div class="container">
					<h1><a href="/madeira">Madeira OO</a></h1>
					<nav>
						<ul>
							<li><a href="/madeira" class="selected">Início</a></li>
							<li><a href="./view/login.jsp">Login</a></li>
						</ul>
					</nav>
				</div>
			</header>
			<main>
				<div class="container">
					<section id="about">
						<h1 class="title">Sobre</h1>
						<div class="description">
							<div class="description-text">
								<p>
							        Lorem ipsum, dolor sit amet consectetur adipisicing elit. Architecto esse cupiditate autem praesentium, fuga modi, harum temporibus quae eveniet nisi veniam debitis, placeat aliquam voluptatibus. Totam incidunt alias eaque accusamus.
							    </p>
							    <p>
							        Lorem ipsum, dolor sit amet consectetur adipisicing elit. Architecto esse cupiditate autem praesentium, fuga modi, harum temporibus quae eveniet nisi veniam debitis, placeat aliquam voluptatibus. Totam incidunt alias eaque accusamus.
							    </p>
							</div>
						    <img src="res/img/img-1.jpg">
						</div>
					</section>
				</div>
				<section id="nosso-time">
					<h1>Nosso Time</h1>
					<div class="menu-cards">
						<div class="card">
						  <img src="res/img/team-1.jpg" alt="Avatar" style="width:100%">
						  <div class="detail">
						    <h4><b>John Doe</b></h4>
						    <p>Architect & Engineer</p>
						  </div>
						</div>
						<div class="card">
						  <img src="res/img/team-2.jpg" alt="Avatar" style="width:100%">
						  <div class="detail">
						    <h4><b>John Doe</b></h4>
						    <p>Architect & Engineer</p>
						  </div>
						</div>
						<div class="card">
						  <img src="res/img/team-3.jpg" alt="Avatar" style="width:100%">
						  <div class="detail">
						    <h4><b>John Doe</b></h4>
						    <p>Architect & Engineer</p>
						  </div>
						</div>
						<div class="card">
						  <img src="res/img/team-4.jpg" alt="Avatar" style="width:100%">
						  <div class="detail">
						    <h4><b>John Doe</b></h4>
						    <p>Architect & Engineer</p>
						  </div>
						</div>
					</div>
				</section>
				
			</main>
			<footer>
				<div class="container">
					<span>Madeira OO | Todos os direitos reservados.</span>
				</div>
			</footer>
		</div>
	</body>
</html>