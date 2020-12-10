<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page import=""%> --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  
    
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<a class="navbar-brand" href="#">Les objets sont nos amis</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarColor01" aria-controls="navbarColor01"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse ml-5" id="navbarColor01">
			<ul class="navbar-nav mr-auto">
			<c:if test="${utilisateurCourant == null }">
				<li class="nav-item active  "><a class="nav-link" href="ServletConnexion">Se connecter <span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item active justify-content end "><a class="nav-link" href="CreerCompte">Créer un compte<span class="sr-only">(current)</span>
				</a></li>
			</c:if>
			
			<c:if test="${utilisateurCourant != null }">	
				<li class="nav-item active "><a class="nav-link" href="#">Enchères <span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item active "><a class="nav-link" href="#">Vendre un article<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item active "><a class="nav-link" href="#">Mon compte <span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item active "><a class="nav-link" href="ServletConnexion?connect=false">Déconnexion<span class="sr-only">(current)</span>
				</a></li>
			
			</c:if>
			</ul>
			
		</div>
	</nav>