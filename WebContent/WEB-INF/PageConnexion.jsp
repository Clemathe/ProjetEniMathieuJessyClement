<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page import=""%> --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>

<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>
<title>Connexion</title>

</head>

<body class="container-fluid">
	<header>
		<jsp:include page="./fragments/header.jsp"></jsp:include>
	</header>
	
	<div class=" container  my-5 col-md-4">
		<c:if test="${errorConnection != null}">

			<div class="container alert alert-danger text-align center" role="alert">
				${errorConnection}</div>
		</c:if>
		<c:if test="${connect != null}">
			
			<div class="container alert alert-success text-align center" role="alert">
				Vous êtes déconnecté</div>
		</c:if>

		<section >
		
			<form action="ServletConnexion" method="post" class="my-5" >
			
				<input type="hidden" name="nouvelleConnexion" value="true">
				
				<div class="form-group">
					<label for="login">Identifiant</label> <input type="text"
						class="form-control" id="login" name="login" autofocus
						placeholder="Votre identifiant" required><small
						class="form-text text-muted">Pseudo ou email</small>
				</div>
				<div class="form-group">
					<label for="password">Password</label> <input type="password"
						class="form-control" id="password" name="pass"
						placeholder=" Votre Mot de passe" required><small
						class="form-text text-muted">Entre 8 et 12 caractères minimum avec
						chiffres, lettres minuscules et majuscules et symbole</small>
				</div>
				<div class="form-check mb-2">
					<input type="checkbox" class="form-check-input" id="rememberMe">
					<label class="form-check-label" for="rememberMe" id="rememberMe">Se
						souvenir de moi</label>
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-info col-4 ">Connexion</button>
					<a class="ml-3"href="ServletCreerCompte" id="forgetPassword">Mot de passe
						Oublié</a>
				</div>
		
			</form>
			
		</section>
		
		<section class="my-5">
		
			<div class="form-group" id="creerUnCompte">
				<a href="CreerCompte"><button class="btn btn-info btn-lg col-12">Créer un
						compte</button></a>
			</div>
			
		</section>
	</div>

	<footer>
		<%@ include file="./fragments/footer.html"%>
	</footer>
</body>
</html>