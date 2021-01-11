package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;
import fr.eni.encheres.dal.jdbc.UtilisateurDAOJDBCImpl;

public class UtilisateurManager {

	private static UtilisateurDAO utilisateurDAO = new UtilisateurDAOJDBCImpl();
	private static Utilisateur utilisateur = new Utilisateur();

	private static BusinessException businessException = new BusinessException();

	public UtilisateurManager() {
		UtilisateurManager.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public static Utilisateur inscriptionUtilisateur(String pseudo, String nom,String prenom, String email, String telephone, String rue, String codePostal, String ville, String password) throws BusinessException {

		validerCoordonnees(pseudo, nom, prenom, email, rue, codePostal, ville, password);

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
			utilisateurDAO.insert(utilisateur);

		} else {

			throw businessException;
		}
		return utilisateur;
	}

	private static void validerCoordonnees(String pseudo, String nom,String prenom, String email, String rue, String codePostal, String ville, String password) {

		if (pseudo.trim().equals("") || nom.trim().equals("")
				|| prenom.trim().equals("") || email.trim().equals("")
				|| rue.trim().equals("") || codePostal.trim().equals("")
				|| ville.trim().equals("") || password.trim().equals("")) {

			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_COORDONNEES_ERREUR);
		}
	}

	public static void modifierUtilisateur(String nom,String prenom, String email, String rue, String codePostal, String ville, String password) throws BusinessException {

		modifierCoordonnees(nom, prenom, email, rue, codePostal, ville, password, businessException);

		if (!businessException.hasErreurs()) {
			utilisateurDAO.update(utilisateur);

		}

	}

	private static void modifierCoordonnees(String nom,String prenom, String email, String rue, String codePostal, String ville, String password, BusinessException businessException) {

		if (nom.trim().equals("") || prenom.trim().equals("")
				|| email.trim().equals("") ||rue.trim().equals("")
				|| codePostal.trim().equals("") || ville.trim().equals("")
				|| password.trim().equals("")) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_COORDONNEES_ERREUR);
		}

	}

	/*public static void ventesUtilisateur(List<ArticleVendu> articlesVendus) throws BusinessException {

		validerListeArticlesVendus(articlesVendus);

	}

	/*private static void validerListeArticlesVendus(List<ArticleVendu> articlesVendus) {
		if (utilisateur.getArticlesVendus() == null || utilisateur.getArticlesVendus().size() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_ARTICLE_VENDU_ERREUR);
		}
	}

	private static void validerListeEncheres(List<Enchere> encheres) {
		if (utilisateur.getEncheres() == null || utilisateur.getEncheres().size() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_ENCHERES_ERREUR);
		}
	}*/

	/*public static void encheresUtilisateur(List<Enchere> encheres) throws BusinessException {

		validerListeEncheres(encheres);

	}*/
	/*public static void achatsUtilisateur(List<ArticleVendu> articlesAchetes) throws BusinessException {

		validerListeArticlesAchetes(articlesAchetes);

	}*/

	/*private static void validerListeArticlesAchetes(List<ArticleVendu> articlesAchetes) {
		if (utilisateur.getArticlesAchetes() == null || utilisateur.getArticlesAchetes().size() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_ARTICLE_ACHETE_ERREUR);
		}

	}*/

	public static Utilisateur selectUserById(int id) throws BusinessException {
		return utilisateurDAO.getById(id);

	}

	public static List<Utilisateur> selectAllUsers() throws BusinessException {
		return utilisateurDAO.getAll();
	}

	public static Utilisateur selectUserByPseudo(String pseudo) throws BusinessException{
		return utilisateurDAO.getByPseudo(pseudo);
	}
	
	public static List<ArticleVendu> selectArticlesVendus ()throws BusinessException{
		return utilisateurDAO.getAllArticlesVendus(utilisateur);
	}
}
