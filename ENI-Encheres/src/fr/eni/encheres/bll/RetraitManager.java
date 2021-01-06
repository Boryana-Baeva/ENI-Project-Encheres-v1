package fr.eni.encheres.bll;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.RetraitDAO;

public class RetraitManager {
	//gestion des erreurs
	private RetraitDAO retraitDAO;
	
	public RetraitManager() {
		this.retraitDAO= DAOFactory.getRetraitDAO();
	}
	
	public Retrait ajoutLieuRetrait ( String rue, String codePostal, String ville ) throws BusinessException {
		
		BusinessException businessException = new BusinessException();
		
		this.validerAdresse(rue, codePostal, ville, businessException);
		Retrait retrait = null;
		if(!businessException.hasErreurs()) {
			retrait = new Retrait();
			retrait.setRue(rue);
			retrait.setCodePostal(codePostal);
			retrait.setVille(ville);
			this.retraitDAO.insert(retrait);
		}
		else
		{
			throw businessException;
		}
		return retrait;
		
	}

	private void validerAdresse(String rue, String codePostal, String ville, BusinessException businessException) {
		
		if(rue == null || codePostal==null || ville==null)
		{
			businessException.ajouterErreur(CodesResultatBLL.REGLE_RETRAITS_ADRESSE_ERREUR);
		}
	}

}
