package fr.eni.encheres.dal;

import java.sql.SQLException;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {
	
	public Utilisateur getUtilisateurById();
	
	boolean getExistingUser(String login, String email);
		

	public String getUserPassword(String login);

	public Utilisateur getUserforSession(String login);

	public void insertUtilisateur(Utilisateur utilisateur) throws SQLException, Exception;
	
	public Utilisateur selectBy(int no_utilisateur) throws SQLException;  
		
	public void update(int no_utilisateur) throws SQLException;  


}

