package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	public void insert(Utilisateur utilisateur) throws BusinessException {

		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try (Connection cnx = Utils.getConnection()) {

			PreparedStatement requete = cnx.prepareStatement(INSERT);
			requete.setString(1, utilisateur.getPseudo());
			requete.setString(2, utilisateur.getNom());
			requete.setString(3, utilisateur.getPrenom());
			requete.setString(4, utilisateur.getEmail());
			if (utilisateur.getTelephone() != null) {
				requete.setString(5, utilisateur.getTelephone());
			}
			else 
			{
				requete.setNull(5, Types.VARCHAR);
			}
			requete.setString(5, utilisateur.getTelephone());
			requete.setString(6, utilisateur.getRue());
			requete.setString(7, utilisateur.getCodePostal());
			requete.setString(8, utilisateur.getVille());
			requete.setString(9, utilisateur.getPassword());
			requete.setInt(10, utilisateur.getCredit());
			requete.setBoolean(11, utilisateur.isAdministrateur());

			requete.executeUpdate();

			requete.close();
			cnx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}

	}

	@Override
	public List<Utilisateur> getAll() throws BusinessException {

		List<Utilisateur> list = new ArrayList<>();
		List<ArticleVendu> listArticlesAchetes = new ArrayList<>();

		try (Connection cnx = Utils.getConnection()) {
			PreparedStatement requete = cnx.prepareStatement(GET_ALL);
			ResultSet rs = requete.executeQuery();

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
			PreparedStatement requete = cnx.prepareStatement(GET_BY_ID);
			requete.setInt(1, id);
			ResultSet rs = requete.executeQuery();

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
			PreparedStatement requete = cnx.prepareStatement(UPDATE);
			requete.setString(1, utilisateur.getPseudo());
			requete.setString(2, utilisateur.getNom());
			requete.setString(3, utilisateur.getPrenom());
			requete.setString(4, utilisateur.getEmail());
			if (utilisateur.getTelephone() != null) {
				requete.setString(5, utilisateur.getTelephone());
			}
			else 
			{
				requete.setNull(5, Types.VARCHAR);
			}
			requete.setString(6, utilisateur.getRue());
			requete.setString(7, utilisateur.getCodePostal());
			requete.setString(8, utilisateur.getVille());
			requete.setString(9, utilisateur.getPassword());
			requete.setInt(10, utilisateur.getCredit());
			requete.setBoolean(11, utilisateur.isAdministrateur());

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
			
			requete.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_ECHEC);
			throw businessException;

		}
	}

}
