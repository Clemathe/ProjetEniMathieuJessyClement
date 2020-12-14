package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class CategorieDAOJdbcImpl implements CategorieDAO{
	@Override
	public int getCategorie(String libelle) {
		int noCategorie =0;
		
		final String SELECT_CATEGORIE = "SELECT no_Categorie FROM CATEGORIES WHERE libelle = \""+libelle+"\"";
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_CATEGORIE);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				 noCategorie = rs.getInt(1);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return noCategorie;
	}
}
