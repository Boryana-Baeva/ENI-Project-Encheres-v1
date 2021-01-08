/**
 * 
 */
package test.fr.eni.encheres.dal.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

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
	 * @throws BusinessException 
	 */
	@Test
	void testGetAll() throws BusinessException {
		
		Utilisateur user1 = new Utilisateur();
		user1.setPseudo("sassou");
		user1.setNom("Maerten");
		user1.setPrenom("sarah");
		user1.setEmail("sassoul@gmail.com");
		user1.setRue("67 rue du moulin");
		user1.setCodePostal("44567");
		user1.setVille("Orleans");
		user1.setPassword("druv");
		
		Utilisateur user2 = new Utilisateur();
		user2.setPseudo("doudou");
		user2.setNom("daerten");
		user2.setPrenom("darah");
		user2.setEmail("dassoul@gmail.com");
		user2.setRue("67 rue du doulin");
		user2.setCodePostal("44567");
		user2.setVille("drleans");
		user2.setPassword("druv");
		
		Utilisateur user3 = new Utilisateur();
		user3.setPseudo("loulou");
		user3.setNom("laerten");
		user3.setPrenom("larah");
		user3.setEmail("lassoul@gmail.com");
		user3.setRue("67 rue du loulin");
		user3.setCodePostal("44567");
		user3.setVille("leans");
		user3.setPassword("lruv");
		
		List<Utilisateur> utilisateurs = utilisateurDao.getAll();
		utilisateurs.add(user1);
		utilisateurs.add(user2);
		utilisateurs.add(user3);
		
		utilisateurDao.insert(user1);
		utilisateurDao.insert(user2);
		utilisateurDao.insert(user3);
		
		List<Utilisateur> allUsers = utilisateurDao.getAll();
		assertEquals(allUsers.size(), utilisateurs.size());
		
		
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
