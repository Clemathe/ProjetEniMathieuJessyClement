<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page import=""%> --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>

<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>
<title>Accueil</title>

</head>

<body class="container-fluid">
<header>
		<jsp:include page="./fragments/header.jsp"></jsp:include>
</header>

<div class=" container  my-5 col-md-4">
		
	<!--Pour l'affichage de messages d'alerte indiquant une connexion ou une déconnexion réussie -->
		<c:if test="${disconnect != null}">
			
			<div class="container alert alert-warning text-align center" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
   			<span aria-hidden="true">&times;</span></button>
				Vous êtes déconnectés</div>
	
		</c:if>

		<c:if test="${nouvelleConnexion != null}">
			
			<div class="alert alert-success alert-dismissible fade show text-align center" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
   			<span aria-hidden="true">&times;</span></button>
 			 Vous êtes connectés</div>
	
		</c:if>
</div>

<!--  -->	

<div class="row justify-content-between mt-5" style >
	<div class="col-4 font"><h1>ENI - Enchères</h1></div>

</div>
<br />



<div class="row">
	<div class="col-4">
		<form action="" method="post">
			
				<legend>Liste des enchères</legend>
				
				<div class="form-group">
					<label for="recherche">Filtres :</label>
					<input type="search" size="40" name="nomArticlePartiel" id="recherche" placeholder="Le nom de l'article contient" class="form-control">
				</div>
					
				<div class="form-group">
					<label for="categories">Catégories :</label>
						<select  name="categories" id="categories" class="form-control">
							<option value="Toutes" selected>Toutes</option>
							<option value="Informatique">Informatique</option>
							<option value="Ameublement" >Ameublement</option>
							<option value="Vêtement" >Vêtements</option>
							<option value="Sport&Loisirs" >Sports et Loisirs</option>
						</select>
					</div>
				</div>
				
				<div class="col-4">
					<div class="form-group">
					<input type="submit" value="rechercher" class="btn btn-info" >
					</div>
			
		</form>
	</div>
</div>

<c:if test="${!empty enchereEnCours}">
<p>Il y' a actuellement ${enchereEnCours.size()} enchères sur notre site.<P>

	<c:forEach var="EEC" items="${enchereEnCours}">
		<div class="card" style="width: 18rem;">
			<img src="./images/${EEC.noArticle}.png" class="card-img-top" alt="vente">
			<div class="card-body">
			    <h5 class="card-title">${EEC.nomArticle}</h5>
			    <p class="card-text">Prix: ${EEC.prixVente} points<br /> 
			    					Fin de l'enchère: ${EEC.dateFinEncheres}<br />
			    <c:if test="${utilisateurCourant != null}">					Vendeur: ${EEC.pseudoVendeur}</p>
			    	<a href="encheres?noArticle=${EEC.noArticle}" class="btn btn-primary">Accéder à la vente</a>
			    </c:if>
			    <c:if test="${utilisateurCourant == null}">
			    <a href="connexion" class="btn btn-primary">Accéder à la vente</a>
			     </c:if>
 			</div>
		</div>
	</c:forEach>

</c:if>
	











	

<footer>
		<%@ include file="./fragments/footer.html"%>
	</footer>
</body>
</html> 