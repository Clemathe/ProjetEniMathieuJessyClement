<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html lang="fr">
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>
<title>Profil</title>
</head>
<bod class = "container" >

 <header>
        <jsp:include page="./fragments/header.jsp"></jsp:include>
    </header>
 <h1 class="text-center my-3">Mon Profil</h1>

 

    <jsp:useBean id="utilisateur" scope="request" class="fr.eni.encheres.bo.Utilisateur"></jsp:useBean>
        <section class="my-2">
        
        	<div class="container alert alert-danger text-align center" role="alert">
			${attention} ${prenom} ${suite1} ${credit}${suite2}</div>
			
			<div class=" my-5 text-center">
                
                <form method="post"action="${pageContext.request.contextPath}/SupprimerCompte" >  
                <button class="btn btn-outline-danger btn-lg my-5 mx-5 "type="Supprimer">Supprimer</button>
                
                <a href="/accueil" class="btn btn-info btn-lg  my-5 mx-5 form-controln col-3 " role="button" aria-disabled="true">Retour</a>
                </form>    
                    
                </div>
			
		
</body>
</html>