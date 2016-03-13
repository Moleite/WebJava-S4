package model;

/**
 * Mod�le servant de support pour les r�servations de vol
 */
public class Reservation {
	
	private int numeroReservation;
	private String login;
	private int numVol;
	private int nbPlaces;
	private boolean confirmation;
	

	/**
	 * Instancier une nouvelle r�servation
	 * 
	 * @param numeroReserv Num�ro unique de la r�servation
	 * @param login Login de l'utilisateur associ� � la r�servation
	 * @param numVol Num�ro du vol associ� � la r�servation
	 * @param nbPlaces Nombre de place r�serv�es
	 * @param confirm Confirmation de la r�servation
	 */
	public Reservation(int numeroReserv, String login, int numVol, int nbPlaces, boolean confirm) {
		this.numeroReservation = numeroReserv;
		this.login = login;
		this.numVol = numVol;
		this.nbPlaces = nbPlaces;
		this.confirmation = confirm;
	}
	
	/**
	 * Instancier une r�servation pour un utilisateur non authentifi�
	 * 
	 * @param numeroReserv Num�ro unique de la r�servation
	 * @param numVol Num�ro du vol associ� � la r�servation
	 * @param nbPlaces Nombre de place r�serv�es
	 */
	public Reservation(int numeroReserv, int numVol, int nbPlaces) {
		this.numeroReservation = numeroReserv;
		this.numVol = numVol;
		this.nbPlaces = nbPlaces;
	}
	
	/**
	 * R�cup�rer le num�ro unique de la r�servation
	 * 
	 * @return le num�ro de la r�servation
	 */
	public int getNumeroReservation() {
		return numeroReservation;
	}
	
	/**
	 * R�cup�rer le login de l'utilisateur associ� � la r�servation
	 * @return le login de l'utilisateur associ� � la r�servation
	 */
	public String getLoginReservation() {
		return login;
	}
	
	/**
	 * R�cup�rer le num�ro du vol associ� � la r�servation
	 * 
	 * @return le le num�ro du vol associ� � la r�servation
	 */
	public int getNumVolReservation() {
		return numVol;
	}

	/**
	 * R�cup�rer le nombre de place r�serv�es
	 * 
	 * @return le nombre de place r�serv�es
	 */
	public int getNbPlacesReservation() {
		return nbPlaces;
	}
	
	
	/**
	 * Tester si la r�servation est confim�e
	 * 
	 * @return le statut de la r�servation (r�serv�e ou pas)
	 */
	public boolean estConfirmee() {
		return confirmation;
	}

	
	
	

	
}
