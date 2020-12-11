package fr.eni.encheres.bo;

import java.util.List;

public class Utilisateur {
	
	private int noUtilisateur; 
	private String pseudo; 
	private String nom; 
	private String prenom; 
	private String email; 
	private String telephone; 
	private String rue; 
	private String codePostal; 
	private String ville; 
	private String motDePasse; 
	private int credit; 
	private boolean administrateur;
	private List<ArticleVendu> ventes;
	private List<ArticleVendu> achats; 
	private List<ArticleVendu> encheres;
	
	public Utilisateur() {
	}
	
	public Utilisateur( String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, int credit) {
		super();
		
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = 0;
		this.administrateur = false; 
	}

	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, int credit, boolean administrateur,
			List<ArticleVendu> ventes, List<ArticleVendu> achats, List<ArticleVendu> encheres) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
		this.ventes = ventes;
		this.achats = achats;
		this.encheres = encheres;
	}
	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, int credit, boolean administrateur) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
		
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public boolean isAdministrateur() {
		return administrateur;
	}
	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}
	public List<ArticleVendu> getVentes() {
		return ventes;
	}
	public void setVentes(List<ArticleVendu> ventes) {
		this.ventes = ventes;
	}
	public List<ArticleVendu> getAchats() {
		return achats;
	}
	public void setAchats(List<ArticleVendu> achats) {
		this.achats = achats;
	}
	public List<ArticleVendu> getEncheres() {
		return encheres;
	}
	public void setEncheres(List<ArticleVendu> encheres) {
		this.encheres = encheres;
	}

	@Override
	public String toString() {
		return String.format(
				"Utilisateur [noUtilisateur=%s, pseudo=%s, nom=%s, prenom=%s, email=%s, telephone=%s, rue=%s, codePostal=%s, ville=%s, motDePasse=%s, credit=%s, administrateur=%s, ventes=%s, achats=%s, encheres=%s]",
				noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit,
				administrateur, ventes, achats, encheres);
	} 
	
	

}
