package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.CodesResultatDAL;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.RetraitDAO;
import fr.eni.encheres.dal.Utils;

public class RetraitDAOJDBCImpl implements RetraitDAO {
	private static final String INSERT = "insert into RETRAITS (rue, code_postal, ville) values (?,?,?)";
	private static final String GET_BY_ID = "SELECT * FROM RETRAITS WHERE no_retrait = ?";
	private static final String GET_ALL = "SELECT * FROM RETRAITS";
	private static final String UPDATE = "UPDATE RETRAITS SET rue = ?, code_postal = ?, ville = ? WHERE no_retrait=?";
	private static final String DELETE = "DELETE RETRAITS WHERE no_retrait = ?";

	//pour commit
	@Override
	public  Retrait insert(Retrait retrait) throws BusinessException // INSERT
	{
		if (retrait == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try (Connection cnx = Utils.getConnection()) {
			PreparedStatement statement = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, retrait.getRue());
			statement.setString(2, retrait.getCodePostal());
			statement.setString(3, retrait.getVille());
			
			
			statement.executeUpdate();
			
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next())
			{
				retrait.setId(rs.getInt(1));
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
		return retrait;
		
	}

	@Override
	public Retrait getById(int id) throws BusinessException // GET_BY_ID
	{
		Retrait retrait = null;
		
		try (Connection cnx = Utils.getConnection()) {
			
			PreparedStatement statement = cnx.prepareStatement(GET_BY_ID);
			statement.setInt(1, id);
			
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
				retrait = new Retrait();
				retrait.setId(rs.getInt("no_retrait"));
				retrait.setRue(rs.getString("rue"));
				retrait.setCodePostal(rs.getString("code_postal"));
				retrait.setVille(rs.getString("ville"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_RETRAIT_ECHEC);
			throw businessException;

		}
		return retrait;
	}

	@Override
	public List<Retrait> getAll() throws BusinessException // GET_ALL
	{
		List<Retrait> retraits = new ArrayList<>();

		try (Connection cnx = Utils.getConnection()) {
			PreparedStatement statement = cnx.prepareStatement(GET_ALL);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Retrait retrait = new Retrait();
				retrait.setId(rs.getInt("no_retrait"));
				retrait.setRue(rs.getString("rue"));
				retrait.setCodePostal(rs.getString("code_postal"));
				retrait.setVille(rs.getString("ville"));
				retraits.add(retrait);
			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_RETRAIT_ECHEC);
			throw businessException;

		}
		return retraits;
	}

	@Override
	public void update(Retrait retrait) throws BusinessException // UPDATE
	{
		try (Connection cnx = Utils.getConnection()) {
			PreparedStatement statement = cnx.prepareStatement(UPDATE);

			statement.setString(1, retrait.getRue());
			statement.setString(2, retrait.getCodePostal());
			statement.setString(3, retrait.getVille());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_ECHEC);
			throw businessException;

		}
	}

	@Override
	public void delete(int id) throws BusinessException // DELETE
	{
		try (Connection cnx = Utils.getConnection()) {
			PreparedStatement statement = cnx.prepareStatement(DELETE);
			statement.setInt(1, id);
			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_ECHEC);
			throw businessException;

		}
	}
}