package model;

/**
 * Mod�le support des comptes
 */
public class Compte {
	
	private String login;
	private String mdp;
	private String nom;
	private String prenom;
	private String role;
	
	/**
	 * Instancier un nouveau compte
	 * 
	 * @param login Login du compte
	 * @param mdp Mot de passe du compte
	 * @param nom Nom du compte
	 * @param prenom Prenom du compte
	 * @param role Role du compte
	 */
	public Compte(String login, String mdp, String nom, String prenom, String role) {
		this.login = login;
		this.mdp = mdp;
		this.nom = nom;
		this.prenom = prenom;
		this.role = role;
	}
	
	/**
	 * R�cup�rer le login du compte
	 * 
	 * @return le login du compte
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * R�cup�rer le mot de passe du compte
	 * 
	 * @return le mot de passe du compte
	 */
	public String getMdp() {
		return mdp;
	}

	/**
	 * R�cup�rer le nom du compte
	 * 
	 * @return le nom du compte
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * R�cup�rer l'adresse du compte
	 * 
	 * @return l'adresse du compte
	 */
	public String getPrenom() {
		return prenom;
	}
	
	/**
	 * R�cup�rer le role du compte
	 * 
	 * @return le role du compte
	 */
	public String getRole() {
		return role;
	}
	
	
	@Override
	public String toString() {
		return this.nom + " (" + this.login + ")";
	}
}
