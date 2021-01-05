package fr.eni.encheres.dal;

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
