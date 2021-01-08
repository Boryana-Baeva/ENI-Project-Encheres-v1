package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utils {
	
	
	public static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=encheres_db","user_encheres","Encheres123");
	}

}
