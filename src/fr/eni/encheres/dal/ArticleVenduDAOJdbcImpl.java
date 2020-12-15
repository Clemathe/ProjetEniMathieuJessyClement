package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

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
		String sqlGetArticleVendu =  "SELECT ARTICLES_VENDUS.no_article,"
				+ "ARTICLES_VENDUS.nom_article,"
				+" ARTICLES_VENDUS.description,"
		 		+" ARTICLES_VENDUS.date_fin_encheres,"
		 		+" ARTICLES_VENDUS.prix_initial,"
		 		+" ARTICLES_VENDUS.prix_vente,"
		 		+" ARTICLES_VENDUS.no_utilisateur as no_vendeur,"
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
		 
		
		 
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(sqlGetArticleVendu);
			pstmt.setInt(1, noArticle);
			rs = pstmt.executeQuery();
			
			List<Enchere> encheres = new ArrayList<>();
			int noMeilleurEncherisseur = 0;
			if(rs.next()) {
				try{
			
					encheres.add(new Enchere(rs.getInt(11), rs.getInt(12)));
					article = new ArticleVendu(rs.getInt(1), rs.getString(2), rs.getString(3), LocalDate.parse(rs.getString(4)),
							rs.getInt(5), rs.getInt(6), new Utilisateur(rs.getInt(7), rs.getString(8)), encheres,
							new Categorie(rs.getInt(9), rs.getString(10)) , new Retrait(rs.getString(13),rs.getString(14) ,rs.getString(15)));
					noMeilleurEncherisseur = rs.getInt(11);
					
				}catch (SQLException e){
					//TODO Pour empêcher le déroulement suivant qui genererait une erreur si cette partie échouait
					cnx.close();
				}
			}
			String meilleurEncherisseur = getPseudoForArticleVendu(noMeilleurEncherisseur);
			
			Utilisateur user = new Utilisateur(noMeilleurEncherisseur, meilleurEncherisseur);
		
			Enchere enchere = article.getEncheres().get(0);

			enchere.setUtilisateur(user);
			
			article.getEncheres().remove(0);
			article.getEncheres().add(enchere);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				cnx.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return article;
	}
	public String getPseudoForArticleVendu(int noMeilleurEncherisseur) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String meilleurEncherisseur = null;
		String buyerNumberToPseudo = " SELECT pseudo FROM utilisateurs WHERE no_utilisateur = ?";

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(buyerNumberToPseudo);
			pstmt.setInt(1, noMeilleurEncherisseur);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				meilleurEncherisseur = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return meilleurEncherisseur;

	}



	@Override
	public void insertArticleVendu(ArticleVendu articleVendu) {
		
		//Insertion du nouvl article 
		String INSERT_ARTICLES_VENDUS ="INSERT INTO ARTICLES_VENDUS "
				+ "(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial,prix_vente,no_utilisateur,no_categorie)"
				+ "values (?,?,?,?,?,?,?,?)";
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLES_VENDUS, PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, articleVendu.getNomArticle());
			pstmt.setString(2,articleVendu.getDescription());
			pstmt.setDate(3, Date.valueOf(articleVendu.getDateDebutEncheres()));
			pstmt.setDate(4, Date.valueOf(articleVendu.getDateFinEncheres()));
			pstmt.setInt(5, articleVendu.getMiseAPrix());
			pstmt.setInt(6, articleVendu.getMiseAPrix());
			pstmt.setInt(7, articleVendu.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(8, articleVendu.getNoCategorie());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			System.out.println("Article ajouté en base de donnée");
			//auto-génération du numero d'article + recupération du numero
			if(rs.next()) {
				articleVendu.setNoArticle(rs.getInt(1));
				
			}
			//renseignement de la table retrait avec noArticle récupéré
			String INSERT_RETRAIT ="INSERT INTO RETRAITS"
				+ "(no_article,rue,code_postal,ville)"
				+ "values(?,?,?,?)";	
			
			
			PreparedStatement pstmt2 = cnx.prepareStatement(INSERT_RETRAIT);
			pstmt2.setInt(1, articleVendu.getNoArticle());
			pstmt2.setString(2, articleVendu.getLieuRetrait().getRue());
			pstmt2.setString(3, articleVendu.getLieuRetrait().getCodePostal());
			pstmt2.setString(4, articleVendu.getLieuRetrait().getVille());
			
			pstmt2.executeUpdate();
			System.out.println("Lieu de retrait ajouté en base de donnée");
			
			rs.close();
			pstmt.close();
			pstmt2.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@Override
	public List<ArticleVendu> getToutesEncheresEnCours() {
		List<ArticleVendu> enchereEnCours = new ArrayList<ArticleVendu>();
		String ENCHERES_EN_COURS="SELECT "
					+ "ARTICLES_VENDUS.no_article,"
					+ "ARTICLES_VENDUS.nom_article, "
					+ "ARTICLES_VENDUS.prix_vente, "
					+ "ARTICLES_VENDUS.date_fin_encheres, "
					+ "UTILISATEURS.pseudo "
					+ "FROM ARTICLES_VENDUS "
					+ "JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur "; 
				 
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
		
		return enchereEnCours;
	}


	@Override
	public boolean enregistrerUneEnchere(Enchere nouvelleEnchere) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		boolean succes = false;
	
		String insertEnchere = " INSERT INTO Encheres VALUES ( ?, ?, ?, ?)";

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(insertEnchere);
			pstmt.setInt(1, nouvelleEnchere.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(2, nouvelleEnchere.getArticle().getNoArticle());
			pstmt.setDate(3, Date.valueOf(LocalDate.now()));
			pstmt.setInt(4, nouvelleEnchere.getMontantEnchere());
			pstmt.executeUpdate();
			succes = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return succes;
	}


	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
