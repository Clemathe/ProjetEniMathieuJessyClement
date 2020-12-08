package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;


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

	public boolean utilisateurExistant(String id) {
		boolean existingUser = UtilisateurManager.utilisateurDAO.getExistingUser(id);
		return existingUser;
		
	}
	
	public boolean VerificationUtilisateurMotDePasse(String id, String password) {
		boolean okUserPass = UtilisateurManager.utilisateurDAO.getMatchingUserPassword(id, password);
		
		return okUserPass;
		
	}
	
}
