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

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletMonProfil
 */
@WebServlet("/MonProfil")
public class ServletMonProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doget mon profil");
	
		Utilisateur utilisateur = new Utilisateur(); 
		utilisateur = (Utilisateur) request.getSession(true).getAttribute("utilisateurCourant");
		
		
		if(utilisateur == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/connexion"); 
			rd.forward(request, response);
			System.out.println("renvoyer vers page se connecter");
			
		} else 
		
		request.setAttribute("utilisateur", utilisateur);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageMonProfil.jsp"); 
		rd.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DoPost Servlet MonProfil");
		
		
		
		if (request.getParameter("modifierProfil") != null ) {
			// entrer dans le formulaire PageModifierProfil.jsp
			Utilisateur utilisateur = new Utilisateur(); 
			utilisateur = (Utilisateur) request.getSession(true).getAttribute("utilisateurCourant");
			request.setAttribute("utilisateur", utilisateur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageModifierProfil.jsp"); 
			rd.forward(request, response);			
		} 
		
		if (request.getParameter("enregistrer") != null) {
			//traitement update
			System.out.println("DoPostServelt après push enregistrer");
			
			
			// 1. Vérifier que le motDePasse saisie = mot de passe en dataBase de l'utilisateur courant. 
			Utilisateur utilisateur = new Utilisateur(); 
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			Boolean userPassOk = false; 
			List<String> messageErreur = new ArrayList<>();
			try {
				
				String motDePasse = request.getParameter("motDePasse");
				
				utilisateur = (Utilisateur) request.getSession(true).getAttribute("utilisateurCourant");
				String login = utilisateur.getPseudo(); 
				
				userPassOk = utilisateurManager.verificationUtilisateurMotDePasse(login, motDePasse); 
			} catch (Exception e) {
				System.out.println();
				messageErreur.add("le mot de passe saisie ne correspond pas à l'utilisateur"); 
			}
			if (userPassOk != false ) {
				
				String pseudo = request.getParameter("pseudo");
				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				String email = request.getParameter("email");
				String telephone = request.getParameter("telephone");
				String rue = request.getParameter("rue");
				String codePostal = request.getParameter("codePostal");
				String ville = request.getParameter("ville");
				String motDePasse = request.getParameter("motDePasse1");
				String motDePasse1 = request.getParameter("motDePasseNew");
				
				//verifier l'intégrité des données du formulaire avant update
				messageErreur.addAll(utilisateurManager.verificationSaisieUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, motDePasse1)); 
				
				
				Boolean validationOK = utilisateurManager.validationCreationCompte(messageErreur);

				
			}
			// appel verif 
			
		}
			// ajouter un input hidden 
			else // rester sur meme page et erreur
			
			
			
			
		
		
		
		
		 // attribut modifier = lancer update
		
			// attribut vide =  entrée sur modifier profil
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageModifierProfil.jsp"); 
			rd.forward(request, response);
			System.out.println("renvoyer vers page modifier");	
			
			
		
		
		// retour sur la page monProfil
		request.setAttribute("utilisateur", utilisateur);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageMonProfil.jsp"); 
		rd.forward(request, response);	
		System.out.println();
		
	}

}
