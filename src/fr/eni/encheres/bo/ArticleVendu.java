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
	private int PrixVente; 
	private boolean etatVente; 
	private Utilisateur acheteur; 
	private Utilisateur vendeur; 
	private List<Enchere> encheres; 
	private Categorie categorie; 
	private Retrait lieuRetrait; 

}
