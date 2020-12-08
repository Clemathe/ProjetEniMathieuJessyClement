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
	<!-- Header -->
	<%@ include file="./fragments/header.html"%>
	<!--/Header -->


	<!-- Contenu page -->
	<!-- 	<div class="col-12 row justify-content-center mb-2 formulaireConnexion"> -->
	<!-- 		<form action="" method="post"> -->
	<!-- 			<div class="row"> -->
	<!-- 				<label class="col-6" for="id">Identifiant</label> <input -->
	<!-- 					class="col-6" type="text" id="id" name="id" /> <label -->
	<!-- 					class="col-6" for="password">Identifiant</label> <input -->
	<!-- 					class="col-6" type="password" name="password" id="password" /> -->
	<!-- 			</div> -->
	<!-- 			<div class="row"> -->
	<!-- 				<input class="col-6" type="submit" value="Connexion" /> <input -->
	<!-- 					type="checkbox" name="remember" value="remmeber" id="remember" /> -->
	<!-- 				<div class="col-6"> -->
	<!-- 					<label for="remember"> Se souvenir de moi</label><a -->
	<!-- 						href="ServletCreerCompte">Mot de passe Oublié</a> -->
	<!-- 				</div> -->
	<!-- 			</div> -->
	<!-- 		</form> -->

	<form action="ServletConnexion" method="post"
		class=" formulaireConnexion">
		<div class="form-group">
			<label for="id">Identifiant</label> <input type="text"
				class="form-control" id="id" name="identifiant"
				placeholder="Votre identifiant">
		</div>
		<div class="form-group">
			<label for="password">Password</label> <input type="password"
				class="form-control" id="password" name="pass"
				placeholder=" Votre Mot de passe">
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

	<div class="form-group" id="creerUnCompte">
		<a href=""><button class="btn btn-info col-12">Créer un
				compte</button></a>
	</div>



	<!-- Footer -->
	<%@ include file="./fragments/footer.html"%>
	<!--/Footer -->
</body>
</html>