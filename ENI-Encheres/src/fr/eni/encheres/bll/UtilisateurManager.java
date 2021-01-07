package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;
import fr.eni.encheres.dal.jdbc.UtilisateurDAOJDBCImpl;

public class UtilisateurManager {

	private static UtilisateurDAO utilisateurDAO = new UtilisateurDAOJDBCImpl();

	public UtilisateurManager() {
		UtilisateurManager.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public static Utilisateur inscriptionUtilisateur(String pseudo, String nom, String prenom, String email,
			String telephone, String rue, String codePostal, String ville, String password,
			List<String> listeArticlesVendus, List<String> listeArticlesAchetes, List<String> listeEncheres)
			throws BusinessException {

		BusinessException businessException = new BusinessException();

		UtilisateurManager.validerCoordonnees(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, password,
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
			utilisateurDAO.insert(utilisateur);

		} else {

			throw businessException;
		}
		return utilisateur;
	}

	public static void modifierUtilisateur(Utilisateur utilisateur) throws BusinessException {

		BusinessException businessException = new BusinessException();

		UtilisateurManager.modifierCoordonnees(utilisateur,businessException);

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

	private static void validerCoordonnees(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String password, BusinessException businessException) {

		if (pseudo == null || nom == null || prenom == null || email == null || rue == null || codePostal == null
				|| ville == null || password == null) {

			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_COORDONNEES_ERREUR);
		}

	}

	private static void validerListeEncheres(List<String> listeEncheres, BusinessException businessException) {
		if (listeEncheres == null || listeEncheres.size() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_ENCHERES_ERREUR);

		}

	}

	private static void validerListeArticlesVendus(List<String> listeArticlesVendus,
			BusinessException businessException) {
		if (listeArticlesVendus == null || listeArticlesVendus.size() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_ARTICLE_VENDU_ERREUR);
		}
	}

	private static void validerListeArticlesAchetes(List<String> listeArticlesAchetes,
			BusinessException businessException) {
		if (listeArticlesAchetes == null || listeArticlesAchetes.size() == 0) {
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
