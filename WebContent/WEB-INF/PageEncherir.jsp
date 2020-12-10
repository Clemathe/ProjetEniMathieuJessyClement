<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page import=""%> --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>
</head>

<body class="container-fuild">
	<header>
		<jsp:include page="./fragments/header.jsp"></jsp:include>
	</header>

	<h2 class="text-center my-5">Detail de la vente</h2>

	<section class="mx-5">
	<div class="row">
		<div class="col-4">
			<img src="./images/2.png" style="width: 18rem;" class="rounded float-left" alt="...">
		</div>

		<div class="col-8">

			<h4>${article.nomArticle}</h4>

			<p>${article.description}</p>

			<div>
				<p>Description :</p>
				<p>${article.categorie}</p>
			</div>

			<p>Catégorie : par</p>
			<%-- 	${article.encheres.get(encheres.size()-1)).montantEnchere} par  ${article.encheres.get(encheres.size()-1)).utilisateur.pseudo} --%>
			<p>Mise à prix : ${article.miseAPrix} points</p>

			<p>Fin de l'enchère : ${article.utilisateur.pseudo}</p>

			<p>Retrait : ${article.dateFinEncheres}</p>

			<p>Vendeur : ${article.vendeur}</p>

			<form action="" method="post" class="row col-4 inline-block">
								
				<div class="row" id="propostion">
					<label for="example-number-input" class="col-4 col-form-label">Ma proposition :</label>
					<div class="col-5">
					<input class="form-control col-12" type="number" value="1"
							name="montant" min="1" max="999" id="example-number-input">
					</div>
				</div>
			
				<input type="submit" value="Enchérir"
					class="btn btn-info btn-lg btn-block">
			</form>

		</div>
</div>





	</section>

	<%@ include file="./fragments/footer.html"%>
</body>
</html>