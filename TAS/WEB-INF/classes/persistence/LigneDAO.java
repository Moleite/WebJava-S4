package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Ligne;
import model.LigneIntrouvableException;

/**
 * DataAccessObject en charge de gérer la récupération des lignes
 */
public class LigneDAO {
	
	private Connection connection;
	private static LigneDAO instance = new LigneDAO();
	
	/**
	 * Créer un nouvel accesseur de lignes
	 */
	private LigneDAO() {
		try {
			this.connection = DBAccess.getConnection();
		} catch(Exception e) {e.printStackTrace();}
	}
	
	/**
	 * Obtenir l'instance de LigneDAO (Singleton)
	 * 
	 * @return l'instance de LigneDAO
	 */
	public static LigneDAO getInstance() {
	      return instance;
	}
	
	/**
	 * Retrouver une ligne à partir de son numéro unique
	 * 
	 * @param numeroLigne Le numéro de la ligne
	 * @return la ligne correspondante
	 * @throws LigneIntrouvableException si la ligne n'existe pas
	 */
	public Ligne trouverLigne(int numeroLigne) throws LigneIntrouvableException {
		try {
			Ligne ligne = null;
			String requete = "SELECT * FROM LIGNES WHERE NumeroLigne = ?";
			PreparedStatement preparedStatement = this.connection.prepareStatement(requete);
			preparedStatement.setInt(1, numeroLigne);
			ResultSet resultats = preparedStatement.executeQuery();
			
			while (resultats.next()){
				String villeDepart = resultats.getString("VilleDepart");
				String villeDestination = resultats.getString("VilleDestination");
				int duree = resultats.getInt("Duree");
				ligne = new Ligne(numeroLigne, villeDepart, villeDestination, duree);
			}
			
			if(ligne != null)
				return ligne;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new LigneIntrouvableException();
	}
}
