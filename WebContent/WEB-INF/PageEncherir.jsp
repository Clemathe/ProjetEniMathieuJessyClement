<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page import=""%> --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="fr">

<head>
<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>


</head>

<body class="container">
	<header>
		<jsp:include page="./fragments/header.jsp"></jsp:include>
	</header>
	<div class="text-center my-5">
	<jsp:useBean id="now" class="java.util.Date"/>
	<fmt:parseDate value="${ article.dateFinEncheres }" pattern="yyyy-MM-dd" var="parsedDateTime" type="both" />
										<fmt:formatDate pattern="dd/MM/yyyy" value="${ parsedDateTime }" var="dateFinEnchere"/>
	<c:if test="${dateFinEnchere > now}">
		<c:choose>
			<c:when test="${utilisateurCourant.pseudo == article.encheres.get(0).utilisateur.pseudo}">Vous avez remporté l'enchère</c:when>
			<c:otherwise>${article.encheres.get(0).utilisateur.pseudo} a remporté l'enchère</c:otherwise>
		</c:choose>
	</c:if>
	
	
	<c:if  test="${enchereMessage != null}">

			<div class="container alert alert-info text-align center" role="alert">
				${enchereMessage}</div>
		</c:if>
	</div>	
	<h2 class="text-center my-5">Detail de la vente</h2>

	<section class="my-1">

		<div class="row">
			<div class="col-md-5">
				<img src="./images/2.png" id="imageDetailArticle" class="rounded float-left" alt="image ordinateur">
			</div>

			<div class="col-md-7 my-10">

				<h4>${article.nomArticle}</h4>

				<div class="d-flex inline-block">
					<p>Description : </p>

					<p class="ml-1"> ${article.description}</p>
				</div>
				
				<p>Catégorie : ${article.categorie.libelle}</p>
				<c:choose>
					<c:when test="${article.encheres.get(0).montantEnchere != null}">
						<p>Meilleur offre : ${article.encheres.get(0).montantEnchere} pts par ${article.encheres.get(0).utilisateur.pseudo}</p>
					</c:when>
					<c:otherwise>
						<p>Aucune enchère</p>
					</c:otherwise>
				</c:choose>
				<p>Mise à prix : ${article.miseAPrix} points</p>

				<p>Fin de l'enchère : <fmt:parseDate value="${ article.dateFinEncheres }" pattern="yyyy-MM-dd" var="parsedDateTime" type="both" />
										<fmt:formatDate pattern="dd/MM/yyyy" value="${ parsedDateTime }" var="dateFinEnchere"/></p>

				<p>Retrait : ${article.lieuRetrait}</p>
							
				<p>Vendeur : ${article.vendeur.pseudo}</p>

				<div class="d-flex justify-content-flex-end">
					<form method="post" action="encheres"
						class="form-horizontal">
						<input type="hidden" name="encherir" value="true">
						<input type="hidden" name="noArticle" value="${param.noArticle}">
						<div class="d-sm-flex flex-row">
							<div class="mt-3">
								<label for="example-number-input">Ma proposition :</label>
							</div>

							<div class="p-2">
								<input class="col-form-label" type="number" value="1"
									name="montant" min="1" max="999" id="example-number-input">
							</div>

							<div class="p-2">
								<input type="submit" value="Enchérir"
									class=" btn btn-info btn-lg btn-block">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>

	<%@ include file="./fragments/footer.html"%>
</body>
</html>