package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Categorie;

public class CategorieDAOJdbcImpl implements CategorieDAO {

	@Override
	public List<Categorie> getCategorie() {
		List<Categorie> categories = new ArrayList<Categorie>();
		
		final String SELECT_CATEGORIE = "SELECT * FROM CATEGORIES";
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_CATEGORIE);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Categorie categorie = new Categorie(rs.getInt(1),rs.getString(2));
				categories.add(categorie);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(categories.toString());
		
		return categories;
	}

}
