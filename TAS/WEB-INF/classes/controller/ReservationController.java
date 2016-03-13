package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import model.Reservation;
import model.Vol;
import model.IntrouvableException;
import model.TAS;
import model.Compte;


public class ReservationController extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private TAS tas = TAS.getInstance();

	/**
	 * Renvoie à l'action correspondante aux paramètres
	 * 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { 
        String action = request.getParameter("action");
		action = (action == null) ? "index" : action;
		
		if(action.equals("lister"))
			this.listerAction(request, response);
		else if(action.equals("commande"))
			this.commandeAction(request, response);
		else if(action.equals("annuler"))
			this.annulerAction(request, response);
		else
			request.getRequestDispatcher("/views/erreur/404.jsp").forward(request, response);

	}
	
	/**
	 * Liste les vols en fonction des paramètres du formulaire
	 * 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void listerAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String villeDestination = request.getParameter("VilleDestination");
		String dateDepart = request.getParameter("DateDepart");
		int nbPlaces = Integer.parseInt(request.getParameter("NombrePlaces"));
		float prix = Integer.parseInt(request.getParameter("NombrePlaces"));
		
		ArrayList<Vol> vols  = (ArrayList<Vol>) tas.getVols(villeDestination, dateDepart, nbPlaces, prix);
		request.setAttribute("vols", vols);
			
		request.getRequestDispatcher("/views/reservation/vols.jsp").forward(request, response);
	}
	
	/**
	 * Permet de commander une réservation
	 * 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void commandeAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String choix = (request.getParameter("choix") == null) ? "0" : request.getParameter("choix");
		int numeroVol = Integer.parseInt(choix);
		
		if(!this.estConnecte(request)) {
			request.getSession().setAttribute("commande", numeroVol);
			response.sendRedirect("login");
			return;
		}
		
		Compte compte = (Compte) request.getSession().getAttribute("user");
		try {
			Vol vol = tas.getVol(numeroVol);
			tas.ajouterReservation(vol, compte);
		} catch (IntrouvableException e) {
			e.printStackTrace();
			request.setAttribute("erreur", "Le vol pour lequel vous souhaitez réserver n'a pas été trouvé !");
			request.getRequestDispatcher("/views/erreur/500.jsp").forward(request, response);
			return;
		}
		request.getSession().removeAttribute("commande");
		response.sendRedirect("compte?action=reservations");
	}
	
	/**
	 * Permet d'annuler une réservation
	 * 
	 * @throws IOException 
	 * @throws ServletException  
	 */
	private void annulerAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		if(!this.estConnecte(request)) {
			response.sendRedirect("login");
			return;
		}
		
		String id = (request.getParameter("id") == null) ? "0" : request.getParameter("id");
		int numeroReservation = Integer.parseInt(id);
		long timeNow = System.currentTimeMillis();
		
		try {
			Reservation reservation = tas.getReservation(numeroReservation);
			String dateDepart = reservation.getVol().getDateDep();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date dateDep;
			try {
				dateDep = formatter.parse(dateDepart);
				long dateDepinMillisec = dateDep.getTime();
				if(dateDepinMillisec - timeNow > 86400000L){
					tas.removeReservation(reservation);
				}
				else{
					request.setAttribute("erreur", "Il est trop tard pour annuler cette réservation en date du " + (reservation.getVol().getDateDep()) + " !");
					request.getRequestDispatcher("/views/erreur/500.jsp").forward(request, response);
					return;
				}			
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} catch (IntrouvableException e) {
			e.printStackTrace();
			request.setAttribute("erreur", "La réservation que vous souhaitez annuler n'a pas été trouvé !");
			request.getRequestDispatcher("/views/erreur/500.jsp").forward(request, response);
			return;
		}
		
		response.sendRedirect("compte?action=reservations");
		return;
	}
	
	/**
	 * Vérifie si l'utilisateur est connecté
	 * 
	 * @return true si il est connecté
	 */
	private boolean estConnecte(HttpServletRequest request) {
		return(request.getSession().getAttribute("user") != null);
	}
}
