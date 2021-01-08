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

public class ArticleVenduManager {

	
	private static ArticleVenduDAO articleVenduDAO;
	
	private static BusinessException businessException = new BusinessException();
	
	public ArticleVenduManager() {
		articleVenduDAO = DAOFactory.getArticleDAO();
	}

	public ArticleVendu nouvelleVente(String nom, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, Categorie categorie, Utilisateur vendeur, Retrait lieuRetrait)
			throws BusinessException {

		

		validerDate(dateDebutEncheres, dateFinEncheres, businessException);

		ArticleVendu articleVendu = null;
		if (!businessException.hasErreurs()) {
			articleVendu = new ArticleVendu();
			articleVendu.setNom(nom);
			articleVendu.setDescription(description);
			articleVendu.setDateDebutEncheres(dateDebutEncheres);
			articleVendu.setDateFinEncheres(dateFinEncheres);
			articleVendu.setMiseAPrix(miseAPrix);
			articleVendu.setCategorie(categorie);
			articleVendu.setLieuRetrait(lieuRetrait);
			articleVenduDAO.insert(articleVendu);
		} else {
			throw businessException;
		}
		return articleVendu;

	}

	private static void validerDate(LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			BusinessException businessException) {
		
		if (dateDebutEncheres == null || dateFinEncheres == null || dateDebutEncheres.isBefore(LocalDate.now()) ||
				dateFinEncheres.isBefore(dateDebutEncheres)) 
		{
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ENCHERES_DATE_ERREUR);
		}
		
	}
	
	
	public static void modifierArticlesVendus (ArticleVendu articleVendu) throws BusinessException{
		
		validerDate(articleVendu.getDateDebutEncheres(),articleVendu.getDateDebutEncheres(),businessException);
		if(!businessException.hasErreurs())
		{
			articleVenduDAO.update(articleVendu);
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
	
	public static void supprimerArticlesVendus (ArticleVendu articleVendu)throws BusinessException{
		
		annulerVente(articleVendu);
		if(articleVendu.getEtatVente() == EtatVente.ANNULE)
		{
			articleVenduDAO.delete(articleVendu.getId());
		}
	}
	
	private static void validerEtatVente(ArticleVendu articleVendu, BusinessException businessException ) throws BusinessException
	{
		if (articleVendu.getEtatVente() == EtatVente.ENCHERES_TERMINEES  
				|| articleVendu.getEtatVente() == EtatVente.RETRAIT_EFFECTUE) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLES_ETAT_VENTE_ERREUR);
		}
	}
	
	public static void annulerVente(ArticleVendu articleVendu) throws BusinessException
	{
		validerEtatVente(articleVendu, businessException);
		if (!businessException.hasErreurs()) {
			articleVendu.setEtatVente(EtatVente.ANNULE);
		}
	}
	
}
