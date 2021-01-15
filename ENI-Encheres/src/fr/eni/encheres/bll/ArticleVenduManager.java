package fr.eni.encheres.bll;

import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.EtatVente;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.jdbc.ArticleVenduDAOJDBCImpl;

public class ArticleVenduManager {

	
	private static ArticleVenduDAO articleVenduDAO = new ArticleVenduDAOJDBCImpl();
	private static ArticleVendu articleVendu = new ArticleVendu();
	private static  EtatVente etatVente;
			
	private static BusinessException businessException = new BusinessException();
	
	public ArticleVenduManager() {
		
	}

	public static  ArticleVendu nouvelleVente(ArticleVendu articleVendu)
			throws BusinessException {

		

		validerDate(articleVendu);

		if (!businessException.hasErreurs()) {
			articleVenduDAO.insert(articleVendu);
		} else {
			throw businessException;
		}
		return articleVendu;

	}

	private static void validerDate(ArticleVendu articleVendu) {
		
		if (articleVendu.getDateDebutEncheres() == null || articleVendu.getDateFinEncheres() == null || articleVendu.getDateDebutEncheres().isBefore(LocalDate.now()) ||
				articleVendu.getDateFinEncheres().isBefore(articleVendu.getDateDebutEncheres())) 
		{
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ENCHERES_DATE_ERREUR);
		}
		
	}
	
	
	public static void modifierArticlesVendus (ArticleVendu article) throws BusinessException{
		
		validerDate(article);
		if(!businessException.hasErreurs())
		{
			articleVenduDAO.update(article);
		}
	}
		
	public static List<ArticleVendu> selectAllArticles()throws BusinessException{
		return articleVenduDAO.getAll();
		
	}
	
	public static ArticleVendu selectArticleById(int id)throws BusinessException{
		return articleVenduDAO.getById(id);
		
	}
	
	public static List<ArticleVendu> selectArticlesByVendeur(int id)throws BusinessException{
		return articleVenduDAO.getByVendeur(id);
	}
	
	public static void supprimerArticlesVendus (int id)throws BusinessException{
		
		annulerVente(etatVente);
		if(articleVendu.getEtatVente() == EtatVente.ANNULE)
		{
			articleVenduDAO.delete(articleVendu.getId());
		}
	}
	
	private static void validerEtatVente(EtatVente etatVente) throws BusinessException
	{
		if (articleVendu.getEtatVente() == EtatVente.ENCHERES_TERMINEES  
				|| articleVendu.getEtatVente() == EtatVente.RETRAIT_EFFECTUE) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLES_ETAT_VENTE_ERREUR);
		}
	}
	
	public static void annulerVente(EtatVente etatVente) throws BusinessException
	{
		validerEtatVente(etatVente);
		if (!businessException.hasErreurs()) {
			articleVendu.setEtatVente(EtatVente.ANNULE);
		}
	}
	
}
