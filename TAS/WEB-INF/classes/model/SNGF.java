package model;

import java.util.List;

import persistence.BilletDAO;
import persistence.DepartDAO;
import persistence.VoyageurDAO;

/**
 * Classe g�n�rale de l'application en charge de d�l�guer les diff�rentes actions possibles sur le mod�le
 */
public class SNGF {
	
	private static SNGF instance = new SNGF();
	private static VoyageurDAO voyageurDao = VoyageurDAO.getInstance();
	private static BilletDAO billetDao = BilletDAO.getInstance();
	private static DepartDAO departDao = DepartDAO.getInstance();

	/**
	 * R�cup�rer un voyageur � partir de son login et de son mot de passe
	 * 
	 * @param loginVoyageur Login suppos� du voyageur
	 * @param passVoyageur Mot de passe suppos� du voyageur
	 * @return le voyageur correspondant aux param�tres
	 * @throws VoyageurIntrouvableException si aucun voyageur ne poss�de cette combinaison
	 */
	public Voyageur getVoyageur(String loginVoyageur, String passVoyageur)
		 throws VoyageurIntrouvableException{
		Voyageur voyageur = voyageurDao.trouverVoyageur(loginVoyageur, passVoyageur);
		return voyageur;
	}
	
	/**
	 * R�cup�rer un billet � partir de son num�ro unique
	 * 
	 * @param numeroBillet num�ro du billet unique
	 * @return le billet correspondant au num�ro
	 * @throws IntrouvableException si le billet ou ses diff�rentes composantes (d�part, voyageur) sont introuvables
	 */
	public Billet getBillet(int numeroBillet) throws IntrouvableException {
		return billetDao.trouverBillet(numeroBillet);
	}
	
	/**
	 * R�cup�rer l'ensemble des billets d'un voyageur
	 * 
	 * @param voyageur le voyageur dont on recherche les billets
	 * @return une liste de billets
	 */
	public List<Billet> getBillets(Voyageur voyageur) {
		return billetDao.recupererBillets(voyageur);
	}
	
	/**
	 * R�cup�rer un d�part � partir de son num�ro unique
	 * 
	 * @param numeroDepart le num�ro du d�part
	 * @return le d�part correspondant
	 * @throws IntrouvableException si le d�part ou la ligne correspondante est introuvable
	 */
	public Depart getDepart(int numeroDepart) throws IntrouvableException {
		return departDao.trouverDepart(numeroDepart);
	}
	
	/**
	 * R�cup�rer un d�part � partir d'un billet
	 * 
	 * @param billet le billet dont on recherche le d�part
	 * @return le d�part correspondant au billet
	 * @throws IntrouvableException si le d�part ou la ligne du d�part est introuvable
	 */
	public Depart getDepart(Billet billet) throws IntrouvableException {
		return departDao.trouverDepart(billet);
	}
	
	/**
	 * R�cup�rer l'ensemble des d�parts suivant certains crit�res
	 * 
	 * @param villeDepart la ville de d�part de la ligne
	 * @param villeDestination la ville de destination de la ligne
	 * @param dateDebut le d�part doit avoir lieu apr�s cette date (format jj/mm/yyyy)
	 * @param dateFin le d�part doit avoir lieu avant cette date (format jj/mm/yyyy)
	 * @return une liste de d�parts
	 */
	public List<Depart> getDeparts(String villeDepart,String villeDestination,String dateDebut,String dateFin){
		return departDao.recupererDeparts(villeDepart, villeDestination, dateDebut, dateFin);
	}
	
	/**
	 * Ajouter un voyageur
	 * 
	 * @param loginVoyageur le login choisi pour le voyageur
	 * @param passVoyageur le mot de passe choisi pour le voyageur
	 * @param nomVoyageur le nom choisi pour le voyageur
	 * @param adresseVoyageur l'adresse choisie pour le voyageur
	 * @return le voyageur ainsi ajout�
	 * @throws VoyageurExisteDejaException si un voyageur correspondant au login existe d�j�
	 */
	public Voyageur addVoyageur(String loginVoyageur, String passVoyageur, String nomVoyageur, String adresseVoyageur) 
			throws VoyageurExisteDejaException {
		Voyageur voyageur = new Voyageur(loginVoyageur, passVoyageur, nomVoyageur, adresseVoyageur);
		voyageurDao.ajouterVoyageur(voyageur);
		return voyageur;
	}
	
	/**
	 * Ajouter un billet
	 * 
	 * @param depart d�part du billet
	 * @param voyageur voyageur futur possesseur du billet
	 */
	public void addBillet(Depart depart, Voyageur voyageur) {
		billetDao.ajouterBillet(depart, voyageur);
	}

	/**
	 * Supprimer un billet
	 * 
	 * @param billet le billet � supprimer
	 */
	public void removeBillet(Billet billet) {
		billetDao.supprimerBillet(billet);
	}
	
	/**
	 * R�cup�rer l'instance unique de SNGF (Singleton)
	 * 
	 * @return l'instance
	 */
	public static SNGF getInstance() {
		return instance;
	}
}
