package fr.eni.encheres.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletDeleteProfil
 */
@WebServlet("/deleteProfil")
public class ServletDeleteProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDeleteProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Utilisateur connectedUser = (Utilisateur) session.getAttribute("ConnectedUser");
		
		
			try {
				UtilisateurManager.supprimerUtilisateur(connectedUser.getId());
				session.removeAttribute("ConnectedUser");
				session.invalidate();
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/accueilDeconnected");
				dispatcher.forward(request, response);
			} catch (BusinessException e) {
				e.printStackTrace();
				RequestDispatcher dispatcher = request.getRequestDispatcher("/profil");
				dispatcher.forward(request, response);
				
			}
		
		
	}

}
