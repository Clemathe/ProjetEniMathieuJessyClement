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
		Utilisateur utilisateurCourant = (Utilisateur)request.getSession().getAttribute("utilisateurCourant");  
		int noUtilisateur = utilisateurCourant.getNoUtilisateur();
		request.setAttribute("noUser", noUtilisateur);
		List<ArticleVendu> listToutesMesVentes = new ArticleVenduManager().getToutesMesVentes(noUtilisateur);
		request.setAttribute("toutesMesVentes", listToutesMesVentes);
		
		
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
		String achatsVentes = request.getParameter("achatsVentes");
		String quelBtnRadioActif = new ArticleVenduManager().radioBtn(achatsVentes);
		
		if (quelBtnRadioActif.equalsIgnoreCase("ventes")){
			
			
			String ventesEnCours = request.getParameter("ventesEnCours");
			String ventesNonDebutees = request.getParameter("ventesNonDebutees");
			String ventesTerminees = request.getParameter("ventesTerminees");
			
			if(ventesEnCours == null & ventesNonDebutees == null & ventesTerminees == null) {
				
				List<ArticleVendu> listToutesMesVentes = new ArticleVenduManager().getToutesMesVentes(noUtilisateur);
				request.setAttribute("toutesMesVentes", listToutesMesVentes);
			}else {
			//recuperation des checkBox
			
				if(ventesEnCours !=null) {
				List<ArticleVendu> listVentesEnCours = new ArticleVenduManager().getVentes(noUtilisateur, ventesEnCours);
				request.setAttribute("ventesEnCours",listVentesEnCours);
				}
				
				
				if(ventesNonDebutees !=null) {
				List<ArticleVendu> listVentesNonDebutees = new ArticleVenduManager().getVentes(noUtilisateur, ventesNonDebutees);
				request.setAttribute("ventesNonDebutees",listVentesNonDebutees);
				} 
				
				
				if(ventesTerminees !=null) {
				List<ArticleVendu> listVentesTerminees = new ArticleVenduManager().getVentes(noUtilisateur, ventesTerminees);
				request.setAttribute("ventesTerminees",listVentesTerminees);
				}
			}
		}
		
		
		
		
		
		
		
		
		String encheresOuvertes = request.getParameter("encheresOuvertes");
		String encheresEnCours = request.getParameter("encheresEnCours");
		String encheresRemportees = request.getParameter("encheresRemportees");
		
		 RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PagesListeEncheresConnecte.jsp");
	     rd.forward(request, response);
		
		
	}
		
		
		
		
		
		
		
		
		
			
			
			
			
			
			
			
			
		
	}

		
		
		
		
		
		
		
		
		
		
		
		
		
	
	

	

