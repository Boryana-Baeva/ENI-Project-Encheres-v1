package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.CodesResultatDAL;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.EnchereDAO;
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

	private static EnchereDAO enchereDao = new EnchereDAOJDBCImpl();
	private static ArticleVenduDAO articleDao = new ArticleVenduDAOJDBCImpl();

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
			}
			else 
			{
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
		List<ArticleVendu> listArticlesAchetes = new ArrayList<>();

		try (Connection cnx = Utils.getConnection()) {
			PreparedStatement statement = cnx.prepareStatement(GET_ALL);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
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
				utilisateur.setArticlesVendus(articleDao.getByVendeur(utilisateur.getId()));
				
				for(Enchere enchere : enchereDao.getRemportesParEncherisseur(utilisateur.getId())) {
					listArticlesAchetes.add(enchere.getArticle());
				}
				
				utilisateur.setArticlesAchetes(listArticlesAchetes);
				utilisateur.setEncheres(enchereDao.getByEncherisseur(utilisateur.getId()));

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
		List<ArticleVendu> listArticlesAchetes = new ArrayList<>();

		try (Connection cnx = Utils.getConnection()) {
			PreparedStatement statement = cnx.prepareStatement(GET_BY_ID);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				utilisateur = new Utilisateur();
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
				utilisateur.setArticlesVendus(articleDao.getByVendeur(utilisateur.getId()));
				
				
				for (Enchere enchere : enchereDao.getRemportesParEncherisseur(utilisateur.getId())) {
					listArticlesAchetes.add(enchere.getArticle());
				}
				
				utilisateur.setArticlesAchetes(listArticlesAchetes);
				utilisateur.setEncheres(enchereDao.getByEncherisseur(utilisateur.getId()));
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
			}
			else 
			{
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
			
			Utilisateur utilisateur = this.getById(id);
			
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
			utilisateur.setArticlesVendus(null);
			
			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_ECHEC);
			throw businessException;

		}
	}

}
