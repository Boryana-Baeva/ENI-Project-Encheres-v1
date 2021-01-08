package test.fr.eni.encheres.dal.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.RetraitDAO;
import fr.eni.encheres.dal.jdbc.ArticleVenduDAOJDBCImpl;
import fr.eni.encheres.dal.jdbc.RetraitDAOJDBCImpl;

class RetraitDAOJDBCImplTest {
	
	private static RetraitDAO retraitDAO = new RetraitDAOJDBCImpl();
	private static ArticleVenduDAO articleDao = new ArticleVenduDAOJDBCImpl();
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	Retrait testInsert(ArticleVendu article) {
		
		Retrait retrait = new Retrait();
		retrait.setArticle(article);
		retrait.setRue("8 Impasse des Colombes");
		retrait.setCodePostal("56789");
		retrait.setVille("Paris");
		return retrait;
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
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

}
