/**
 * 
 */
package test.fr.eni.encheres.dal.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.UtilisateurDAO;
import fr.eni.encheres.dal.jdbc.ArticleVenduDAOJDBCImpl;
import fr.eni.encheres.dal.jdbc.CategorieDAOJDBCImpl;
import fr.eni.encheres.dal.jdbc.UtilisateurDAOJDBCImpl;


class UtilisateurDAOJDBCImplTest {

	private static ArticleVenduDAO articleVenduDao = new ArticleVenduDAOJDBCImpl();
	private static CategorieDAO categorieDao = new CategorieDAOJDBCImpl();
	private static UtilisateurDAO utilisateurDao = new UtilisateurDAOJDBCImpl();
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link fr.eni.encheres.dal.jdbc.UtilisateurDAOJDBCImpl#insert(fr.eni.encheres.bo.Utilisateur)}.
	 */
	@Test
	void testInsert() {
		try {
			utilisateurDao.insert(UtilisateurDAOJDBCImplTest.getTestUtilisateur());
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Test method for {@link fr.eni.encheres.dal.jdbc.UtilisateurDAOJDBCImpl#getAll()}.
	 */
	@Test
	void testGetAll() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link fr.eni.encheres.dal.jdbc.UtilisateurDAOJDBCImpl#getById(int)}.
	 */
	@Test
	void testGetById() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link fr.eni.encheres.dal.jdbc.UtilisateurDAOJDBCImpl#update(fr.eni.encheres.bo.Utilisateur)}.
	 */
	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link fr.eni.encheres.dal.jdbc.UtilisateurDAOJDBCImpl#delete(int)}.
	 */
	@Test
	void testDelete() {
		fail("Not yet implemented");
	}
	
	
	public static Utilisateur getTestUtilisateur() 
	{
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setPseudo("user123");
		utilisateur.setNom("Doe");
		utilisateur.setPrenom("John");
		utilisateur.setEmail("john.doe@gmail.com");
		utilisateur.setTelephone("06 12 34 56 78");
		utilisateur.setRue("47 rue Penny Lane");
		utilisateur.setCodePostal("L18");
		utilisateur.setVille("Liverpool");
		utilisateur.setPassword("xxxxxx");
		utilisateur.setCredit(500);
		utilisateur.setAdministrateur(false);
		
		return utilisateur;
	}

}
