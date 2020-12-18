package fr.eni.encheres.bll;

import java.time.LocalDate;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EnchereDAO;
import fr.eni.encheres.dal.EnchereDAOJdbcImpl;

public class EnchereManager {
	
	private static EnchereDAO enchereDAO;
	
	public EnchereManager() {
		enchereDAO = DAOFactory.getEnchereDAO();
	}
	
	public String Encherir(int montantEnchere, Utilisateur utilisateurCourant, int noArticle) {
		ArticleVenduManager aVManager = new ArticleVenduManager();
		
		int soldeUtilisateur = utilisateurCourant.getCredit();
		ArticleVendu articleCourant = aVManager.getArticleVendu(noArticle);
		LocalDate echeanceEnchere = articleCourant.getDateFinEncheres();
		boolean auMoinsUneEnchere= auMoinsUneEnchere(articleCourant);
		int prixActuel = articleCourant.getPrixVente();
		
		String message = null;
		// La date de fin d'enchere est elle atteinte ?
		if ( echeanceEnchere.compareTo(LocalDate.now()) > 0 ) {
			// L'encherisseur est-il different du vendeur ?
			
			if(utilisateurCourant.getNoUtilisateur() != articleCourant.getVendeur().getNoUtilisateur()) {
				// L'encherisseur a-t-il assez de crédit ?
				if (soldeUtilisateur > montantEnchere ) {
					// Son offre est elle superieur au prix actuel ?
					if( montantEnchere > prixActuel) {
						
						UtilisateurManager uManager = new UtilisateurManager();
						
						// creation de l'objet enchère
						Enchere nouvelleEnchere = new Enchere(LocalDate.now(), montantEnchere, articleCourant, utilisateurCourant);
						
						try {
							//TODO Begin transacation
							enchereDAO.enregistrerUneEnchere(nouvelleEnchere);
							uManager.debiterUtilisateur(montantEnchere, utilisateurCourant);
							aVManager.mettreAJourLePrixDeVente(montantEnchere, noArticle);
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
			message = "Vous ne pouvez pas enchérir sur votre vente";
		}
	}else {
		message = "L'enchère est déjà terminée";
		
	}
		return message;
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
}
