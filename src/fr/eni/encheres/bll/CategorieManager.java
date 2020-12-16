package fr.eni.encheres.bll;




import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.DAOFactory;

public class CategorieManager {
	private static CategorieDAO categorieDAO;
	
	public CategorieManager() {
		categorieDAO = DAOFactory.getCategorie();
	}
	public int getCategorie(String libelle){
		return CategorieManager.categorieDAO.getCategorie(libelle);
	}
	
	
}
