package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;


public class UtilisateurManager {

	private static UtilisateurDAO utilisateurDAO;
	
	/**
	 * @param daoRepas
	 */
	public UtilisateurManager () {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	public Utilisateur utilisateurParIdentifiant() {
		
		return UtilisateurManager.utilisateurDAO.getUtilisateurById();
		
	}

	
	
	public boolean utilisateurExistant(String login, String email) {
		boolean existingUser = UtilisateurManager.utilisateurDAO.getExistingUser(login, email);
		return existingUser;
	}
	
	
	
	public boolean verificationUtilisateurMotDePasse(String login, String password) {
		Boolean userPassOk = false; 
		String userPasswordBDD = UtilisateurManager.utilisateurDAO.getUserPassword(login);
		
		if (userPasswordBDD != null && userPasswordBDD.trim().equals(password.trim())) {
			userPassOk = true;
		}
		
		return userPassOk;
		
	}

	public Utilisateur getUtilisateurPourSession(String login) {
		Utilisateur utilisateurCourant =  utilisateurDAO.getUserforSession(login);
		utilisateurCourant.setMotDePasse(null);
		System.out.println(utilisateurCourant);
		return utilisateurCourant;
		
	}


	
}
