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
@WebServlet(urlPatterns = { "/ServletConnexion", "/connexion" })
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Boolean deconnection = Boolean.parseBoolean(request.getParameter("disconnect"));

		if (deconnection == true) {
			// déconnection de la session
			request.getSession().invalidate();

			// Pour l'affichage d'un message de déconnection dans la jsp accueil
			request.setAttribute("disconnect", "true");

			RequestDispatcher rd = request.getRequestDispatcher("/accueil");
			rd.forward(request, response);
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

		// authentification de l'utilisateur et récupération du login
		String userLogin = getAuthentication(request, response, UManager);

		// Si l'utilisateur a été authentifié, son login est retourné sinon celui ci est nul
		if (userLogin != null) {
			// Création d'une nouvelle session
			HttpSession sessionCourante = request.getSession(true);

			// Création du profil de l'utilisateur et stockage en session
			Utilisateur utilisateurCourant = UManager.getUtilisateurPourSession(userLogin);
			sessionCourante.setAttribute("utilisateurCourant", utilisateurCourant);
			
		
			// Attribut pour afficher une alerte de connexion réussie
			request.setAttribute("nouvelleConnexion", "true");

			RequestDispatcher rd = getServletContext().getRequestDispatcher("/ServletAccueil");
			rd.forward(request, response);

		} else {

			String errorConnection = "Identifiant ou mot de passe incorrect";
			request.setAttribute("errorConnection", errorConnection);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageConnexion.jsp");
			rd.forward(request, response);
		}

	}

	public String getAuthentication(HttpServletRequest request, HttpServletResponse response,
			UtilisateurManager UManager) {

		String login = null;
		String password = null;
		String checkedLogin = null;

		boolean matchingUserPassword = false;
		boolean nouvelleConnexion = Boolean.parseBoolean(request.getParameter("nouvelleConnexion")) == true;
		boolean fromCreationToConnection = request.getAttribute("loginCreated") != null;

		if (nouvelleConnexion) {

			login = (String) request.getParameter("login");
			password = (String) request.getParameter("pass");
			matchingUserPassword = UManager.verificationUtilisateurMotDePasse(login, password);

		} else if (fromCreationToConnection) {

			login = (String) request.getAttribute("loginCreated");
			//password = (String) request.getAttribute("pass");
			matchingUserPassword = true;
		}
		
		if (matchingUserPassword) {
			checkedLogin = login;
		}

		return checkedLogin;
	}
}
