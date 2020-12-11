package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.DAOFactory;

public class CategorieManager {
	private static CategorieDAO categorieDAO;
	
	public CategorieManager() {
		categorieDAO = DAOFactory.getCategorie();
	}
	public List<Categorie> getCategorie(){
		return CategorieManager.categorieDAO.getCategorie();
	}
	
	
	
	
}
