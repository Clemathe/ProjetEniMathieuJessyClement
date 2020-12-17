<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 

<%-- <%@ page import=""%> --%>

 

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 

<!DOCTYPE html>
<html lang="fr">

 

<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>
<title>Mon profil</title>
</head>

 

<body class="container">
    <header>
        <jsp:include page="./fragments/header.jsp"></jsp:include>
    </header>

 

    <h1 class="text-center my-5">Mon Profil</h1>

 

    <jsp:useBean id="utilisateur" scope="request" class="fr.eni.encheres.bo.Utilisateur"></jsp:useBean>
        
        <!--  faire le traitement : si l'utilisateur qui visite la page n'est pas propréitaire de ce profil
        alors masquer les informations personnelles js -->

 

    <section class="my-2">
    
    	<c:if test="${ message != null}">
			
			<div class="container alert alert-danger text-align center" role="alert">
			${message}</div>
		</c:if>
        
                <div class="saisie row my-2">
                    <label for="pseudo" class="col-md-2 col-form-label"> Pseudo
                        : </label>
                    <div class="form-controln col-md-4">
                        <jsp:getProperty property="pseudo" name="utilisateur" />
                    </div>

 

                    <label for="nom" class="col-md-2 col-form-label"> Nom : </label>
                    <div class="form-controln col-md-4">
                        <jsp:getProperty property="nom" name="utilisateur" />
                    </div>
                </div>

 

                <div class="saisie row my-2">
                    <label for="prenom" class="col-md-2 col-form-label"> Prénom
                        : </label>
                    <div class="form-controln col-md-4">
                        <jsp:getProperty property="prenom" name="utilisateur" />
                    </div>

 

                    <label for="email" class="col-md-2 col-form-label"> Email :
                    </label>
                    <div class="form-controln col-md-4">
                        <jsp:getProperty property="email" name="utilisateur" />
                    </div>
                </div>

 

                <div class="saisie row my-2">
                    <label for="telephone" class="col-md-2 col-form-label">
                        Téléphone : </label>
                    <div class="form-controln col-md-4">
                        <jsp:getProperty property="telephone" name="utilisateur" />
                    </div>

 

                    <label for="rue" class="col-md-2 col-form-label"> Rue : </label>
                    <div class="form-controln col-md-4">
                        <jsp:getProperty property="rue" name="utilisateur" />
                    </div>
                </div>

 

                <div class="saisie row my-2">
                    <label for="codePostal" class="col-md-2 col-form-label">
                        Code Postal : </label>
                    <div class="form-controln col-md-4">
                        <jsp:getProperty property="codePostal" name="utilisateur" />
                    </div>

 

                    <label for="ville" class="col-md-2 col-form-label"> Ville :
                    </label>
                    <div class="form-controln col-md-4">
                        <jsp:getProperty property="ville" name="utilisateur" />
                    </div>
                </div>

 


                <div class=" my-5 text-center">
                
                <form method="post"action="${pageContext.request.contextPath}/MonProfil" >
               
                <input type="hidden" name="modifierProfil" value="modifierProfil">
                <button class="btn btn-info my-5 mx-5 form-controln col-3 "type="submit">Modifier</button>
                </form>    
                    
                </div>
    
    </section>

 

    <footer>
        <%@ include file="./fragments/footer.html"%>
    </footer>
</body>
</html>