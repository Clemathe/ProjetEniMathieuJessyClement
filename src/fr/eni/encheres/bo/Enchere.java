package fr.eni.encheres.bo;

import java.time.LocalDate;

public class Enchere {
	
	private LocalDate dateEnchere; 
	private int montantEnchere; 
	private ArticleVendu article; 
	private Utilisateur utilisateur;
	private int noArticle;
	private int noUtilisateur;
	/**
	 * 
	 */
	public Enchere() {
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	/**
	 * @param dateEnchere
	 * @param montantEnchere
	 * @param article
	 * @param utilisateur
	 */
	public Enchere(LocalDate dateEnchere, int montantEnchere, ArticleVendu article, Utilisateur utilisateur) {
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.article = article;
		this.utilisateur = utilisateur;
	}
	public Enchere(int noUtilisateur, int noArticle, LocalDate dateEnchere,int montantEnchere) {
		this.noUtilisateur=noUtilisateur;
		this.noArticle=noArticle;
		this.dateEnchere=dateEnchere;
		this.montantEnchere=montantEnchere;
	}

	public Enchere( int noUtilisateur, int montantEnchere) {
		this.utilisateur = new Utilisateur(noUtilisateur, null);
		this.montantEnchere = montantEnchere;
		
	}
	/**
	 * @return the dateEnchere
	 */
	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	/**
	 * @param dateEnchere the dateEnchere to set
	 */
	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	/**
	 * @return the montantEnchere
	 */
	public int getMontantEnchere() {
		return montantEnchere;
	}

	/**
	 * @param montantEnchere the montantEnchere to set
	 */
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	/**
	 * @return the article
	 */
	public ArticleVendu getArticle() {
		return article;
	}

	/**
	 * @param article the article to set
	 */
	public void setArticle(ArticleVendu article) {
		this.article = article;
	}

	/**
	 * @return the utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	/**
	 * @param utilisateur the utilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + ", article=" + article
				+ ", utilisateur=" + utilisateur + "]";
	}

	
	
}
