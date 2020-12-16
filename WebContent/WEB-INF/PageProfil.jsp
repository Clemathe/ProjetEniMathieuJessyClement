<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html lang="fr">
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>
<title>Profil</title>
</head>
<body>
 <h1 class="text-center my-5">Mon Profil</h1>

 

    <jsp:useBean id="utilisateur" scope="request" class="fr.eni.encheres.bo.Utilisateur"></jsp:useBean>
        <section class="my-2">
        
        	<div class="container alert alert-danger text-align center" role="alert">
			${attention}</div>
			
		
</body>
</html>