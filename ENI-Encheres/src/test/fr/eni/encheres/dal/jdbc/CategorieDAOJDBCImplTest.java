package test.fr.eni.encheres.dal.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

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
	void testInsert() throws BusinessException {

		categorieDao.insert(getTestCategorie());
		
	}

	@Test
	void testGetById() throws BusinessException {
		
		List<Categorie> categories = categorieDao.getAll();
		
		for (Categorie categorie : getTestListCategories()) {
			categories.add(categorie);
			categorieDao.insert(categorie);
		}
		
		Categorie categorieAttendu = categories.get(categories.size()-1);
		
		Categorie categorieRecupere = categorieDao.getById(categorieAttendu.getId());
		
		assertEquals(categorieAttendu.getId(), categorieRecupere.getId());
		
	}

	@Test
	void testGetAll() throws BusinessException {
			
		List<Categorie> categories = categorieDao.getAll();
		
		for (Categorie categorie : getTestListCategories()) {
			categories.add(categorie);
			categorieDao.insert(categorie);
		}
			
		List<Categorie> allCategories = categorieDao.getAll();
		assertEquals(allCategories.size(), categories.size());
	}

	@Test
	void testUpdate() throws BusinessException {
		
		categorieDao.insert(getTestCategorie());
		
		List<Categorie> categories = categorieDao.getAll();
		
		Categorie categorieAModifier = categories.get(categories.size()-1);
		
		categorieAModifier.setLibelle("cuisine");
		
		categorieDao.update(categorieAModifier);
		
		Categorie categorieModifier = categorieDao.getById(categorieAModifier.getId());
		
		assertEquals(categorieModifier.getLibelle(), "cuisine");
	}
		

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

	public static List<Categorie> getTestListCategories()
	{
		List<Categorie> categories = new ArrayList<>();
		
		categories.add(new Categorie("Multimedia"));
		categories.add(new Categorie("Textile"));
		categories.add(new Categorie("Bijoux"));
		categories.add(new Categorie("food"));
		categories.add(new Categorie("sport"));
		categories.add(new Categorie("t�l�phonie"));
		
		return categories;
		
	}
	
}
