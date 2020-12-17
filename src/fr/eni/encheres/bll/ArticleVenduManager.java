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
		LocalDate echeanceEnchere = articleCourant.getDateFinEncheres();
		boolean auMoinsUneEnchere= auMoinsUneEnchere(articleCourant);
		int prixActuel = articleCourant.getPrixVente();
		
		String message = null;
		// La date de fin d'enchere est elle atteinte ?
		if ( echeanceEnchere.compareTo(LocalDate.now()) > 0 ) {
			// L'encherisseur a il assez de crédit ?
			if (soldeUtilisateur > montantEnchere ) {
				// Son offre est elle superieur au prix actuel ?
				if( montantEnchere > prixActuel) {
					
					UtilisateurManager uManager = new UtilisateurManager();
					// creation de l'objet enchère
					Enchere nouvelleEnchere = new Enchere(LocalDate.now(), montantEnchere, articleCourant, utilisateurCourant);
					
					try {
						//TODO Begin transcation
						articleVenduDAO.enregistrerUneEnchere(nouvelleEnchere);
						uManager.debiterUtilisateur(montantEnchere, utilisateurCourant);
						articleVenduDAO.mettreAJourLePrixDeVente(montantEnchere, noArticle);
						if (auMoinsUneEnchere) uManager.rembourserUtilisateur(prixActuel, articleCourant.getEncheres().get(0).getUtilisateur());
						//TODO Commit Transcation
					} catch (Exception e) {
						
						message = "L'enchère n'a pas pu être enregistrée";
						
						return message;
					}
							
					message = "Enchère enregistrée";
			
				}else {
					message = "Votre enchère est inférieure à l'enchère la plus haute ou à la mise à prix";
				}
		}else {
				message = "Vous n'avez pas suffisament de crédit";
		}
	
	}else {
		message = "L'enchère est déjà terminée";
		
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

	// L'article contient il déjà une enchère ?
	public boolean auMoinsUneEnchere(ArticleVendu articleCourant) {
		int enchereLaPlusHaute;
		boolean auMoinsUne;
		try {  
			enchereLaPlusHaute = articleCourant.getEncheres().get(0).getMontantEnchere();
			auMoinsUne = true;
			System.out.println("auMoinsUneEnchere = true;");
		} catch (Exception e) { 
			auMoinsUne = false;
			System.out.println("auMoinsUneEnchere = false;");
	}
		return auMoinsUne;

	}
	//JSP liste mes enchères : verif Checkbox
	
	public String radioBtn (String achatsVentes) {
	
	String quelRadioCoche = null;
		
	System.out.println("Controle Valeur RadioBouton dans article Manager : "+achatsVentes);
	if (achatsVentes.equalsIgnoreCase("vente")) {
		quelRadioCoche = "ventes";
	}else if(achatsVentes.equalsIgnoreCase("vente")) {
		quelRadioCoche = "achats";
	}
	return quelRadioCoche;
	}
	
	
	public List<ArticleVendu> getVentes (int noUtilisateur, String ventes){
		List<ArticleVendu> ventesAAfficher = null;
		LocalDate ceJour = LocalDate.now();
		System.out.println("controle type de vente : "+ventes);
		
		if (ventes.equals("vEnCours")) {
			ventesAAfficher = new ArticleVenduManager().getVentesEnCours(noUtilisateur, ceJour);
		}
		if (ventes.equals("vNonDebutees")) {
			
			ventesAAfficher = new ArticleVenduManager().getVentesNonDebutees(noUtilisateur, ceJour);
		}
		if (ventes.equals("vTerminees")) {
			ventesAAfficher = new ArticleVenduManager().getVentesTerminees(noUtilisateur, ceJour);
		}
		
		
		return ventesAAfficher;
	}
	
	public List<ArticleVendu> getToutesMesVentes (int noUtilisateur) {
		
		return ArticleVenduManager.articleVenduDAO.getToutesMesVentes(noUtilisateur);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}