package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.EnchereDAO;
import fr.eni.encheres.dal.UtilisateurDAO;

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
	public void insert(Enchere enchere) {
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement requete = cnx.prepareStatement(INSERT);
            requete.setDate(1, Date.valueOf(enchere.getDate()));
            requete.setInt(2, enchere.getMontant());
            requete.setInt(3, enchere.getArticle().getId());
            requete.setInt(4, enchere.getEncherisseur().getId());
            requete.setBoolean(5, enchere.isRemporte());
           
            requete.executeUpdate();

        } catch (SQLException e) {
           System.out.println(e.getMessage());
        }
	}

	@Override
	public List<Enchere> getAll() {
		
		List<Enchere> list = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
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
			System.out.println(e.getMessage());
		}
		return list;
	}

	@Override
	public Enchere getById(int id) {
		
		Enchere enchere = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
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
			System.out.println(e.getMessage());
		}
				
		return enchere;
	}

	@Override
	public List<Enchere> getByEncherisseur() {
		
		List<Enchere> list = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 PreparedStatement requete = cnx.prepareStatement(GET_BY_ENCHERISSEUR);
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
			System.out.println(e.getMessage());
		}
		return list;
	}
	
	@Override
	public List<Enchere> getRemportesParEncherisseur() {
		
		List<Enchere> list = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 PreparedStatement requete = cnx.prepareStatement(GET_REMPORTES_PAR_ENCHERISSEUR);
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
			System.out.println(e.getMessage());
		}
		return list;
	}

	
	@Override
	public void update(Enchere enchere) {
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement requete = cnx.prepareStatement(UPDATE);
			requete.setDate(1, Date.valueOf(enchere.getDate()));
			requete.setInt(2, enchere.getMontant());
            requete.setInt(3, enchere.getArticle().getId());
            requete.setInt(4, enchere.getEncherisseur().getId());
            requete.setBoolean(5, enchere.isRemporte());
            
            requete.executeUpdate();
           
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void delete(int id) {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement requete = cnx.prepareStatement(DELETE);
            requete.setInt(1, id);
            requete.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}

	
	

}
