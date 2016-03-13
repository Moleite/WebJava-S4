package model;

/**
 * Modèle servant de support pour les lignes
 */
public class Ligne {

	private int numeroLigne;
	private String villeDepart;
	private String villeDestination;
	private int duree;
	
	/**
	 * Instancier une nouvelle ligne
	 * 
	 * @param numeroLigne Numéro unique de la ligne
	 * @param villeDepart Ville de départ des trains empruntant cette ligne
	 * @param villeDestination Ville d'arrivée des trains empruntant cette ligne
	 * @param duree Durée du voyage
	 */
	public Ligne(int numeroLigne, String villeDepart, String villeDestination,
			int duree) {
		this.numeroLigne = numeroLigne;
		this.villeDepart = villeDepart;
		this.villeDestination = villeDestination;
		this.duree = duree;
	}

	/**
	 * Récupérer le numéro de la ligne
	 * 
	 * @return le numéro de la ligne
	 */
	public int getNumeroLigne() {
		return numeroLigne;
	}

	/**
	 * Récupérer la ville de départ
	 * 
	 * @return la ville de départ
	 */
	public String getVilleDepart() {
		return villeDepart;
	}

	/**
	 * Récupérer la ville d'arrivée
	 * 
	 * @return la ville d'arrivée
	 */
	public String getVilleDestination() {
		return villeDestination;
	}

	/**
	 * Obtenir la durée d'un trajet sur cette ligne
	 * 
	 * @return la durée du trajet
	 */
	public int getDuree() {
		return duree;
	}	
}
