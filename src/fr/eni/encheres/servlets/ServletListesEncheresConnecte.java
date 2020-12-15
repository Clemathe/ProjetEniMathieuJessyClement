package fr.eni.encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
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
		Utilisateur utilisateurCourant = (Utilisateur)request.getSession().getAttribute("utilisateurCourant");  
		int noUtilisateur = utilisateurCourant.getNoUtilisateur();
		ArticleVenduManager mesVentes = new ArticleVenduManager();
		request.setAttribute("mesVentes",mesVentes.getMesVentes(noUtilisateur));
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PagesListeEncheresConnecte.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String achatVente = request.getParameter("achatsVentes");
		if(achatVente.equalsIgnoreCase("vente")) {
			request.setAttribute("vente", "vente");
		}else if(achatVente.equalsIgnoreCase("achat")) {
			request.setAttribute("achat", "achat");
		}
		doGet(request, response);
	}

}
