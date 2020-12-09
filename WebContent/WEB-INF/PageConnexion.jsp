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

<body class="container">
	<Header>
		<%@ include file="./fragments/header.html"%>
	</Header>
	<nav></nav>
	
	<c:if test="${errorConnection != null}">
		</div>
			<div class="alert alert-danger" role="alert">
	 		 ${errorConnection}
		</div>
	</c:if>
	
	<section class= my-5>
		<form action="ServletConnexion" method="post"
			class= my-5>
			<div class="form-group">
				<label for="login">Identifiant</label> <input type="text"
					class="form-control" id="login" name="login"
					 autofocus placeholder="Votre identifiant" required >
			</div>
			<div class="form-group">
				<label for="password">Password</label> <input type="password"
					class="form-control" id="password" name="pass"
					placeholder=" Votre Mot de passe" required><small class="form-text text-muted">8 caractères minimum avec chiffres, lettres et symbole</small>
			</div>
			<div class="form-check">
				<input type="checkbox" class="form-check-input" id="rememeberMe">
				<label class="form-check-label" for="rememeberMe" id="rememeberMe">Se
					souvenir de moi</label>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-info col-6">Connexion</button>
				<a href="ServletCreerCompte" id="forgetPassword">Mot de passe
					Oublié</a>
			</div>

		</form>
	</section>
	<section>
		<div class="form-group class= my-5" id="creerUnCompte">
			<a href=""><button class="btn btn-info col-12">Créer un
					compte</button></a>
		</div>
	</section>


	<footer>
		<%@ include file="./fragments/footer.html"%>
	</footer>
</body>
</html>