package test.fr.eni.encheres.bll;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.ArticleVendu;

class ArticleVenduManagerTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testArticleVenduManager() {
		fail("Not yet implemented");
	}

	@Test
	void testNouvelleVente() throws BusinessException {
		ArticleVendu  articleVendu = new ArticleVendu();
		articleVendu.setNom("ordinateur");
		articleVendu.setDescription("c'est un ordinateur vraiment cool");
		articleVendu.setDateDebutEncheres(LocalDate.of(2020, 01, 12));
		articleVendu.setDateFinEncheres(LocalDate.of(2020, 01, 18));
		articleVendu.setMiseAPrix(20);
		articleVendu.setVendeur(UtilisateurManager.selectUserById(1));
		articleVendu.setCategorie(CategorieManager.selectionnerCategorieById(1));
		ArticleVenduManager.nouvelleVente(articleVendu);
	}

	@Test
	void testModifierArticlesVendus() {
		fail("Not yet implemented");
	}

	@Test
	void testSelectAllArticles() {
		fail("Not yet implemented");
	}

	@Test
	void testSelectArticleById() {
		fail("Not yet implemented");
	}

	@Test
	void testSelectArticlesByVendeur() {
		fail("Not yet implemented");
	}

	@Test
	void testSupprimerArticlesVendus() {
		fail("Not yet implemented");
	}

	@Test
	void testAnnulerVente() {
		fail("Not yet implemented");
	}

}
