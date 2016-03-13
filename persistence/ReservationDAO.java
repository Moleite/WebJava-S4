package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Reservation;
import model.ReservationIntrouvableException;
import model.Vol;
import model.IntrouvableException;
import model.Compte;

/**
 * DataAccessObject en charge de gérer la récupération et l'enregistrement des réservations
 */
public class ReservationDAO {
	
	private Connection connection;
	private static ReservationDAO instance = new ReservationDAO();
	private VolDAO volDAO = VolDAO.getInstance();
	private CompteDAO compteDAO = CompteDAO.getInstance();
	private static int compteur = 0;
	private Object verrou;
	
	/**
	 * Créer un nouvel accesseur de billets, utilise un verrou pour synchroniser son compteur
	 */
	private ReservationDAO() {
		this.verrou = new Object();
		try {
			this.connection = DB_TAS.getConnection();
		} catch(Exception e) {e.printStackTrace();}
	}
	
	/**
	 * Retrouver une réservation à partir de son numéro unique
	 * 
	 * @param numeroRes Le numéro de la réservation
	 * @return la réservation correspondante
	 * @throws IntrouvableException si la réservation ou un de ses composants n'existe pas
	 */
	public Reservation trouverReservation(int numeroReservation) throws IntrouvableException {
		try {
			Reservation reservation = null;
			String requete = "SELECT * FROM RESERVATIONS WHERE NumeroRes = ?";
			PreparedStatement preparedStatement = this.connection.prepareStatement(requete);
			preparedStatement.setInt(1, numeroReservation);
			ResultSet resultats = preparedStatement.executeQuery();
			
			while (resultats.next()){
				Vol vol = this.volDAO.trouverVol(resultats.getInt("NumeroVol"));
				Compte compte = this.compteDAO.trouverCompte(resultats.getString("Login"));
				reservation = new Reservation(numeroReservation, vol, compte, resultats.getInt("NombrePlaces"), resultats.getBoolean("Confirmation"));
			}
			
			if(reservation != null)
				return reservation;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new ReservationIntrouvableException();
	}
	
	/**
	 * Obtenir l'instance de ReservationDAO (Singleton)
	 * 
	 * @return l'instance de ReservationDAO
	 */
	public static ReservationDAO getInstance() {
	      return instance;
	}
	
	/**
	 * Récupérer les réservations d'un voyageur
	 * 
	 * @param compte le compte dont on souhaite obtenir les réservations
	 * @return la liste des réservations du compte
	 */
	public List<Reservation> recupererReservations(Compte compte) {
		List<Reservation> reservations = new ArrayList<Reservation>();
		
		try {
			String requete = "SELECT * FROM RESERVATIONS WHERE Login = ?";
			PreparedStatement preparedStatement = this.connection.prepareStatement(requete);
			preparedStatement.setString(1, compte.getLogin());
			ResultSet resultats = preparedStatement.executeQuery();
			
			while (resultats.next()){
				try {
					Vol v = volDAO.trouverVol(resultats.getInt("NumeroVol"));
					Reservation r = new Reservation(resultats.getInt("NumeroRes"), v, compte, resultats.getInt("NombrePlaces"), resultats.getBoolean("Confirmation"));
					reservations.add(r);
				} catch (IntrouvableException e) {
					e.printStackTrace();
				}
			}			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return reservations;
	}

	/**
	 * Ajouter une réservation à un compte
	 * 
	 * @param numeroVol Vol associé à la réservation
	 * @param compte Compte qui possèdera la réservation 
	 */
	public void ajouterReservation(Vol vol, Compte compte) {
		try {
			String requete = "INSERT INTO RESERVATIONS VALUES (?, ?, ?)";
			PreparedStatement preparedStatement = this.connection.prepareStatement(requete);
			synchronized (this.verrou) {
				preparedStatement.setInt(1, ++ReservationDAO.compteur);
			}
			preparedStatement.setInt(2, vol.getNumeroVol());
			preparedStatement.setString(3, compte.getLogin());
			preparedStatement.executeUpdate();
			try {
				String requete2 = "UPDATE VOLS SET NOMBREPLACES = NOMBREPLACES - 1 WHERE NUMEROVOL= ?";
				PreparedStatement preparedStatement2 = this.connection.prepareStatement(requete2);	
				preparedStatement2.setInt(1, vol.getNumeroVol());
				preparedStatement2.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Supprimer une réservation
	 * 
	 * @param numeroRes La réservation à supprimer
	 */
	public void supprimerReservation(Reservation reservation) {
		try {
			String requete = "DELETE FROM RESERVATIONS WHERE NumeroRes = ? ";
			PreparedStatement preparedStatement = this.connection.prepareStatement(requete);
			preparedStatement.setInt(1, reservation.getNumeroReservation());
			preparedStatement.executeUpdate();
			try {
				String requete2 = "UPDATE VOLS SET NOMBREPLACES = NOMBREPLACES + 1 WHERE NUMEROVOL= ?";
				PreparedStatement preparedStatement2 = this.connection.prepareStatement(requete2);	
				preparedStatement2.setInt(1, reservation.getVol().getNumeroVol());
				preparedStatement2.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
