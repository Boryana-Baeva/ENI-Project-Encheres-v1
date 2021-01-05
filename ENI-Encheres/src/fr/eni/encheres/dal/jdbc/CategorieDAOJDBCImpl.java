package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.ConnectionProvider;

public class CategorieDAOJDBCImpl implements CategorieDAO {

	private static final String INSERT = "insert into CATEGORIES values (?)";
	private static final String GET_BY_ID = "select * from CATEGORIES where no_categorie=?";
	private static final String GET_ALL = "select * from CATEGORIES";
	private static final String UPDATE = "update CATEGORIES set libelle = ? where no_categorie=?";
	private static final String DELETE = "delete CATEGORIES where no_categorie=?";

	@Override
	public void insert(Categorie categorie) {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, categorie.getLibelle());

			pstmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	@Override
	public Categorie getById(int id) {

		Categorie categorie = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(GET_BY_ID);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				categorie= new Categorie();
				categorie.setId(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
			}
					

		} catch (Exception e) {

			e.printStackTrace();

		}

		return categorie;
	}

	@Override
	public List<Categorie> getAll() {
		
		List<Categorie> categories = new ArrayList<>();

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(GET_ALL);

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Categorie categorie = new Categorie();
				categorie.setId(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
			}
					

		} catch (Exception e) {

			e.printStackTrace();

		}
		return categories;
	}

	@Override
	public void update(Categorie categorie) {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
			pstmt.setInt(1, categorie.getId());
			pstmt.setString(2, categorie.getLibelle());

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
