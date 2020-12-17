package fr.eni.encheres.dal;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

public interface ArticleVenduDAO {

	public List<ArticleVendu> getenchereEnCours(String categorie, String nomArticlePartiel);
	
	public ArticleVendu getArticleVendu(int noArticle);

	public void insertArticleVendu(ArticleVendu articleVendu);
	
	public List<ArticleVendu> getToutesEncheresEnCours();

	public boolean enregistrerUneEnchere(Enchere nouvelleEnchere) throws SQLException;

	public List<ArticleVendu> getMesVentes(int noUtilisateur);
	
	public List<ArticleVendu> getVentesEnCours(int noUtilisateur, LocalDate ceJour);
	
	public List<ArticleVendu> getVentesNonDebutees(int noUtilisateur, LocalDate ceJour);
	
	public List<ArticleVendu> getVentesTerminees(int noUtilisateur, LocalDate ceJour);
	
	public Utilisateur selectBy(int no_utilisateur) throws SQLException;

	void mettreAJourLePrixDeVente(int prixDeVente, int no_article) throws SQLException;

	List<ArticleVendu> getToutesMesVentes(int noUtilisateur);

}
