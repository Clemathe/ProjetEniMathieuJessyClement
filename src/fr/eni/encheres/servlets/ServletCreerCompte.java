package fr.eni.encheres.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.UtilisateurManager;

/**
 * Servlet implementation class ServletCreerCompte
 */
@WebServlet("/CreerCompte")
public class ServletCreerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageCreerCompte.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// enregistrement des saisies

		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String motDePasse = request.getParameter("motDePasse");
		String motDePasse1 = request.getParameter("motDePasse1");


		List<String> messageErreur = new ArrayList<>();

		// verification de la saise et intégrité des données

		UtilisateurManager utilisateurManager = new UtilisateurManager();
		messageErreur = utilisateurManager.verificationSaisieUtilisateur(pseudo, nom, prenom, email, telephone, rue,
				codePostal, ville, motDePasse, motDePasse1);
		boolean validationOK = utilisateurManager.validationCreationCompte(messageErreur);
		
		
		// insert ou affichage message erreur

						if (validationOK != true) {
			
			String erreur = utilisateurManager.traitementErreur(validationOK, messageErreur); 
			request.setAttribute("Erreur", erreur);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/PageCreerCompte.jsp");
			rd.forward(request, response);
			
		} else
			
			try {
				utilisateurManager.creerUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal,
						ville, motDePasse);
			} catch (SQLException e) {
				e.printStackTrace();
				String messageErreurDal = "échec creation de l'utilisateur :  pseudo et/ou email déjà utilisé(s )";
				request.setAttribute("Erreur", messageErreurDal);
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/PageCreerCompte.jsp");
				rd.forward(request, response);
			}	
				// fin traitement accompli renvoi l'utilisateur créé à la connexion	
				request.setAttribute("creationUtilisateur", "true"); 
				request.setAttribute("loginCreated", email);
			    request.setAttribute("passwordCreated", motDePasse);  
			    RequestDispatcher rd = request.getRequestDispatcher("/connexion");
			    rd.forward(request, response);
	}


}


