<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
    
<!DOCTYPE html>
<html>


<head>

<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>
<title>Mes encheres</title>
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
		
	<div class="text-center"><h2>Liste de mes Ventes en cours</h2></div>	
		
	
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
		
		<div class="col-lg-4 form-check">
			<input type="radio" class="form-check-input" id="achatsVentes" name="achatsVentes" value="achat" >
			<label class="form-check-label" for="achatsVentes">Mes achats</label>
				
					<div class="custom-control custom-switch">
						<input type="checkbox" class="custom-control-input" id="encheresOuvertes" name="encheresOuvertes" value="eOuvertes">
						<label class="custom-control-label" for="encheresOuvertes">Enchères ouvertes</label>
					</div>
					
					<div class="custom-control custom-switch">
						<input type="checkbox"  class="custom-control-input" id="encheresEnCours" name="encheresEnCours" value="eEnCours">
						<label class="custom-control-label"  for="encheresEnCours"> Mes enchères en cours</label>
					</div>
					
					<div class="custom-control custom-switch">
						<input type="checkbox"  class="custom-control-input" id="encheresRemportees" name="encheresRemportees"value="eRemportees">
						<label class="custom-control-label"  for="encheresRemportees"> Mes enchères remportées</label>
					</div>
		</div>	

		<div class="col-lg-4 form-check">
 				<input type="radio" class="form-check-input" id="achatsVentes" name="achatsVentes" value="vente"checked>
 				<label class="form-check-label" for="achatsVentes">Mes ventes</label>
		
 					<div class="custom-control custom-switch">
 						<input type="checkbox" class="custom-control-input" id="ventesEnCours" name="ventesEnCours" value="vEnCours">
 						<label class="custom-control-label" for="ventesEnCours">Mes ventes en cours</label>
   				</div>
   				
   				<div class="custom-control custom-switch">
   					<input type="checkbox"  class="custom-control-input" id="ventesNonDebutees" name="ventesNonDebutees" value="vNonDebutees">
   					<label class="custom-control-label"  for="ventesNonDebutees"> ventes non débutées</label>
   				</div>
   				
   				<div class="custom-control custom-switch">
   					<input type="checkbox"  class="custom-control-input" id="ventesTerminees" name="ventesTerminees" value="vTerminees">
   					<label class="custom-control-label"  for="ventesTerminees"> ventes terminées</label>
   				</div>
		</div>
</form>	
	
			

			
			
					
	
<c:if test="${!empty mesVentes}">
<h2>Mes Ventes</h2>
</c:if>
<c:if test="${!empty mesVentesEnCours}">

<div class="row col-lg-12">

	<c:forEach var="vec" items="${mesVentesEnCours}">
		<div class="col-lg-4 col-12 col-md-6">
			<div class="card" >
				<div class="card-header text-center">
					Vente en cours
				</div>
				<div class="card-body">
					<h5 class="card-title">${vec.nomArticle}</h5>
					<p class="card-text">Mise a prix: ${vec.miseAPrix} points<br>
										prix de vente: ${vec.prixVente}<br>
										Mis aux enchères le ${vec.dateDebutEncheres}<br>
										Fin de l'enchère le ${vec.dateFinEncheres}<br>
					</p>
					<div class="text-center">
					<a href="encheres?noArticle=${vec.noArticle}" class="btn btn-info">Accéder à la vente</a>	
					</div>		
				</div>  
			</div>
		</div>
	</c:forEach>
	</div>




















			</c:if>



		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

</body>
</html>