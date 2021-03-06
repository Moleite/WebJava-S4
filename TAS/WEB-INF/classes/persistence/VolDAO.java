package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Reservation;
import model.Vol;
import model.VolIntrouvableException;
import model.IntrouvableException;

/**
 * DataAccessObject en charge de g�rer la r�cup�ration des vols
 */
public class VolDAO {
	
	private Connection connection;
	private static VolDAO instance = new VolDAO();
	private static int compteur = 0;
	private Object verrou;
	
	/**
	 * Cr�er un nouvel accesseur de vols
	 */
	private VolDAO() {
		this.verrou = new Object();
		try {
			this.connection = DB_TAS.getConnection();
		} catch(Exception e) {e.printStackTrace();}
	}
	
	/**
	 * Obtenir l'instance de VolDAO (Singleton)
	 * 
	 * @return l'instance de VolDAO
	 */
	public static VolDAO getInstance() {
	      return instance;
	}

	public Vol trouverVol(int numeroVol) throws IntrouvableException {
		try {
			Vol vol = null;
			String requete = "SELECT * FROM VOLS WHERE NumeroVol = ?";
			PreparedStatement preparedStatement = this.connection.prepareStatement(requete);
			preparedStatement.setInt(1, numeroVol);
			ResultSet resultats = preparedStatement.executeQuery();
			
			while (resultats.next()) {
				vol = new Vol(numeroVol, resultats.getString("DESTINATION"),
						convertDate(resultats.getString("DATEDEPART")),resultats.getInt("NOMBREPLACES"),
						resultats.getFloat("PRIX"));
			}
			
			if(vol != null)
				return vol;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new VolIntrouvableException();
	}
	
	public List<Vol> recupererVols(String villeDestination, String dateDepart, int nombrePlaces) {

		List<Vol> vols = new ArrayList<Vol>();
		
		try {
			String requete = "SELECT v.NUMEROVOL, v.DESTINATION, v.DATEDEPART, v.NOMBREPLACES, v.PRIX FROM VOLS v WHERE v.DESTINATION = ? AND v.DATEDEPART = ? AND v.NOMBREPLACES >= ?";
			PreparedStatement preparedStatement = this.connection.prepareStatement(requete);
			preparedStatement.setString(1, villeDestination);
			preparedStatement.setString(2, convertDate(dateDepart));
			preparedStatement.setInt(3, nombrePlaces);
			
			ResultSet resultats = preparedStatement.executeQuery();
			while (resultats.next()){
//				try {
					Vol v = new Vol(resultats.getInt("NUMEROVOL"), resultats.getString("DESTINATION"),
							convertDate2(resultats.getString("DATEDEPART")),resultats.getInt("NOMBREPLACES"),
							resultats.getFloat("PRIX"));
					vols.add(v);
//				}
//				catch(IntrouvableException e) {
//					e.printStackTrace();
//				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vols;
	}
	public Vol trouverVol(Reservation reservation) throws IntrouvableException {
		try {
			Vol vol = null;
			String requete = "SELECT v.* FROM VOLS v, RESERVATIONS r WHERE r.NUMERORES = ? AND v.NUMEROVOL = r.NUMEROVOL";
			PreparedStatement preparedStatement = this.connection.prepareStatement(requete);
			preparedStatement.setInt(1, reservation.getNumeroReservation());
			ResultSet resultats = preparedStatement.executeQuery();
			
			while (resultats.next()) {
				vol = new Vol(resultats.getInt("NUMEROVOL"), resultats.getString("DESTINATION"),
						convertDate(resultats.getString("DATEDEPART")),resultats.getInt("NOMBREPLACES"),
						resultats.getFloat("PRIX"));
			}
			
			if(vol != null)
				return vol;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new VolIntrouvableException();
	}
	
	/**
	 * Convert French date format to English format
	 * 
	 * @param date date to convert
	 * @return the french date
	 */
	private static String convertDate(String date) {
		String frenchDate = date.substring(6, 10) + "/" + date.substring(3, 5) + "/" + date.substring(0, 2);
		return frenchDate;
	}
	
	/**
	 * Convert English date format to French format
	 * 
	 * @param date date to convert
	 * @return the french date
	 */
	private static String convertDate2(String date) {
		  String frenchDate = date.substring(8, 10) + "/" + date.substring(5, 7) + "/" + date.substring(0, 4);
		  return frenchDate;
	}
	
	public static void main(String[] args) {

		System.out.println(new VolDAO().recupererVols("Londres", "14/03/2016", 400));
		System.out.println(convertDate("2016-03-14"));
		
	}

	public void ajouterVol(String destination, String dateDebut, int nombrePlaces, float prix) {
		try {
			String requete = "INSERT INTO VOLS VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = this.connection.prepareStatement(requete);
			synchronized (this.verrou) {
				preparedStatement.setInt(1, ++VolDAO.compteur);
			}
			preparedStatement.setString(2, destination);
			preparedStatement.setString(3, dateDebut);
			preparedStatement.setInt(4, nombrePlaces);
			preparedStatement.setFloat(5, prix);

			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void ajouterVol(String destination, String date, String nbPlaces,
			String prix) {
		try {
			String requete = "INSERT INTO VOLS VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = this.connection.prepareStatement(requete);
			synchronized (this.verrou) {
				preparedStatement.setInt(1, ++VolDAO.compteur);
			}
			preparedStatement.setString(2, destination);
			preparedStatement.setString(3, date);
			preparedStatement.setString(4, nbPlaces);
			preparedStatement.setString(5, prix);

			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
