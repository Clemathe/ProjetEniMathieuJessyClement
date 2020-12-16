package fr.eni.encheres.bll;

import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.DAOFactory;

public class ArticleVenduManager {
	
	private static ArticleVenduDAO articleVenduDAO;
	
	
	public ArticleVenduManager() {
		articleVenduDAO = DAOFactory.articleVenduDAO();
	}
	
	public List<ArticleVendu> getEnchereEnCours(String categorie, String nomArticlePartiel){
		
		return ArticleVenduManager.articleVenduDAO.getenchereEnCours(categorie, nomArticlePartiel);
	}
	
	public List<ArticleVendu> getToutesEncheresEnCours(){
		
		return ArticleVenduManager.articleVenduDAO.getToutesEncheresEnCours();
	}
	public List<ArticleVendu> getMesVentes (int noUtilisateur){
		
		return ArticleVenduManager.articleVenduDAO.getMesVentes(noUtilisateur);
	}

	public ArticleVendu getArticleVendu(int noArticle) {
		
		return ArticleVenduManager.articleVenduDAO.getArticleVendu(noArticle);
	}
	
	
	public void insertArticleVendu (ArticleVendu articleVendu) {
		
		ArticleVenduManager.articleVenduDAO.insertArticleVendu(articleVendu);
	}

	public String Encherir(int montantEnchere, Utilisateur utilisateurCourant, int noArticle) {
		int soldeUtilisateur = utilisateurCourant.getCredit();
		ArticleVendu articleCourant = getArticleVendu(noArticle);
		boolean auMoinsUneEnchere= false;
		String message = null;
		// L'encherisseur a il assez de crédit ?
		if (soldeUtilisateur > montantEnchere ) {
			int enchereLaPlusHaute = 0;
			
			try {  // L'article contient il déjà une enchère ?
				enchereLaPlusHaute = articleCourant.getEncheres().get(0).getMontantEnchere();
				auMoinsUneEnchere = true;
			} catch (Exception e) { 
				auMoinsUneEnchere = false;
			} // Deux possibilités : l'article contient une enchère et cette enchère est inférieure à celle de l'encherisseur. 
				// Ou bien pas d'enchere dans l'article et enchère proposée supérieure au prix initial 
			if(( enchereLaPlusHaute < montantEnchere && auMoinsUneEnchere ) || ( !auMoinsUneEnchere && soldeUtilisateur > articleCourant.getMiseAPrix() )) {
				
				UtilisateurManager uManager = new UtilisateurManager();
				// creation de l'objet enchère
				Enchere nouvelleEnchere = new Enchere(LocalDate.now(), montantEnchere, articleCourant, utilisateurCourant);
				
				try {
					articleVenduDAO.enregistrerUneEnchere(nouvelleEnchere);
					
				} catch (Exception e) {
					
					message = "L'enchère n'a pas pu être enregistrée";
					
					return message;
				}
				try{
					uManager.debiterUtilisateur(montantEnchere, utilisateurCourant);
				}catch(Exception e){
					//TODO Update BDD pour effacer enchère
					message = "L'enchère n'a pas pu être enregistrée";
					
					return message;
				}
				try{
					if (auMoinsUneEnchere) uManager.rembourserUtilisateur(enchereLaPlusHaute, articleCourant.getEncheres().get(0).getUtilisateur());
				}catch(Exception e){
					//TODO Update BDD pour effacer enchère et débit utilisateur
				}
						
				message = "Enchère enregistrée";
		
			}else {
				message = "Votre enchère est inférieure à l'enchère la plus haute";
			}
		}else {
			message = "Vous n'avez pas suffisament de crédit";
		}
		
		
		
		return message;
	}
	


	public List<ArticleVendu> getVentesEnCours (int noUtilisateur, LocalDate ceJour) {
		
		return ArticleVenduManager.articleVenduDAO.getVentesEnCours(noUtilisateur, ceJour);
	}
	
	public List<ArticleVendu> getVentesNonDebutees (int noUtilisateur, LocalDate ceJour) {
		
		return ArticleVenduManager.articleVenduDAO.getVentesNonDebutees(noUtilisateur, ceJour);
	}

	public List<ArticleVendu> getVentesTerminees (int noUtilisateur, LocalDate ceJour) {
		
		return ArticleVenduManager.articleVenduDAO.getVentesTerminees(noUtilisateur, ceJour);
	}





}
