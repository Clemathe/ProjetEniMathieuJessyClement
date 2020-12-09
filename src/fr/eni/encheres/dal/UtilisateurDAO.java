package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {
	
	public Utilisateur getUtilisateurById();
	
	public boolean getExistingUser(String id);
		
	public  boolean getMatchingUserPassword(String id, String password);

}

