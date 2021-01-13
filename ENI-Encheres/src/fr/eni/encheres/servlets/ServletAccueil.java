package fr.eni.encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bo.ArticleVendu;

/**
 * Servlet implementation class ServletListeEncheres
 */
@WebServlet("/accueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAccueil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		List<ArticleVendu> articles = new ArrayList<>();
		
		if (session.getAttribute("ConnectedUser") != null) {
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/accueilConnected.jsp");
		}
		else {
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/accueilDeconnected.jsp");
		}
		
		/*try {
			articles = ArticleVenduManager.selectAllArticles();
					
		} catch (BusinessException e) {
			//e.printStackTrace();
			dispatcher.forward(request, response);
		}
			
		if (articles.size() != 0) {
			request.setAttribute("listeArticles", articles);
		} */
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
