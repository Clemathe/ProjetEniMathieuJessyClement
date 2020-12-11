<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html lang="fr">
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>
<title>Mon profil/title>
</head>
<body>
<h1 class="text-center my-5"> Mon Profil </h1>

<div class="saisie row my-2">
			<label for="pseudo" class="col-md-2 col-form-label"> Pseudo : </label>
			
			<input class="form-controln col-md-4" type="text" id="pseudo"
				name="pseudo" value="${param.pseudo}" required="required"
				autofocus="autofocus" placeholder="Ada1815"> <label
				for="nom" class="col-md-2 col-form-label"> Nom : </label>
			
			<input class="form-controln col-md-4" type="text" id="nom" name="nom"
				value="${param.nom}" required="required" placeholder="Lovelace">
		</div>
	${utilisateur.pseudo}
	${utilisateur.nom}
	${utilisateur.prenom}
	${utilisateur.email}
	${utilisateur.telephone}
	${utilisateur.rue}
	${utilisateur.codePostal}
	${utilisateur.ville}
	${utilisateur.motDePasse}
	${utilisateur.credit}

				<p>${article.description}</p>


				<p>Description :</p>
				<p>${article.categorie}</p>



<footer>
		<%@ include file="./fragments/footer.html"%>
</footer>
</body>
</html>