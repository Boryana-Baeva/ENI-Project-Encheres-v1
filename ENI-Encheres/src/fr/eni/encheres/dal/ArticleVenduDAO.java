package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;

public interface ArticleVenduDAO {

	public void insert(ArticleVendu articleVendu) throws BusinessException;
	
	public ArticleVendu getById(int id) throws BusinessException;
	
	public List<ArticleVendu> getAll() throws BusinessException;
	
	public List<ArticleVendu> getByVendeur(int id) throws BusinessException;
	
	public void update (ArticleVendu articleVendu) throws BusinessException;
	
	public void delete (int id) throws BusinessException;
}
