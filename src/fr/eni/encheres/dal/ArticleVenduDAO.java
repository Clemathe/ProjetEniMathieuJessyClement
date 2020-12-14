package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;

public interface ArticleVenduDAO {

	public List<ArticleVendu> getenchereEnCours(String categorie, String nomArticlePartiel);
	
	public ArticleVendu getArticleVendu(int noArticle);

	public void insertArticleVendu(ArticleVendu articleVendu);
	
	public List<ArticleVendu> getToutesEncheresEnCours();

	public boolean enregistrerUneEnchere(Enchere nouvelleEnchere);
}
