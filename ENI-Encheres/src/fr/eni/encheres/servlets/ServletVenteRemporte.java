package fr.eni.encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletVenteRemporte
 */
@WebServlet("/venteRemporte")
public class ServletVenteRemporte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletVenteRemporte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArticleVendu articleAffiche = null;
		List<Enchere> encheres = new ArrayList<>();
		Utilisateur encherisseur = null;
		Enchere meilleureOffre = null;
		
		int id = Integer.parseInt(request.getParameter("idArticle"));
		
		try {
			articleAffiche = ArticleVenduManager.selectArticleById(id);
			encheres = EnchereManager.selectionnerEncheresParArticle(id);
			
			if (encheres.size() > 0) {		
				meilleureOffre = encheres.get(encheres.size()-1);
				encherisseur = meilleureOffre.getEncherisseur();
				meilleureOffre.setRemporte(true);
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (articleAffiche != null) {
			request.setAttribute("ArticleAffiche", articleAffiche);
			request.setAttribute("GagnantEnchere", encherisseur);
			request.setAttribute("MeilleureOffre", meilleureOffre);
			request.getRequestDispatcher("/WEB-INF/jsp/remporterVente.jsp").forward(request, response);
		
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
