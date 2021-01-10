package test.fr.eni.encheres.dal.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.RetraitDAO;
import fr.eni.encheres.dal.UtilisateurDAO;
import fr.eni.encheres.dal.jdbc.ArticleVenduDAOJDBCImpl;
import fr.eni.encheres.dal.jdbc.CategorieDAOJDBCImpl;
import fr.eni.encheres.dal.jdbc.RetraitDAOJDBCImpl;
import fr.eni.encheres.dal.jdbc.UtilisateurDAOJDBCImpl;

class ArticleVenduDAOJDBCImplTest {

	private static ArticleVenduDAO articleVenduDao = new ArticleVenduDAOJDBCImpl();
	private static CategorieDAO categorieDao = new CategorieDAOJDBCImpl();
	private static UtilisateurDAO utilisateurDao = new UtilisateurDAOJDBCImpl();
	private static RetraitDAO retraitDao = new RetraitDAOJDBCImpl();
	
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
	void testGetById() throws BusinessException {
		Utilisateur user1 = new Utilisateur("sanzza","maerten","prenom","maerten#gmail.com","3648836279","44 rue maurice","44567","mauriceVille","udfgfgf",600,true);
		utilisateurDao.insert(user1);
		
		ArticleVendu  article1 = new ArticleVendu("macbookpro","c'est un ordinateur vraiment cool", LocalDate.of(2020, 01, 12), LocalDate.of(2020, 01, 18),200, user1, categorieDao.getById(3));
		ArticleVendu article2 = new ArticleVendu("tapis de course","c'est un tapis de course vraiment cool", LocalDate.of(2020, 01, 18), LocalDate.of(2020, 01, 23),300, user1, categorieDao.getById(4));
		System.out.println(article1);
		/*article1.set
		 * Nom("macbookpro");
		article1.setDescription("c'est un ordinateur vraiment cool");
		article1.setDateDebutEncheres(LocalDate.of(2020, 01, 12));
		article1.setDateFinEncheres(LocalDate.of(2020, 01, 18));
		article1.setMiseAPrix(200);
		article1.setVendeur(utilisateurDao.getById(3));
		article1.setCategorie(categorieDao.getById(3));
		article1.setLieuRetrait(retraitDao.getById(3));
		
		
		ArticleVendu  article2 = new ArticleVendu();
		article2.setNom("tapis de course");
		article2.setDescription("capacité d'aller jusqu'à 15 Km/h");
		article2.setDateDebutEncheres(LocalDate.of(2020, 01, 17));
		article2.setDateFinEncheres(LocalDate.of(2020, 02, 18));
		article2.setMiseAPrix(100);
		article2.setVendeur(utilisateurDao.getById(0));
		article2.setCategorie(categorieDao.getById(0));
		article2.setLieuRetrait(retraitDao.getById(0));*/
		
		
		
		List<ArticleVendu> articles = articleVenduDao.getAll();
		articles.add(article1);
		articles.add(article2);
		
		
		articleVenduDao.insert(article1);
		articleVenduDao.insert(article2);
		
		List<ArticleVendu> allArticles = articleVenduDao.getAll();
		assertEquals(allArticles.size(), articles.size());
		
		
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
