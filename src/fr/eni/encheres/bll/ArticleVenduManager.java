package fr.eni.encheres.bll;

import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;
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
	

	public ArticleVendu getArticleVendu(int noArticle) {
		
		return ArticleVenduManager.articleVenduDAO.getArticleVendu(noArticle);
	}
	public void insertArticleVendu (ArticleVendu articleVendu) {
		
		ArticleVenduManager.articleVenduDAO.insertArticleVendu(articleVendu);
	}
}
