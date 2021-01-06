package fr.eni.encheres.bll;

import java.time.LocalDate;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.DAOFactory;

public class ArticleVenduManager {

	//gestion des erreurs
	private ArticleVenduDAO articleVenduDAO;

	public ArticleVenduManager() {
		this.articleVenduDAO = DAOFactory.getArticleDAO();
	}

	public ArticleVendu nouvelleVente(String nom, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, Categorie categorie, Utilisateur vendeur, Retrait lieuRetrait)
			throws BusinessException {

		BusinessException businessException = new BusinessException();

		this.validerDate(dateDebutEncheres, dateFinEncheres, businessException);

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
			this.articleVenduDAO.insert(articleVendu);
		} else {
			throw businessException;
		}
		return articleVendu;

	}

	private void validerDate(LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			BusinessException businessException) {
		
		if (dateDebutEncheres == null || dateFinEncheres == null || dateDebutEncheres.isBefore(LocalDate.now()) ||
				dateFinEncheres.isBefore(dateDebutEncheres)) 
		{
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ENCHERES_DATE_ERREUR);
		}
		
	}
}
