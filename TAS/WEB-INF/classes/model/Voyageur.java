package model;

/**
 * Mod�le support des voyageurs
 */
public class Voyageur {
	
	private String loginVoyageur;
	private String passVoyageur;
	private String nomVoyageur;
	private String adresseVoyageur;
	
	/**
	 * Instancier un nouveau voyageur
	 * 
	 * @param loginVoyageur Login unique du voyageur
	 * @param passVoyageur Mot de passe du voyageur (ne sera pas encrypt�)
	 * @param nomVoyageur Nom du voyageur
	 * @param adresseVoyageur Adresse du voyageur
	 */
	public Voyageur(String loginVoyageur, String passVoyageur, String nomVoyageur, String adresseVoyageur) {
		this.loginVoyageur = loginVoyageur;
		this.passVoyageur = passVoyageur;
		this.nomVoyageur = nomVoyageur;
		this.adresseVoyageur = adresseVoyageur;
	}
	
	/**
	 * R�cup�rer le login du voyageur
	 * 
	 * @return le login du voyageur
	 */
	public String getLoginVoyageur() {
		return loginVoyageur;
	}

	/**
	 * R�cup�rer le mot de passe du voyageur
	 * 
	 * @return le mot de passe du voyageur (en clair)
	 */
	public String getPassVoyageur() {
		return passVoyageur;
	}

	/**
	 * R�cup�rer le nom du voyageur
	 * 
	 * @return le nom du voyageur
	 */
	public String getNomVoyageur() {
		return nomVoyageur;
	}

	/**
	 * R�cup�rer l'adresse du voyageur
	 * 
	 * @return l'adresse du voyageur
	 */
	public String getAdresseVoyageur() {
		return adresseVoyageur;
	}
	
	@Override
	public String toString() {
		return this.nomVoyageur + " (" + this.loginVoyageur + ")";
	}
}
