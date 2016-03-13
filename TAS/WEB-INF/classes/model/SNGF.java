package model;

import java.util.List;

import persistence.BilletDAO;
import persistence.DepartDAO;
import persistence.VoyageurDAO;

/**
 * Classe générale de l'application en charge de déléguer les différentes actions possibles sur le modèle
 */
public class SNGF {
	
	private static SNGF instance = new SNGF();
	private static VoyageurDAO voyageurDao = VoyageurDAO.getInstance();
	private static BilletDAO billetDao = BilletDAO.getInstance();
	private static DepartDAO departDao = DepartDAO.getInstance();

	/**
	 * Récupérer un voyageur à partir de son login et de son mot de passe
	 * 
	 * @param loginVoyageur Login supposé du voyageur
	 * @param passVoyageur Mot de passe supposé du voyageur
	 * @return le voyageur correspondant aux paramètres
	 * @throws VoyageurIntrouvableException si aucun voyageur ne possède cette combinaison
	 */
	public Voyageur getVoyageur(String loginVoyageur, String passVoyageur)
		 throws VoyageurIntrouvableException{
		Voyageur voyageur = voyageurDao.trouverVoyageur(loginVoyageur, passVoyageur);
		return voyageur;
	}
	
	/**
	 * Récupérer un billet à partir de son numéro unique
	 * 
	 * @param numeroBillet numéro du billet unique
	 * @return le billet correspondant au numéro
	 * @throws IntrouvableException si le billet ou ses différentes composantes (départ, voyageur) sont introuvables
	 */
	public Billet getBillet(int numeroBillet) throws IntrouvableException {
		return billetDao.trouverBillet(numeroBillet);
	}
	
	/**
	 * Récupérer l'ensemble des billets d'un voyageur
	 * 
	 * @param voyageur le voyageur dont on recherche les billets
	 * @return une liste de billets
	 */
	public List<Billet> getBillets(Voyageur voyageur) {
		return billetDao.recupererBillets(voyageur);
	}
	
	/**
	 * Récupérer un départ à partir de son numéro unique
	 * 
	 * @param numeroDepart le numéro du départ
	 * @return le départ correspondant
	 * @throws IntrouvableException si le départ ou la ligne correspondante est introuvable
	 */
	public Depart getDepart(int numeroDepart) throws IntrouvableException {
		return departDao.trouverDepart(numeroDepart);
	}
	
	/**
	 * Récupérer un départ à partir d'un billet
	 * 
	 * @param billet le billet dont on recherche le départ
	 * @return le départ correspondant au billet
	 * @throws IntrouvableException si le départ ou la ligne du départ est introuvable
	 */
	public Depart getDepart(Billet billet) throws IntrouvableException {
		return departDao.trouverDepart(billet);
	}
	
	/**
	 * Récupérer l'ensemble des départs suivant certains critères
	 * 
	 * @param villeDepart la ville de départ de la ligne
	 * @param villeDestination la ville de destination de la ligne
	 * @param dateDebut le départ doit avoir lieu après cette date (format jj/mm/yyyy)
	 * @param dateFin le départ doit avoir lieu avant cette date (format jj/mm/yyyy)
	 * @return une liste de départs
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
	 * @return le voyageur ainsi ajouté
	 * @throws VoyageurExisteDejaException si un voyageur correspondant au login existe déjà
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
	 * @param depart départ du billet
	 * @param voyageur voyageur futur possesseur du billet
	 */
	public void addBillet(Depart depart, Voyageur voyageur) {
		billetDao.ajouterBillet(depart, voyageur);
	}

	/**
	 * Supprimer un billet
	 * 
	 * @param billet le billet à supprimer
	 */
	public void removeBillet(Billet billet) {
		billetDao.supprimerBillet(billet);
	}
	
	/**
	 * Récupérer l'instance unique de SNGF (Singleton)
	 * 
	 * @return l'instance
	 */
	public static SNGF getInstance() {
		return instance;
	}
}
