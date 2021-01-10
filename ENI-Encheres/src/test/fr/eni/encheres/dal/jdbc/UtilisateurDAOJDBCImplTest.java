/**
 * 
 */
package test.fr.eni.encheres.dal.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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
	 * @throws BusinessException 
	 */
	@Test
	void testInsert() throws BusinessException {
		
		Utilisateur user = utilisateurDao.insert(getTestUtilisateur());
		
		assertNotNull(user.getId());
		
	}

	/**
	 * Test method for {@link fr.eni.encheres.dal.jdbc.UtilisateurDAOJDBCImpl#getAll()}.
	 * @throws BusinessException 
	 */
	@Test
	void testGetAll() throws BusinessException {
		
		List<Utilisateur> utilisateurs = utilisateurDao.getAll();
		
		for (Utilisateur utilisateur : getTestListUtilisateurs()) {
			utilisateurs.add(utilisateur);
			utilisateurDao.insert(utilisateur);
		}
			
		List<Utilisateur> allUsers = utilisateurDao.getAll();
		
		assertEquals(allUsers.size(), utilisateurs.size());
	
	}

	/**
	 * Test method for {@link fr.eni.encheres.dal.jdbc.UtilisateurDAOJDBCImpl#getById(int)}.
	 * @throws BusinessException 
	 */
	@Test
	void testGetById() throws BusinessException {
	
		utilisateurDao.insert(getTestUtilisateur());
		
		List<Utilisateur> users = utilisateurDao.getAll();
		Utilisateur userAttendu = users.get(users.size()-1);
		Utilisateur userRecupere = utilisateurDao.getById(userAttendu.getId());
		
		assertEquals(userAttendu.getId(), userRecupere.getId());
			
	}

	/**
	 * Test method for {@link fr.eni.encheres.dal.jdbc.UtilisateurDAOJDBCImpl#update(fr.eni.encheres.bo.Utilisateur)}.
	 * @throws BusinessException 
	 */
	@Test
	void testUpdate() throws BusinessException {
		
		utilisateurDao.insert(getTestUtilisateur());
		
		List<Utilisateur> users = utilisateurDao.getAll();
		
		Utilisateur userAUpdate = users.get(users.size()-1);
		
		userAUpdate.setPrenom("Yana");
		
		utilisateurDao.update(userAUpdate);
		
		Utilisateur userUpdated = utilisateurDao.getById(userAUpdate.getId());
		
		assertEquals(userUpdated.getPrenom(), "Yana");
	}

	/**
	 * Test method for {@link fr.eni.encheres.dal.jdbc.UtilisateurDAOJDBCImpl#delete(int)}.
	 * @throws BusinessException 
	 */
	@Test
	void testDelete() throws BusinessException {
		
		utilisateurDao.insert(getTestUtilisateur());
		
		List<Utilisateur> users = utilisateurDao.getAll();
		
		users.remove(users.size()-1);
		
		utilisateurDao.delete(users.size()-1);
		
		List<Utilisateur> newUsers = utilisateurDao.getAll();
		
		assertEquals(users.size(), newUsers.size());
	}
	
	
	public static Utilisateur getTestUtilisateur() 
	{
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setPseudo("beatle123");
		utilisateur.setNom("McCartney");
		utilisateur.setPrenom("Pault");
		utilisateur.setEmail("beatle@gmail.com");
		utilisateur.setTelephone("06 12 34 56 78");
		utilisateur.setRue("47 rue Penny Lane");
		utilisateur.setCodePostal("L18");
		utilisateur.setVille("Liverpool");
		utilisateur.setPassword("xxxxxx");
		utilisateur.setCredit(500);
		utilisateur.setAdministrateur(false);
		
		return utilisateur;
	}
	
	public static List<Utilisateur> getTestListUtilisateurs()
	{
		List<Utilisateur> users = new ArrayList<>();
		
		Utilisateur user1 = new Utilisateur();
		user1.setPseudo("sassou");
		user1.setNom("Maerten");
		user1.setPrenom("sarah");
		user1.setEmail("sassoul@gmail.com");
		user1.setRue("67 rue du moulin");
		user1.setCodePostal("44567");
		user1.setVille("Orleans");
		user1.setPassword("druv");
		users.add(user1);
		
		Utilisateur user2 = new Utilisateur();
		user2.setPseudo("doudou");
		user2.setNom("daerten");
		user2.setPrenom("darah");
		user2.setEmail("dassoul@gmail.com");
		user2.setRue("67 rue du doulin");
		user2.setCodePostal("44567");
		user2.setVille("drleans");
		user2.setPassword("druv");
		users.add(user2);
		
		
		Utilisateur user3 = new Utilisateur();
		user3.setPseudo("loulou");
		user3.setNom("laerten");
		user3.setPrenom("larah");
		user3.setEmail("lassoul@gmail.com");
		user3.setRue("67 rue du loulin");
		user3.setCodePostal("44567");
		user3.setVille("leans");
		user3.setPassword("lruv");
		users.add(user3);
		
		return users;
	}

}
