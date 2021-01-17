package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.CodesResultatDAL;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DAOFactory;
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
	private static final String GET_BY_RETRAIT = "select * from ARTICLES_VENDUS where no_retrait=?";

	private static UtilisateurDAO utilisateurDAO = new UtilisateurDAOJDBCImpl();
	private static CategorieDAO categorieDAO = new CategorieDAOJDBCImpl();
	private static RetraitDAO retraitDAO = new RetraitDAOJDBCImpl();

	@Override
	public ArticleVendu insert(ArticleVendu articleVendu) throws BusinessException {

		if (articleVendu == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement statement = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, articleVendu.getNom());
			statement.setString(2, articleVendu.getDescription());
			statement.setDate(3, java.sql.Date.valueOf(articleVendu.getDateDebutEncheres()));
			statement.setDate(4, java.sql.Date.valueOf(articleVendu.getDateFinEncheres()));
			statement.setInt(5, articleVendu.getMiseAPrix());
			statement.setInt(6, articleVendu.getVendeur().getId());
			statement.setInt(7, articleVendu.getCategorie().getId());
			statement.setInt(8, articleVendu.getLieuRetrait().getId());

			statement.executeUpdate();

			ResultSet rs = statement.getGeneratedKeys();

			if (rs.next()) {
				articleVendu.setId(rs.getInt(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
		return articleVendu;

	}

	@Override
	public ArticleVendu getById(int id) throws BusinessException {

		ArticleVendu articleVendu = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(GET_BY_ID);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				articleVendu = articleBuilder(rs);

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

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement statement = cnx.prepareStatement(GET_ALL);

			ResultSet rs = statement.executeQuery();

			ArticleVendu articleVendu = null;

			while (rs.next()) {
				articleVendu = articleBuilder(rs);
				articlesVendus.add(articleVendu);
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

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(GET_BY_VENDEUR);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			ArticleVendu articleVendu = null;

			while (rs.next()) {
				articleVendu = articleBuilder(rs);
				articlesVendus.add(articleVendu);

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

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement statement = cnx.prepareStatement(UPDATE);

			statement.setString(1, articleVendu.getNom());
			statement.setString(2, articleVendu.getDescription());
			statement.setDate(3, java.sql.Date.valueOf(articleVendu.getDateDebutEncheres()));
			statement.setDate(4, java.sql.Date.valueOf(articleVendu.getDateFinEncheres()));
			statement.setInt(5, articleVendu.getMiseAPrix());
			statement.setInt(6, articleVendu.getPrixVente());
			statement.setInt(7, articleVendu.getVendeur().getId());
			statement.setInt(8, articleVendu.getCategorie().getId());
			if (articleVendu.getLieuRetrait() != null) {
				statement.setInt(9, articleVendu.getLieuRetrait().getId());
			} else {
				statement.setNull(9, Types.INTEGER);
			}
			statement.setInt(10, articleVendu.getId());

			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_ECHEC);
			throw businessException;

		}
	}

	@Override
	public void delete(int id) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			pstmt.setInt(1, id);

			// Gestion des dépendances
			ArticleVendu article = this.getById(id);
			article.setVendeur(null);
 			article.setCategorie(null);
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

	@Override
	public List<ArticleVendu> getByRetrait(Retrait retrait) throws BusinessException {

		List<ArticleVendu> listeArticleVendu = new ArrayList<ArticleVendu>();

		try (Connection cnx = ConnectionProvider.getConnection()) {

			ArticleVendu articleVendu = null;

			PreparedStatement statement = cnx.prepareStatement(GET_BY_RETRAIT);
			statement.setInt(1, retrait.getId());

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				articleVendu = articleBuilder(rs);
				listeArticleVendu.add(articleVendu);
			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_RETRAIT_ECHEC);
			throw businessException;

		}

		return listeArticleVendu;

	}

	public ArticleVendu articleBuilder(ResultSet rs) throws BusinessException, SQLException {

		Utilisateur vendeur = this.getVendeurArticle(rs.getInt("no_utilisateur"));
		Categorie categorie = this.getCategorieArticle(rs.getInt("no_categorie"));
		Retrait retrait = this.getRetraitArticle(rs.getInt("no_retrait"));

		ArticleVendu articleVendu = new ArticleVendu();

		articleVendu.setId(rs.getInt("no_article"));
		articleVendu.setNom(rs.getString("nom_article"));
		articleVendu.setDescription(rs.getString("description"));
		articleVendu.setDateDebutEncheres((rs.getDate("date_debut_encheres").toLocalDate()));
		articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
		articleVendu.setMiseAPrix(rs.getInt("prix_initial"));
		articleVendu.setPrixVente(rs.getInt("prix_vente"));
		articleVendu.setVendeur(vendeur);
		articleVendu.setCategorie(categorie);
		articleVendu.setLieuRetrait(retrait);

		return articleVendu;

	}

	private Utilisateur getVendeurArticle(int vendeurId) {
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		Utilisateur vendeurArticle = null;
		try {
			vendeurArticle = utilisateurDAO.getById(vendeurId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vendeurArticle;
	}

	private Categorie getCategorieArticle(int categorieId) {
		CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
		Categorie categorieArticle = null;
		try {
			categorieArticle = categorieDAO.getById(categorieId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categorieArticle;
	}

	private Retrait getRetraitArticle(int retraitId) {
		RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();
		Retrait retraitArticle = null;
		try {
			retraitArticle = retraitDAO.getById(retraitId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retraitArticle;
	}

}
