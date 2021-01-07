package fr.eni.encheres.bll;

import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EnchereDAO;

public class EnchereManager {
	
	
	private static EnchereDAO enchereDAO;
	private static BusinessException businessException;
	
	public EnchereManager() {
		
		enchereDAO=DAOFactory.getEnchereDAO();
	}
	
	public Enchere ajoutEnchere (Enchere enchere) throws BusinessException {

		this.validerDate(enchere.getDate(), businessException);
		
		if(!businessException.hasErreurs())
		{
			enchereDAO.insert(enchere);
		}
		else
		{
			throw businessException;
		}
		return enchere;
		
	}

	public static Enchere selectionnerEnchereById(int id) throws BusinessException
	{
		return enchereDAO.getById(id);
	}

	public static List<Enchere> selectionnerToutesLesEncheres() throws BusinessException
	{
		return enchereDAO.getAll();
	}
	
	private void validerDate(LocalDate date, BusinessException businessException) {
		
		if(date == null || date.isAfter(LocalDate.now()))
		{
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ENCHERES_DATE_ERREUR);
		}		
	}
}
