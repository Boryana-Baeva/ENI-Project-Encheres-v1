package test.fr.eni.encheres.dal.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.jdbc.CategorieDAOJDBCImpl;

class CategorieDAOJDBCImplTest {

	private static CategorieDAO categorieDao = new CategorieDAOJDBCImpl();
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testInsert() {
		try {
			categorieDao.insert(CategorieDAOJDBCImplTest.getTestCategorie());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
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
	
	public static Categorie getTestCategorie()
	{
		Categorie categorie = new Categorie();
		categorie.setLibelle("Périphériques");
		
		return categorie;
	}
	

}
