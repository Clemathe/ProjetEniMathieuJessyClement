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
	
	private Utilisateur utilisateur;
	private String pseudoVendeur;
	private int noCategorie;
	
	private int noUtilisateur;
	
	

	
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
			LocalDate dateFinEncheres, int miseAPrix, int noCategorie,Utilisateur utilisateur, Retrait lieuRetrait) {
		super();
		
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.noCategorie = noCategorie;
		this.utilisateur = utilisateur;
		this.lieuRetrait = lieuRetrait;
	}
	//utilisé dans page d'accueil
	public ArticleVendu(int noArticle, String nomArticle, int PrixVente, LocalDate dateFinEncheres, String pseudoVendeur) {
		setNoArticle(noArticle); 
		setNomArticle(nomArticle);
		setPrixVente(PrixVente);;
		setDateDebutEncheres(dateFinEncheres);
		setPseudoVendeur(pseudoVendeur);
		
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
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateFinEncheres, int miseAPrix, int prixVente,
			 Utilisateur vendeur, List<Enchere> encheres, Categorie categorie,
			Retrait lieuRetrait) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.vendeur = vendeur;
		this.encheres = encheres;
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
	public String getPseudoVendeur() {
		return pseudoVendeur;
	}
	public void setPseudoVendeur(String pseudoVendeur) {
		this.pseudoVendeur = pseudoVendeur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

}
