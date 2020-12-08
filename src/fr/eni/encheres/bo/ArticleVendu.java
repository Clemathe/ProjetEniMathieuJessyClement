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
	private Utilisateur noAcheteur; 
	private Utilisateur noVendeur; 
	private List<Enchere> encheres; 
	private Categorie categorieArticle; 
	private Retrait lieuRetrait; 

}
