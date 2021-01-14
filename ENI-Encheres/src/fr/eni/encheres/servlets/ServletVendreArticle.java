package fr.eni.encheres.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.RetraitManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;

/**
 * Servlet implementation class ServletVendreArticle
 */
@WebServlet("/vendre")
public class ServletVendreArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletVendreArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/vendreArticle.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArticleVendu article = null;
		
		try {
			String nom = request.getParameter("article");
			String description = request.getParameter("description");
			String dateDebutEncheres = request.getParameter("dEnchere");
			String dateFinEncheres = request.getParameter("fEnchere");
			String miseAPrix = request.getParameter("mPrix");
			String categorie = request.getParameter("categorie");
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");
			
			Retrait retrait = null;
			
			if (!rue.trim().equals("") || !rue.isEmpty() 
					|| !codePostal.trim().equals("") || !codePostal.isEmpty()
					|| !ville.trim().equals("") || !ville.isEmpty()) {
				
				retrait = new Retrait();
				retrait.setRue(rue);
				retrait.setCodePostal(codePostal);
				retrait.setVille(ville);
				
				RetraitManager.ajouterLieuRetrait(retrait);				
			} 
			
			List<Categorie> allCategories = CategorieManager.selectionnerToutesLesCategories();
			Categorie newCategorie = null;
			
			if (!allCategories.contains(categorie)) {
				newCategorie = new Categorie();
				newCategorie.setLibelle(categorie);
				
				CategorieManager.ajouterCategorie(newCategorie);
			}
			
			if (retrait == null) {
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
