package model;

/**
 * Modèle servant de support pour les réservations de vol
 */
public class Reservation {
	
	private int numeroReservation;
	private String login;
	private int numVol;
	private int nbPlaces;
	private boolean confirmation;
	

	/**
	 * Instancier une nouvelle réservation
	 * 
	 * @param numeroReserv Numéro unique de la réservation
	 * @param login Login de l'utilisateur associé à la réservation
	 * @param numVol Numéro du vol associé à la réservation
	 * @param nbPlaces Nombre de place réservées
	 * @param confirm Confirmation de la réservation
	 */
	public Reservation(int numeroReserv, String login, int numVol, int nbPlaces, boolean confirm) {
		this.numeroReservation = numeroReserv;
		this.login = login;
		this.numVol = numVol;
		this.nbPlaces = nbPlaces;
		this.confirmation = confirm;
	}
	
	/**
	 * Instancier une réservation pour un utilisateur non authentifié
	 * 
	 * @param numeroReserv Numéro unique de la réservation
	 * @param numVol Numéro du vol associé à la réservation
	 * @param nbPlaces Nombre de place réservées
	 */
	public Reservation(int numeroReserv, int numVol, int nbPlaces) {
		this.numeroReservation = numeroReserv;
		this.numVol = numVol;
		this.nbPlaces = nbPlaces;
	}
	
	/**
	 * Récupérer le numéro unique de la réservation
	 * 
	 * @return le numéro de la réservation
	 */
	public int getNumeroReservation() {
		return numeroReservation;
	}
	
	/**
	 * Récupérer le login de l'utilisateur associé à la réservation
	 * @return le login de l'utilisateur associé à la réservation
	 */
	public String getLoginReservation() {
		return login;
	}
	
	/**
	 * Récupérer le numéro du vol associé à la réservation
	 * 
	 * @return le le numéro du vol associé à la réservation
	 */
	public int getNumVolReservation() {
		return numVol;
	}

	/**
	 * Récupérer le nombre de place réservées
	 * 
	 * @return le nombre de place réservées
	 */
	public int getNbPlacesReservation() {
		return nbPlaces;
	}
	
	
	/**
	 * Tester si la réservation est confimée
	 * 
	 * @return le statut de la réservation (réservée ou pas)
	 */
	public boolean estConfirmee() {
		return confirmation;
	}

	
	
	

	
}
