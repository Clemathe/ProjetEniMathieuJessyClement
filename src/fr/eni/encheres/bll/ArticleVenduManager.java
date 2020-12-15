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
	

	public ArticleVendu getArticleVendu(int noArticle) {
		
		return ArticleVenduManager.articleVenduDAO.getArticleVendu(noArticle);
	}
	
	
	public void insertArticleVendu (ArticleVendu articleVendu) {
		
		ArticleVenduManager.articleVenduDAO.insertArticleVendu(articleVendu);
	}

	public String Encherir(int montantEnchere, Utilisateur utilisateurCourant, int noArticle) {
		int soldeUtilisateur = utilisateurCourant.getCredit();
		ArticleVendu articleCourant = getArticleVendu(noArticle);
		boolean auMoinsUneEnchere = true;
		String message = null;
	
		if (soldeUtilisateur > montantEnchere ) {
			int enchereLaPlusHaute = 0;
			
			try {
				enchereLaPlusHaute = articleCourant.getEncheres().get(0).getMontantEnchere();
				
			} catch (Exception e) {
				auMoinsUneEnchere = false;
			}
			if(enchereLaPlusHaute < montantEnchere && auMoinsUneEnchere || !auMoinsUneEnchere && soldeUtilisateur > articleCourant.getMiseAPrix() ) {
				System.out.println("etape2");
				UtilisateurManager uManager = new UtilisateurManager();
				Enchere nouvelleEnchere = new Enchere(LocalDate.now(), montantEnchere, articleCourant, utilisateurCourant);
				try {
					uManager.debiterUtilisateur(montantEnchere, utilisateurCourant);
					articleVenduDAO.enregistrerUneEnchere(nouvelleEnchere);
					
					uManager.rembourserUtilisateur(enchereLaPlusHaute, articleCourant.getEncheres().get(0).getUtilisateur());
					
					message = "Enchère enregistrée";
					
				} catch (Exception e) {
					message = "Un problème s'est produit";
				}
			
			}else {
				message = "Votre enchère est inférieure à l'enchère la plus haute";
			}
		}else {
			message = "Vous n'avez pas suffisament de crédit";
		}
		
		
		
		return message;
	}
}
