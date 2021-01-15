package fr.eni.encheres.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletEncherir
 */
@WebServlet("/encherir")
public class ServletEncherir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEncherir() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/detailVente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur connectedUser = (Utilisateur) session.getAttribute("ConnectedUser");
		int currentCredit = connectedUser.getCredit();
		
		int prixEnchere = Integer.parseInt(request.getParameter("mPrix"));
		int idArticle = Integer.parseInt(request.getParameter("idArticle"));
		
		ArticleVendu articleAffiche = null;
		try {
			articleAffiche = ArticleVenduManager.selectArticleById(idArticle);
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		
		if (articleAffiche != null && prixEnchere > articleAffiche.getMiseAPrix()
				&& connectedUser.getCredit() >= prixEnchere) {
			Enchere enchere = new Enchere(LocalDate.now(), prixEnchere, articleAffiche, connectedUser);
			articleAffiche.setMiseAPrix(prixEnchere);
			
			try {
				EnchereManager.ajoutEnchere(enchere);
				request.setAttribute("mPrix", prixEnchere);
				
				ArticleVenduManager.modifierArticlesVendus(articleAffiche);
				connectedUser.setCredit(currentCredit - prixEnchere);
				UtilisateurManager.modifierUtilisateur(connectedUser);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("/WEB-INF/jsp/accueilConnected.jsp").forward(request, response);
		}
		
	}

}
