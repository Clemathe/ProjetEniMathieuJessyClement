package fr.eni.encheres.servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletVendreUnArticle
 */
@WebServlet(urlPatterns= {"/ServletVendreUnArticle", "/vendre"})
public class ServletVendreUnArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String CHAMP_FICHIER= "fichier";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateurCourant = (Utilisateur)request.getSession().getAttribute("utilisateurCourant");
		String rue = utilisateurCourant.getRue();
		String codePostal = utilisateurCourant.getCodePostal();
		String ville = utilisateurCourant.getVille();
		
		Retrait retrait = new Retrait (rue,codePostal,ville);
		request.setAttribute("retrait", retrait);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageVendreUnArticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recuperation des paramètres depuis la JSP PageVEndreUnArticle
		String nomArticle = (String)request.getParameter("nomArticle");
		String description = (String)request.getParameter("description");
		String categorieLibelle = (String)request.getParameter("categorieLibelle");
		int miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
		
		LocalDate dateDebutEnchere = LocalDate.parse(request.getParameter("dateDebutEncheres"));
		LocalDate dateFinEnchere = LocalDate.parse(request.getParameter("dateFinEncheres"));
		
		String rue = (String)request.getParameter("rue");
		String codePostal = (String)request.getParameter("codePostal");
		String ville = (String)request.getParameter("ville");
		
		//recuperation des informations manquantes pour créer un ArticleVendu en BDD
		//Recuperation du noUtilisateur à partir de la session en cours 
		Utilisateur utilisateurCourant = (Utilisateur)request.getSession().getAttribute("utilisateurCourant");
		int noUtilisateur = utilisateurCourant.getNoUtilisateur();
		
		//recuperation du noCategorie a partir du libelle choisit par le user
		CategorieManager categorie = new CategorieManager();
		int noCategorie = categorie.getCategorie(categorieLibelle);
		
		
		System.out.println(nomArticle+" "+description+" "+ categorieLibelle+" "+ miseAPrix+" "+dateDebutEnchere+" "+dateFinEnchere+" "+ rue+" "+ codePostal+" "+ ville+" "+ noUtilisateur);
		
		request.getSession().setAttribute("utilisateurCourant", utilisateurCourant);		
		
		//construction du retrait avec les paramêtres colléctés:
		Retrait lieuRetrait = new Retrait(rue, codePostal, ville);
		//construction d'un utilisateur "léger"
		Utilisateur utilisateur = new Utilisateur(noUtilisateur);
		
		//Construction de l'article à créer en BDD
		ArticleVendu articleAVendre = new ArticleVendu(nomArticle,description,dateDebutEnchere,dateFinEnchere,miseAPrix,noCategorie,utilisateur,lieuRetrait);
		ArticleVenduManager articleVendu = new ArticleVenduManager();
		articleVendu.insertArticleVendu(articleAVendre);
		
	}
	
	

	

}
