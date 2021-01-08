package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.DAOFactory;

public class CategorieManager {
	
	
	private static CategorieDAO categorieDAO;
	private static BusinessException businessException = new BusinessException();
	
	public CategorieManager() {
		categorieDAO = DAOFactory.getCategorieDAO();
	}
	
	public static Categorie ajouterCategorie (Categorie categorie) throws BusinessException{
		
		validerLibelle(categorie.getLibelle(), businessException);
		
		if(!businessException.hasErreurs()) {
			categorieDAO.insert(categorie);
		}
		else
		{
			throw businessException;
		}
		return categorie;
		
	}
	
	public static Categorie selectionnerCategorieById(int id ) throws BusinessException
	{
		return categorieDAO.getById(id);
	}

	public static List<Categorie> selectionnerToutesLesCategories() throws BusinessException
	{
		return categorieDAO.getAll();
	}
	
	public static void modifierCategorie(Categorie categorie) throws BusinessException 
	{
		validerLibelle(categorie.getLibelle(), businessException);
		
		if (!businessException.hasErreurs()) {
			categorieDAO.update(categorie);
		}
	}
	
	public static void supprimerCategorie(Categorie categorie) throws BusinessException {
		
		validerLibelle(categorie.getLibelle(), businessException);
		
		if (!businessException.hasErreurs()) {
			categorieDAO.delete(categorie.getId());
		}
	}
	
	private static void validerLibelle(String libelle, BusinessException businessException) {
		
		if (libelle == null || libelle.trim().equals(""))
		{
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CATEGORIES_LIBELLE_ERREUR);
		}
	}
	
}
