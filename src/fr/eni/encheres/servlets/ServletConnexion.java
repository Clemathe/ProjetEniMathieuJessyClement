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

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/ServletConnexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageConnexion.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurManager UManager = new UtilisateurManager();
		String login = (String) request.getParameter("login");
		String password = (String) request.getParameter("pass");
		
		boolean matchingUserPassword = UManager.VerificationUtilisateurMotDePasse(login, password);
		
		
		
		if (!matchingUserPassword) {
			//TODO
		}else {
			HttpSession sessionUtilisateur = request.getSession();
			sessionUtilisateur.setAttribute("login", request.getParameter("login") );

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PagesListeEncheresConnecte.jsp");
			rd.forward(request, response);
		}
		
		
	
		
		
		
	}

}
