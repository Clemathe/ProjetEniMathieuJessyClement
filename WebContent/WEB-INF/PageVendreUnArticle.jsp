<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
    
    
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>
<title>Vendre un article</title>
</head>
<body class="container-fluid">
	<header>
		<jsp:include page="./fragments/header.jsp"></jsp:include>
	</header>

<div class="row  mt-5"  >
	<div class="col-1"></div>
	<div class="col-3 "><h1>ENI - Enchères</h1></div>
	<div class="col-1"></div>
	<div class="col-3"><h2>Nouvelle Vente</h2></div>
	<div class="col-4"></div>
</div>
<br />

<div class="row">
	<div class="col-3"></div>
	<div class="col-4">
		<form action="ServletVendreUnArticle" method="post" ><!-- enctype="multipart/form-data" -->
			<div class="form-group">
				<label for="nomArticle">Article: </label>
				<input  required type="text" size="40" name="nomArticle" id="nomArticle" placeholder="Le nom de l'article à vendre." class="form-control">
			</div>
			<div class="form-group">
				<label for="description">Description: </label>
				<textarea  class="form-control" rows="4" cols="4" name="description" id="description" placeholder="Décrivez votre article en quelques mots."></textarea>
			</div>	
			<div class="form-group">
				<label for="categorieLibelle">Catégories: </label>
				<select  required name="categorieLibelle" id="categorieLibelle" class="form-control">
					<option value="Informatique">Informatique</option>
					<option value="Ameublement" >Ameublement</option>
					<option value="Vetement" >Vêtements</option>
					<option value="Sport&Loisirs" >Sports et Loisirs</option>
				</select>
			</div>
			<!--  <div class="form-group">
				<input  type="file" value="Ajouter une photo" class="btn btn-outline-secondary btn-block" >
			</div>-->
			<div class="form-group">
				<label for="miseAPrix">Mise à prix: </label>
				<input required type="number" class="form-control" name="miseAPrix">
			</div>
			<div class="form-group">
				<label for="dateDebutEncheres">Début de l'enchère: </label>
				<input  type="date" id="date" class="form-control" name="dateDebutEncheres">
			</div>
			<div class="form-group">
				<label for="dateFinEncheres">Fin de l'enchère: </label>
				<input  type="date" id="date" class="form-control" name="dateFinEncheres">
			</div>	
			</div>
			<div class="col-3">
				<fieldset>
					<legend align="left">Retrait</legend>
					
					<label for="rue">Rue: </label>
					<input required type="text" size="10" name="rue" id="rue" 
					value="${retrait.rue}" placeholder="numero et rue du retrait" class="form-control">
					
					<label for="codePostal">Code postal: </label>
					<input required type="text" size="10" name="codePostal" 
					id="codePostal" value="${retrait.codePostal}" placeholder="ex : 44000" class="form-control">
					
					<label for="ville">Ville: </label>
					<input required type="text" size="10" value="${retrait.ville}" name="ville" id="ville" placeholder="ex : Nantes" class="form-control">
				</fieldset>
				<br/>
				<div class="form-group">
					<input  type="submit" value="Ajout de l'article" class="btn btn-info btn-lg btn-block" >
			</div>	
		</form>
	</div>
</div>















<footer>
		<%@ include file="./fragments/footer.html"%>
</footer>
</body>
</html>