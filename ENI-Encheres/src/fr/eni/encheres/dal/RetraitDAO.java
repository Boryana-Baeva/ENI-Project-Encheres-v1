package fr.eni.encheres.dal;
import java.util.List;

import fr.eni.encheres.bo.Retrait;


public interface RetraitDAO {
	
public void insert (Retrait retrait);
public Retrait getById (int id);
public List<Retrait> getAll();
public void update(Retrait retrait);
public void delete(int id);

}
