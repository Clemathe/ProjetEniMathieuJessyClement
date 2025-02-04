package fr.eni.encheres.dal;

import java.sql.SQLException;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {
	


	public Utilisateur getUserforSession(String login, String hashPassword) throws SQLException;

	public void insertUtilisateur(Utilisateur utilisateur) throws SQLException, Exception;
	
	public Utilisateur selectBy(int no_utilisateur) throws SQLException;  
		
	public void update(Utilisateur utilisateur) throws SQLException; 
	
	public void rembourserUtilisateur(int enchereLaPlusHaute, Utilisateur user);

	public void debiterUtilisateur(int montantEnchere, Utilisateur utilisateurCourant);
	
	public void supprimerUtilisateur (int no_utilisateur) throws SQLException;







}

