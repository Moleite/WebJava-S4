package model;

/**
 * Modèle servant de support pour les billets de trains
 */
public class Billet {
	
	private int numeroBillet;
	private Depart depart;
	private Voyageur voyageur;

	/**
	 * Instancier un nouveau billet
	 * 
	 * @param numeroBillet Numéro unique du billet
	 * @param depart Départ associé au billet
	 * @param voyageur Voyageur possédant le billet
	 */
	public Billet(int numeroBillet, Depart depart, Voyageur voyageur) {
		this.numeroBillet = numeroBillet;
		this.depart = depart;
		this.voyageur = voyageur;
	}
	
	/**
	 * Instancier un nouveau billet pour un voyageur non authentifié
	 * 
	 * @param numeroBillet Numéro unique du billet
	 * @param depart Départ associé au billet
	 */
	public Billet(int numeroBillet, Depart depart) {
		this(numeroBillet, depart, null);
	}
	
	/**
	 * Récupérer le numéro unique du billet
	 * 
	 * @return le numéro du billet
	 */
	public int getNumeroBillet() {
		return numeroBillet;
	}
	
	/**
	 * Récupérer le départ associé au billet
	 * 
	 * @return le départ associé au billet
	 */
	public Depart getDepart() {
		return depart;
	}
	
	/**
	 * Récupérer le possesseur du billet
	 * 
	 * @return le voyageur possédant le billet, ou null s'il n'y en a pas encore
	 */
	public Voyageur getVoyageur() {
		return voyageur;
	}
	
	/**
	 * Définir le voyageur possesseur du billet
	 * 
	 * @param voyageur le voyageur qui possède le billet
	 */
	public void setVoyageur(Voyageur voyageur) {
		this.voyageur = voyageur;
	}
}
