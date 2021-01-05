package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.UtilisateurDAO;


public class ArticleVenduDAOJDBCImpl implements ArticleVenduDAO {

	private static final String INSERT = "insert into ARTICLES_VENDUS VALUES (?,?,?,?,?,?,?,?)";
	private static final String GET_BY_ID = "select * from ARTICLES_VENDUS where no_article= ?";
	private static final String GET_ALL = "select * from ARTICLES_VENDUS";
	private static final String UPDATE = "update ARTICLES_VENDUS set nom_article = ?, description = ?,"
			+ "							 date_debut_encheres=?, date_fin_encheres= ?, prix_initial= ?, prix_vente= ?, "
			+ "							 no_utilisateur= ?, no_categorie=? where no_article= ? ";
	private static final String DELETE = "delete ARTICLES_VENDUS where id = ?";

	@Override
	public void insert(ArticleVendu articleVendu) {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, articleVendu.getNom());
			pstmt.setString(2, articleVendu.getDescription());
			pstmt.setDate(3, java.sql.Date.valueOf(articleVendu.getDateDebutEncheres()));
			pstmt.setDate(4, java.sql.Date.valueOf(articleVendu.getDateFinEncheres()));
			pstmt.setInt(5, articleVendu.getMiseAPrix());
			pstmt.setInt(6, articleVendu.getPrixVente());
			pstmt.setInt(7, articleVendu.getVendeur().getId());
			pstmt.setInt(8, articleVendu.getCategorie().getId());

			pstmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Override
	public ArticleVendu getById(int id) {
		
		ArticleVendu articleVendu = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(GET_BY_ID);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			
			UtilisateurDAO utilisateurDAO = new UtilisateurDAOJDBCImpl();
			CategorieDAO categorieDAO = new CategorieDAOJDBCImpl();

			if (rs.next()) {
				articleVendu = new ArticleVendu();
				articleVendu.setId(rs.getInt("no_article"));
				articleVendu.setNom(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateDebutEncheres((rs.getDate("date_debut_encheres").toLocalDate()));
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				articleVendu.setMiseAPrix(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				articleVendu.setVendeur(utilisateurDAO.getById(rs.getInt("no_utilisateur"))); 
				articleVendu.setCategorie(categorieDAO.getById(rs.getInt("no_categorie")));

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return articleVendu;
	}

	@Override
	public List<ArticleVendu> getAll() {

		List<ArticleVendu>  articlesVendus = new ArrayList<>();
		
		
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(GET_ALL);
			
			ResultSet rs = pstmt.executeQuery();
			
			UtilisateurDAO utilisateurDAO = new UtilisateurDAOJDBCImpl();
			CategorieDAO categorieDAO = new CategorieDAOJDBCImpl();
			
			while(rs.next()) {
				ArticleVendu articleVendu = new ArticleVendu();
				articleVendu.setId(rs.getInt("no_article"));
				articleVendu.setNom(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateDebutEncheres((rs.getDate("date_debut_encheres").toLocalDate()));
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				articleVendu.setMiseAPrix(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				articleVendu.setVendeur(utilisateurDAO.getById(rs.getInt("no_utilisateur"))); 
				articleVendu.setCategorie(categorieDAO.getById(rs.getInt("no_categorie")));
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return articlesVendus;
	}

	@Override
	public void update(ArticleVendu articleVendu) {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
			pstmt.setInt(1, articleVendu.getId());
			pstmt.setString(2, articleVendu.getNom());
			pstmt.setString(3, articleVendu.getDescription());
			pstmt.setDate(4, java.sql.Date.valueOf(articleVendu.getDateDebutEncheres()));
			pstmt.setDate(5, java.sql.Date.valueOf(articleVendu.getDateFinEncheres()));
			pstmt.setInt(6, articleVendu.getMiseAPrix());
			pstmt.setInt(7, articleVendu.getPrixVente());
			pstmt.setInt(8, articleVendu.getVendeur().getId());
			pstmt.setInt(9, articleVendu.getCategorie().getId());

			pstmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			
			pstmt.setInt(1, id);
			

			pstmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
