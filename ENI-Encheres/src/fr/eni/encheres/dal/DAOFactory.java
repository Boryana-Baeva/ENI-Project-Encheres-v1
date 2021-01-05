package fr.eni.encheres.dal;

import fr.eni.encheres.dal.jdbc.ArticleVenduDAOJDBCImpl;
import fr.eni.encheres.dal.jdbc.UtilisateurDAOJDBCImpl;

public abstract class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDAO()
	{
		return new UtilisateurDAOJDBCImpl();		
	}
	
	public static ArticleVenduDAO getArticleDAO()
	{
		return new ArticleVenduDAOJDBCImpl();		
	}
}
