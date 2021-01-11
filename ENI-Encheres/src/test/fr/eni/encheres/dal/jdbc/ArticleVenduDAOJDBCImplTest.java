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
import fr.eni.encheres.bo.Retrait;
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
		
		Retrait lieuRetrait = RetraitDAOJDBCImplTest.getTestRetrait();
		try {
			retraitDao.insert(lieuRetrait);
		}catch(BusinessException e) {
			e.printStackTrace();
		}
		
		
		ArticleVendu articleVendu = ArticleVenduDAOJDBCImplTest.getTestArticleVendu(utilisateur, categorie, lieuRetrait);
		try {
			articleVenduDao.insert(articleVendu);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		assertNotNull(categorie.getId());
		assertNotNull(utilisateur.getId());
		assertNotNull(articleVendu.getId());
		assertNotNull(lieuRetrait.getId());
		
		
	}

	@Test
	void testGetById() throws BusinessException {
		Utilisateur user1 = new Utilisateur("sanzza","maerten","prenom","maerten#gmail.com","3648836279","44 rue maurice","44567","mauriceVille","udfgfgf",600,true);
		utilisateurDao.insert(user1);
		
		ArticleVendu  article1 = new ArticleVendu("macbookpro","c'est un ordinateur vraiment cool", LocalDate.of(2020, 01, 12), LocalDate.of(2020, 01, 18),200, user1, categorieDao.getById(3));
		ArticleVendu article2 = new ArticleVendu("tapis de course","c'est un tapis de course vraiment cool", LocalDate.of(2020, 01, 18), LocalDate.of(2020, 01, 23),300, user1, categorieDao.getById(4));
		
		
		List<ArticleVendu> articles = articleVenduDao.getAll();
		articles.add(article1);
		articles.add(article2);
		
		
		articleVenduDao.insert(article1);
		articleVenduDao.insert(article2);
		
		List<ArticleVendu> allArticles = articleVenduDao.getAll();
		assertEquals(allArticles.size(), articles.size());
		
		
	}
	
	@Test
	void testGetAll() throws BusinessException {
		
		Utilisateur user1 = new Utilisateur("sanzza","maerten","prenom","maerten#gmail.com","3648836279","44 rue maurice","44567","mauriceVille","udfgfgf",600,true);
		utilisateurDao.insert(user1);
		Categorie cat1 = new Categorie("multimedia");
		ArticleVendu article1 = new ArticleVendu("pc","c'est un ordinateur vraiment cool", LocalDate.of(2020, 01, 12), LocalDate.of(2020, 01, 18),200, user1, cat1);
		ArticleVendu article2 = new ArticleVendu("macbookpro","c'est un ordinateur vraiment cool", LocalDate.of(2020, 01, 12), LocalDate.of(2020, 01, 18),200, user1, cat1);
		ArticleVendu article3 = new ArticleVendu("imac","c'est un ordinateur vraiment cool", LocalDate.of(2020, 01, 12), LocalDate.of(2020, 01, 18),200, user1, cat1);
		
		List<ArticleVendu> articleVendus = articleVenduDao.getAll();
		articleVendus.add(article1);
		articleVendus.add(article2);
		articleVendus.add(article3);
		
		articleVenduDao.insert(article1);
		articleVenduDao.insert(article2);
		articleVenduDao.insert(article3);
		
		List<ArticleVendu> allArticles = articleVenduDao.getAll();
		
		assertEquals(allArticles.size(), articleVendus.size());
				
	}
	
	@Test
	void testGetByRetrait() throws BusinessException{
		
		Utilisateur user1 = new Utilisateur("sanzza","maerten","prenom","maerten#gmail.com","3648836279","44 rue maurice","44567","mauriceVille","udfgfgf",600,true);
		Categorie cat1 = new Categorie("multimedia");
		Retrait ret1 = new Retrait("34 avenue des Champs Elysee","75000","Paris");
		
		utilisateurDao.insert(user1);
		categorieDao.insert(cat1);
		retraitDao.insert(ret1);
		
		List<ArticleVendu> listeArticleVendus = articleVenduDao.getByRetrait(ret1);
		
		ArticleVendu newArticleVendu = articleVenduDao.insert(getTestArticleVendu(user1, cat1, ret1));
		
		listeArticleVendus.add(newArticleVendu);
		
		List<ArticleVendu> listAllArticleVendus = articleVenduDao.getByRetrait(ret1);
		
		assertEquals(listAllArticleVendus.size(), listeArticleVendus.size());
	}
	
	@Test
	void testGetByVendeur() throws BusinessException {
		
		Utilisateur user1 = new Utilisateur("sanzza","maerten","prenom","maerten#gmail.com","3648836279","44 rue maurice","44567","mauriceVille","udfgfgf",600,true);
		Categorie cat1 = new Categorie("multimedia");
		Retrait ret1 = new Retrait("34 avenue des Champs Elysee","75000","Paris");
		
		utilisateurDao.insert(user1);
		categorieDao.insert(cat1);
		retraitDao.insert(ret1);
	
		List<ArticleVendu> listeArticleVendu = articleVenduDao.getByVendeur(user1.getId());
		
		ArticleVendu newArticle = articleVenduDao.insert(getTestArticleVendu(user1, cat1, ret1));
		
		listeArticleVendu.add(newArticle);
		
		List<ArticleVendu>listeAllArticleVendus = articleVenduDao.getByVendeur(user1.getId());
		
		assertEquals(listeArticleVendu.size(), listeAllArticleVendus.size());
		
	
	}

	// 
	@Test
	void testUpdate() throws BusinessException {
		
		Utilisateur user1 = new Utilisateur("sanzza","maerten","prenom","maerten#gmail.com","3648836279","44 rue maurice","44567","mauriceVille","udfgfgf",600,true);
		Categorie cat1 = new Categorie("multimedia");
		Retrait ret1 = new Retrait("34 avenue des Champs Elysee","75000","Paris");
		
		articleVenduDao.insert(getTestArticleVendu(user1, cat1, ret1));
		
		List<ArticleVendu> articleVendus =articleVenduDao.getAll();
		
		ArticleVendu articleAUpdate = articleVendus.get(articleVendus.size()-1);
		
		articleAUpdate.setNom("sac a dos");
		
		articleVenduDao.update(articleAUpdate);
		
		ArticleVendu articleUpdate = articleVenduDao.getById(articleAUpdate.getId());
		
		assertEquals(articleUpdate.getNom(), "sac a dos");
		
		
	}

	@Test
	
	void testDelete() throws BusinessException {
		
		Utilisateur user1 = new Utilisateur("sanzza","maerten","prenom","maerten#gmail.com","3648836279","44 rue maurice","44567","mauriceVille","udfgfgf",600,true);
		Categorie cat1 = new Categorie("multimedia");
		Retrait ret1 = new Retrait("34 avenue des Champs Elysee","75000","Paris");
		
		utilisateurDao.insert(user1);
		categorieDao.insert(cat1);
		retraitDao.insert(ret1);
		
		articleVenduDao.insert(getTestArticleVendu(user1, cat1, ret1));
		
		List<ArticleVendu> articleVendus = articleVenduDao.getAll();
		
		articleVenduDao.delete(articleVendus.size()-1);
		
		articleVendus.remove(articleVendus.size()-1);
		
		List<ArticleVendu>newArticles = articleVenduDao.getAll();
		
		assertEquals(articleVendus.size(), newArticles.size());
		
	}
	
	public static ArticleVendu getTestArticleVendu(Utilisateur utilisateur, Categorie categorie , Retrait lieuRetrait)
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
		article.setLieuRetrait(lieuRetrait);
	
		return article;
	}

}
