package fr.eni.encheres.dal;

import fr.eni.encheres.bll.UtilisateurDAO;

public abstract class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDAO()
	{
		return new UtilisateurDAOJdbcImpl();
	}
}

