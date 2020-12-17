package fr.eni.encheres.bo;

import java.time.LocalDate;

import java.util.List;

public class ArticleVendu {
	
	private int noArticle; 
	private String nomArticle; 
	private String description; 
	private LocalDate dateDebutEncheres; 
	private LocalDate dateFinEncheres; 
	private int miseAPrix; 
	private int prixVente; 
	private boolean etatVente; 
	private Utilisateur acheteur; 
	private Utilisateur vendeur; 
	private List<Enchere> encheres; 
	private Categorie categorie; 
	private Retrait lieuRetrait;
	
	private Enchere enchere;
	

	
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, boolean etatVente, Utilisateur acheteur,
			Utilisateur vendeur, List<Enchere> encheres, Categorie categorie, Retrait lieuRetrait) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.acheteur = acheteur;
		this.vendeur = vendeur;
		this.encheres = encheres;
		this.categorie = categorie;
		this.lieuRetrait = lieuRetrait;
	}
	//utilisé dans ajout nouvel article à la vente
	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix,Categorie categorie,Utilisateur vendeur, Retrait lieuRetrait) {
		super();
		
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.categorie = categorie;
		this.vendeur = vendeur;
		this.lieuRetrait = lieuRetrait;
	}
	//utilisé dans page d'accueil
	public ArticleVendu(int noArticle, String nomArticle, int PrixVente, LocalDate dateDebutEncheres,LocalDate dateFinEncheres, Utilisateur vendeur) {
		setNoArticle(noArticle); 
		setNomArticle(nomArticle);
		setPrixVente(PrixVente);
		setDateDebutEncheres(dateDebutEncheres);
		setDateFinEncheres(dateFinEncheres);
		setVendeur(vendeur);
		
	}
	//utilisé dans mesVentes
	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, Enchere enchere, 
			Categorie categorie, Retrait lieuRetrait) {
		
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.setEnchere(enchere);
		this.categorie = categorie;
		this.lieuRetrait = lieuRetrait;
	}
	//utilisé dans getVentesEnCours / ventesnondebutees
	public ArticleVendu(int noArticle, String nomArticle, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente) {
		this.noArticle=noArticle;
		this.nomArticle = nomArticle;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		
	}
	
	
	
	public ArticleVendu(String nomArticle, String description, LocalDate dateFinEncheres, int miseAPrix, int prixVente,
			boolean etatVente, Utilisateur acheteur, Utilisateur vendeur, List<Enchere> encheres, Categorie categorie,
			Retrait lieuRetrait) {
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.acheteur = acheteur;
		this.vendeur = vendeur;
		this.encheres = encheres;
		this.categorie = categorie;
		this.lieuRetrait = lieuRetrait;
	}
	// GetArticleVendu avec Enchere 
	public ArticleVendu(int noArticle, String nomArticle, String description,LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int miseAPrix, int prixVente,
			 Utilisateur vendeur, List<Enchere> encheres, Categorie categorie,
			Retrait lieuRetrait) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.vendeur = vendeur;
		this.encheres = encheres;
		this.categorie = categorie;
		this.lieuRetrait = lieuRetrait;
	}
	// GetArticleVendu sans Enchere 
	public ArticleVendu(int noArticle, String nomArticle, String description,LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int miseAPrix, int prixVente,
			 Utilisateur vendeur, Categorie categorie,
			Retrait lieuRetrait) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.vendeur = vendeur;
		this.categorie = categorie;
		this.lieuRetrait = lieuRetrait;
	}
	

	
	
	@Override
	public String toString() {
		return "ArticleVendu"
				+ "[noArticle=" + noArticle + ","
						+ " nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", acheteur=" + acheteur
				+ ", vendeur=" + vendeur + ", encheres=" + encheres + ", categorie=" + categorie + ", lieuRetrait="
				+ lieuRetrait + "]";
	}
	

	public int getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	public String getNomArticle() {
		return nomArticle;
	}
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}
	
	public int getMiseAPrix() {
		return miseAPrix;
	}
	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}
	public int getPrixVente() {
		return prixVente;
	}
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}
	public boolean isEtatVente() {
		return etatVente;
	}
	public void setEtatVente(boolean etatVente) {
		this.etatVente = etatVente;
	}
	public Utilisateur getAcheteur() {
		return acheteur;
	}
	public void setAcheteur(Utilisateur acheteur) {
		this.acheteur = acheteur;
	}
	public Utilisateur getVendeur() {
		return vendeur;
	}
	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}
	public List<Enchere> getEncheres() {
		return encheres;
	}
	public void setEncheres(List<Enchere> encheres) {
		this.encheres = encheres;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public Retrait getLieuRetrait() {
		return lieuRetrait;
	}
	public void setLieuRetrait(Retrait lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	} 
	
	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	
	public Enchere getEnchere() {
		return enchere;
	}
	public void setEnchere(Enchere enchere) {
		this.enchere = enchere;
	}

}
