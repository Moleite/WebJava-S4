package model;

/**
 * Modèle servant de support pour les départs
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
	 * Récupérer le numéro unique du départ
	 * 
	 * @return le numéro du départ
	 */
	public int getNumeroDepart() {
		return numeroDepart;
	}
	
	/**
	 * Récupérer la date du départ
	 * 
	 * @return la date du départ
	 */
	public String getDateDep() {
		return dateDep;
	}
	
	/**
	 * Récupérer la capacité du train
	 * 
	 * @return la capacité du train
	 */
	public int getCapacite() {
		return capacite;
	}
	
	/**
	 * Récupérer la ligne de train
	 * 
	 * @return la ligne de train
	 */
	public Ligne getLigne() {
		return ligne;
	}
	
}
