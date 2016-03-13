package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Voyageur;
import model.VoyageurExisteDejaException;
import model.VoyageurIntrouvableException;

/**
 * DataAccessObject en charge de gérer la récupération des voyageurs
 */
public class VoyageurDAO {
	
	private Connection connection;
	private static VoyageurDAO instance = new VoyageurDAO();
	
	/**
	 * Créer un nouvel accesseur de voyageurs
	 */
	private VoyageurDAO() {
//		try {
//			this.connection = DBAccess.getConnection();
//		} catch(Exception e) {e.printStackTrace();}
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String url = "jdbc:mysql://127.0.0.1/tchou";
		String user = "benharka";
		String pass = "benharka";
		
		try {
			this.connection = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Obtenir l'instance de VoyageurDAO (Singleton)
	 * 
	 * @return l'instance de VoyageurDAO
	 */
	public static VoyageurDAO getInstance() {
		return instance;
	}
	
	
	public void ajouterVoyageur(Voyageur voyageur) throws VoyageurExisteDejaException {
		try {
			String requete = "INSERT INTO VOYAGEURS VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStatement = this.connection.prepareStatement(requete);
			preparedStatement.setString(1, voyageur.getLoginVoyageur());
			preparedStatement.setString(2, voyageur.getPassVoyageur());
			preparedStatement.setString(3, voyageur.getNomVoyageur());
			preparedStatement.setString(4, voyageur.getAdresseVoyageur());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new VoyageurExisteDejaException();
		}
	}
	
	public Voyageur trouverVoyageur(String login, String pass) throws VoyageurIntrouvableException {
		try {
			Voyageur user = null;
			String requete = "SELECT * FROM VOYAGEURS WHERE loginVoyageur = ? AND passVoyageur = ?";
			PreparedStatement preparedStatement = this.connection.prepareStatement(requete);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, pass);
			ResultSet resultats = preparedStatement.executeQuery();
			
			while (resultats.next()) {
				String loginVoyageur = resultats.getString("LoginVoyageur");
				String passVoyageur = resultats.getString("PassVoyageur");
				String nomVoyageur = resultats.getString("NomVoyageur");
				String adresseVoyageur = resultats.getString("AdresseVoyageur");
				user = new Voyageur(loginVoyageur,passVoyageur,nomVoyageur,adresseVoyageur);
			}
			
			if(user != null)
				return user;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new VoyageurIntrouvableException();
	}
	
	public Voyageur trouverVoyageur(String loginVoyageur) throws VoyageurIntrouvableException {
		try {
			Voyageur user = null;
			String requete = "SELECT * FROM VOYAGEURS WHERE loginVoyageur = ?";
			PreparedStatement preparedStatement = this.connection.prepareStatement(requete);
			preparedStatement.setString(1, loginVoyageur);
			ResultSet resultats = preparedStatement.executeQuery();
			
			while (resultats.next()) {
				String passVoyageur = resultats.getString("PassVoyageur");
				String nomVoyageur = resultats.getString("NomVoyageur");
				String adresseVoyageur = resultats.getString("AdresseVoyageur");
				user = new Voyageur(loginVoyageur,passVoyageur,nomVoyageur,adresseVoyageur);
			}
			
			if(user != null)
				return user;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new VoyageurIntrouvableException();
	}
}
