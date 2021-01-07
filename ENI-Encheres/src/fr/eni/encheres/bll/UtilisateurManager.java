package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;
import fr.eni.encheres.dal.jdbc.UtilisateurDAOJDBCImpl;

public class UtilisateurManager {

	private static UtilisateurDAO utilisateurDAO = new UtilisateurDAOJDBCImpl();

	private static BusinessException businessException;

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

	public static void modifierUtilisateur(Utilisateur utilisateur) throws BusinessException {

		modifierCoordonnees(utilisateur, businessException);

		if (!businessException.hasErreurs()) {
			utilisateurDAO.update(utilisateur);

		}

	}

	private static void modifierCoordonnees(Utilisateur utilisateur, BusinessException businessException) {

		if (utilisateur.getNom().trim().equals("") || utilisateur.getPrenom().trim().equals("")
				|| utilisateur.getEmail().trim().equals("") || utilisateur.getRue().trim().equals("")
				|| utilisateur.getCodePostal().trim().equals("") || utilisateur.getVille().trim().equals("")
				|| utilisateur.getPassword().trim().equals("")) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_COORDONNEES_ERREUR);
		}

	}

	public static void ventesUtilisateur(Utilisateur utilisateur) throws BusinessException {

		validerListeArticlesVendus(utilisateur);

	}

	private static void validerListeArticlesVendus(Utilisateur utilisateur) {
		if (utilisateur.getArticlesVendus() == null || utilisateur.getArticlesVendus().size() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_ARTICLE_VENDU_ERREUR);
		}
	}

	private static void validerListeEncheres(Utilisateur utilisateur) {
		if (utilisateur.getEncheres() == null || utilisateur.getEncheres().size() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_ENCHERES_ERREUR);
		}
	}

	public static void encheresUtilisateur(Utilisateur utilisateur) throws BusinessException {

		validerListeEncheres(utilisateur);

	}
	public static void achatsUtilisateur(Utilisateur utilisateur) throws BusinessException {

		validerListeArticlesAchetes(utilisateur);

	}

	private static void validerListeArticlesAchetes(Utilisateur utilisateur) {
		if (utilisateur.getArticlesAchetes() == null || utilisateur.getArticlesAchetes().size() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_ARTICLE_ACHETE_ERREUR);
		}

	}

	public static Utilisateur selectionnerUtilisateurById(int id) throws BusinessException {
		return utilisateurDAO.getById(id);

	}

	public static List<Utilisateur> selectionnerTousLesUtilisateurs() throws BusinessException {
		return utilisateurDAO.getAll();
	}

}
