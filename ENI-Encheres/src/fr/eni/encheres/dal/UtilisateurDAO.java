package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {
	
	public void insert(Utilisateur utilisateur);
	
	public List<Utilisateur> getAll();
	
	public Utilisateur getById(int id);
	
	public void update(Utilisateur utilisateur);
	
	public void delete(int id);
}
