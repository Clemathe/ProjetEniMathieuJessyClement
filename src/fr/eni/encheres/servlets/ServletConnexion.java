package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet(urlPatterns={"/ServletConnexion", "/connexion"})
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("connect") != null) {
			System.out.println("false");
			request.getSession().invalidate();
			request.setAttribute("connect", "false");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageConnexion.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UtilisateurManager UManager = new UtilisateurManager();
	
		String login = null;
		String password = null;
		boolean matchingUserPassword = false;

		if ( request.getParameter("login") != null) {
			
		login = (String) request.getParameter("login");
		password = (String) request.getParameter("pass");
		matchingUserPassword = UManager.verificationUtilisateurMotDePasse(login, password);
		
		}else if ( request.getAttribute("login") != null) {
		 
		login = (String) request.getAttribute("login");
		password = (String) request.getAttribute("pass");
		matchingUserPassword = UManager.verificationUtilisateurMotDePasse(login, password);
		}
		if (matchingUserPassword) {
			
			Utilisateur utilisateurCourant = UManager.getUtilisateurPourSession(login);
			
			HttpSession sessionCourante = request.getSession(true);
			sessionCourante.setAttribute("utilisateurCourant", utilisateurCourant);
			
			request.setAttribute("nouvelleConnexion", request.getParameter("nouvelleConnexion"));
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/ServletAccueil");
			rd.forward(request, response);
			
		} else {
			
			String errorConnection = "Identifiant ou mot de passe incorrect";
			request.setAttribute("errorConnection", errorConnection);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageConnexion.jsp");
			rd.forward(request, response);
		}
			
	}
}
