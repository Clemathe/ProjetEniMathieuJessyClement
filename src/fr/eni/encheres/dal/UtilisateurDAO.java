package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {
	
	public Utilisateur getUtilisateurById();
	
	boolean getExistingUser(String login, String email);
		

	public String getUserPassword(String login);

	public Utilisateur getUserforSession(String login);

	

}

