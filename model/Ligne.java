package model;

/**
 * Mod�le servant de support pour les lignes
 */
public class Ligne {

	private int numeroLigne;
	private String villeDepart;
	private String villeDestination;
	private int duree;
	
	/**
	 * Instancier une nouvelle ligne
	 * 
	 * @param numeroLigne Num�ro unique de la ligne
	 * @param villeDepart Ville de d�part des trains empruntant cette ligne
	 * @param villeDestination Ville d'arriv�e des trains empruntant cette ligne
	 * @param duree Dur�e du voyage
	 */
	public Ligne(int numeroLigne, String villeDepart, String villeDestination,
			int duree) {
		this.numeroLigne = numeroLigne;
		this.villeDepart = villeDepart;
		this.villeDestination = villeDestination;
		this.duree = duree;
	}

	/**
	 * R�cup�rer le num�ro de la ligne
	 * 
	 * @return le num�ro de la ligne
	 */
	public int getNumeroLigne() {
		return numeroLigne;
	}

	/**
	 * R�cup�rer la ville de d�part
	 * 
	 * @return la ville de d�part
	 */
	public String getVilleDepart() {
		return villeDepart;
	}

	/**
	 * R�cup�rer la ville d'arriv�e
	 * 
	 * @return la ville d'arriv�e
	 */
	public String getVilleDestination() {
		return villeDestination;
	}

	/**
	 * Obtenir la dur�e d'un trajet sur cette ligne
	 * 
	 * @return la dur�e du trajet
	 */
	public int getDuree() {
		return duree;
	}	
}
