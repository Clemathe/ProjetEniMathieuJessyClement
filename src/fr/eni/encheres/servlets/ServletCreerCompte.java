package fr.eni.encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

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
		System.out.println("doG servletCreerCompte");

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

		// int noUtilisateur = 0;
		String pseudo = null;
		String nom = null;
		String prenom = null;
		String email = null;
		String telephone = null;
		String rue = null;
		String codePostal = null;
		String ville = null;
		String motDePasse = null;
		// int credit = 0;
		List<String> MessageErreur = new ArrayList<>();
		Utilisateur utilisateur = new Utilisateur();

		// lecture et vérification pseudo

		try {

			pseudo = request.getParameter("pseudo");
			if (pseudo == null || !pseudo.matches("[a-zA-Z0-9]+")) {
				MessageErreur.add("erreur de saisie du pseudonyme : null ou caractères interdit");
				System.out.println("erreur pseudo");
			} else
				utilisateur.setPseudo(pseudo);
				System.out.println("pseudo entré : " + pseudo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// lecture et vérification nom
		try {
			nom = request.getParameter("nom");
			if (nom == null || !nom.matches("^[a-zA-Z]*$")) {

				MessageErreur.add("erreur de saisie du Nom : null ou caractère interdit");
				System.out.println("erreur nom");

			} else
				utilisateur.setNom(nom);
			System.out.println("nom entré : " + nom);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// lecture et vérification prénom

		try {
			prenom = request.getParameter("prenom");
			if (prenom == null || !prenom.matches("^[a-zA-Z]*$")) {

				MessageErreur.add("erreur de saisie du Prenom : null ou caractère interdit");
				System.out.println("erreur prenom");

			} else
				utilisateur.setPrenom(prenom);
			System.out.println("nom entré : " + prenom);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// lecture et vérification email

		try {
			email = request.getParameter("email");
			if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$")) {

				MessageErreur.add("erreur de saisie de l'email : null ou format interdit");
				System.out.println("erreur email");

			} else
				utilisateur.setEmail(email);
			System.out.println("email entré : " + email);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// lecture et vérification téléphone

		try {
			telephone = request.getParameter("telephone");
			if (telephone == null || !telephone.matches("(0|(\\+33)|(0033))[1-9][0-9]{8}")) {

				MessageErreur.add("erreur de saisie du numéro de téléphone : null ou format interdit");
				System.out.println("erreur telephone");

			} else
				utilisateur.setTelephone(telephone);
			System.out.println("telephone entré : " + telephone);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// lecture et vérification adresse

		try {
			rue = request.getParameter("rue");
			if (rue == null) {

				MessageErreur.add("erreur de saisie de la rue : null ");
				System.out.println("erreur rue");

			} else
				utilisateur.setRue(rue);
			System.out.println("rue entrée : " + rue);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// lecture et vérification ville

		try {
			ville = request.getParameter("ville");
			if (ville == null || !ville.matches("^[a-zA-Z]*$")) {

				MessageErreur.add("erreur de saisie de la ville : null ou caractère interdit");
				System.out.println("erreur ville");

			} else
				utilisateur.setVille(ville);
			;
			System.out.println("ville entrée: " + ville);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// lecture et vérification code Postal

		try {
			codePostal = request.getParameter("codePostal");
			if (codePostal == null || !codePostal.matches("^[0-9]{5}$")) {

				MessageErreur.add("erreur de saisie du code postal : null ou caractère interdit");
				System.out.println("erreur code postal");

			} else
				utilisateur.setCodePostal(codePostal);
			System.out.println("code Postal entré : " + codePostal);

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		String motDePasse1 = null;

		try {
			motDePasse1 = request.getParameter("motDePasse1");
			motDePasse = request.getParameter("motDePasse");

			//solution verification motDePasse sans regexPattern
			
			if (!motDePasse.equals(motDePasse1)) {
				MessageErreur
						.add("erreur de confirmation du mot de passe : veuillez entrer de nouveau votre mot de passe");
				System.out.println("erreur confirmation motDepasse" + motDePasse);
			} else
				utilisateur.setMotDePasse(motDePasse);
			System.out.println("Mot de passe entré : " + motDePasse);

			// solution avec pattern à débugger 
			
			/*
			 * lecture et vérification des mots de passe avec contrainte : - 8 caractères au
			 * minimum - au moins une lettre (minuscule ou majuscule) - au moins un chiffre
			 * - les caractères spéciaux sont autoriés - pas d'espace
			 * 
			 * if (motDePasse1 == null || !motDePasse1.matches(
			 * "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[-+!*$@%_])([-+!*$@%_\\w]{8,15})$")) {
			 * MessageErreur.add(
			 * "erreur de saisie du mot de passe : il doit contenir 8 caractères au minimum, au moins une lettre (minuscule ou majuscule), au moins un chiffre, les caractères spéciaux sont autoriés"
			 * ); System.out.println("erreur saisie motDepasse1 " + motDePasse1);
			 * 
			 * } else if (!motDePasse.equals(motDePasse1)) { MessageErreur
			 * .add("erreur de confirmation du mot de passe : veuillez entrer de nouveau votre mot de passe"
			 * ); System.out.println("erreur confirmation motDepasse" + motDePasse); } else
			 * { utilisateur.setMotDePasse(motDePasse);
			 * System.out.println("Mot de passe entré : " + motDePasse); }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Fin des vérifications et traitement de la création utilisateur
		
		//instanciation StringBuilder pour envoie message erreur 
		
		StringBuilder sb = new StringBuilder();

		if (MessageErreur.isEmpty()) {
			
			// creation de l'Utilisateur avant envoie 
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			try {
				
				//creation de l'utilisateur en BDD
				utilisateurManager.creerUtilisateur(utilisateur);
				
				//envoie des attributs necessaires à la connexion
				
				
				request.setAttribute("login", utilisateur.getPseudo() );
				request.setAttribute ("pass", utilisateur.getMotDePasse()); 
				
				
				
				RequestDispatcher rd = request.getRequestDispatcher("/connexion"); 
				rd.forward(request, response);


			} catch (Exception e) {
				// vérifie unicité en BDD
				MessageErreur.add("echec creation de l'utilisateur :  pseudo et/ou email déjà utilisé(s )");
				e.printStackTrace();
			}

		} else {
					
		// renvoie les message.erreur à la jsp.	
		for (String s : MessageErreur) {
			sb.append(s); 
			sb.append("/t"); 			
		}	
		System.out.println(sb.toString());
		String Erreur = sb.toString(); 
		request.setAttribute("Erreur",Erreur);
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/PageCreerCompte.jsp");
		rd.forward(request, response);
		
		}
	}

}
