package fr.eni.encheres.bll;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateur;

public class TestBLL {

	public static void main(String[] args) throws BusinessException {
		// TODO Auto-generated method stub

		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setPseudo("sassou");
		utilisateur.setNom("maerten");
		utilisateur.setPrenom("sarah");
		utilisateur.setEmail("maerten@outlook.com");
		utilisateur.setTelephone("0612591551");
		utilisateur.setRue("132 Rue de Lausanne");
		utilisateur.setCodePostal("76000");
		utilisateur.setVille("Rouen");
		utilisateur.setPassword("sarah01");
		/**
		 * TEST UTILISATEUR
		 */
		UtilisateurManager.inscriptionUtilisateur(utilisateur);
		//UtilisateurManager.modifierUtilisateur("", "", "jeanmi@youhou.fr","56 rue de la bule", "56789", "Bulville", "ugfufz");
		//UtilisateurManager.encheresUtilisateur(null);
		//UtilisateurManager.achatsUtilisateur(null);
		//UtilisateurManager.ventesUtilisateur(null);
		//UtilisateurManager.selectAllUsers();
		//UtilisateurManager.selectUserById(1);
		/**
		 * TEST ARTICLE VENDU 
		 */
		
		
		/**
		 * TEST 
		 */
		
		/**
		 * TEST UTILISATEUR
		 */
		
		/**
		 * TEST UTILISATEUR
		 */
	}

}
