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
@WebServlet("/ServletConnexion")
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
		
		String login = (String) request.getParameter("login");
		String password = (String) request.getParameter("pass");

		boolean matchingUserPassword = UManager.verificationUtilisateurMotDePasse(login, password);
		String mdp = "Clement86*";
		System.out.println("res " +mdp.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[-+!*$@%_])([-+!*$@%_\\w]{8,15})$"));
		System.out.println("res2 " +mdp.matches("^(?=.*\\\\d) (?=.*[a-zA-Z])(?=\\\\S+$).{8,12}"));	
		
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
