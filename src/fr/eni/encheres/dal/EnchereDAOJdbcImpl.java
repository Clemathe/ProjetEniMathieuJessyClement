package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import fr.eni.encheres.bo.Enchere;

public class EnchereDAOJdbcImpl implements EnchereDAO {


	@Override
	public boolean enregistrerUneEnchere(Enchere nouvelleEnchere) throws SQLException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		boolean succes = false;
	
		String insertEnchere = " INSERT INTO Encheres VALUES ( ?, ?, ?, ?)";

		try {
			cnx = ConnectionProvider.getConnection();
			cnx.setAutoCommit(false);
			pstmt = cnx.prepareStatement(insertEnchere);
			pstmt.setInt(1, nouvelleEnchere.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(2, nouvelleEnchere.getArticle().getNoArticle());
			pstmt.setDate(3, Date.valueOf(LocalDate.now()));
			pstmt.setInt(4, nouvelleEnchere.getMontantEnchere());
			pstmt.executeUpdate();
			succes = true;
			cnx.commit();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			cnx.rollback();
			e.printStackTrace();
		}
		return succes;
	}
}
