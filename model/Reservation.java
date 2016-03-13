package model;

/**
 * Modèle servant de support pour les billets de trains
 */
public class Reservation {
	
	private int numeroReservation;
	private Vol vol;
	private Compte compte;
	private String login;
	private int numVol;
	private int nbPlaces;
	private boolean confirmation;

	/**
	 * Instancier un nouveau billet
	 * 
	 * @param numeroReserv Numéro unique du billet
	 * @param depart Départ associé au billet
	 * @param voyageur Voyageur possédant le billet
	 */
	public Reservation(int numeroReserv, Vol vol, Compte compte) {
		this.numeroReservation = numeroReserv;
		this.vol = vol;
		this.compte = compte;
		this.login = compte.getLogin();
		this.numVol = vol.getNumeroVol();
		this.confirmation = true;
	}
	
	/**
	 * Instancier un nouveau billet pour un voyageur non authentifié
	 * 
	 * @param numeroBillet Numéro unique du billet
	 * @param depart Départ associé au billet
	 */
	public Reservation(int numeroBillet, Vol depart) {
		this(numeroBillet, depart, null);
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
	 * Récupérer le départ associé au billet
	 * 
	 * @return le départ associé au billet
	 */
	public Vol getDepart() {
		return vol;
	}
	
	/**
	 * Récupérer le possesseur(compte) de la réservation
	 * 
	 * @return le compte possédant la réservation, ou null s'il n'y en a pas encore
	 */
	public Compte getVoyageur() {
		return compte;
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
