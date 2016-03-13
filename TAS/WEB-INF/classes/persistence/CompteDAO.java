package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Compte;
import model.CompteExisteDejaException;
import model.CompteIntrouvableException;

/**
 * DataAccessObject en charge de gérer la récupération des comptes
 */
public class CompteDAO {
	
	private Connection connection;
	private static CompteDAO instance = new CompteDAO();
	
	/**
	 * Créer un nouvel accesseur de comptes
	 */
	private CompteDAO() {
		try {
			this.connection = DB_TAS.getConnection();
		} catch(Exception e) {e.printStackTrace();}
	}
	
	/**
	 * Obtenir l'instance de CompteDAO (Singleton)
	 * 
	 * @return l'instance de CompteDAO
	 */
	public static CompteDAO getInstance() {
		return instance;
	}
	
	
	public void ajouterCompte(Compte compte) throws CompteExisteDejaException {
		try {
			String requete = "INSERT INTO COMPTES VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = this.connection.prepareStatement(requete);
			preparedStatement.setString(1, compte.getLogin());
			preparedStatement.setString(2, compte.getMdp());
			preparedStatement.setString(3, compte.getNom());
			preparedStatement.setString(4, compte.getPrenom());
			preparedStatement.setString(5, compte.getRole());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CompteExisteDejaException();
		}
	}
	
	public Compte trouverCompte(String log, String pass) throws CompteIntrouvableException {
		try {
			Compte compte = null;
			String requete = "SELECT * FROM COMPTES WHERE login = ? AND mdp = ?";
			PreparedStatement preparedStatement = this.connection.prepareStatement(requete);
			preparedStatement.setString(1, log);
			preparedStatement.setString(2, pass);
			ResultSet resultats = preparedStatement.executeQuery();
			
			while (resultats.next()) {
				String login = resultats.getString("Login");
				String mdp = resultats.getString("Mdp");
				String nom = resultats.getString("Nom");
				String prenom = resultats.getString("Prenom");
				String role = resultats.getString("Role");
				compte = new Compte(login, mdp, nom, prenom, role);
			}
			
			if(compte != null)
				return compte;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new CompteIntrouvableException();
	}
	
	public Compte trouverCompte(String login) throws CompteIntrouvableException {
		try {
			Compte compte = null;
			String requete = "SELECT * FROM COMPTES WHERE login = ?";
			PreparedStatement preparedStatement = this.connection.prepareStatement(requete);
			preparedStatement.setString(1, login);
			ResultSet resultats = preparedStatement.executeQuery();
			
			while (resultats.next()) {
				String mdp = resultats.getString("Mdp");
				String nom = resultats.getString("Nom");
				String prenom = resultats.getString("Prenom");
				String role = resultats.getString("Role");
				compte = new Compte(login,mdp,nom,prenom,role);
			}
			
			if(compte != null)
				return compte;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new CompteIntrouvableException();
	}
}
