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

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletVendreUnArticle
 */
@WebServlet(urlPatterns={"/ServletVendreUnArticle"})
public class ServletVendreUnArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String CHAMP_FICHIER= "fichier";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		LocalDate lDDebut= LocalDate.parse(request.getParameter("dateDebutEncheres"));
		Date dateDebutEncheres = Date.valueOf(lDDebut);
		LocalDate lDFin = LocalDate.parse(request.getParameter("dateFinEncheres"));
		Date dateFinEncheres = Date.valueOf(lDFin);
		
		
		String rue = (String)request.getParameter("rue");
		String codePostal = (String)request.getParameter("codePostal");
		String ville = (String)request.getParameter("ville");
		
		//recuperation des informations manquantes pour créer un ArticleVendu en BDD : noUtilisateur et noCategorie 
		Utilisateur utilisateurCourant = (Utilisateur)request.getSession().getAttribute("utilisateurCourant");
		int noUtilisateur = utilisateurCourant.getNoUtilisateur();
		
		Categorie categorieCourante = new Categorie();
		int noCategorie = categorieCourante.getNoCategorie();
		
		
		System.out.println(nomArticle+" "+description+" "+ categorieLibelle+" "+ miseAPrix+" "+dateDebutEncheres+" "+dateFinEncheres+" "+ rue+" "+ codePostal+" "+ ville+" "+ noUtilisateur+" "+ noCategorie);
		
		
		
		request.getSession().setAttribute("utilisateurCourant", utilisateurCourant);		
		
		
		
	}
	
	
	
	
	

}
