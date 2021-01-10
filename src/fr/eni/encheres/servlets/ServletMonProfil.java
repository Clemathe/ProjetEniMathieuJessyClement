
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

import fr.eni.encheres.bll.MD5Utils;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

@WebServlet("/MonProfil")
public class ServletMonProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//
		
		UtilisateurManager um = new UtilisateurManager(); 
	
		Utilisateur utilisateurCourant =  (Utilisateur) request.getSession().getAttribute("utilisateurCourant");
		Utilisateur utilisateur = um.selectBy(utilisateurCourant.getNoUtilisateur()); 
		System.out.println(utilisateur);
		
		if (utilisateur == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/connexion");
			rd.forward(request, response);
			System.out.println("renvoyer vers page se connecter");
		} else

		request.setAttribute("utilisateur", utilisateur);
		System.out.println(utilisateur);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageMonProfil.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Utilisateur utilisateurSession = new Utilisateur();
		UtilisateurManager um = new UtilisateurManager();
		
		Utilisateur utilisateurCourant =  (Utilisateur) request.getSession().getAttribute("utilisateurCourant");
		utilisateurSession = um.selectBy(utilisateurCourant.getNoUtilisateur()); 
		
		List<String> messageUtilisateur = new ArrayList<>();
		String message = "Votre profil a bien été modifié";
		String modifierProfil = request.getParameter("modifierProfil");
		String enregistrer = request.getParameter("enregistrer");
		
		// entrer dans le formulaire PageModifierProfil.jsp avec parmaètres utilisteur

		if (modifierProfil != null && !modifierProfil.isEmpty()) {
			System.out.println("DoPost Servlet MonProfil première entrée");

			request.setAttribute("utilisateur", utilisateurSession);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageModifierProfil.jsp");
			rd.forward(request, response);
		}

		else if (enregistrer != null && !enregistrer.isEmpty())

		{
			// traitement update
			System.out.println("DoPostServelt enregistrer");

			// 1. Vérifier que le motDePasse saisie = mot de passe en BDD de l'utilisateur
			// courant :

		
			String motDePasseSaisie = (String) request.getParameter("motDePasse");
			String hashPassword1 = MD5Utils.digest(motDePasseSaisie);

			//mot de passe de l'utilisateurCourant
			String motDePasseSession = utilisateurSession.getMotDePasse(); 
			
			// verifier que les deux mots de passe matchent :
			
			boolean userPassOK = hashPassword1.equals(motDePasseSession);
			
			if (userPassOK != true) {
			message = "le mot de passe saisie ne correspond pas à l'utilisateur";
			} else

				System.out.println("DoPost Servlet MonProfil if userPass != false");

			request.setCharacterEncoding("UTF-8");
			int noUtilisateur = utilisateurSession.getNoUtilisateur(); 
			String pseudo = (String) request.getParameter("pseudo");
			String nom = (String) request.getParameter("nom");
			String prenom = (String) request.getParameter("prenom");
			String email = (String) request.getParameter("email");
			String telephone = (String) request.getParameter("telephone");
			String rue = (String) request.getParameter("rue");
			String codePostal = (String) request.getParameter("codePostal");
			String ville = (String) request.getParameter("ville");
			String motDePasse = (String) request.getParameter("motDePasse1");
			String motDePasse1 = (String) request.getParameter("motDePasseNew");

			// verifier l'intégrité des données du formulaire avant update
			messageUtilisateur = (um.verificationSaisieUtilisateur(pseudo, nom, prenom, email,
					telephone, rue, codePostal, ville, motDePasse, motDePasse1));

			boolean validationOK = um.validationCreationCompte(messageUtilisateur);

			// procéder à l'update en DBB si validationOK = true;

			if (validationOK == true) {

				String statutUpdate = "ok";
				try {
					statutUpdate = um.modifierUtilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue,
							codePostal, ville, motDePasse);
					System.out.println("try/catch utilisateurManager.modifierU" + statutUpdate);

				} catch (Exception e) {
					messageUtilisateur.add(statutUpdate);
				}
			}
			// affichage du messageUtilisateur
			messageUtilisateur.add(message);
			String messageU = um.traitementErreur(validationOK, messageUtilisateur); 
			
			request.setAttribute("message", messageU);
			doGet(request, response);
			

			/*if (utilisateurSession == null) {
				RequestDispatcher rd = request.getRequestDispatcher("/connexion");
				rd.forward(request, response);
				System.out.println("renvoyer vers page se connecter");
			} else */
			
			//request.changeSessionId();
			//request.setAttribute("n", utilisateur);
			//RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/PagesListeEncheresConnecte.jsp");
			//rd.forward(request, response);

		}
	}

}
