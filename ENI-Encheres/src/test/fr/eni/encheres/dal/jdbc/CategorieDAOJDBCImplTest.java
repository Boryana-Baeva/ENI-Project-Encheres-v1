package test.fr.eni.encheres.dal.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.AssertStatement;
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
	void testGetById() throws BusinessException {
		
		Categorie cat1 = new Categorie("Multimédia");
		Categorie cat2 = new Categorie("Textile");
		Categorie cat3 = new Categorie("Bijoux");
		
		categorieDao.insert(cat1);
		categorieDao.insert(cat2);
		categorieDao.insert(cat3);
		
		List<Categorie> categories = categorieDao.getAll();
		
		Categorie categorieAttendu = categories.get(categories.size()-1);
		
		Categorie categorieRecupere = categorieDao.getById(categorieAttendu.getId());
		
		assertEquals(categorieAttendu.getId(), categorieRecupere.getId());
		
	}

	@Test
	void testGetAll() throws BusinessException {
		
		Categorie cat4 = new Categorie("food");
		Categorie cat5 = new Categorie("sport");
		Categorie cat6 = new Categorie("téléphonie");
		
		List<Categorie> categories = categorieDao.getAll();
		categories.add(cat4);
		categories.add(cat5);
		categories.add(cat6);
		
		
		categorieDao.insert(cat4);
		categorieDao.insert(cat5);
		categorieDao.insert(cat6);
		
		List<Categorie> allCategories = categorieDao.getAll();
		assertEquals(allCategories.size(), categories.size());
	}

	/*@Test
	void testUpdate() throws BusinessException {
		/*Categorie cat7 = new Categorie("literie");
		cat7 = categorieDao.insert(cat7);
		
		cat7.setLibelle("cuisine");
		
		Categorie newCategorie = categorieDao.update(cat7);
		
		
		System.out.println(cat7.getLibelle());
		System.out.println(newCategorie.getLibelle());*/
		
		
		
		/*Categorie cat7 = new Categorie("literie");
		categorieDao.insert(cat7);
		
		List<Categorie> categories = categorieDao.getAll();
		
		Categorie categorieAModifier = categories.get(categories.size()-1);
		
		categorieAModifier.setLibelle("ghzi");
		
		categorieDao.update(categorieAModifier);
		
		Categorie categorieModifier = categorieDao.getById(categorieAModifier.getId());
		
		assertEquals(categorieModifier.getLibelle(), "cuisine");*/
		
		
	

	@Test
	void testDelete() throws BusinessException {
		
		List<Categorie> categories = categorieDao.getAll();
		
		categories.remove(categories.size()-1);
		
		categorieDao.delete(categories.size()-1);
		
		List<Categorie> newCategorie = categorieDao.getAll();
		
		assertEquals(categories.size(), newCategorie.size());
	}

	public static Categorie getTestCategorie() {
		Categorie categorie = new Categorie();
		categorie.setLibelle("Imprimantes");

		return categorie;
	}

}
