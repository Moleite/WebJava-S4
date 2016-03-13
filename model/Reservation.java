package model;

/**
 * Mod�le servant de support pour les r�servations de vols
 */
public class Reservation {
	
	private int numeroReservation;
	private Vol vol;
	private Compte compte;
	private int nbPlaces;
	private boolean confirmation;

	/**
	 * Instancier un nouveau r�servation
	 * 
	 * @param numeroReserv Num�ro unique de la r�servation
	 * @param vol Vol associ� � la r�servation
	 * @param compte Compte poss�dant la r�servation
	 */
	public Reservation(int numeroReserv, Vol vol, Compte compte, int nbPlaces, boolean confirmation) {
		this.numeroReservation = numeroReserv;
		this.vol = vol;
		this.compte = compte;
		this.nbPlaces = nbPlaces;
		this.confirmation = confirmation;
	}
	
	/**
	 * Instancier une nouvelle r�servation pour un compte non authentifi�
	 * 
	 * @param numeroReservation Num�ro unique de la r�servation
	 * @param vol Vol associ� � la r�servation
	 */
	public Reservation(int numeroReservation, Vol vol, int nbPlaces, boolean confirmation) {
		this(numeroReservation, vol, null, nbPlaces, confirmation);
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
	 * R�cup�rer le vol associ� � la r�servation
	 * 
	 * @return le vol associ� � la r�servation
	 */
	public Vol getVol() {
		return vol;
	}
	
	/**
	 * R�cup�rer le possesseur(compte) de la r�servation
	 * 
	 * @return le compte poss�dant la r�servation, ou null s'il n'y en a pas encore
	 */
	public Compte getCompte() {
		return compte;
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