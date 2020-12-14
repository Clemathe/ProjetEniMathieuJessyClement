<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- <%@ page import=""%> --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="fr">

<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>
<title>Mon profil</title>
</head>

<body class="container">
	<header>
		<jsp:include page="./fragments/header.jsp"></jsp:include>
	</header>
	<h1 class="text-center my-5">Mon Profil</h1>
	<section class="my-2">
	
	
	
	<div class="saisie row my-2">

		<c:if test="${ Erreur != null}">

			<div class="container alert alert-danger text-align center"
				role="alert">${Erreur}</div>
		</c:if>


	</div>
	

<form method="post"action="${pageContext.request.contextPath}/CreerCompte" class="form-horizontal">
		
		<div class="saisie row my-2">
			<label for="pseudo" class="col-md-2 col-form-label"> Pseudo : </label>
			
			<input class="form-controln col-md-4" type="text" id="pseudo"
				name="pseudo" value="${param.pseudo}" required="required"
				autofocus="autofocus" placeholder="${utilisateur.pseudo}"> 
				
			<label for="nom" class="col-md-2 col-form-label"> Nom : </label>
			
			<input class="form-controln col-md-4" type="text" id="nom" name="nom"
				value="${param.nom}" required="required" placeholder=" ${utilisateur.nom}">
		</div>


		<div class="row my-2">
			<label for="prenom" class="col-md-2 col-form-label"> Prénom : </label>
			
			<input class="form-controln col-md-4" type="text" id="prenom" 
			name="prenom" value="${param.prenom}" required="required"placeholder=" ${utilisateur.prenom}"> 
			
			<label for="email" class="col-md-2 col-form-label"> Email : </label>
			
			<input class="form-controln col-md-4" type="email" size="50" id="email"
				name="email" value="${param.email}" required="required"
				placeholder="${utilisateur.email}">
		</div>

		<div class="row my-2">

			<label for="telephone" class="col-md-2 col-form-label"> Téléphone : </label>
			
			<input class="form-controln col-md-4" type="tel" id="telephone"
				name="telephone" value="${param.telephone}" placeholder="${utilisateur.telephone}"> 
				
			<label for="rue" class="col-2 col-form-label"> Rue : </label>
			
			<input class="form-controln col-md-4" type="text" id="rue" name="rue"
				value="${param.rue}" required="required" placeholder=" ${utilisateur.rue}">
		</div>

		<div class="row my-2">
			<label for="codePostal" class="col-md-2 col-form-label"> Code Postal : </label>
			
			<input class="form-controln col-md-4" type="text" id="codePostal"
				name="codePostal" value="${param.codePostal}" required="required"
				placeholder="${utilisateur.codePostal}"> 
			<label for="ville" class="col-md-2 col-form-label"> Ville : </label>
			
			<input class="form-controln col-md-4" type="text" id="ville"
				name="ville" value="${param.ville}" required="required"
				placeholder="${utilisateur.ville}">
		</div>
		
		<div class="row my-2">
		<label for="motDePasseActuel" class="col-md-2 col-form-label"> Mot de passe actuel : </label>
			
			<input class="form-controln col-md-4" type="password" id="motDePasse1"
				name="motDePasse1" required="required"
				placeholder="${utilisateur.motDePasse}"> 
		</div>

		<div class="row my-2">
			<label for="motDePasse1" class="col-md-2 col-form-label"> Nouveau mot de passe : </label>
			
			<input class="form-controln col-md-4" type="password" id="motDePasse1"
				name="motDePasse1" required="required"
				placeholder="${utilisateur.motDePasse}"> 
			<label for="motDePasse" class="col-md-2 col-form-label"> Confirmation : </label>
			
			<input class="form-controln col-md-4" type="password" id="motDePasse"
				name="motDePasse" value="${param.motDePasse}" required="required"
				placeholder="">
		</div>
		<div class="row my-2">
		<label class="col-md-2 col-form-label">Credit :</label> 
		${utilisateur.credit}
		</div>
		
		<div class="d-flex justify-content-around">

			<button class="btn btn-info my-5 mx-5 form-controln col-3 "
				type="submit">Enregistrer</button>

			<button class="btn btn-info my-5 mx-5 form-controln col-3"
				type="reset">Supprimer mon compte</button>

		</div>

	</form>

</section>

	<footer>
		<%@ include file="./fragments/footer.html"%>
	</footer>
</body>
</html>