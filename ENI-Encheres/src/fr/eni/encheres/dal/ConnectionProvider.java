package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public abstract class ConnectionProvider {
	
private static DataSource dataSource;

// connection
	
	/**
	 * Au chargement de la classe, la DataSource est recherchée dans l'arbre JNDI
	 */
	static
	{
		Context contex;
		try {
			contex = new InitialContext();
			ConnectionProvider.dataSource = (DataSource)contex.lookup("jdbc/pool_cnx");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("Impossible d'accéder à la base de données");
		}
	}
	
	/**
	 * Cette méthode retourne une connexion opérationnelle issue du pool de connexion
	 * vers la base de donnee
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException 
	{
		return ConnectionProvider.dataSource.getConnection();
	}

}
