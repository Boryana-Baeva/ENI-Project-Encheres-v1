package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Categorie;


public interface CategorieDAO {
	
	
	public void insert(Categorie categorie);
	
	public Categorie getById(int id);
	
	public List<Categorie> getAll();
	
	public void update (Categorie categorie);
	
	public void delete (int id);

}
