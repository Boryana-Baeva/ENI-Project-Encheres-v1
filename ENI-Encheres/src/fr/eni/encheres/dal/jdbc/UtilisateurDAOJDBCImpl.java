package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.CodesResultatDAL;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.EnchereDAO;
import fr.eni.encheres.dal.RetraitDAO;
import fr.eni.encheres.dal.UtilisateurDAO;
import fr.eni.encheres.dal.Utils;
import fr.eni.encheres.BusinessException;

public class UtilisateurDAOJDBCImpl implements UtilisateurDAO {

	private static final String INSERT = "INSERT INTO UTILISATEURS VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL = "SELECT * FROM UTILISATEURS";
	private static final String GET_BY_ID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur=?";
	private static final String UPDATE = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?,"
			+ "email=?, telephone=?, rue=?, code_postal=?, ville=?," + " mot_de_passe=?, credit=?, administrateur=?";
	private static final String DELETE = "DELETE UTILISATEURS WHERE no_utilisateur=?";
	private static final String GET_ARTICLES_VENDUS = "select * from ARTICLES_VENDUS WHERE no_utilisateur=?";

	private static EnchereDAO enchereDao = new EnchereDAOJDBCImpl();
	private static ArticleVenduDAO articleDao = new ArticleVenduDAOJDBCImpl();
	private static CategorieDAO categorieDao = new CategorieDAOJDBCImpl();
	private static RetraitDAO retraitDao = new RetraitDAOJDBCImpl();

	@Override
	public Utilisateur insert(Utilisateur utilisateur) throws BusinessException {

		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try (Connection cnx = Utils.getConnection()) {

			PreparedStatement statement = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, utilisateur.getPseudo());
			statement.setString(2, utilisateur.getNom());
			statement.setString(3, utilisateur.getPrenom());
			statement.setString(4, utilisateur.getEmail());
			if (utilisateur.getTelephone() != null) {
				statement.setString(5, utilisateur.getTelephone());
			} else {
				statement.setNull(5, Types.VARCHAR);
			}
			statement.setString(5, utilisateur.getTelephone());
			statement.setString(6, utilisateur.getRue());
			statement.setString(7, utilisateur.getCodePostal());
			statement.setString(8, utilisateur.getVille());
			statement.setString(9, utilisateur.getPassword());
			statement.setInt(10, utilisateur.getCredit());
			statement.setBoolean(11, utilisateur.isAdministrateur());

			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();

			if (rs.next()) {
				utilisateur.setId(rs.getInt(1));
			}

			statement.close();
			cnx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
		return utilisateur;
	}

