package test.fr.eni.encheres.dal.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.EnchereDAO;
import fr.eni.encheres.dal.RetraitDAO;
import fr.eni.encheres.dal.UtilisateurDAO;
import fr.eni.encheres.dal.jdbc.ArticleVenduDAOJDBCImpl;
import fr.eni.encheres.dal.jdbc.CategorieDAOJDBCImpl;
import fr.eni.encheres.dal.jdbc.EnchereDAOJDBCImpl;
import fr.eni.encheres.dal.jdbc.RetraitDAOJDBCImpl;
import fr.eni.encheres.dal.jdbc.UtilisateurDAOJDBCImpl;

class EnchereDAOJDBCImplTest {

	private static ArticleVenduDAO articleVenduDao = new ArticleVenduDAOJDBCImpl();
	private static CategorieDAO categorieDao = new CategorieDAOJDBCImpl();
	private static UtilisateurDAO utilisateurDao = new UtilisateurDAOJDBCImpl();
	private static EnchereDAO enchereDao = new EnchereDAOJDBCImpl();
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testInsert() throws BusinessException {
		
		Utilisateur utilisateur = UtilisateurDAOJDBCImplTest.getTestListUtilisateurs().get(1);
		Categorie categorie = CategorieDAOJDBCImplTest.getTestListCategories().get(2);
		ArticleVendu article = ArticleVenduDAOJDBCImplTest.getTestArticleVendu(utilisateur, categorie);
		
		utilisateurDao.insert(utilisateur);
		categorieDao.insert(categorie);
		articleVenduDao.insert(article);
		
		Enchere enchere = getTestEnchere(utilisateur, categorie, article);
		
		enchere = enchereDao.insert(enchere);
		
		assertNotNull(enchere.getId());
	}

	/*@Test
	void testGetAll() throws BusinessException {
		List<Enchere> encheres = enchereDao.getAll();
		
		for (Enchere enchere : getTestListEncheres()) {
			encheres.add(enchere);
			enchereDao.insert(enchere);
		}
		
		List<Enchere> allEncheres = enchereDao.getAll();
		
		assertEquals(encheres.size(), allEncheres.size());
	}*/
	

	/*@Test
	void testGetById() throws BusinessException {
		
		Utilisateur utilisateur = UtilisateurDAOJDBCImplTest.getTestListUtilisateurs().get(0);
		Categorie categorie = CategorieDAOJDBCImplTest.getTestListCategories().get(3);
		ArticleVendu article = ArticleVenduDAOJDBCImplTest.getTestArticleVendu(utilisateur, categorie);
		
		utilisateurDao.insert(utilisateur);
		categorieDao.insert(categorie);
		articleVenduDao.insert(article);
	
		enchereDao.insert(getTestEnchere(utilisateur, categorie, article));
		
		List<Enchere> encheres = enchereDao.getAll();
		Enchere enchereAttendu = encheres.get(encheres.size()-1);
		Enchere enchereRecupere = enchereDao.getById(enchereAttendu.getId());
		
		assertEquals(enchereAttendu.getId(), enchereRecupere.getId());
	} */

	@Test
	void testGetByEncherisseur() {
		fail("Not yet implemented");
	}

	@Test
	void testGetRemportesParEncherisseur() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() throws BusinessException {
		Utilisateur utilisateur = UtilisateurDAOJDBCImplTest.getTestListUtilisateurs().get(0);
		Categorie categorie = CategorieDAOJDBCImplTest.getTestListCategories().get(3);
		ArticleVendu article = ArticleVenduDAOJDBCImplTest.getTestArticleVendu(utilisateur, categorie);
		
		utilisateurDao.insert(utilisateur);
		categorieDao.insert(categorie);
		articleVenduDao.insert(article);
		
		enchereDao.insert(getTestEnchere(utilisateur, categorie, article));
		
		List<Enchere> encheres = enchereDao.getAll();
		
		encheres.remove(encheres.size()-1);
		
		enchereDao.delete(encheres.size()-1);
		
		List<Enchere> newEncheres = enchereDao.getAll();
		
		assertEquals(encheres.size(), newEncheres.size());
	}

	public static Enchere getTestEnchere(Utilisateur utilisateur, Categorie categorie, ArticleVendu article) throws BusinessException
	{		
		Enchere enchere = new Enchere();
		enchere.setDate(LocalDate.now());
		enchere.setMontant(article.getMiseAPrix()+120);
		enchere.setArticle(article);
		enchere.setEncherisseur(utilisateur);
		enchere.setRemporte(true);
		
		return enchere;
		
	}
	
	public static List<Enchere> getTestListEncheres() throws BusinessException
	{
		List<Enchere> encheres = new ArrayList<>();
		
		Utilisateur utilisateur1 = UtilisateurDAOJDBCImplTest.getTestListUtilisateurs().get(0);
		Categorie categorie1 = CategorieDAOJDBCImplTest.getTestListCategories().get(1);
		ArticleVendu article1 = ArticleVenduDAOJDBCImplTest.getTestArticleVendu(utilisateur1, categorie1);
		
		utilisateurDao.insert(utilisateur1);
		categorieDao.insert(categorie1);
		articleVenduDao.insert(article1);
		
		Enchere enchere1 = new Enchere();
		enchere1.setDate(LocalDate.now().plusDays(5));
		enchere1.setMontant(article1.getMiseAPrix()+20);
		enchere1.setArticle(article1);
		enchere1.setEncherisseur(utilisateur1);
		enchere1.setRemporte(false);
		
		Utilisateur utilisateur2 = UtilisateurDAOJDBCImplTest.getTestListUtilisateurs().get(1);
		Categorie categorie2 = CategorieDAOJDBCImplTest.getTestListCategories().get(0);
		ArticleVendu article2 = ArticleVenduDAOJDBCImplTest.getTestArticleVendu(utilisateur2, categorie2);
		
		utilisateurDao.insert(utilisateur2);
		categorieDao.insert(categorie2);
		articleVenduDao.insert(article2);
		
		Enchere enchere2 = new Enchere();
		enchere2.setDate(LocalDate.now().plusDays(10));
		enchere2.setMontant(article2.getMiseAPrix()+35);
		enchere2.setArticle(article2);
		enchere2.setEncherisseur(utilisateur2);
		enchere2.setRemporte(false);
		
		Utilisateur utilisateur3 = UtilisateurDAOJDBCImplTest.getTestListUtilisateurs().get(2);
		Categorie categorie3 = CategorieDAOJDBCImplTest.getTestListCategories().get(3);
		ArticleVendu article3 = ArticleVenduDAOJDBCImplTest.getTestArticleVendu(utilisateur3, categorie3);
		
		utilisateurDao.insert(utilisateur3);
		categorieDao.insert(categorie3);
		articleVenduDao.insert(article3);
		
		Enchere enchere3 = new Enchere();
		enchere3.setDate(LocalDate.now().plusDays(3));
		enchere3.setMontant(article3.getMiseAPrix()+250);
		enchere3.setArticle(article3);
		enchere3.setEncherisseur(utilisateur3);
		enchere3.setRemporte(true);
		
		return encheres;
		
	}
	
	
}
