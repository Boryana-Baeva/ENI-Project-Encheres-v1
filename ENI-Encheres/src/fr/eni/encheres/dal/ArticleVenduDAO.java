package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;

public interface ArticleVenduDAO {

	public void insert(ArticleVendu articleVendu);
	
	public ArticleVendu getById(int id);
	
	public List<ArticleVendu> getAll();
	
	public List<ArticleVendu> getByVendeur();
	
	public void update (ArticleVendu articleVendu);
	
	public void delete (int id);
}
