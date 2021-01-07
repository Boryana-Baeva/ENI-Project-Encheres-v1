package fr.eni.encheres.bll;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.DAOFactory;

public class CategorieManager {
	
	
	private CategorieDAO categorieDAO;
	
	public CategorieManager() {
		this.categorieDAO = DAOFactory.getCategorieDAO();
	}
	
	public Categorie categorie ( String libelle) throws BusinessException{
		
		BusinessException businessException = new BusinessException();
		
		this.validerLibelle(libelle, businessException);
		
		Categorie categorie =  null;
		if(!businessException.hasErreurs()) {
			categorie = new Categorie();
			categorie.setLibelle(libelle);
			this.categorieDAO.insert(categorie);
		}
		else
		{
			throw businessException;
		}
		return categorie;
		
	}

	private void validerLibelle(String libelle, BusinessException businessException) {
		
		if (libelle == null)
		{
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CATEGORIES_LIBELLE_ERREUR);
		}
	}

}
