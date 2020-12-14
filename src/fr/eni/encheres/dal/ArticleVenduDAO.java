package fr.eni.encheres.dal;

import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

public interface ArticleVenduDAO {

	public List<ArticleVendu> getenchereEnCours(String categorie, String nomArticlePartiel);
	
	public ArticleVendu getArticleVendu(int noArticle);

	public void insertArticleVendu(String nomArticle,String description,LocalDate dateDebutEnchere,
			LocalDate dateFinEnchere,int miseAPrix, int noCategorie,Utilisateur utilisateur,Retrait lieuRetrait);
	

}
