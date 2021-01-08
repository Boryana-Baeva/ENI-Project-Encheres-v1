package test.fr.eni.encheres.dal.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.UtilisateurDAO;
import fr.eni.encheres.dal.jdbc.ArticleVenduDAOJDBCImpl;
import fr.eni.encheres.dal.jdbc.CategorieDAOJDBCImpl;
import fr.eni.encheres.dal.jdbc.UtilisateurDAOJDBCImpl;

class ArticleVenduDAOJDBCImplTest {

	private static ArticleVenduDAO articleVenduDao = new ArticleVenduDAOJDBCImpl();
	private static CategorieDAO categorieDao = new CategorieDAOJDBCImpl();
	private static UtilisateurDAO utilisateurDao = new UtilisateurDAOJDBCImpl();
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testInsert() {
		
		Categorie categorie = CategorieDAOJDBCImplTest.getTestCategorie();
		try {
			categorieDao.insert(categorie);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
			
		Utilisateur utilisateur = UtilisateurDAOJDBCImplTest.getTestUtilisateur();
		try {
			utilisateurDao.insert(utilisateur);
		} catch (BusinessException e1) {
			e1.printStackTrace();
		} 
		
		ArticleVendu articleVendu = ArticleVenduDAOJDBCImplTest.getTestArticleVendu(utilisateur, categorie);
		try {
			articleVenduDao.insert(articleVendu);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		assertNotNull(categorie.getId());
		assertNotNull(utilisateur.getId());
		assertNotNull(articleVendu.getId());
		
	}

	@Test
	void testGetById() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAll() {
		fail("Not yet implemented");
	}

	@Test
	void testGetByVendeur() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}
	
	public static ArticleVendu getTestArticleVendu(Utilisateur utilisateur, Categorie categorie)
	{
		ArticleVendu article = new ArticleVendu();
		article.setNom("HP Deskjet 2723");
		article.setDescription("All-in-One - imprimante multifonctions - couleur");
		article.setDateDebutEncheres(LocalDate.now());
		article.setDateFinEncheres(LocalDate.now().plusDays(7));
		article.setMiseAPrix(100);
		article.setPrixVente(250);
		article.setVendeur(utilisateur);
		article.setCategorie(categorie);
		
		/*Utilisateur vendeur = new Utilisateur("yana", "Baeva", "Boryana", "b.baeva@gamail.com", "0612345678", "47 rue Lucie Aubrac", "33300", "Bordeaux", "123456", 100, true);
		try {
			utilisateurDao.insert(vendeur);
		} catch (BusinessException e) {
	
			e.printStackTrace();
		}
		try {
			article.setVendeur(utilisateurDao.getById(vendeur.getId()));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		article.setCategorie(new Categorie("Some category"));
		*/
		
		return article;
	}

}
