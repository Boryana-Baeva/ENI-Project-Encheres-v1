package fr.eni.encheres.bll;

import java.time.LocalDate;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EnchereDAO;

public class EnchereManager {
	
	//gestion des erreurs
	private EnchereDAO enchereDAO;
	
	public EnchereManager() {
		
		this.enchereDAO=DAOFactory.getEnchereDAO();
	}
	
	public Enchere ajoutEnchere (LocalDate date, int montant, ArticleVendu article,
			Utilisateur encherisseur) throws BusinessException {
		
		BusinessException businessException = new BusinessException();
		this.validerDate(date, businessException);
		
		Enchere enchere = null;
		
		if(!businessException.hasErreurs())
		{
			enchere = new Enchere();
			enchere.setDate(date);
			enchere.setMontant(montant);
			enchere.setArticle(article);
			enchere.setEncherisseur(encherisseur);
			this.enchereDAO.insert(enchere);
		}
		else
		{
			throw businessException;
		}
		return enchere;
		
	}

	private void validerDate(LocalDate date, BusinessException businessException) {
		
		if(date == null || date.isAfter(LocalDate.now()))
		{
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ENCHERES_DATE_ERREUR);
		}
		
	}

}
