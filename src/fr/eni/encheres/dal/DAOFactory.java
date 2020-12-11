package fr.eni.encheres.dal;

public abstract class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDAO()
	{
		return new UtilisateurDAOJdbcImpl();
	}
	public static ArticleVenduDAO getEnchereDAO() {
		
		return new ArticleVenduDAOJdbcImpl();
	}
	public static CategorieDAO getCategorie() {
		return new CategorieDAOJdbcImpl();
	}
}

