package fr.eni.encheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletListesEncheresConnecte
 */
@WebServlet(urlPatterns = { "/ServletListesEncheresConnecte","/mesEncheres"})
public class ServletListesEncheresConnecte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PagesListeEncheresConnecte.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateurCourant = (Utilisateur)request.getSession().getAttribute("utilisateurCourant");  
		int noUtilisateur = utilisateurCourant.getNoUtilisateur();
		
		
		//recuperation de quel radioBtn est coché ?
		String achatVente = request.getParameter("achatsVentes");
		//recuperation des checkBox
		String ventesEnCours = request.getParameter("ventesEnCours");
		String ventesNonDebutees = request.getParameter("ventesNonDebutees");
		String ventesTerminees = request.getParameter("ventesTerminees");
		
		String encheresOuvertes = request.getParameter("encheresOuvertes");
		String encheresEnCours = request.getParameter("encheresEnCours");
		String encheresRemportees = request.getParameter("encheresRemportees");
		
		System.out.println(achatVente);
		//radiobtn VENTE
		if(achatVente.equalsIgnoreCase("vente")) {
			LocalDate ceJour = LocalDate.now();
			System.out.println(ceJour);
			
			//récupérer les ventes en Cours
			if(ventesEnCours.equals("vEnCours")) {
			ArticleVenduManager mesVentesEnCours = new ArticleVenduManager();
			List<ArticleVendu> ventes = mesVentesEnCours.getVentesEnCours(noUtilisateur, ceJour);
			System.out.println(ventes.toString());
			request.setAttribute("mesVentesEnCours",ventes);
			
			}
			
			
			
			
			
			
			
			
			
			
			
		}else if(achatVente.equalsIgnoreCase("achat")) {
			request.setAttribute("achat", "achat");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		doGet(request, response);
	}

}
