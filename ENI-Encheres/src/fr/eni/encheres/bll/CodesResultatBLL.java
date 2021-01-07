package fr.eni.encheres.bll;

public abstract class CodesResultatBLL {
	
	/**
	 * Echec quand le libelle ne respecte pas les règles définies
	 */
	public static final int REGLE_CATEGORIES_LIBELLE_ERREUR=20000;
	
	/**
	 * Echec quand la la date de l'enchere ne respecte pas les règles définies
	 */
	public static final int REGLE_ENCHERES_DATE_ERREUR=20001;
	
	/**
	 * Echec quand l'adresse  ne respecte pas les règles définies
	 */
	public static final int REGLE_RETRAITS_ADRESSE_ERREUR=20002;
	
	/**
	 * Echec quand les coordonnées de l'utilisateur ne respecte pas les règles définies
	 */
	public static final int REGLE_UTILISATEURS_COORDONNEES_ERREUR=20003;
	
	/**
	 * Echec quand la liste des articles vendus ne respecte pas les règles définies
	 */
	public static final int REGLE_UTILISATEURS_ARTICLE_VENDU_ERREUR=20004;
	
	/**
	 * Echec quand la liste des articles achetés ne respecte pas les règles définies
	 */
	public static final int REGLE_UTILISATEURS_ARTICLE_ACHETE_ERREUR=20005;
	
	/**
	 * Echec quand la liste des encheres ne respecte pas les règles définies
	 */
	public static final int REGLE_UTILISATEURS_ENCHERES_ERREUR=20006;
	/**
	 * Echec quand la date de début et de fin des enchères  ne respectent pas les règles définies
	 */
	public static final int REGLE_ARTICLES_DATE_ERREUR=20007;
	/**
	 * Echec quand la date de début et de fin des enchères  ne respectent pas les règles définies
	 */
	public static final int REGLE_ARTICLES_ERREUR=20007;

}
