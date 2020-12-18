package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
		Boolean loginCreated = (request.getParameter("loginCreated") != null);
		
		Cookie[] cookies = request.getCookies(); 
		Cookie cookieLogin = null; 
		
		if (deconnection == true) {
			
			// déconnection de la session
			request.getSession().invalidate();

			// Pour l'affichage d'un message de déconnection dans la jsp accueil
			request.setAttribute("disconnect", "true");

			RequestDispatcher rd = request.getRequestDispatcher("/accueil");
			rd.forward(request, response);
			
		}
//		 if (cookies!=null) {
//			for(Cookie c : cookies) {
//				if(c.getName().equals("login")) {
//					cookieLogin = c; 
//					break; 
//				}
//			}
//		} 
//		if (cookieLogin != null) {
//			request.getSession().setAttribute("login", cookieLogin.getValue());
//			RequestDispatcher rd = request.getRequestDispatcher("/accueil");
//			rd.forward(request, response);
//			
//		}
		else {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageConnexion.jsp");
		rd.forward(request, response);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
//		boolean nouvelleConnexion = Boolean.parseBoolean(request.getParameter("nouvelleConnexion")); 
		boolean creationUtilisateur = request.getAttribute("creationUtilisateur") != null;
		String login = (String) request.getParameter("login");
		String password = (String) request.getParameter("pass");
		String seSouvenir = request.getParameter("rememberMe"); 
		System.out.println("connexion "+request.getAttribute("creationUtilisateur"));
		System.out.println("connexion "+ creationUtilisateur);
		if (seSouvenir != null) {
			Cookie cookieLogin = new Cookie("login", login); 
			cookieLogin.setMaxAge(200000);
			response.addCookie(cookieLogin);
		}
//		if (nouvelleConnexion) {
//			 login = (String) request.getParameter("login");
//			 password = (String) request.getParameter("pass");
//			 
//		}
		if (creationUtilisateur) {
			 login = (String) request.getAttribute("loginCreated");
			 password = (String) request.getAttribute("passwordCreated");
		}
		UtilisateurManager UManager = new UtilisateurManager();
		
			try {Utilisateur utilisateurCourant = UManager.getUtilisateurPourSession(login, password);
			System.out.println("servelt connexion : " + utilisateurCourant);
			
				if(utilisateurCourant != null) {
					HttpSession sessionCourante = request.getSession(true);
					// Création du profil de l'utilisateur et stockage en session
					sessionCourante.setAttribute("utilisateurCourant", utilisateurCourant);
					
					// Attribut pour afficher une alerte de connexion réussie
					request.setAttribute("nouvelleConnexion", "true");
					request.changeSessionId();
					//sessionCourante.setMaxInactiveInterval(5);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/ServletAccueil");
					rd.forward(request, response);
		
				}else {
		
					String errorConnection = "Identifiant ou mot de passe incorrect";
					request.setAttribute("errorConnection", errorConnection);
		
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageConnexion.jsp");
					rd.forward(request, response);
				}
			}catch (Exception e) {
				// Sinon je retourne à la page d'ajout pour indiquer les problèmes:
				e.printStackTrace();
				request.setAttribute("erreurs", e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageAccueilNonConnecte.jsp");
				rd.forward(request, response);
			}
		//}
	}
}