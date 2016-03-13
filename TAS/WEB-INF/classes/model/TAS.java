package model;

import java.util.List;

import persistence.*;


/**
 * Classe g�n�rale de l'application en charge de d�l�guer les diff�rentes actions possibles sur le mod�le
 */
public class TAS {
	
	private static TAS instance = new TAS();
	private static CompteDAO compteDao = CompteDAO.getInstance();
	private static ReservationDAO reservationDao = ReservationDAO.getInstance();
	private static VolDAO volDao = VolDAO.getInstance();

	/**
	 * R�cup�rer un compte � partir de son login et de son mot de passe
	 * 
	 * @param login Login suppos� du compte
	 * @param mdp Mot de passe suppos� du compte
	 * @return le compte correspondant aux param�tres
	 * @throws CompteIntrouvableException si aucun voyageur ne poss�de cette combinaison
	 */
	public Compte getCompte(String login, String mdp)
		 throws CompteIntrouvableException{
		Compte compte = compteDao.trouverCompte(login, mdp);
		return compte;
	}
	
	/**
	 * R�cup�rer une r�servation � partir de son num�ro unique
	 * 
	 * @param numeroReservation Num�ro unique de la r�servation
	 * @return la r�servation correspondante au num�ro
	 * @throws IntrouvableException si la r�servation ou ses diff�rentes composantes (vol, compte) sont introuvables
	 */
	public Reservation getReservation(int numeroReservation) throws IntrouvableException {
		return reservationDao.trouverReservation(numeroReservation);
	}
	
	/**
	 * R�cup�rer l'ensemble des r�servation d'un compte
	 * 
	 * @param compte le compte dont on recherche les r�servation
	 * @return une liste de r�servation
	 */
	public List<Reservation> getReservations(Compte compte) {
		return reservationDao.recupererReservations(compte);
	}
	
	/**
	 * R�cup�rer un vol � partir de son num�ro unique
	 * 
	 * @param numeroVol le num�ro du vol
	 * @return le vol correspondant
	 * @throws IntrouvableException si le vol correspondant est introuvable
	 */
	public Vol getVol(int numeroVol) throws IntrouvableException {
		return volDao.trouverVol(numeroVol);
	}
	
	/**
	 * R�cup�rer un un vol � partir d'une r�servation
	 * 
	 * @param reserv la r�servation dont on recherche le vol
	 * @return le vol correspondant � la r�servation
	 * @throws IntrouvableException si le vol 
	 */
	public Vol getVol(Reservation reserv) throws IntrouvableException {
		return volDao.trouverVol(reserv);
	}
	
	/**
	 * R�cup�rer l'ensemble des v suivant certains crit�res
	 * 
	 * 
	 * @param villeDestination la ville de destination du vol
	 * @param dateDebut le vol doit avoir lieu apr�s cette date (format jj/mm/yyyy)
	 * @param nbPlace le nombre de place du vol (format jj/mm/yyyy)
	 * @return une liste de vols
	 */
	public List<Vol> getVols(String villeDestination, String dateDebut, int nbPlace, float prix){
		return volDao.recupererVols(villeDestination, dateDebut, nbPlace, prix);
	}
	
	/**
	 * Ajouter un comtpe
	 * 
	 * @param login le login choisi pour le compte
	 * @param mdp le mot de passe choisi pour le compte
	 * @param nom le nom choisi pour le compte
	 * @param prenom le prenom choisi pour le compte
	 * @param le role du compte (utilisateur ou admin)
	 * @return le compte ainsi ajout�
	 * @throws CompteExisteDejaException si un voyageur correspondant au login existe d�j�
	 */
	public Compte ajouterCompte(String login, String mdp, String nom, String prenom, String role) 
			throws CompteExisteDejaException {
		Compte compte = new Compte(login, mdp, nom, prenom,role );
		compteDao.ajouterCompte(compte);
		return compte;
	}
	
	/**
	 * Ajouter une r�servation
	 * 
	 * @param vol vol de la r�servation
	 * @param compte comtpe futur de la r�servation
	 */
	public void ajouterReservation(Vol vol, Compte compte) {
		reservationDao.ajouterReservation(vol, compte);
	}

	/**
	 * Supprimer une r�servation
	 * 
	 * @param reservation la r�servation � supprimer
	 */
	public void removeReservation(Reservation reservation) {
		reservationDao.supprimerReservation(reservation);
	}
	
	/**
	 * R�cup�rer l'instance unique de SNGF (Singleton)
	 * 
	 * @return l'instance
	 */
	public static TAS getInstance() {
		return instance;
	}
}