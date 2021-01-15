package fr.eni.encheres.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.RetraitManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;

/**
 * Servlet implementation class ServletUpdateVente
 */
@WebServlet("/updateVente")
public class ServletUpdateVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUpdateVente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idArticle = Integer.parseInt(request.getParameter("idArticle"));
		ArticleVendu updatedArticle = null;
		
		try {
			updatedArticle = ArticleVenduManager.selectArticleById(idArticle);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		if (updatedArticle != null) {
			request.setAttribute("UpdatedArticle", updatedArticle);
			request.getRequestDispatcher("/WEB-INF/jsp/updateVente.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idArticle = Integer.parseInt(request.getParameter("idArticle"));
		ArticleVendu updatedArticle = null;
		
		String nom = request.getParameter("article");
		String description = request.getParameter("description");
		int idCategorie = Integer.parseInt(request.getParameter("categorie"));
		int miseAPrix = Integer.parseInt(request.getParameter("mPrix"));
		LocalDate dateDebutEncheres = LocalDate.parse(request.getParameter("dEnchere"));
		LocalDate dateFinEncheres = LocalDate.parse(request.getParameter("fEnchere"));
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		
		
		try {
			updatedArticle = ArticleVenduManager.selectArticleById(idArticle);
			
			Categorie categorie = CategorieManager.selectionnerCategorieById(idCategorie);
			Retrait retrait = RetraitManager.selectionnerRetraitById(updatedArticle.getLieuRetrait().getId());
			retrait.setRue(rue);
			retrait.setCodePostal(codePostal);
			retrait.setVille(ville);
			RetraitManager.modifierRetrait(retrait);
				
			updatedArticle.setNom(nom);
			updatedArticle.setDescription(description);
			updatedArticle.setMiseAPrix(miseAPrix);
			updatedArticle.setDateDebutEncheres(dateDebutEncheres);
			updatedArticle.setDateFinEncheres(dateFinEncheres);
			updatedArticle.setCategorie(categorie);
			updatedArticle.setLieuRetrait(retrait);
			ArticleVenduManager.modifierArticlesVendus(updatedArticle);
			
			request.setAttribute("idArticle", idArticle);
			request.getRequestDispatcher("/detailVente").forward(request, response);
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		
	}

}
