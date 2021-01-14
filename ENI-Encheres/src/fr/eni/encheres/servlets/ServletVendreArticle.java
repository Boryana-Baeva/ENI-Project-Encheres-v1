package fr.eni.encheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.RetraitManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

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
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/vendreArticle.jsp");
		
		HttpSession session = request.getSession();
		ArticleVendu article = new ArticleVendu();
		
		try {
			String nom = request.getParameter("article");
			String description = request.getParameter("description");
			LocalDate dateDebutEncheres = LocalDate.parse(request.getParameter("dEnchere"));
			LocalDate dateFinEncheres = LocalDate.parse(request.getParameter("fEnchere"));
			int miseAPrix = Integer.parseInt(request.getParameter("mPrix"));
			int categerieId = Integer.parseInt(request.getParameter("categorie"));
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");
			
			Categorie categorie = CategorieManager.selectionnerCategorieById(categerieId);
			Utilisateur utilisateur = (Utilisateur) session.getAttribute("ConnectedUser");
			Retrait retrait = new Retrait();
				
			retrait = new Retrait();
			retrait.setRue(rue);
			retrait.setCodePostal(codePostal);
			retrait.setVille(ville);
				
			RetraitManager.ajouterLieuRetrait(retrait);				
			
			article.setNom(nom);
			article.setDescription(description);
			article.setDateDebutEncheres(dateDebutEncheres);
			article.setDateFinEncheres(dateFinEncheres);
			article.setMiseAPrix(miseAPrix);
			article.setCategorie(categorie);
			article.setLieuRetrait(retrait);
			article.setVendeur(utilisateur);
			
			ArticleVenduManager.nouvelleVente(article);
			
			//dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
