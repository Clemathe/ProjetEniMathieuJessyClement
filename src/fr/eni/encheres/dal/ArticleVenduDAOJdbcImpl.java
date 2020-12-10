package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import fr.eni.encheres.bo.ArticleVendu;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {

	@Override
	public List<ArticleVendu> getenchereEnCours(String categorie, String nomArticlePartiel) {
		System.out.println(categorie +" "+ nomArticlePartiel);
		List<ArticleVendu> enchereEnCours = new ArrayList<ArticleVendu>();
		String ENCHERES_EN_COURS =
				"SELECT "
				+ "ARTICLES_VENDUS.no_article, "
				+ "ARTICLES_VENDUS.nom_article, "
				+ "ARTICLES_VENDUS.prix_vente, "
				+ "ARTICLES_VENDUS.date_fin_encheres, "
				+ "UTILISATEURS.pseudo "
				+ "FROM ARTICLES_VENDUS "
				+ "JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur "
				+ "JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie "
				+ "WHERE libelle = \""+categorie + "\" AND nom_article LIKE \"%"+nomArticlePartiel+"%\"";
		
	
		try (Connection cnx = ConnectionProvider.getConnection();){
			PreparedStatement pstmt = cnx.prepareStatement(ENCHERES_EN_COURS);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ArticleVendu articleVendu= new ArticleVendu(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getDate(4),rs.getString(5));
				enchereEnCours.add(articleVendu);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(enchereEnCours.toString());
		
		
		
		return enchereEnCours;
	}

}
