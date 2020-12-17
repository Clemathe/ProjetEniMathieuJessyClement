package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.Date;
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
import fr.eni.encheres.bo.Utilisateur;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {
	
	@Override
	public List<ArticleVendu> getenchereEnCours(String categorie, String nomArticlePartiel) {
		
		List<ArticleVendu> enchereEnCours = new ArrayList<ArticleVendu>();
		
		
		if(categorie.equals("Toutes")) {
			String TOUTES_ENCHERES_EN_COURS="SELECT "
					+ "ARTICLES_VENDUS.no_article,"
					+ "ARTICLES_VENDUS.nom_article, "
					+ "ARTICLES_VENDUS.prix_vente, "
					+ "ARTICLES_VENDUS.date_debut_encheres, "
					+ "ARTICLES_VENDUS.date_fin_encheres, "
					+ "UTILISATEURS.pseudo "
					+ "FROM ARTICLES_VENDUS "
					+ "JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur "
					+ "WHERE nom_article LIKE  ? "; 
			
			String concat= "%"+nomArticlePartiel+"%";
			
			try (Connection cnx = ConnectionProvider.getConnection();){
				PreparedStatement pstmt = cnx.prepareStatement(TOUTES_ENCHERES_EN_COURS);
				pstmt.setString(1,concat);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					ArticleVendu articleVendu= new ArticleVendu(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getDate(4).toLocalDate(),rs.getDate(5).toLocalDate(),rs.getString(6));
					enchereEnCours.add(articleVendu);
				}
				rs.close();
				pstmt.close();
				cnx.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			String ENCHERES_EN_COURS="SELECT "
					+ "ARTICLES_VENDUS.no_article,"
					+ "ARTICLES_VENDUS.nom_article, "
					+ "ARTICLES_VENDUS.prix_vente, "
					+ "ARTICLES_VENDUS.date_debut_encheres, "
					+ "ARTICLES_VENDUS.date_fin_encheres, "
					+ "UTILISATEURS.pseudo "
					+ "FROM ARTICLES_VENDUS "
					+ "JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur "
					+ "JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie "
					+ "WHERE nom_article LIKE ? AND libelle = ? ";
			
			String concat= "%"+nomArticlePartiel+"%";
				 
		try (Connection cnx = ConnectionProvider.getConnection();){
			PreparedStatement pstmt = cnx.prepareStatement(ENCHERES_EN_COURS);
			pstmt.setString(1,concat);
			pstmt.setString(2, categorie);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ArticleVendu articleVendu= new ArticleVendu(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getDate(4).toLocalDate(),rs.getDate(5).toLocalDate(),rs.getString(6));
				enchereEnCours.add(articleVendu);
			}
			rs.close();
			pstmt.close();
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		
		System.out.println(enchereEnCours);
		
		return enchereEnCours;
	}
	
	@Override
	public List<ArticleVendu> getMesVentes(int noUtilisateur) {
		
		List<ArticleVendu> mesVentes = new ArrayList<ArticleVendu>();
		String SELECT_MES_VENTES ="SELECT "
				+ "ARTICLES_VENDUS.no_article,"
				+ "ARTICLES_VENDUS.nom_article,"
				+ "ARTICLES_VENDUS.description,"
				+ "ARTICLES_VENDUS.date_debut_encheres, "
				+ "ARTICLES_VENDUS.date_fin_encheres, "
				+" ARTICLES_VENDUS.prix_initial,"
				+" ARTICLES_VENDUS.prix_vente,"
				+" CATEGORIES.no_categorie,"
				+" CATEGORIES.libelle,"
				+" ENCHERES.no_utilisateur,"
				+" ENCHERES.montant_enchere,"
				+" ENCHERES.date_enchere,"
				+" RETRAITS.rue,"
				+" RETRAITS.code_postal,"
				+" RETRAITS.ville"
				+" FROM ARTICLES_VENDUS"
				+" JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie JOIN ENCHERES ON ARTICLES_VENDUS.no_article = ENCHERES.no_article "
				+" JOIN RETRAITS ON ARTICLES_VENDUS.no_article = RETRAITS.no_article "
				+" WHERE ARTICLES_VENDUS.no_utilisateur = ? and ENCHERES.montant_enchere = "
				+" (SELECT MAX(montant_enchere) FROM ARTICLES_VENDUS"
		 		+" JOIN ENCHERES ON ARTICLES_VENDUS.no_article=ENCHERES.no_article)"; 
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_MES_VENTES);
			pstmt.setInt(1,noUtilisateur);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Enchere enchere = new Enchere(rs.getInt(10),rs.getInt(1),rs.getDate(12).toLocalDate(),rs.getInt(11));
				Categorie categorie = new Categorie(rs.getInt(8),rs.getString(9));
				Retrait retrait = new Retrait(rs.getString(13),rs.getString(14),rs.getString(15));
				
				ArticleVendu articleVendu= new ArticleVendu(rs.getString(2), rs.getString(3),
						rs.getDate(4).toLocalDate(),rs.getDate(5).toLocalDate(),rs.getInt(6), rs.getInt(7),enchere, categorie, retrait); 
						
						
				mesVentes.add(articleVendu);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(mesVentes.toString());
		return mesVentes;
	}
	
//	@Override
//	public ArticleVendu getArticleVendu(int noArticle) {
//		
//		
//		
//		ArticleVendu article = null;
//		String sqlGetArticleAvecEnchere =  "SELECT ARTICLES_VENDUS.no_article,"
//				+ "ARTICLES_VENDUS.nom_article,"
//				+" ARTICLES_VENDUS.description,"
//				+" ARTICLES_VENDUS.date_debut_encheres,"
//		 		+" ARTICLES_VENDUS.date_fin_encheres,"
//		 		+" ARTICLES_VENDUS.prix_initial,"
//		 		+" ARTICLES_VENDUS.prix_vente,"
//		 		+" ARTICLES_VENDUS.no_utilisateur as no_vendeur,"
//				+" UTILISATEURS.pseudo as vendeur,"
//		 		+" CATEGORIES.no_categorie,"
//		 		+" CATEGORIES.libelle,"
//		 		+" ENCHERES.no_utilisateur as encherisseur,"
//		 		+" ENCHERES.montant_enchere,"
//				+" RETRAITS.rue,"
//				+" RETRAITS.code_postal,"
//				+" RETRAITS.ville"
//		 		+" FROM ARTICLES_VENDUS "
//				+" JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur"
//				+" JOIN RETRAITS ON ARTICLES_VENDUS.no_article = RETRAITS.no_article"
//		 		+" JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie"
//		 		+" JOIN ENCHERES ON ARTICLES_VENDUS.no_article = ENCHERES.no_article"
//		 		+" WHERE ARTICLES_VENDUS.no_article = 22 and ENCHERES.montant_enchere = "
//		 		+" (SELECT MAX(montant_enchere) FROM ARTICLES_VENDUS"
//		 		+" JOIN ENCHERES ON ARTICLES_VENDUS.no_article=ENCHERES.no_article)"; 
//		 
//		String sqlGetArticleSansEnchere =  "SELECT ARTICLES_VENDUS.no_article,"
//				+ " ARTICLES_VENDUS.nom_article,"
//				+ " ARTICLES_VENDUS.description,"
//				+" ARTICLES_VENDUS.date_debut_encheres,"
//				+ " ARTICLES_VENDUS.date_fin_encheres,"
//				+ " ARTICLES_VENDUS.prix_initial,"
//				+ " ARTICLES_VENDUS.prix_vente,"
//				+ " ARTICLES_VENDUS.no_utilisateur as numéro_vendeur,"
//				+ " UTILISATEURS.pseudo as vendeur,"
//				+ " CATEGORIES.no_categorie,"
//				+ " CATEGORIES.libelle,"
//				+ " RETRAITS.rue,"
//				+ " RETRAITS.code_postal,"
//				+ " RETRAITS.ville"
//				+ " FROM ARTICLES_VENDUS"
//				+ " JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur"
//				+ " JOIN RETRAITS ON ARTICLES_VENDUS.no_article = RETRAITS.no_article"
//				+ " JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie"
//				+ " WHERE ARTICLES_VENDUS.no_article = ? "; 
//		 
//		try (Connection cnx = ConnectionProvider.getConnection();) {
//			
//			
//			System.out.println("DAO debut, numero article : "+ noArticle);
//			
//			if (getMeilleurEnchere(noArticle) != null) {
//				
//				PreparedStatement pstmt = cnx.prepareStatement(sqlGetArticleAvecEnchere);
////				pstmt.setInt(1, noArticle);
//				ResultSet rs = pstmt.executeQuery();
//				List<Enchere> encheres = new ArrayList<>();
//				int noMeilleurEncherisseur = 0;
//				System.out.println(" chemin 1 avant rs.next  ");
//				if(rs.next()) {
//					
//						System.out.println("apres le rs.next");
//						encheres.add(new Enchere(rs.getInt(12), rs.getInt(13)));
//						System.out.println("DAO enchere " + encheres);
//						article = new ArticleVendu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(),
//								rs.getDate(5).toLocalDate(),rs.getInt(6), rs.getInt(7), new Utilisateur(rs.getInt(8), rs.getString(9)), encheres,
//								new Categorie(rs.getInt(10), rs.getString(11)) , new Retrait(rs.getString(14),rs.getString(15) ,rs.getString(16)));
//						
//						noMeilleurEncherisseur = rs.getInt(12);
//					
//				
//				String meilleurEncherisseur = getPseudoForArticleVendu(noMeilleurEncherisseur);
//				Utilisateur user = new Utilisateur(noMeilleurEncherisseur, meilleurEncherisseur);
//			
//				
//				Enchere enchere = article.getEncheres().get(0);
//		
//				enchere.setUtilisateur(user);
//					
//				article.getEncheres().remove(0);
//				article.getEncheres().add(enchere);
//				System.out.println("DAO1 article  = " + article);
//				rs.close();
//				pstmt.close();
//				cnx.close();
//				
//				
//				}
//			}else {
//				
//				PreparedStatement pstmt = cnx.prepareStatement(sqlGetArticleSansEnchere);
//				pstmt.setInt(1, noArticle);
//				ResultSet rs = pstmt.executeQuery();
//				List<Enchere> encheres = new ArrayList<>();
//				int noMeilleurEncherisseur = 0;
//				System.out.println(" chemin 2 avant rs.next : ");
//				if(rs.next()) {
//					
//					System.out.println(" chemin 2 apres rs.next : ");			
//						article = new ArticleVendu(rs.getInt(1), rs.getString(2), rs.getString(3), (rs.getDate(4)).toLocalDate(),
//								(rs.getDate(5)).toLocalDate(), rs.getInt(6), rs.getInt(7), new Utilisateur(rs.getInt(8), rs.getString(9)),
//								new Categorie(rs.getInt(10), rs.getString(11)) , new Retrait(rs.getString(12),rs.getString(13) ,rs.getString(14)));
//
//				}System.out.println("DAO2 article  = " + article);
//				rs.close();
//				pstmt.close();
//				cnx.close();
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//			
//		}
//		
//		return article;
//	}
	public String getMeilleurEnchere(int noArticle) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlMeilleurEncherisseur = "SELECT MAX(montant_enchere) FROM ENCHERES WHERE no_article = ?";
		String meilleurEncherisseur = null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(sqlMeilleurEncherisseur);
			pstmt.setInt(1, noArticle);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				meilleurEncherisseur = rs.getString(1);
			}
			rs.close();
			pstmt.close();
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			System.out.println("meilleurEncherisseur " +meilleurEncherisseur);
		return meilleurEncherisseur;

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
		}finally {
			try {
				rs.close();
				pstmt.close();
				cnx.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			System.out.println("n cat" + articleVendu.getNoCategorie());
			
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
			cnx.close();
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
					+ "ARTICLES_VENDUS.date_debut_encheres, "
					+ "ARTICLES_VENDUS.date_fin_encheres, "
					+ "UTILISATEURS.pseudo "
					+ "FROM ARTICLES_VENDUS "
					+ "JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur "; 
				 
		try (Connection cnx = ConnectionProvider.getConnection();){
			PreparedStatement pstmt = cnx.prepareStatement(ENCHERES_EN_COURS);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ArticleVendu articleVendu= new ArticleVendu(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getDate(4).toLocalDate(),rs.getDate(5).toLocalDate(),rs.getString(6));
				enchereEnCours.add(articleVendu);
			}
			rs.close();
			pstmt.close();
			cnx.close();;
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return enchereEnCours;
	}


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
			pstmt.close();
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			cnx.rollback();
			e.printStackTrace();
		}
		return succes;
	}
	
	@Override
	public List<ArticleVendu> getToutesMesVentes(int noUtilisateur) {
		List<ArticleVendu> mesVentesEnCours = new ArrayList<ArticleVendu>();
		String SELECT_TOUTES_MES_VENTES =
				"SELECT "
				+" ARTICLES_VENDUS.no_article,"
				+" ARTICLES_VENDUS.nom_article,"
				+" ARTICLES_VENDUS.date_debut_encheres, "
				+" ARTICLES_VENDUS.date_fin_encheres, "
				+" ARTICLES_VENDUS.prix_initial, "
				+" ARTICLES_VENDUS.prix_vente "
				+" FROM ARTICLES_VENDUS"
				+" WHERE no_utilisateur = ? "; 
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_TOUTES_MES_VENTES);
			pstmt.setInt(1,noUtilisateur);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ArticleVendu articleVendu= new ArticleVendu(rs.getInt(1),rs.getString(2),
						rs.getDate(3).toLocalDate(),rs.getDate(4).toLocalDate(),rs.getInt(5), rs.getInt(6)); 
				mesVentesEnCours.add(articleVendu);
			}
			pstmt.close();
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return mesVentesEnCours;
	}
	@Override
	public List<ArticleVendu> getVentesEnCours(int noUtilisateur, LocalDate ceJour) {
		List<ArticleVendu> mesVentesEnCours = new ArrayList<ArticleVendu>();
		String SELECT_MES_VENTES_EN_COURS =
				"SELECT "
				+ "ARTICLES_VENDUS.no_article,"
				+ "ARTICLES_VENDUS.nom_article,"
				+ "ARTICLES_VENDUS.date_debut_encheres, "
				+ "ARTICLES_VENDUS.date_fin_encheres, "
				+" ARTICLES_VENDUS.prix_initial, "
				+ " ARTICLES_VENDUS.prix_vente "
				+" FROM ARTICLES_VENDUS"
				+" WHERE ((no_utilisateur = ?) AND (date_debut_encheres <  ? )AND (date_fin_encheres >  ? )) "; 
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_MES_VENTES_EN_COURS);
			pstmt.setInt(1,noUtilisateur);
			pstmt.setDate(2, Date.valueOf(ceJour));
			pstmt.setDate(3, Date.valueOf(ceJour));
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				ArticleVendu articleVendu= new ArticleVendu(rs.getInt(1),rs.getString(2),
						rs.getDate(3).toLocalDate(),rs.getDate(4).toLocalDate(),rs.getInt(5), rs.getInt(6)); 
				mesVentesEnCours.add(articleVendu);
			}
			pstmt.close();
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return mesVentesEnCours;
	}

	@Override
	public List<ArticleVendu> getVentesNonDebutees(int noUtilisateur, LocalDate ceJour) {
		
		List<ArticleVendu> ventesNonDebutees = new ArrayList<ArticleVendu>();
		String SELECT_MES_VENTES_EN_COURS =
				"SELECT "
				+ "ARTICLES_VENDUS.no_article,"
				+ "ARTICLES_VENDUS.nom_article,"
				+ "ARTICLES_VENDUS.date_debut_encheres, "
				+ "ARTICLES_VENDUS.date_fin_encheres, "
				+" ARTICLES_VENDUS.prix_initial, "
				+"ARTICLES_VENDUS.prix_vente "
				+" FROM ARTICLES_VENDUS "
				+" WHERE ((no_utilisateur = ?) AND (date_debut_encheres >  ? ))"; 
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_MES_VENTES_EN_COURS);
			pstmt.setInt(1,noUtilisateur);
			pstmt.setDate(2, Date.valueOf(ceJour));
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				ArticleVendu articleVendu= new ArticleVendu(rs.getInt(1),rs.getString(2),
						rs.getDate(3).toLocalDate(),rs.getDate(4).toLocalDate(),rs.getInt(5), rs.getInt(6)); 
				ventesNonDebutees.add(articleVendu);
			}
			pstmt.close();
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return ventesNonDebutees;
	}

	@Override
	public List<ArticleVendu> getVentesTerminees(int noUtilisateur, LocalDate ceJour) {
		
		List<ArticleVendu> ventesTerminees = new ArrayList<ArticleVendu>();
		String SELECT_MES_VENTES_EN_COURS =
				"SELECT "
				+ "ARTICLES_VENDUS.no_article,"
				+ "ARTICLES_VENDUS.nom_article,"
				+ "ARTICLES_VENDUS.date_debut_encheres, "
				+ "ARTICLES_VENDUS.date_fin_encheres, "
				+" ARTICLES_VENDUS.prix_initial, "
				+ " ARTICLES_VENDUS.prix_vente "
				+" FROM ARTICLES_VENDUS"
				+" WHERE ((no_utilisateur = ?) AND (date_fin_encheres < ? ))"; 
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_MES_VENTES_EN_COURS);
			pstmt.setInt(1,noUtilisateur);
			pstmt.setDate(2, Date.valueOf(ceJour));
		
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				ArticleVendu articleVendu= new ArticleVendu(rs.getInt(1),rs.getString(2),
						rs.getDate(3).toLocalDate(),rs.getDate(4).toLocalDate(),rs.getInt(5), rs.getInt(6)); 
				ventesTerminees.add(articleVendu);
			}
			pstmt.close();
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block jessy dit coucou
			e.printStackTrace();
		}
		
	
		return ventesTerminees;
	}

	
	@Override
	public ArticleVendu getArticleVendu(int noArticle) {
		List<Enchere> encheres = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		ArticleVendu article = null;
		String sqlGetArticleAvecEnchere =  "SELECT ARTICLES_VENDUS.no_article,"
				+ "ARTICLES_VENDUS.nom_article,"
				+" ARTICLES_VENDUS.description,"
				+" ARTICLES_VENDUS.date_debut_encheres,"
		 		+" ARTICLES_VENDUS.date_fin_encheres,"
		 		+" ARTICLES_VENDUS.prix_initial,"
		 		+" ARTICLES_VENDUS.prix_vente,"
		 		+" ARTICLES_VENDUS.no_utilisateur as no_vendeur,"
				+" UTILISATEURS.pseudo as vendeur,"
		 		+" CATEGORIES.no_categorie,"
		 		+" CATEGORIES.libelle,"
				+" RETRAITS.rue,"
				+" RETRAITS.code_postal,"
				+" RETRAITS.ville"
		 		+" FROM ARTICLES_VENDUS"
				+" JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur"
				+" JOIN RETRAITS ON ARTICLES_VENDUS.no_article = RETRAITS.no_article"
		 		+" JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie"
		 		+" WHERE ARTICLES_VENDUS.no_article = ?";
		
		System.out.println("DAO debut, numero article : "+ noArticle);
		
		try (Connection cnx = ConnectionProvider.getConnection();) {
			
			 	pstmt = cnx.prepareStatement(sqlGetArticleAvecEnchere);
				pstmt.setInt(1, noArticle);
				
				rs = pstmt.executeQuery();
				System.out.println(" chemin 1 avant rs.next  ");
				if(rs.next()) {
						String meilleurEnchere = getMeilleurEnchere(noArticle);
						System.out.println("apres le rs.next");
						
						encheres.add(getEnchere(noArticle));
						
						System.out.println("DAO encheres " + encheres);
						article = new ArticleVendu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(),
								rs.getDate(5).toLocalDate(),rs.getInt(6), meilleurEnchere != null? Integer.parseInt(meilleurEnchere) : rs.getInt(6), new Utilisateur(rs.getInt(8), rs.getString(9)), encheres,
								new Categorie(rs.getInt(10), rs.getString(11)) , new Retrait(rs.getString(12),rs.getString(13) ,rs.getString(14)));
				
				System.out.println("DAO1 article  = " + article);
				rs.close();
				pstmt.close();
				cnx.close();
			
			}
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return article;
	}
	public Enchere getEnchere(int noArticle) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Enchere enchere = null;
		String SQL_GET_ENCHERE = 
				"SELECT ENCHERES.no_utilisateur,"
				+ " ENCHERES.no_article, "
				+ " ENCHERES.montant_enchere, "
				+ " UTILISATEURS.pseudo "
				+ " FROM ENCHERES "
				+ " JOIN UTILISATEURS "
				+ " ON ENCHERES.no_utilisateur = UTILISATEURS.no_utilisateur "
				+ " WHERE no_article = ? "
				+ " AND montant_enchere = (SELECT MAX(montant_enchere) FROM ENCHERES WHERE no_article = ? )";

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SQL_GET_ENCHERE);
			pstmt.setInt(1, noArticle);
			pstmt.setInt(2, noArticle);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int noUtilisateur = rs.getInt(1);
				UtilisateurDAOJdbcImpl getUser = new UtilisateurDAOJdbcImpl();
				Utilisateur user = getUser.selectBy(noUtilisateur);
				enchere = new Enchere(null,rs.getInt(3), null, user);
			}
		
			pstmt.close();
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DAO getEnchere : " + enchere);
		return enchere;
	}
	
	
	@Override
	public void mettreAJourLePrixDeVente(int prixDeVente, int no_article) throws SQLException   {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		int rs;
		final String SQL_PRIX_DE_VENTE = "UPDATE ARTICLES_VENDUS SET prix_vente = ? WHERE no_article = ?";

		try {
			System.out.println("debut MAJprixVente : "+ prixDeVente);
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SQL_PRIX_DE_VENTE);
			pstmt.setInt(1, prixDeVente);
			pstmt.setInt(2, no_article);
			rs = pstmt.executeUpdate();
			if(rs == 0) {
				throw new SQLException();
			}
			System.out.println("fin MAJprixVente : "+ prixDeVente);
			pstmt.close();
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}




	


	
	
	
	
	
	

}
