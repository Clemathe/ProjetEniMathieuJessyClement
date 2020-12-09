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

	public boolean utilisateurExistant(String login) {
		boolean existingUser = UtilisateurManager.utilisateurDAO.getExistingUser(login);
		return existingUser;
	}
	
	public boolean VerificationUtilisateurMotDePasse(String login, String password) {
		boolean okUserPass = UtilisateurManager.utilisateurDAO.getMatchingUserPassword(login, password);
		
		return okUserPass;
		
	}
	
}
