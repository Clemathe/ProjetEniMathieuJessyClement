package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.dal.DAOFactory;


public class UtilisateurManager {

	private static UtilisateurDAO utilisateurDAO;
	
	
	/**
	 * @param daoRepas
	 */
	public UtilisateurManager () {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
}