	@Override
	public List<Utilisateur> getAll() throws BusinessException {

		List<Utilisateur> list = new ArrayList<>();
		Utilisateur utilisateur = null;
		
		try (Connection cnx = Utils.getConnection()) {
			PreparedStatement statement = cnx.prepareStatement(GET_ALL);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				utilisateur = utilisateurBuilder(rs);
				list.add(utilisateur);
			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEURS_ECHEC);
			throw businessException;

		}
		return list;
	}

	@Override
	public Utilisateur getById(int id) throws BusinessException {

		Utilisateur utilisateur = null;

		try (Connection cnx = Utils.getConnection()) {
			PreparedStatement statement = cnx.prepareStatement(GET_BY_ID);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				utilisateur = utilisateurBuilder(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEURS_ECHEC);
			throw businessException;

		}

		return utilisateur;
	}

	@Override
	public void update(Utilisateur utilisateur) throws BusinessException {

		try (Connection cnx = Utils.getConnection()) {
			PreparedStatement statement = cnx.prepareStatement(UPDATE);
			statement.setString(1, utilisateur.getPseudo());
			statement.setString(2, utilisateur.getNom());
			statement.setString(3, utilisateur.getPrenom());
			statement.setString(4, utilisateur.getEmail());
			if (utilisateur.getTelephone() != null) {
				statement.setString(5, utilisateur.getTelephone());
			} else {
				statement.setNull(5, Types.VARCHAR);
			}
			statement.setString(6, utilisateur.getRue());
			statement.setString(7, utilisateur.getCodePostal());
			statement.setString(8, utilisateur.getVille());
			statement.setString(9, utilisateur.getPassword());
			statement.setInt(10, utilisateur.getCredit());
			statement.setBoolean(11, utilisateur.isAdministrateur());

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

		try (Connection cnx = Utils.getConnection()) {
			
			PreparedStatement statement = cnx.prepareStatement(DELETE);
			statement.setInt(1, id);
			
			List<ArticleVendu> listeArticles=articleDao.getByVendeur(id);
			
			for (ArticleVendu articleVendu : listeArticles) {
				articleDao.delete(articleVendu.getId());
			}
			
		/*	Utilisateur utilisateur = this.getById(id);

			// Supprimer toutes les encheres faite par cet utilisateur
			for (Enchere enchere : enchereDao.getByEncherisseur(id)) {
				enchereDao.delete(enchere.getId());
			}
			utilisateur.setEncheres(null);

			// Unset la collection des articles achetés par cet utilisateur
			utilisateur.setArticlesAchetes(null);

			// Supprimer tous les articles vendus par cet utilisateur
			for (ArticleVendu article : articleDao.getByVendeur(id)) {
				articleDao.delete(article.getId());
			}
			utilisateur.setArticlesVendus(null);*/

			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_ECHEC);
			throw businessException;

		}
	}

	public List<ArticleVendu> getAllArticlesVendus(Utilisateur utilisateur) throws BusinessException {

		List<ArticleVendu> listeArticlesVendus = new ArrayList<ArticleVendu>();

		try (Connection cnx = Utils.getConnection()) {
			PreparedStatement statement = cnx.prepareStatement(GET_ARTICLES_VENDUS);
			statement.setInt(1, utilisateur.getId());
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				ArticleVendu articleVendu = new ArticleVendu();
				articleVendu.setId(rs.getInt("no_article"));
				articleVendu.setNom(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateDebutEncheres((rs.getDate("date_debut_encheres").toLocalDate()));
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				articleVendu.setMiseAPrix(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				articleVendu.setVendeur(utilisateur);
				articleVendu.setCategorie(categorieDao.getById(rs.getInt("no_categorie")));
				articleVendu.setLieuRetrait(retraitDao.getById(rs.getInt("no_retrait")));
				listeArticlesVendus.add(articleVendu);
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLES_ECHEC);
			throw businessException;
		}
		return listeArticlesVendus;
	}

	
	public Utilisateur utilisateurBuilder(ResultSet rs) throws SQLException
	{
		/*List<ArticleVendu> articlesVendus = this.getArticlesVendusUtilisateur(rs.getInt("no_utilisateur"));
		List<ArticleVendu> articlesAchetes = this.getArticlesAchetesUtilisateur(rs.getInt("no_utilisateur"));
		List<Enchere> encheres = this.getEncheresUtilisateur(rs.getInt("no_utilisateur"));*/
		
		Utilisateur utilisateur = new Utilisateur();
		
		utilisateur.setId(rs.getInt("no_utilisateur"));
		utilisateur.setPseudo(rs.getString("pseudo"));
		utilisateur.setNom(rs.getString("nom"));
		utilisateur.setPrenom(rs.getString("prenom"));
		utilisateur.setEmail(rs.getString("email"));
		utilisateur.setTelephone(rs.getString("telephone"));
		utilisateur.setRue(rs.getString("rue"));
		utilisateur.setCodePostal(rs.getString("code_postal"));
		utilisateur.setVille(rs.getString("ville"));
		utilisateur.setPassword(rs.getString("mot_de_passe"));
		utilisateur.setCredit(rs.getInt("credit"));
		utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
		/*utilisateur.setArticlesVendus(articlesVendus);
		utilisateur.setArticlesAchetes(articlesAchetes);
		utilisateur.setEncheres(encheres);*/
		
		
		return utilisateur;

	}
	
	
	/*private List<ArticleVendu> getArticlesVendusUtilisateur(int userId)
	{
		List<ArticleVendu> articlesVendus = new ArrayList<>();
		
		try {
			articlesVendus = articleDao.getByVendeur(userId);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		return articlesVendus;
		
	}
	
	private List<ArticleVendu> getArticlesAchetesUtilisateur(int userId)
	{
		List<ArticleVendu> articlesAchetes = new ArrayList<>();
		
		try {
			for (Enchere enchere : enchereDao.getRemportesParEncherisseur(userId)) {
				articlesAchetes.add(enchere.getArticle());
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		return articlesAchetes;
	}
	
	private List<Enchere> getEncheresUtilisateur(int userId)
	{
		List<Enchere> encheres = new ArrayList<>();
		
		try {
			encheres = enchereDao.getByEncherisseur(userId);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		return encheres;
	}*/
	
}
