package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {

		
	@Override
	public List<ArticleVendu> getenchereEnCours(String categorie, String nomArticlePartiel) {
		
		List<ArticleVendu> enchereEnCours = new ArrayList<ArticleVendu>();
		String ENCHERES_EN_COURS;
		
		if(categorie.equals("Toutes")) {
			ENCHERES_EN_COURS="SELECT "
					+ "ARTICLES_VENDUS.no_article,"
					+ "ARTICLES_VENDUS.nom_article, "
					+ "ARTICLES_VENDUS.prix_vente, "
					+ "ARTICLES_VENDUS.date_fin_encheres, "
					+ "UTILISATEURS.pseudo "
					+ "FROM ARTICLES_VENDUS "
					+ "JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur "
					+ "WHERE nom_article LIKE \"%"+nomArticlePartiel+"%\""; 
		}else {
			ENCHERES_EN_COURS="SELECT "
					+ "ARTICLES_VENDUS.no_article,"
					+ "ARTICLES_VENDUS.nom_article, "
					+ "ARTICLES_VENDUS.prix_vente, "
					+ "ARTICLES_VENDUS.date_fin_encheres, "
					+ "UTILISATEURS.pseudo "
					+ "FROM ARTICLES_VENDUS "
					+ "JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur "
					+ "JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie "
					+ "WHERE nom_article LIKE \"%"+nomArticlePartiel+"%\" AND libelle = \""+categorie+ "\"";
		}
				 
		try (Connection cnx = ConnectionProvider.getConnection();){
			PreparedStatement pstmt = cnx.prepareStatement(ENCHERES_EN_COURS);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ArticleVendu articleVendu= new ArticleVendu(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getDate(4).toLocalDate(),rs.getString(5));
				enchereEnCours.add(articleVendu);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		System.out.println(enchereEnCours);
		
		return enchereEnCours;
	}
	
	
	
	@Override
	public ArticleVendu getArticleVendu(int noArticle) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArticleVendu article = null;
		String sqlGetArticleVendu =  "SELECT ARTICLES_VENDUS.nom_article,"
				+" ARTICLES_VENDUS.description,"
		 		+" ARTICLES_VENDUS.date_fin_encheres,"
		 		+" ARTICLES_VENDUS.prix_initial,"
		 		+" ARTICLES_VENDUS.prix_vente,"
				+" UTILISATEURS.pseudo as vendeur,"
		 		+" CATEGORIES.no_categorie,"
		 		+" CATEGORIES.libelle,"
		 		+" ENCHERES.no_utilisateur as encherisseur,"
		 		+" ENCHERES.montant_enchere,"
				+" RETRAITS.rue,"
				+" RETRAITS.code_postal,"
				+" RETRAITS.ville"
		 		+" FROM ARTICLES_VENDUS "
				+" JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur"
				+" JOIN RETRAITS ON ARTICLES_VENDUS.no_article = RETRAITS.no_article"
		 		+" JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie"
		 		+" JOIN ENCHERES ON ARTICLES_VENDUS.no_article = ENCHERES.no_article"
		 		+" WHERE ARTICLES_VENDUS.no_article = ? and ENCHERES.montant_enchere = "
		 		+" (SELECT MAX(montant_enchere) FROM ARTICLES_VENDUS"
		 		+" JOIN ENCHERES ON ARTICLES_VENDUS.no_article=ENCHERES.no_article)"; 
		 
		 String BuyerNumberToPseudo = "SELECT no_utilisateur FROM utilisateurs WHERE no_utilisateur = ?";
		 
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(sqlGetArticleVendu);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				
//				article = new ArticleVendu(nomArticle, description, dateFinEncheres, miseAPrix,
//						prixVente, etatVente, acheteur, vendeur, new Enchere( montantEnchere), new Categorie(noCategorie, libelle) , new Retrait(rue, codePostal, ville))
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	

}
