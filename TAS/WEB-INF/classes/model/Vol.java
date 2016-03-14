package model;

/**
 * Mod�le servant de support pour les vols
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
	 * @param dateDep Date de d�part du vol
	 * @param nbPlace Nombre de places du vol
	 * @param prix Prix du vol
	 */	
	public Vol(int numeroDepart, String destination, String dateDep, int nbPlace, float prix) {
		this.numeroVol = numeroDepart;
		this.dateDepart = dateDep;
		this.nbPlaces = nbPlace;
		this.destination = destination;
		this.prix = prix;		
	}
	
	public Vol(int numeroVol, String destination, String date,
			String nbPlaces, String prix) {
		this.numeroVol = numeroVol;
		this.destination = destination;
		this.dateDepart = date;
	}

	/**
	 * R�cup�rer le num�ro unique du vol
	 * 
	 * @return le num�ro du vol
	 */
	public int getNumeroVol() {
		return numeroVol;
	}
	
	/**
	 * R�cup�rer la date du vol
	 * 
	 * @return la date du vol
	 */
	public String getDateDep() {
		return dateDepart;
	}
	
	/**
	 * R�cup�rer le nombre de places du vol
	 * 
	 * @return le nombre de places du vol
	 */
	public int getCapacite() {
		return nbPlaces;
	}
	
	/**
	 * R�cup�rer la destination du vol
	 * 
	 * @return la destination du vol
	 */
	public String getDestination() {
		return destination;
	}
	
	/**
	 * R�cup�rer le prix du vol
	 * 
	 * @return la ligne de vol
	 */
	public Float getPrix() {
		return prix;
	}
	
}
