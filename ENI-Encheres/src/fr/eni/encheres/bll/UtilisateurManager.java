package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurManager {

	
	private UtilisateurDAO utilisateurDAO;

	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public Utilisateur inscriptionUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String password, List<String> listeArticlesVendus,
			List<String> listeArticlesAchetes, List<String> listeEncheres) throws BusinessException {

		BusinessException businessException = new BusinessException();

		this.validerListeArticlesVendus(listeArticlesVendus, businessException);
		this.validerListeArticlesAchetes(listeArticlesAchetes, businessException);
		this.validerListeEncheres(listeEncheres, businessException);
		this.validerCoordonnees(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, password,
				businessException);

		Utilisateur utilisateur = null;

		if (!businessException.hasErreurs()) {
			utilisateur = new Utilisateur();
			utilisateur.setPseudo(pseudo);
			utilisateur.setNom(nom);
			utilisateur.setPrenom(prenom);
			utilisateur.setEmail(email);
			utilisateur.setTelephone(telephone);
			utilisateur.setRue(rue);
			utilisateur.setCodePostal(codePostal);
			utilisateur.setVille(ville);
			utilisateur.setPassword(password);
			this.utilisateurDAO.insert(utilisateur);

		} else {

			throw businessException;
		}
		return utilisateur;
	}

	private void validerCoordonnees(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String password, BusinessException businessException) {
		
		if (pseudo == null || nom == null || email == null || rue == null || codePostal == null || ville == null
				|| password == null) {
			
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_COORDONNEES_ERREUR);
		}
				
	}

	private void validerListeEncheres(List<String> listeEncheres, BusinessException businessException) {
		if (listeEncheres == null || listeEncheres.size() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_ENCHERES_ERREUR);
		}

	}

	private void validerListeArticlesVendus(List<String> listeArticlesVendus, BusinessException businessException) {
		if (listeArticlesVendus == null || listeArticlesVendus.size() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_ARTICLE_VENDU_ERREUR);
		}
	}

	public void validerListeArticlesAchetes(List<String> listeArticlesAchetes, BusinessException businessException) {
		if (listeArticlesAchetes == null || listeArticlesAchetes.size() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_ARTICLE_ACHETE_ERREUR);
		}

	}

}
