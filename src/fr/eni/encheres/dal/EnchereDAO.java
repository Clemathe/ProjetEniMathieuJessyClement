package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO {
	
	public List<Enchere> getenchere(Categorie categorie, String nomArticlePartiel);
	
	
}
