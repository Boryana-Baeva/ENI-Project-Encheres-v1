package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO {
	
	public void insert(Enchere enchere);
	
	public List<Enchere> getAll();
	
	public Enchere getById(int id);
	
	public List<Enchere> getByEncherisseur();
	
	public List<Enchere> getRemportesParEncherisseur();
	
	public void update(Enchere enchere);
	
	public void delete(int id);
}
