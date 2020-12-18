package fr.eni.encheres.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletSupprimerCompte
 */
@WebServlet("/SupprimerCompte")
public class ServletSupprimerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Utilisateur utilisateurSession = new Utilisateur();
		UtilisateurManager um = new UtilisateurManager();
		try {
			int no_utilisateur = (int) request.getSession(true).getAttribute("no_utilisateur");
			utilisateurSession = um.selectBy(no_utilisateur); 
		} catch (Exception e) {
			System.out.println("erreur dans récupération utilisateur doGet Supprimer Compte");
		}
		
		
		String prenom = (String) utilisateurSession.getPrenom();
		int credit = (Integer) utilisateurSession.getCredit();
		String attention = "Attention ";
		String suite1 = " vous êtes sur le point de supprimer votre compte utilisateur, cette action est irréversible et vos ";
		String suite2 = " crédit(s) seront perdus. "
				+ "Pour confirmer la suppression du compte cliquez sur supprimer" + " sinon cliquez sur retour ";
		request.setAttribute("prenom", prenom);
		request.setAttribute("attention", attention);
		request.setAttribute("suite1", suite1);
		request.setAttribute("suite2", suite2);
		request.setAttribute("credit", credit);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageProfil.jsp");
		rd.forward(request, response);

	}

	/**
	 * jess
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Utilisateur utilisateurSession = new Utilisateur();
		UtilisateurManager um = new UtilisateurManager();
		try {
			int no_utilisateur = (int) request.getSession(true).getAttribute("no_utilisateur");
			utilisateurSession = um.selectBy(no_utilisateur); 
		} catch (Exception e) {
			System.out.println("erreur dans récupération utilisateur doGet Supprimer Compte");
		}
				
		
		int no_utilisateur = (int) utilisateurSession.getNoUtilisateur();
		
		if (no_utilisateur > 0) {
			try {
				um.supprimer(no_utilisateur);
			} catch (SQLException e) {
				e.printStackTrace();
				RequestDispatcher rd = request.getRequestDispatcher("/connexion");
				rd.forward(request, response);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageAccueilNonConnecte.jsp");
			rd.forward(request, response);

		} 

	}
}
