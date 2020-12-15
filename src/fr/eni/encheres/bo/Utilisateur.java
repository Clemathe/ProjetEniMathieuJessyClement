package fr.eni.encheres.bo;

import java.io.Serializable;
import java.util.List;

public class Utilisateur implements Serializable{
	
	
	/**
	 * La class Utilisateur respecte les règles d'un JavaBean:
	 * -implémente l'interface Serializable
	 * -Respecte la nomenclature des getters et setters
	 * -il y a un constructeur sans parametre
	 */
	private static final long serialVersionUID = 1L;
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
	// Utilisateur en session
	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, int credit, boolean administrateur) {
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
		this.credit = credit;
		this.administrateur = administrateur;
		
	}
	// Utilisateur select
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
	

	public Utilisateur(int noUtilisateur, String pseudo) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
	}

	public Utilisateur(int noUtilisateur) {
		this.noUtilisateur=noUtilisateur;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (administrateur ? 1231 : 1237);
		result = prime * result + ((codePostal == null) ? 0 : codePostal.hashCode());
		result = prime * result + credit;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((motDePasse == null) ? 0 : motDePasse.hashCode());
		result = prime * result + noUtilisateur;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + ((pseudo == null) ? 0 : pseudo.hashCode());
		result = prime * result + ((rue == null) ? 0 : rue.hashCode());
		result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
		result = prime * result + ((ville == null) ? 0 : ville.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Utilisateur other = (Utilisateur) obj;
		if (administrateur != other.administrateur) {
			return false;
		}
		if (codePostal == null) {
			if (other.codePostal != null) {
				return false;
			}
		} else if (!codePostal.equals(other.codePostal)) {
			return false;
		}
		if (credit != other.credit) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (motDePasse == null) {
			if (other.motDePasse != null) {
				return false;
			}
		} else if (!motDePasse.equals(other.motDePasse)) {
			return false;
		}
		if (noUtilisateur != other.noUtilisateur) {
			return false;
		}
		if (nom == null) {
			if (other.nom != null) {
				return false;
			}
		} else if (!nom.equals(other.nom)) {
			return false;
		}
		if (prenom == null) {
			if (other.prenom != null) {
				return false;
			}
		} else if (!prenom.equals(other.prenom)) {
			return false;
		}
		if (pseudo == null) {
			if (other.pseudo != null) {
				return false;
			}
		} else if (!pseudo.equals(other.pseudo)) {
			return false;
		}
		if (rue == null) {
			if (other.rue != null) {
				return false;
			}
		} else if (!rue.equals(other.rue)) {
			return false;
		}
		if (telephone == null) {
			if (other.telephone != null) {
				return false;
			}
		} else if (!telephone.equals(other.telephone)) {
			return false;
		}
		if (ville == null) {
			if (other.ville != null) {
				return false;
			}
		} else if (!ville.equals(other.ville)) {
			return false;
		}
		return true;
	} 
	
	

}
