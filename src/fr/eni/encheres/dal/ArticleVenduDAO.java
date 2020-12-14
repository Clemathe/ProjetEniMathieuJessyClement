package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;

public interface ArticleVenduDAO {

	public List<ArticleVendu> getenchereEnCours(String categorie, String nomArticlePartiel);
	
	public ArticleVendu getArticleVendu(int noArticle);

	public void insertArticleVendu(ArticleVendu articleVendu);
	

}
