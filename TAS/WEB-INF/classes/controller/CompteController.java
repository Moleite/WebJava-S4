package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import model.IntrouvableException;
import model.Reservation;
import model.TAS;
import model.Compte;
import model.CompteExisteDejaException;
import model.Vol;


public class CompteController extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private TAS tas = TAS.getInstance();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { 
        
		String action = request.getParameter("action");
		action = (action == null) ? "index" : action;
		
		if(action.equals("index"))
			this.indexAction(request, response);
		else if(action.equals("creer"))
			this.creerAction(request, response);
		else if(action.equals("reservations"))
			this.reservationsAction(request, response);
		else if(action.equals("deconnexion"))
			this.deconnexionAction(request, response);
		else if(action.equals("ajouter"))
			this.ajouterAction(request, response);
		else
			request.getRequestDispatcher("/views/erreur/404.jsp").forward(request, response);
	}

	/**
	 * Afficher la page d'accueil de l'espace compte
	 * 
	 * @param request requ�te HTTP courante
	 * @param response r�ponse HTTP pour la requ�te courante
	 * @throws IOException
	 * @throws ServletException
	 */
	private void indexAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if(!this.estConnecte(request)) {
			response.sendRedirect("login");
			return;
		}
		request.getRequestDispatcher("/views/compte/index.jsp").forward(request, response);
	}

	/**
	 * Inscrire un nouveau comtpe
	 * 
	 * @param request requ�te HTTP courante
	 * @param response r�ponse HTTP pour la requ�te courante
	 * @throws IOException
	 * @throws ServletException
	 */
	private void creerAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String login = request.getParameter("login");
		String mdp = request.getParameter("mdp");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		
		
		if("POST".equals(request.getMethod())) {
			try {
				HttpSession session = request.getSession();
				session.setAttribute("user", tas.ajouterCompte(login, mdp, nom, prenom, "client"));		
				if(session.getAttribute("commande") != null) {
					response.sendRedirect("reservation?action=commande&choix=" + session.getAttribute("commande"));
					return;
				}
				response.sendRedirect("home");
				return;
			}
			catch (CompteExisteDejaException e) {
				e.printStackTrace();
				request.setAttribute("erreur", true);
			}
		}
		
		request.getRequestDispatcher("/views/compte/creer.jsp").forward(request, response);
	}
	
	/**
	 * R�cup�rer les reservation du compte en session
	 * 
	 * @param request requ�te HTTP courante
	 * @param response r�ponse HTTP pour la requ�te courante
	 * @throws IOException
	 * @throws ServletException
	 */
	private void reservationsAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if(!this.estConnecte(request)) {
			response.sendRedirect("login");
			return;
		}
		
		Compte compte = (Compte) request.getSession().getAttribute("user");
		ArrayList<Reservation> reservations = (ArrayList<Reservation>) this.tas.getReservations(compte);
				
		request.setAttribute("reservations", reservations);
		request.getRequestDispatcher("/views/compte/reservations.jsp").forward(request, response);
	}

	/**
	 * D�connecter le compte en session
	 * 
	 * @param request requ�te HTTP courante
	 * @param response r�ponse HTTP pour la requ�te courante
	 * @throws IOException
	 * @throws ServletException
	 */
	private void deconnexionAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getSession().removeAttribute("user");
		response.sendRedirect("home");
	}
	
	private void ajouterAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String destination = request.getParameter("Destination");
		String date = request.getParameter("DateDebut");
		String nbPlaces = request.getParameter("NombrePlaces");
		String prix = request.getParameter("Prix");
		
//		int nbPlacesi = Integer.parseInt(nbPlaces);
//		float prixi = Integer.parseInt(prix);
		
		
		
		tas.ajouterVol(destination, date, nbPlaces, prix);
		response.sendRedirect("compte?action=index");
	}
	
	/**
	 * V�rifie si un compte est actuellement connect�
	 * 
	 * @param request requ�te HTTP courante
	 * @return true si l'utilisateur est connect�, false sinon
	 */
	private boolean estConnecte(HttpServletRequest request) {
		return(request.getSession().getAttribute("user") != null);
	}
}