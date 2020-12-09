<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- <%@ page import=""%> --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>
<title>Nouveau Compte</title>
</head>

<body class="container">
	<%@ include file="./fragments/header.html"%>
	<div class="col-12"></div>
	<h1 class="my-5 text center">Mon profil</h1>

	<div id="formulaire-responsive">

		<form method="post"
			action="${pageContext.request.contextPath}/CreerCompte"
			class="form-horizontal"></form>


		<div class="row my-2">
			<label for="pseudo" class="col-3 col-form-label"> Pseudo : </label>
			<%-- traitement ifEmpty  à faire --%>
			<input class="form-controln col-3" type="text" id="pseudo"
				name="pseudo" value="${param.pseudo}" required="required" autofocus="autofocus" placeholder="Votre pseudo ?"> <label for="nom"
				class="col-3 col-form-label"> Nom : </label>
			<%-- traitement ifEmpty  à faire --%>
			<input class="form-controln col-3" type="text" id="nom" name="nom"
				value="${param.nom}" required="required" placeholder=" Votre nom :">
		</div>


		<div class="row my-2">
			<label for="prenom" class="col-3 col-form-label"> Prénom : </label>
			<%-- traitement ifEmpty à faire --%>
			<input class="form-controln col-3" type="text" id="prenom"
				name="prenom" value="${param.prenom}"required="required" placeholder="Votre prénom ?"> <label for="email"
				class="col-3 col-form-label"> Email : </label>
			<%-- traitement format email  à faire --%>
			<input class="form-controln col-3" type="email" size = "50" id="email"
				name="email" value="${param.email}"required="required" placeholder="Votre email ?">
		</div>

		<div class="row my-2">
		
			<label for="telephone" class="col-3 col-form-label">
				Téléphone : </label>
			<%-- traitement if not conform à faire --%>
			<input class="form-controln col-3" type="text" id="telephone"
				name="telephone" value="${param.telephone}"required="required" placeholder="Votre numero de téléphone ?"> <label
				for="rue" class="col-3 col-form-label"> Rue : </label>
			<%-- traitement if empty  à faire --%>
			<input class="form-controln col-3" type="text" id="rue" name="rue"
				value="${param.rue}"required="required" placeholder=" rue :">
		</div>

		<div class="row my-2">
			<label for="codePostal" class="col-3 col-form-label"> Code
				Postal : </label>
			<%-- traitement if not conform à faire --%>
			<input class="form-controln col-3" type="text" id="codePostal"
				name="codePostal" value="${param.codePostal}"required="required" placeholder=" codePostal:"> <label
				for="ville" class="col-3 col-form-label"> Ville : </label>
			<%-- traitement if empty  à faire --%>
			<input class="form-controln col-3" type="text" id="ville"
				name="ville" value="${param.ville}"required="required" placeholder=" ville :">
		</div>

		<div class="row my-2">
			<label for="motDePasse1" class="col-3 col-form-label"> Mot de
				Passe : </label>
			<%-- traitement if not conform à faire --%>
			<input class="form-controln col-3" type="password" id="motDePasse1"
				name="motDePasse1" required="required" placeholder=" choisissez un mot de passe :"> <label for="motDePasse"
				class="col-3 col-form-label"> Confirmer : </label>
			<%-- traitement if = motDePasse1  à faire --%>
			<input class="form-controln col-3" type="password" id="motDePasse"
				name="motDePasse" value="${param.motDePasse}" required="required" placeholder=" répétez le mot de passe :">
		</div>

	</div>
	<div>

		<a class="btn btn-info my-5 mx-5 "
			href="${pageContext.request.contextPath}/CreerCompte"><input
			type="submit" value="Créer" /></a> <a class="btn btn-info my-5  mx-5 "
			href="${pageContext.request.contextPath}/ServletAccueil"><input
			type="button" value="Annuler" /></a>
	</div>
	<footer>
		<%@ include file="./fragments/footer.html"%>
	</footer>



</body>
</html>