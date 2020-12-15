package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import fr.eni.encheres.bo.Utilisateur;


public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	private static final String INSERT_UTILISATEUR = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
	private static final String SELECT_BY_ID = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS where id=?"; 
	private static final String UPDATE = "UPDATE UTILISATEURS set pseudo = ?, nom =?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=? where id=?"; 
	private static final String REMBOURSER_UTILISATEUR = "UPDATE Utilisateurs SET credit = credit + ? WHERE no_utilisateur = ?";
	private static final String DEBITER_UTILISATEUR = "UPDATE Utilisateurs SET credit = credit - ? WHERE no_utilisateur = ?";
	private static final String SELECT_USER = "SELECT no_utilisateur, pseudo,nom , prenom, email, telephone, rue, code_postal, ville, credit, administrateur FROM  Utilisateurs where ? = ? and mot_de_passe = ?";




	@Override
	public Utilisateur getUserforSession(String login, String hashPassword) throws SQLException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Utilisateur user = null;

		try {
			cnx = ConnectionProvider.getConnection();
		
			if (login.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$")) {
				
				pstmt = cnx.prepareStatement(SELECT_USER);
				pstmt.setString(1, "email");
				pstmt.setString(2, login.trim());
				pstmt.setString(3, hashPassword);
				rs = pstmt.executeQuery();
				System.out.println(login);
			} else {
				pstmt = cnx.prepareStatement(SELECT_USER);
				pstmt.setString(1, "pseudo");
				pstmt.setString(2, login.trim());
				pstmt.setString(3, hashPassword);
				rs = pstmt.executeQuery();
			}
			
			if (rs.next()) {
				user = new Utilisateur(rs.getInt("no_utilisateur"),rs.getString("pseudo"),rs.getString("nom"),
						rs.getString("prenom"),rs.getString("email"),rs.getString("telephone"),rs.getString("rue"),
						rs.getString("code_postal"),rs.getString("ville"), rs.getInt("credit"), rs.getBoolean("administrateur"));
				
			}
			
			System.out.println(user);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				cnx.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public void insertUtilisateur(Utilisateur utilisateur) throws Exception {
		
		 try (Connection cnx = ConnectionProvider.getConnection()){
			
			 try {
				
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_UTILISATEUR, PreparedStatement.RETURN_GENERATED_KEYS);
				
				pstmt.setString(1, utilisateur.getPseudo());
				pstmt.setString(2, utilisateur.getNom());
				pstmt.setString(3, utilisateur.getPrenom());
				pstmt.setString(4, utilisateur.getEmail());
				pstmt.setString(5, utilisateur.getTelephone());
				pstmt.setString(6, utilisateur.getRue());
				pstmt.setString(7, utilisateur.getCodePostal());
				pstmt.setString(8, utilisateur.getVille());
				pstmt.setString(9, utilisateur.getMotDePasse());
				pstmt.setInt(10, utilisateur.getCredit());
				pstmt.setBoolean(11, utilisateur.isAdministrateur());
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				
				if(rs.next())
				{
					utilisateur.setNoUtilisateur(rs.getInt(1));
				}
				rs.close();
				cnx.commit();
				pstmt.close();
				cnx.close();				
				
			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback(); 
				throw e;			}
		} catch (Exception e) {
			throw e;
					
			
		}
		 
	}

	@Override
	public Utilisateur selectBy(int no_utilisateur) throws SQLException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Utilisateur utilisateur = null;
		
		try{
			 cnx = ConnectionProvider.getConnection(); 
			 pstmt = cnx.prepareStatement(SELECT_BY_ID); 
			 pstmt.setInt(1, no_utilisateur);
			 rs=pstmt.executeQuery(); 
			 
			 if(rs.next()){				
				 utilisateur = new Utilisateur(rs.getInt("no_utilisateur"),rs.getString("pseudo"),rs.getString("nom"), rs.getString("prenom"), 
						 rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"),
						 rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur")); 
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return utilisateur;
	}

	@Override
	public void update(int no_utilisateur) throws SQLException {
		PreparedStatement pstmt = null; 
		Connection cnx = null; 
		
		try {
			cnx = ConnectionProvider.getConnection();
			try {
				pstmt = cnx.prepareStatement(UPDATE); 
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	
	}
	
	@Override
	public void rembourserUtilisateur(int enchereLaPlusHaute, Utilisateur user) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		
				try {
					cnx = ConnectionProvider.getConnection();
					pstmt = cnx.prepareStatement(REMBOURSER_UTILISATEUR);
					cnx.setAutoCommit(false);
					pstmt.setInt(1, enchereLaPlusHaute);		
					pstmt.setInt(2, user.getNoUtilisateur());
					pstmt.executeUpdate();
					cnx.commit();
					pstmt.close();
					cnx.close();
	}catch (SQLException e) {
		e.printStackTrace();
		try {
			cnx.rollback();
			throw e;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
	}
	}

	@Override
	public void debiterUtilisateur(int montantEnchere, Utilisateur utilisateurCourant) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		System.out.println(-(montantEnchere));
				try {
					cnx = ConnectionProvider.getConnection();
					pstmt = cnx.prepareStatement(DEBITER_UTILISATEUR);
					cnx.setAutoCommit(false);
					pstmt.setInt(1, montantEnchere);		
					pstmt.setInt(2, utilisateurCourant.getNoUtilisateur());
					pstmt.executeUpdate();
					cnx.commit();
					pstmt.close();
					cnx.close();
	}catch (SQLException e) {
		e.printStackTrace();
		try {
			
			throw e;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
	}
		
	}




}
