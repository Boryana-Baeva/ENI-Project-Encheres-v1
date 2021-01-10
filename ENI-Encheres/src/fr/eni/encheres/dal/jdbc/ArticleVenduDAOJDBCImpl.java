package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.CodesResultatDAL;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.RetraitDAO;
import fr.eni.encheres.dal.UtilisateurDAO;
import fr.eni.encheres.dal.Utils;

public class ArticleVenduDAOJDBCImpl implements ArticleVenduDAO {

	private static final String INSERT = "insert into ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres,prix_initial,no_utilisateur,no_categorie,no_retrait) VALUES (?,?,?,?,?,?,?,?)";
	private static final String GET_BY_ID = "select * from ARTICLES_VENDUS where no_article= ?";
	private static final String GET_ALL = "select * from ARTICLES_VENDUS";
	private static final String GET_BY_VENDEUR = "select * from ARTICLES_VENDUS where no_utilisateur= ?";
	private static final String UPDATE = "update ARTICLES_VENDUS set nom_article = ?, description = ?,"
			+ "							 date_debut_encheres=?, date_fin_encheres= ?, prix_initial= ?, prix_vente= ?, "
			+ "							 no_utilisateur= ?, no_categorie=?, no_retrait=? where no_article= ? ";
	private static final String DELETE = "delete ARTICLES_VENDUS where no_article = ?";

	private static UtilisateurDAO utilisateurDAO = new UtilisateurDAOJDBCImpl();
	private static CategorieDAO categorieDAO = new CategorieDAOJDBCImpl();
	private static RetraitDAO retraitDAO = new RetraitDAOJDBCImpl();

	@Override
	public void insert(ArticleVendu articleVendu) throws BusinessException {

		if (articleVendu == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try (Connection cnx = Utils.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, articleVendu.getNom());
			pstmt.setString(2, articleVendu.getDescription());
			pstmt.setDate(3, java.sql.Date.valueOf(articleVendu.getDateDebutEncheres()));
			pstmt.setDate(4, java.sql.Date.valueOf(articleVendu.getDateFinEncheres()));
			pstmt.setInt(5, articleVendu.getMiseAPrix());	
			pstmt.setInt(6, articleVendu.getVendeur().getId());
			pstmt.setInt(7, articleVendu.getCategorie().getId());
			retraitDAO.insert(articleVendu.getLieuRetrait());
			pstmt.setInt(8, articleVendu.getLieuRetrait().getId());

			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				articleVendu.setId(rs.getInt(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
		
	}

	@Override
	public ArticleVendu getById(int id) throws BusinessException {

		ArticleVendu articleVendu = null;

		try (Connection cnx = Utils.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(GET_BY_ID);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

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
				articleVendu.setLieuRetrait(retraitDAO.getById(rs.getInt("no_retrait")));

			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLES_ECHEC);
			throw businessException;

		}
		return articleVendu;
	}

	@Override
	public List<ArticleVendu> getAll() throws BusinessException {

		List<ArticleVendu> articlesVendus = new ArrayList<>();

		try (Connection cnx = Utils.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(GET_ALL);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
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
				articleVendu.setLieuRetrait(retraitDAO.getById(rs.getInt("no_retrait")));
			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLES_ECHEC);
			throw businessException;

		}
		return articlesVendus;
	}

	@Override
	public List<ArticleVendu> getByVendeur(int id) throws BusinessException {

		List<ArticleVendu> articlesVendus = new ArrayList<>();

		try (Connection cnx = Utils.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(GET_BY_VENDEUR);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
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
				articleVendu.setLieuRetrait(retraitDAO.getById(rs.getInt("no_retrait")));
			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLES_ECHEC);
			throw businessException;

		}
		return articlesVendus;
	}

	@Override
	public void update(ArticleVendu articleVendu) throws BusinessException {
		try (Connection cnx = Utils.getConnection()) {

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
			pstmt.setInt(10, articleVendu.getLieuRetrait().getId());

			pstmt.executeUpdate();

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

			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			pstmt.setInt(1, id);
			
			//::::Gestion des dépendances::::
			// Supprimer l'article de la liste des articles vendus du vendeur
			ArticleVendu article = this.getById(id);
			Utilisateur vendeur = article.getVendeur();
			
			vendeur.getArticlesVendus().remove(article);
			utilisateurDAO.update(vendeur);
			article.setVendeur(null);
			
			// Unset de la categorie de l'article
			article.setCategorie(null);
			
			// Supprimer le lieu de retrait défini pour cet article
			article.setLieuRetrait(null);
			retraitDAO.delete(article.getLieuRetrait().getId());
			
			// Supprimer l'article
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_ECHEC);
			throw businessException;

		}
	}



}
