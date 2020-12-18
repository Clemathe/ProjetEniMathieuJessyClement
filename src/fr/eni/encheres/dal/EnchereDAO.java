package fr.eni.encheres.dal;

import java.sql.SQLException;

import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO {

	public boolean enregistrerUneEnchere(Enchere nouvelleEnchere) throws SQLException;
	
}
