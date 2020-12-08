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

}
