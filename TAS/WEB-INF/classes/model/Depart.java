package model;

/**
 * Mod�le servant de support pour les d�parts
 */
public class Depart {
	
	private int numeroDepart;
	private Ligne ligne;
	private String dateDep;
	private int capacite;
	
	
	public Depart(int numeroDepart, Ligne ligne, String dateDep, int capacite) {
		this.numeroDepart = numeroDepart;
		this.dateDep = dateDep;
		this.capacite = capacite;
		this.ligne = ligne;
	}
	
	/**
	 * R�cup�rer le num�ro unique du d�part
	 * 
	 * @return le num�ro du d�part
	 */
	public int getNumeroDepart() {
		return numeroDepart;
	}
	
	/**
	 * R�cup�rer la date du d�part
	 * 
	 * @return la date du d�part
	 */
	public String getDateDep() {
		return dateDep;
	}
	
	/**
	 * R�cup�rer la capacit� du train
	 * 
	 * @return la capacit� du train
	 */
	public int getCapacite() {
		return capacite;
	}
	
	/**
	 * R�cup�rer la ligne de train
	 * 
	 * @return la ligne de train
	 */
	public Ligne getLigne() {
		return ligne;
	}
	
}
