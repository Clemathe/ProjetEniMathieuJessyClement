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

<body class="container">
<header>
		<jsp:include page="./fragments/header.jsp"></jsp:include>
</header>

<div class="container my-5 col-md-4">
		
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

	
<h2 class="text-center" >Liste des enchères</h2>



	<div class="">
	<form  class="row align-items-start" action="" method="post">
		<div class="col-lg-4">
		<label class="col-form-label" for="nomArticlePartiel">Filtres :</label>
		<input type="search"  name="nomArticlePartiel" id="recherche" 
				placeholder="Le nom de l'article contient" class="form-control">	
		</div>

		<div class="col-lg-4">		
		<label class="col-form-label" for="categories">Catégories :</label>
			<select  name="categories" id="categories" class="custom-select form-control">
				<option value="Toutes" selected>Toutes</option>
				<option value="Informatique">Informatique</option>
				<option value="Ameublement" >Ameublement</option>
				<option value="Vetement" >Vêtements</option>
				<option value="Sport&Loisirs" >Sports et Loisirs</option>
			</select>
		</div>
		<br />	
		<div class="col-lg-4">	
		<br />
		
		<input type="submit" value="rechercher" class="btn-lg btn-info btn-block" >
		</div>	
	</form>					
	</div>		
					
	<br />		
		
	


<c:if test="${!empty enchereEnCours}">
<div class=" text-center" >
<h2>Il y'a actuellement ${enchereEnCours.size()} enchères sur notre site.</h2>
<br />	
</div>

<div class="row col-lg-12">

	<c:forEach var="EEC" items="${enchereEnCours}">
		<div class="col-lg-4 col-12 col-md-6">
			<div class="card" >
				<div class="card-header text-center">
					<img src="./images/${EEC.noArticle}.png" class="card-img-top" alt="vente">
				</div>
				<div class="card-body">
					<h5 class="card-title">${EEC.nomArticle}</h5>
					<p class="card-text">Prix: ${EEC.prixVente} points<br /> 
					Fin de l'enchère: ${EEC.dateFinEncheres}<br />
					Vendeur: ${EEC.pseudoVendeur}</p>
					<div class="text-center">
						<c:if test="${utilisateurCourant != null}">					
						<a href="encheres?noArticle=${EEC.noArticle}" class="btn btn-info">Accéder à la vente</a>
						</c:if>
						<c:if test="${utilisateurCourant == null}">
						<a href="connexion" class="btn btn-info">Accéder à la vente</a>
						</c:if>
			    	</div>				
				</div>  
			</div>
		</div>
	</c:forEach>
	</div>
	</div>



</c:if>
	






</div>




	

<footer>
		<%@ include file="./fragments/footer.html"%>
	</footer>
</body>
</html> 