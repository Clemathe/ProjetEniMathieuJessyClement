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

	



		<form method="post"
			action="${pageContext.request.contextPath}/MonProfil"
			class="form-horizontal">


			<div class="saisie row my-2">
				<label for="pseudo" class="col-md-2 col-form-label"> Pseudo
					: </label> <input class="form-controln col-md-4" type="text" id="pseudo"
					name="pseudo" value="${utilisateur.pseudo}" required> <label
					for="nom" class="col-md-2 col-form-label"> Nom : </label> <input
					class="form-controln col-md-4" type="text" id="nom" name="nom"
					value="${utilisateur.nom}" required>
			</div>




			<div class="row my-2">
				<label for="prenom" class="col-md-2 col-form-label"> Prénom
					: </label> <input class="form-controln col-md-4" type="text" id="prenom"
					name="prenom" value="${utilisateur.prenom}" required> <label
					for="email" class="col-md-2 col-form-label"> Email : </label> <input
					class="form-controln col-md-4" type="email" size="50" id="email"
					name="email" value="${utilisateur.email}" required>
			</div>



			<div class="row my-2">



				<label for="telephone" class="col-md-2 col-form-label">
					Téléphone : </label> <input class="form-controln col-md-4" type="tel"
					id="telephone" name="telephone" value="${utilisateur.telephone}">

				<label for="rue" class="col-2 col-form-label"> Rue : </label> <input
					class="form-controln col-md-4" type="text" id="rue" name="rue"
					value="${utilisateur.rue}" required>
			</div>



			<div class="row my-2">
				<label for="codePostal" class="col-md-2 col-form-label">
					Code Postal : </label> <input class="form-controln col-md-4" type="text"
					id="codePostal" name="codePostal" value="${utilisateur.codePostal}"
					required> <label for="ville"
					class="col-md-2 col-form-label"> Ville : </label> <input
					class="form-controln col-md-4" type="text" id="ville" name="ville"
					value="${utilisateur.ville}" required>


			</div>

			<div class="row my-2">
				<label for="motDePasseActuel" class="col-md-2 col-form-label">
					Mot de passe actuel : </label> <input class="form-controln col-md-4"
					type="password" id="motDePasse" name="motDePasse"
					value="${param.motDePasse}" required>
			</div>



			<div class="row my-2">
				<label for="motDePasse1" class="col-md-2 col-form-label">
					Nouveau mot de passe : </label> <input class="form-controln col-md-4"
					type="password" id="motDePasse1" name="motDePasse1" required
					placeholder="${param.motDePasse1}"> <label for="motDePasse"
					class="col-md-2 col-form-label"> Confirmation : </label> <input
					class="form-controln col-md-4" type="password" id="motDePasseNew"
					name="motDePasseNew" value="${param.motDePasseNew}" required>
					
			</div >
			
			<div class="row my-3">
				<label class="col-md-2 col-form-label">Credit :</label>
				${utilisateur.credit}
			</div>

			<div class="d-flex justify-content-around" >
				<input type="hidden" name="enregistrer" value="enregistrer">
				<button class="btn btn-info my-5 mx-5 form-controln col-3 "
					type="submit">Enregistrer</button>
					</div>
		</form>
<div class="text-center my-2" >
		<form method="get"
			action="${pageContext.request.contextPath}/SupprimerCompte"
			class="form-horizontal">
			<input type="hidden" name="supprimer" value="supprimer">
			<button class="btn btn-info my-5 mx-5 form-controln col-3" type=""
				value="">Supprimer compte</button>
		</form>		
		</div>


	</section>



	<footer>
		<%@ include file="./fragments/footer.html"%>
	</footer>
</body>
</html>