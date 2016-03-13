package model;

/**
 * Modèle servant de support pour les réservations de vols
 */
public class Reservation {
	
	private int numeroReservation;
	private Vol vol;
	private Compte compte;
	private int nbPlaces;
	private boolean confirmation;

	/**
	 * Instancier un nouveau réservation
	 * 
	 * @param numeroReserv Numéro unique de la réservation
	 * @param vol Vol associé à la réservation
	 * @param compte Compte possédant la réservation
	 */
	public Reservation(int numeroReserv, Vol vol, Compte compte, int nbPlaces, boolean confirmation) {
		this.numeroReservation = numeroReserv;
		this.vol = vol;
		this.compte = compte;
		this.nbPlaces = nbPlaces;
		this.confirmation = confirmation;
	}
	
	/**
	 * Instancier une nouvelle réservation pour un compte non authentifié
	 * 
	 * @param numeroReservation Numéro unique de la réservation
	 * @param vol Vol associé à la réservation
	 */
	public Reservation(int numeroReservation, Vol vol, int nbPlaces, boolean confirmation) {
		this(numeroReservation, vol, null, nbPlaces, confirmation);
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
	 * Récupérer le vol associé à la réservation
	 * 
	 * @return le vol associé à la réservation
	 */
	public Vol getVol() {
		return vol;
	}
	
	/**
	 * Récupérer le possesseur(compte) de la réservation
	 * 
	 * @return le compte possédant la réservation, ou null s'il n'y en a pas encore
	 */
	public Compte getCompte() {
		return compte;
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