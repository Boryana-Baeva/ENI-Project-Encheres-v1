package fr.eni.encheres.dal;

public abstract class CodesResultatDAL {

	/**
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int INSERT_OBJET_NULL=10000;
	
	/**
	 * Echec général quand erreur non gérée à l'insertion 
	 */
	public static final int INSERT_OBJET_ECHEC=10001;
	
	/**
	 * Echec de la lecture des Articles
	 */
	public static final int LECTURE_ARTICLES_ECHEC=10002;
	
	/**
	 * Echec de la lecture des Utilisateurs
	 */
	public static final int LECTURE_UTILISATEURS_ECHEC=10003;
	
	/**
	 * Echec de la lecture des Categorie
	 */
	public static final int LECTURE_CATEGORIES_ECHEC=10004;
	/**
	 * Echec de la lecture des Encheres
	 */
	public static final int LECTURE_ENCHERES_ECHEC=10005;
	/**
	 * Echec de la lecture des Retrait
	 */
	public static final int LECTURE_RETRAIT_ECHEC=10007;
	
	/**
	 * Echec général quand erreur non gérée à l'insertion 
	 */
	public static final int UPDATE_OBJET_ECHEC=10008;
	/**
	 * Echec général quand erreur non gérée à l'insertion 
	 */
	public static final int DELETE_OBJET_ECHEC=10009;
	
}
