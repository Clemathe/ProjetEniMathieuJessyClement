package fr.eni.encheres.bll;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
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

	public void mettreAJourLePrixDeVente(int montantEnchere, int noArticle) throws SQLException {
		articleVenduDAO.mettreAJourLePrixDeVente(montantEnchere, noArticle);
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