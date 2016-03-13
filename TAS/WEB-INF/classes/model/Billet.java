package model;

/**
 * Mod�le servant de support pour les billets de trains
 */
public class Billet {
	
	private int numeroBillet;
	private Depart depart;
	private Voyageur voyageur;

	/**
	 * Instancier un nouveau billet
	 * 
	 * @param numeroBillet Num�ro unique du billet
	 * @param depart D�part associ� au billet
	 * @param voyageur Voyageur poss�dant le billet
	 */
	public Billet(int numeroBillet, Depart depart, Voyageur voyageur) {
		this.numeroBillet = numeroBillet;
		this.depart = depart;
		this.voyageur = voyageur;
	}
	
	/**
	 * Instancier un nouveau billet pour un voyageur non authentifi�
	 * 
	 * @param numeroBillet Num�ro unique du billet
	 * @param depart D�part associ� au billet
	 */
	public Billet(int numeroBillet, Depart depart) {
		this(numeroBillet, depart, null);
	}
	
	/**
	 * R�cup�rer le num�ro unique du billet
	 * 
	 * @return le num�ro du billet
	 */
	public int getNumeroBillet() {
		return numeroBillet;
	}
	
	/**
	 * R�cup�rer le d�part associ� au billet
	 * 
	 * @return le d�part associ� au billet
	 */
	public Depart getDepart() {
		return depart;
	}
	
	/**
	 * R�cup�rer le possesseur du billet
	 * 
	 * @return le voyageur poss�dant le billet, ou null s'il n'y en a pas encore
	 */
	public Voyageur getVoyageur() {
		return voyageur;
	}
	
	/**
	 * D�finir le voyageur possesseur du billet
	 * 
	 * @param voyageur le voyageur qui poss�de le billet
	 */
	public void setVoyageur(Voyageur voyageur) {
		this.voyageur = voyageur;
	}
}
