package model;

/**
 * Modèle servant de support pour les vols
 */
public class Vol {
	
	private int numeroVol;
	private String destination;
	private String dateDepart;
	private int nbPlaces;
	private float prix;
	
	
	
	/**
	 * Instancier un nouveau vol
	 * 
	 * @param numeroDepart numero du vol
	 * @param destination Destination du vol
	 * @param dateDep Date de départ du vol
	 * @param nbPlace Nombre de place du vol
	 * @param prix Prix du vol
	 */	
	public Vol(int numeroDepart, String destination, String dateDep, int nbPlace, float prix) {
		this.numeroVol = numeroDepart;
		this.dateDepart = dateDep;
		this.nbPlaces = nbPlace;
		this.destination = destination;
		this.prix = prix;		
	}
	
	/**
	 * Récupérer le numéro unique du vol
	 * 
	 * @return le numéro du vol
	 */
	public int getNumeroDepart() {
		return numeroVol;
	}
	
	/**
	 * Récupérer la date du vol
	 * 
	 * @return la date du vol
	 */
	public String getDateDep() {
		return dateDepart;
	}
	
	/**
	 * Récupérer le nombre de place du vol
	 * 
	 * @return le nombre de place du vol
	 */
	public int getCapacite() {
		return nbPlaces;
	}
	
	/**
	 * Récupérer la ligne de vol
	 * 
	 * @return la ligne de vol
	 */
	public String getDestination() {
		return destination;
	}
	
}
