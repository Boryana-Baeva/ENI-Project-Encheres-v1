package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Categorie;


public interface CategorieDAO {
	
	
	public Categorie insert(Categorie categorie) throws BusinessException;
	
	public Categorie getById(int id) throws BusinessException;
	
	public List<Categorie> getAll() throws BusinessException;
	
	public void update (Categorie categorie) throws BusinessException;
	
	public void delete (int id) throws BusinessException;


}
