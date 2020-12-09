package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import fr.eni.encheres.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {


	@Override
	public Utilisateur getUtilisateurById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getExistingUser(String login, String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getUserPassword(String login) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String userPassword = null;

		try {
			cnx = ConnectionProvider.getConnection();

			if (login.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$")) {

				String sql = "SELECT mot_de_passe FROM utilisateurs AS u WHERE u.email = ?";
				pstmt = cnx.prepareStatement(sql);
				pstmt.setString(1, login.trim());
				rs = pstmt.executeQuery();
				System.out.println(login);
			} else {
				String sql = "SELECT mot_de_passe FROM utilisateurs AS u WHERE u.pseudo = ?";
				pstmt = cnx.prepareStatement(sql);
				pstmt.setString(1, login);
				rs = pstmt.executeQuery();
			}
			System.out.println(login);
			if (rs.next()) {
				userPassword = rs.getString("mot_de_passe");
			}
			System.out.println(userPassword);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {
				cnx.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userPassword;
	}

	@Override
	public Utilisateur getUserforSession(String login) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Utilisateur user = null;

		try {
			cnx = ConnectionProvider.getConnection();
		
			if (login.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$")) {

				String sql = "SELECT * FROM utilisateurs WHERE email = ?";
				pstmt = cnx.prepareStatement(sql);
				pstmt.setString(1, login.trim());
				rs = pstmt.executeQuery();
				System.out.println(login);
			} else {
				String sql = "SELECT * FROM utilisateurs WHERE pseudo = ?";
				pstmt = cnx.prepareStatement(sql);
				pstmt.setString(1, login);
				rs = pstmt.executeQuery();
			}
			
			if (rs.next()) {
				user = new Utilisateur(rs.getInt("no_utilisateur"),rs.getString("pseudo"),rs.getString("nom"),
						rs.getString("prenom"),rs.getString("email"),rs.getString("telephone"),rs.getString("rue"),
						rs.getString("code_postal"),rs.getString("ville"),rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));
				
			}
			
			System.out.println(user);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				cnx.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}
}
