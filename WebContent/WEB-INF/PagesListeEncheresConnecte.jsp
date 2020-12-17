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
		
	<div class="text-center"><h2>Liste de mes enchères en cours</h2></div>	
		
		
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
	
			

			
			
					
	
<c:if test="${mesVentes != null}">
<h2>Mes ventes</h2>
</c:if>
<c:if test="${!empty ventesEnCours }">
	<h2>Mes ventes en cours</h2>
	<div class="row col-lg-12">
	<c:forEach var="vec" items="${ventesEnCours}">
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
			<br> 
		</div>
	</c:forEach>
	</div>
</c:if>	

<c:if test="${!empty ventesNonDebutees }">
<h2>Mes ventes non débutées</h2>
	<div class="row col-lg-12">
	<c:forEach var="vnd" items="${ventesNonDebutees}">
		<div class="col-lg-4 col-12 col-md-6">
			<div class="card" >
				<div class="card-header text-center">
					Vente non débutées
				</div>
				<div class="card-body">
					<h5 class="card-title">${vnd.nomArticle}</h5>
					<p class="card-text">Mise a prix: ${vnd.miseAPrix} points<br>
										prix de vente: ${vnd.prixVente}<br>
										Mis aux enchères le ${vnd.dateDebutEncheres}<br>
										Fin de l'enchère le ${vnd.dateFinEncheres}<br>
					</p>
					<div class="text-center">
					<a href="" class="btn btn-info">Annuler la vente</a>
					<a href="" class="btn btn-info">Modifier la vente</a>	
					</div>		
				</div> 
				<br> 
			</div>
		</div>
	</c:forEach>
	</div>
</c:if>	
<c:if test="${ !empty ventesTerminees }">
<h2>Mes ventes terminées</h2>
	<div class="row col-lg-12">
	<c:forEach var="vt" items="${ventesTerminees}">
		<div class="col-lg-4 col-12 col-md-6">
			<div class="card" >
				<div class="card-header text-center">
					Vente terminée
				</div>
				<div class="card-body">
					<h5 class="card-title">${vt.nomArticle}</h5>
					<p class="card-text">Mise a prix: ${vt.miseAPrix} points<br>
										prix de vente: ${vt.prixVente}<br>
										Mis aux enchères le ${vt.dateDebutEncheres}<br>
										Fin de l'enchère le ${vt.dateFinEncheres}<br>
					</p>
						
				</div> 
				<br>  
			</div>
		</div>
	</c:forEach>
	</div>
</c:if>				
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

</body>
</html>