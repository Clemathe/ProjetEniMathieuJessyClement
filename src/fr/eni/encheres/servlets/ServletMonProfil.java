

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

@WebServlet("/MonProfil")
public class ServletMonProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//
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

		
		boolean modifierProfil = request.getParameter("modifierProfil") != null; 
		
		
		 if (modifierProfil ) {
			System.out.println("DoPost Servlet MonProfil if request.getParameter != null");
			// entrer dans le formulaire PageModifierProfil.jsp
			Utilisateur utilisateur = new Utilisateur(); 
			utilisateur = (Utilisateur) request.getSession(true).getAttribute("utilisateurCourant");
			request.setAttribute("utilisateur", utilisateur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageModifierProfil.jsp"); 
			rd.forward(request, response);			
		} 
		 
		System.out.println("DoPostServelt avant push enregistrer");
		
		boolean enregistrer = request.getParameter("enregistrer") != null; 
		System.out.println(request.getParameter("enregistrer"));
		if (enregistrer) {
			
			//traitement update
			System.out.println("DoPostServelt après push enregistrer");
			
			// 1. Vérifier que le motDePasse saisie = mot de passe en dataBase de l'utilisateur courant. 
			Utilisateur utilisateur = new Utilisateur(); 
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			Boolean userPassOk = false; 
			List<String> messageUtilisateur = new ArrayList<>();
			try {
				System.out.println("DoPost Servlet MonProfil try/catch verification mot de passe");
				String motDePasse = request.getParameter("motDePasse");
				
				utilisateur = (Utilisateur) request.getSession(true).getAttribute("utilisateurCourant");
				String login = utilisateur.getPseudo(); 
				
				//userPassOk = utilisateurManager.verificationUtilisateurMotDePasse(login, motDePasse); 
			} catch (Exception e) {
				System.out.println();
				messageUtilisateur.add("le mot de passe saisie ne correspond pas à l'utilisateur"); 
			}
			if (userPassOk != false ) {
				
				System.out.println("DoPost Servlet MonProfil if userPass != false");
				
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
				messageUtilisateur.addAll(utilisateurManager.verificationSaisieUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, motDePasse1)); 
		
				Boolean validationOK = utilisateurManager.validationCreationCompte(messageUtilisateur);
				
				// procéder à l'update en DBB si validationOK = true; 
				String updatetOK = null; 
				if (validationOK == true) {
					System.out.println("DoPost Servlet MonProfil if validationOK = true");
					 try {
						 updatetOK = utilisateurManager.modifierUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse1); 
						 System.out.println("try/catch utilisateurManager.modifierU");
					} catch (Exception e) {
						messageUtilisateur.add(updatetOK); 
					}
				}

			}
			// affichage du messageUtilisateur 
			System.out.println("DoPost Servlet MonProfil affichage du messageUtilisateur");
			StringBuilder sb = new StringBuilder();
				for (String s : messageUtilisateur) {
					sb.append(s);
					sb.append(" ");
				}
				System.out.println(sb.toString());
			String message = sb.toString();
			request.setAttribute("messageUtilisateur", message);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/PageModifierProfil.jsp");
			rd.forward(request, response);		
		}
		
		/*if (supprimer) {
			
			/* afficher message êtes vous sûr de vouloir supprimer votre profil ? 
			String alerteSupression = " êtes vous sûr de vouloir supprimer votre profil ? "; 
			request.setAttribute("messageUtilisateur", alerteSupression);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/PageModifierProfil.jsp");
			rd.forward(request, response);*/
			
			// traitement supprimer
			String no_utilsateur = request.getParameter("no_utilisateur"); 
			
			System.out.println("DoPostServelt en dehors de tous les if");
			
	}// 
}
		

		



