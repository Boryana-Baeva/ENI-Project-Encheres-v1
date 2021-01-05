package fr.eni.encheres.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


/**
 * Servlet implementation class ServletTestPoolConnexion
 */
@WebServlet("/ServletTestPoolConnection")
public class ServletTestPoolConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTestPoolConnection() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out= response.getWriter();
		//Création d'un objet de type Context permettant la recherche d'une ressource nommée dans l'arbre JNDI
		try {
			Context context = new InitialContext();
			//Recherche de la ressource
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc/pool_cnx");
			//Demande d'une connexion. La méthode getConnection met la demande en attente tant qu'il n'y a pas de connexion disponible
			Connection cnx = dataSource.getConnection();
			//Exploitation de la connexion
			out.print("La connexion est "+ (cnx.isClosed()?"fermée":"ouverte")+".");
			//Libération de la connexion. Elle n'est pas fermée mais remise dans le pool
			cnx.close();
		} catch (NamingException | SQLException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			out.println("Une erreur est survenue lors de l'utilisation de la base de données : " + e.getMessage());
		}
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}