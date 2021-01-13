package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletUpdateProfil
 */
@WebServlet("/updateProfil")
public class ServletUpdateProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUpdateProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/updateProfil.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/updateProfil.jsp");
		
		HttpSession session = request.getSession();
		Utilisateur connectedUser = (Utilisateur) session.getAttribute("ConnectedUser");
		
		try {
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String telephone = request.getParameter("telephone");
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codepostale");
			String ville = request.getParameter("ville");
			String password = request.getParameter("motDePasseActuel");
			String newPassword = request.getParameter("newMotDePasse");
			String confirmation = request.getParameter("motDePasseConfirmation");
			
			/*if(nom.length()==0 || nom.isEmpty() ) {
				request.setAttribute("erreur", "Le nom n'a pas été renseigné, veuillez le saisir ...");
				dispatcher.forward(request, response);
			}	
			else if (prenom.length()==0 || prenom.isEmpty() ) {
				request.setAttribute("erreur", "Le prénom n'a pas été renseigné, veuillez le saisir ...");
				dispatcher.forward(request, response);
			}
			else if (email.length()==0 || email.isEmpty() ) {
				request.setAttribute("erreur", "L'email n'a pas été renseigné, veuillez le saisir ...");
				dispatcher.forward(request, response);
			}
			else if (password.length()==0 || password.isEmpty() ) {
				request.setAttribute("erreur", "Le mot de passe n'a pas été renseigné, veuillez le saisir ...");
				dispatcher.forward(request, response);
			}
			else if (rue.length()==0 || rue.isEmpty() ) {
				request.setAttribute("erreur", "La rue n'a pas été renseigné, veuillez le saisir ...");
				dispatcher.forward(request, response);
			}
			else if (ville.length()==0 || ville.isEmpty() ) {
				request.setAttribute("erreur", "La ville n'a pas été renseigné, veuillez le saisir ...");
				dispatcher.forward(request, response);
			}
			else if (codePostal.length()==0 || codePostal.isEmpty() ) {
				request.setAttribute("erreur", "Le code postal n'a pas été renseigné, veuillez le saisir ...");
				dispatcher.forward(request, response);
			}
			else { }*/
			
				connectedUser.setNom(nom);
				connectedUser.setPrenom(prenom);
				connectedUser.setEmail(email);
				connectedUser.setTelephone(telephone);
				connectedUser.setRue(rue);
				connectedUser.setCodePostal(codePostal);
				connectedUser.setVille(ville);
				
				if (newPassword.equals(confirmation)) {
					connectedUser.setPassword(newPassword);
				} 
							
				UtilisateurManager.modifierUtilisateur(connectedUser);
				session.setAttribute("ConnectedUser", connectedUser);
				
				this.getServletContext().getRequestDispatcher("/profil").forward(request, response);
			
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
