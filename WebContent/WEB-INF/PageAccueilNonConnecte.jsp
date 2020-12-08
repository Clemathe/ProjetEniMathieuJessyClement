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
	<%@ include file="./fragments/header.html"%>
<div class="row justify-content-between mt-5" style >
	<div class="col-4 font"><h1>ENI - Enchères</h1></div>
	<div class="col-3"><a href="ServletConnexion" >S'inscrire - Se connecter</a></div>
</div>
<br />



<div class="row">
<div class="col-4">
<form action="" method="post">
	<fieldset>
		<legend>Liste des enchères</legend>
		
		<div class="form-group">
			<label for="recherche">Filtres :</label>
			<input type="search" size="40" name="name" id="recherche" placeholder="Le nom de l'article contient" class="form-control">
			</div>
			
			<div class="form-group">
				<label for="categories">Catégories :</label>
				<select  name="categories" id="categories" class="form-control">
					<option value="informatique" selected>Informatique</option>
					<option value="ameublement" >Ameublement</option>
					<option value="vetement" >Vêtements</option>
					<option value="sportetloisirs" >Sports et Loisirs</option>
				</select>
			</div>
		</div>
		
		<div class="col-4">
			<div class="form-group">
			<input type="submit" value="rechercher" class="btn btn-info" >
			</div>
	</fieldset>
</form>
</div>

</div>













	


	<%@ include file="./fragments/footer.html" %>
</body>
</html> 