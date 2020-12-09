package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EnchereDAO;

public class EnchereManager {
	private static EnchereDAO enchereDAO;
	
	
	
	public EnchereManager() {
		enchereDAO = DAOFactory.getEnchereDAO();
	}
	
	public List<Enchere> getEnchereEnCours(Categorie categorie, String nomArticlePartiel){
		
		return EnchereManager.enchereDAO.getenchere(categorie, nomArticlePartiel);
	}
	
	
	
}
