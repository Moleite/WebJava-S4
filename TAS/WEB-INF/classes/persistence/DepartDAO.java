package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Billet;
import model.Depart;
import model.DepartIntrouvableException;
import model.IntrouvableException;

/**
 * DataAccessObject en charge de gérer la récupération des départs
 */
public class DepartDAO {
	
	private Connection connection;
	private static DepartDAO instance = new DepartDAO();
	private LigneDAO ligneDAO = LigneDAO.getInstance();
	
	/**
	 * Créer un nouvel accesseur de départs
	 */
	private DepartDAO() {
		try {
			this.connection = DBAccess.getConnection();
		} catch(Exception e) {e.printStackTrace();}
	}
	
	/**
	 * Obtenir l'instance de DepartDAO (Singleton)
	 * 
	 * @return l'instance de DepartDAO
	 */
	public static DepartDAO getInstance() {
	      return instance;
	}

	public Depart trouverDepart(int numeroDepart) throws IntrouvableException {
		try {
			Depart depart = null;
			String requete = "SELECT * FROM DEPARTS WHERE NumeroDepart = ?";
			PreparedStatement preparedStatement = this.connection.prepareStatement(requete);
			preparedStatement.setInt(1, numeroDepart);
			ResultSet resultats = preparedStatement.executeQuery();
			
			while (resultats.next()) {
				depart = new Depart(numeroDepart, ligneDAO.trouverLigne(resultats.getInt("NumeroLigne")),
						convertDate(resultats.getString("DateDep")), resultats.getInt("Capacite"));
			}
			
			if(depart != null)
				return depart;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new DepartIntrouvableException();
	}
	
	public List<Depart> recupererDeparts(String villeDepart,
			String villeDestination, String dateDebut, String dateFin) {

		List<Depart> departs = new ArrayList<Depart>();
		
		try {
			String requete = "SELECT d.NUMERODEPART,d.DATEDEP, d.NUMEROLIGNE, d.CAPACITE FROM DEPARTS d,LIGNES l WHERE l.NUMEROLIGNE = d.NUMEROLIGNE AND d.DATEDEP >= ? AND d.DATEDEP <= ? AND l.VILLEDEPART = ? AND l.VILLEDESTINATION = ? ";
			PreparedStatement preparedStatement = this.connection.prepareStatement(requete);
			preparedStatement.setString(1, dateDebut);
			preparedStatement.setString(2, dateFin);
			preparedStatement.setString(3, villeDepart);
			preparedStatement.setString(4, villeDestination);
			
			ResultSet resultats = preparedStatement.executeQuery();
			while (resultats.next()){
				try {
					Depart d = new Depart(resultats.getInt("NUMERODEPART"), ligneDAO.trouverLigne(resultats.getInt("NumeroLigne")),
							convertDate(resultats.getString("DateDep")),resultats.getInt("Capacite"));
					departs.add(d);
				}
				catch(IntrouvableException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return departs;
	}
	public Depart trouverDepart(Billet billet) throws IntrouvableException {
		try {
			Depart depart = null;
			String requete = "SELECT d.* FROM DEPARTS d, BILLETS b WHERE b.NUMEROBILLET = ? AND d.NUMERODEPART = b.NUMERODEPART";
			PreparedStatement preparedStatement = this.connection.prepareStatement(requete);
			preparedStatement.setInt(1, billet.getNumeroBillet());
			ResultSet resultats = preparedStatement.executeQuery();
			
			while (resultats.next()) {
				depart = new Depart(resultats.getInt("NumeroDepart"), ligneDAO.trouverLigne(resultats.getInt("NumeroLigne")),
						convertDate(resultats.getString("DateDep")), resultats.getInt("Capacite"));
			}
			
			if(depart != null)
				return depart;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new DepartIntrouvableException();
	}
	
	/**
	 * Convert English date format to French format
	 * 
	 * @param date date to convert
	 * @return the french date
	 */
	private String convertDate(String date) {
		String frenchDate = date.substring(8, 10) + "/" + date.substring(5, 7) + "/" + date.substring(0, 4);
		return frenchDate;
	}
}
