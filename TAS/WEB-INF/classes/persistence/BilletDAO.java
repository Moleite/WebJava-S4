package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Billet;
import model.BilletIntrouvableException;
import model.Depart;
import model.IntrouvableException;
import model.Voyageur;

/**
 * DataAccessObject en charge de gérer la récupération et l'enregistrement des billets
 */
public class BilletDAO {
	
	private Connection connection;
	private static BilletDAO instance = new BilletDAO();
	private DepartDAO departDAO = DepartDAO.getInstance();
	private VoyageurDAO voyageurDAO = VoyageurDAO.getInstance();
	private static int compteur = 0;
	private Object verrou;
	
	/**
	 * Créer un nouvel accesseur de billets, utilise un verrou pour synchroniser son compteur
	 */
	private BilletDAO() {
		this.verrou = new Object();
		try {
			this.connection = DBAccess.getConnection();
		} catch(Exception e) {e.printStackTrace();}
	}
	
	/**
	 * Retrouver un billet à partir de son numéro unique
	 * 
	 * @param numeroBillet Le numéro du billet
	 * @return le billet correspondant
	 * @throws IntrouvableException si le billet ou un de ses composants n'existe pas
	 */
	public Billet trouverBillet(int numeroBillet) throws IntrouvableException {
		try {
			Billet billet = null;
			String requete = "SELECT * FROM BILLETS WHERE NumeroBillet = ?";
			PreparedStatement preparedStatement = this.connection.prepareStatement(requete);
			preparedStatement.setInt(1, numeroBillet);
			ResultSet resultats = preparedStatement.executeQuery();
			
			while (resultats.next()){
				Depart depart = this.departDAO.trouverDepart(resultats.getInt("NumeroDepart"));
				Voyageur voyageur = this.voyageurDAO.trouverVoyageur(resultats.getString("LoginVoyageur"));
				billet = new Billet(numeroBillet, depart, voyageur);
			}
			
			if(billet != null)
				return billet;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new BilletIntrouvableException();
	}
	
	/**
	 * Obtenir l'instance de BilletDAO (Singleton)
	 * 
	 * @return l'instance de BilletDAO
	 */
	public static BilletDAO getInstance() {
	      return instance;
	}
	
	/**
	 * Récupérer les billets d'un voyageur
	 * 
	 * @param voyageur le voyageur dont on souhaite obtenir les billets
	 * @return la liste des billets du voyageur
	 */
	public List<Billet> recupererBillets(Voyageur voyageur) {
		List<Billet> billets = new ArrayList<Billet>();
		
		try {
			String requete = "SELECT * FROM BILLETS WHERE LoginVoyageur = ?";
			PreparedStatement preparedStatement = this.connection.prepareStatement(requete);
			preparedStatement.setString(1, voyageur.getLoginVoyageur());
			ResultSet resultats = preparedStatement.executeQuery();
			
			while (resultats.next()){
				try {
					Depart d = departDAO.trouverDepart(resultats.getInt("NumeroDepart"));
					Billet b = new Billet(resultats.getInt("NumeroBillet"), d, voyageur);
					billets.add(b);
				} catch (IntrouvableException e) {
					e.printStackTrace();
				}
			}			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return billets;
	}

	/**
	 * Ajouter un billet à un voyageur
	 * 
	 * @param numeroDepart Départ associé au billet
	 * @param voyageur Voyageur qui possèdera le billet 
	 */
	public void ajouterBillet(Depart depart, Voyageur voyageur) {
		try {
			String requete = "INSERT INTO BILLETS VALUES (?, ?, ?)";
			PreparedStatement preparedStatement = this.connection.prepareStatement(requete);
			synchronized (this.verrou) {
				preparedStatement.setInt(1, ++BilletDAO.compteur);
			}
			preparedStatement.setInt(2, depart.getNumeroDepart());
			preparedStatement.setString(3, voyageur.getLoginVoyageur());
			preparedStatement.executeUpdate();
			try {
				String requete2 = "UPDATE DEPARTS SET CAPACITE = CAPACITE - 1 WHERE NUMERODEPART= ?";
				PreparedStatement preparedStatement2 = this.connection.prepareStatement(requete2);	
				preparedStatement2.setInt(1, depart.getNumeroDepart());
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
	 * Supprimer un billet
	 * 
	 * @param numeroBillet Le billet à supprimer
	 */
	public void supprimerBillet(Billet billet) {
		try {
			String requete = "DELETE FROM BILLETS WHERE NumeroBillet = ? ";
			PreparedStatement preparedStatement = this.connection.prepareStatement(requete);
			preparedStatement.setInt(1, billet.getNumeroBillet());
			preparedStatement.executeUpdate();
			try {
				String requete2 = "UPDATE DEPARTS SET CAPACITE = CAPACITE + 1 WHERE NUMERODEPART= ?";
				PreparedStatement preparedStatement2 = this.connection.prepareStatement(requete2);	
				preparedStatement2.setInt(1, billet.getDepart().getNumeroDepart());
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
