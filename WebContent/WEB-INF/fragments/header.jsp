<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page import=""%> --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  
    
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top ">
		<a class="navbar-brand mr-auto p-2" href="accueil">Logo</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarColor01" aria-controls="navbarColor01"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
	<div class="navbarColor01">
		<div class="collapse navbar-collapse ml-5 p-2 " id="navbarColor01">
			<ul class="navbar-nav mr-auto" id="navigation">
			<c:if test="${utilisateurCourant == null }">
				<li class="nav-item active  "><a class="nav-link" href="connexion">Se connecter <span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item active justify-content end "><a class="nav-link" href="CreerCompte">Créer un compte<span class="sr-only">(current)</span>
				</a></li>
			</c:if>
			
			<c:if test="${utilisateurCourant != null }">
		
				<li  class="nav-item active "><p id="credit" class="nav-link"><c:set var="credit" scope="request" value="someValue"/> 
				<c:out default="None" escapeXml="true" value="${utilisateurCourant.credit > 1 ? 'Crédits : ' : 'Crédit : '}${utilisateurCourant.credit}" /> <span class="sr-only">(current)</span>
				</li>	
				<li class="nav-item active "><a class="nav-link" href="mesEncheres">Enchères<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item active "><a class="nav-link" href="vendre">Vendre un article<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item active "><a class="nav-link" href="MonProfil">Mon profil <span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item active "><a class="nav-link" href="connexion?disconnect=true">Déconnexion<span class="sr-only">(current)</span>
				</a></li>
			
			</c:if>
			</ul>
			</div>
		</div>
	</nav>