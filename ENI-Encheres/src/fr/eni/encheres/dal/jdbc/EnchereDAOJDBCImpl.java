package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.CodesResultatDAL;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.EnchereDAO;
import fr.eni.encheres.dal.UtilisateurDAO;
import fr.eni.encheres.dal.Utils;

public class EnchereDAOJDBCImpl implements EnchereDAO{
	private static final String INSERT = "INSERT INTO ENCHERES VALUES(?,?,?,?)";
	private static final String GET_ALL = "SELECT * FROM ENCHERES";
	private static final String GET_BY_ID = "SELECT * FROM ENCHERES WHERE no_enchere=?";
	private static final String GET_BY_ENCHERISSEUR = "SELECT * FROM ENCHERES WHERE no_utilisateur=?";
	private static final String GET_REMPORTES_PAR_ENCHERISSEUR = "SELECT * FROM ENCHERES WHERE no_utilisateur=? AND remporte=?";
	private static final String UPDATE = "UPDATE ENCHERES SET date_enchere=?, montant_enchere=?,"  +
											"no_article=?, no_utilisateur=?";
	private static final String DELETE = "DELETE ENCHERES WHERE no_enchere=?";
	
	private static ArticleVenduDAO articleDao = new ArticleVenduDAOJDBCImpl();
	private static UtilisateurDAO utilisateurDAO = new UtilisateurDAOJDBCImpl();
	
	@Override
	public void insert(Enchere enchere) throws BusinessException {
		
		if (enchere == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		try (Connection cnx = Utils.getConnection()) {
            PreparedStatement requete = cnx.prepareStatement(INSERT);
            requete.setDate(1, Date.valueOf(enchere.getDate()));
            requete.setInt(2, enchere.getMontant());
            requete.setInt(3, enchere.getArticle().getId());
            requete.setInt(4, enchere.getEncherisseur().getId());
            requete.setBoolean(5, enchere.isRemporte());
           
            requete.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
	}

	@Override
	public List<Enchere> getAll() throws BusinessException {
		
		List<Enchere> list = new ArrayList<>();
		
		try (Connection cnx = Utils.getConnection()) {
			 PreparedStatement requete = cnx.prepareStatement(GET_ALL);
			 ResultSet rs = requete.executeQuery();
			 
			while (rs.next()) {
				Enchere enchere = new Enchere();
				enchere.setId(rs.getInt("no_enchere"));
				enchere.setDate(rs.getDate("date_enchere").toLocalDate());
				enchere.setMontant(rs.getInt("montant_enchere"));
				enchere.setArticle(articleDao.getById(rs.getInt("no_article")));
				enchere.setEncherisseur(utilisateurDAO.getById(rs.getInt("no_utilisateur")));
				enchere.setRemporte(rs.getBoolean("remporte"));
				
				list.add(enchere);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ENCHERES_ECHEC);
			throw businessException;

		}
		return list;
	}

	@Override
	public Enchere getById(int id) throws BusinessException {
		
		Enchere enchere = null;
		
		try (Connection cnx = Utils.getConnection()) {
			PreparedStatement requete = cnx.prepareStatement(GET_BY_ID);
			requete.setInt(1, id);
			ResultSet rs = requete.executeQuery();
			
			if(rs.next()) 
			{
				enchere = new Enchere();
				enchere.setId(rs.getInt("no_enchere"));
				enchere.setDate(rs.getDate("date_enchere").toLocalDate());
				enchere.setMontant(rs.getInt("montant_enchere"));
				enchere.setArticle(articleDao.getById(rs.getInt("no_article")));
				enchere.setEncherisseur(utilisateurDAO.getById(rs.getInt("no_utilisateur")));
				enchere.setRemporte(rs.getBoolean("remporte"));
				
			}
						
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ENCHERES_ECHEC);
			throw businessException;

		}
				
		return enchere;
	}

	@Override
	public List<Enchere> getByEncherisseur(int id) throws BusinessException {
		
		List<Enchere> list = new ArrayList<>();
		
		try (Connection cnx = Utils.getConnection()) {
			 PreparedStatement requete = cnx.prepareStatement(GET_BY_ENCHERISSEUR);
			 requete.setInt(1, id);
			 ResultSet rs = requete.executeQuery();
			 
			while (rs.next()) {
				Enchere enchere = new Enchere();
				enchere.setId(rs.getInt("no_enchere"));
				enchere.setDate(rs.getDate("date_enchere").toLocalDate());
				enchere.setMontant(rs.getInt("montant_enchere"));
				enchere.setArticle(articleDao.getById(rs.getInt("no_article")));
				enchere.setEncherisseur(utilisateurDAO.getById(rs.getInt("no_utilisateur")));
				enchere.setRemporte(rs.getBoolean("remporte"));
				
				list.add(enchere);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ENCHERES_ECHEC);
			throw businessException;

		}
		return list;
	}
	
	@Override
	public List<Enchere> getRemportesParEncherisseur(int id) throws BusinessException {
		
		List<Enchere> list = new ArrayList<>();
		
		try (Connection cnx = Utils.getConnection()) {
			 PreparedStatement requete = cnx.prepareStatement(GET_REMPORTES_PAR_ENCHERISSEUR);
			 requete.setInt(1, id);
			 requete.setBoolean(2, true);
			 ResultSet rs = requete.executeQuery();
			 
			while (rs.next()) {
				Enchere enchere = new Enchere();
				enchere.setId(rs.getInt("no_enchere"));
				enchere.setDate(rs.getDate("date_enchere").toLocalDate());
				enchere.setMontant(rs.getInt("montant_enchere"));
				enchere.setArticle(articleDao.getById(rs.getInt("no_article")));
				enchere.setEncherisseur(utilisateurDAO.getById(rs.getInt("no_utilisateur")));
				enchere.setRemporte(rs.getBoolean("remporte"));
				
				list.add(enchere);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ENCHERES_ECHEC);
			throw businessException;
		}
		return list;
	}
	
	@Override
	public void update(Enchere enchere) throws BusinessException {
		
		try (Connection cnx = Utils.getConnection()) {
			PreparedStatement requete = cnx.prepareStatement(UPDATE);
			requete.setDate(1, Date.valueOf(enchere.getDate()));
			requete.setInt(2, enchere.getMontant());
            requete.setInt(3, enchere.getArticle().getId());
            requete.setInt(4, enchere.getEncherisseur().getId());
            requete.setBoolean(5, enchere.isRemporte());
            
            requete.executeUpdate();
           
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_ECHEC);
			throw businessException;

		}
		
	}

	@Override
	public void delete(int id) throws BusinessException {
		try (Connection cnx = Utils.getConnection()) {
			PreparedStatement requete = cnx.prepareStatement(DELETE);
            requete.setInt(1, id);
            requete.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_ECHEC);
			throw businessException;

		}
	}

	
	

}
