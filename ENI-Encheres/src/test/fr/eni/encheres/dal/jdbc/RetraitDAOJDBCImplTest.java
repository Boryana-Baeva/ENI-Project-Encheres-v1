package test.fr.eni.encheres.dal.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.RetraitDAO;
import fr.eni.encheres.dal.jdbc.ArticleVenduDAOJDBCImpl;
import fr.eni.encheres.dal.jdbc.RetraitDAOJDBCImpl;

class RetraitDAOJDBCImplTest {
	//pour commit
	
	private static RetraitDAO retraitDAO = new RetraitDAOJDBCImpl();
	private static ArticleVenduDAO articleDao = new ArticleVenduDAOJDBCImpl();
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		

	}

	@Test
	void  testInsert() throws BusinessException {
		
		Retrait retrait = retraitDAO.insert(getTestRetrait());
			
		assertNotNull(retrait.getId());
		
	}


	@Test
	void testGetById() throws BusinessException {
		
		retraitDAO.insert(getTestRetrait());
		
		List<Retrait> retraits = retraitDAO.getAll();
		Retrait retraitAttendu = retraits.get(retraits.size()-1);
		Retrait retraitRecupere = retraitDAO.getById(retraitAttendu.getId());
		
		assertEquals(retraitAttendu.getId(), retraitRecupere.getId());
		
	}

	@Test
	void testGetAll() throws BusinessException {
		
		List<Retrait> retraits = retraitDAO.getAll();
		
		for (Retrait retrait : getTestListRetraits()) {
			retraits.add(retrait);
			retraitDAO.insert(retrait);
		}
			
		List<Retrait> allRetraits = retraitDAO.getAll();
		
		assertEquals(allRetraits.size(), retraits.size());
		
		
	}
	
	
//Échec de la conversion de la valeur nvarchar 'Paris' en type de données int.
	@Test
	void testUpdate() throws BusinessException {
	
		retraitDAO.insert(getTestRetrait());
	
		List<Retrait> retraits = retraitDAO.getAll();
		
		Retrait retraitAUpdate = retraits.get(retraits.size()-1);
		
		retraitAUpdate.setCodePostal("45678");
		
		retraitDAO.update(retraitAUpdate);
		
		Retrait retraitUpdate = retraitDAO.getById(retraitAUpdate.getId());
		
		assertEquals(retraitUpdate.getCodePostal(), "45678");
	}

	@Test
	void testDelete() throws BusinessException {
		
		retraitDAO.insert(getTestRetrait());
		
		List<Retrait> retraits = retraitDAO.getAll();
		
		retraits.remove(retraits.size()-1);
		
		retraitDAO.delete(retraits.size()-1);
		
		List<Retrait> newRetraits = retraitDAO.getAll();
		
		assertEquals(retraits.size(), newRetraits.size());
	}
	
	
	public static Retrait getTestRetrait()
	{
		Retrait retrait = new Retrait();
		retrait.setRue("8 Impasse des Colombes");
		retrait.setCodePostal("56789");
		retrait.setVille("Paris");
		
		return retrait;
	}

	public static List<Retrait> getTestListRetraits()
	{
		List<Retrait> retraits = new ArrayList<>();
		
		retraits.add(new Retrait("57 rue general leclerc","56789","Paris"));
		retraits.add(new Retrait("68 rue marechal juin", "34567", "Bordeaux"));
		retraits.add(new Retrait("76 avenue montmartre","28100","Dreux"));
		
		return retraits;
		
	}
}
