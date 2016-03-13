package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import model.Reservation;
import model.TAS;
import model.Compte;
import model.CompteExisteDejaException;


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
		else if(action.equals("reservation"))
			this.reservationsAction(request, response);
		else if(action.equals("deconnexion"))
			this.deconnexionAction(request, response);
		else
			request.getRequestDispatcher("/views/erreur/404.jsp").forward(request, response);
	}

	/**
	 * Afficher la page d'accueil de l'espace compte
	 * 
	 * @param request requête HTTP courante
	 * @param response réponse HTTP pour la requête courante
	 * @throws IOException
	 * @throws ServletException
	 */
	private void indexAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if(!this.estConnecte(request)) {
			response.sendRedirect("login");
			return;
		}
		
		request.getRequestDispatcher("/views/voyageur/index.jsp").forward(request, response);
	}

	/**
	 * Inscrire un nouveau comtpe
	 * 
	 * @param request requête HTTP courante
	 * @param response réponse HTTP pour la requête courante
	 * @throws IOException
	 * @throws ServletException
	 */
	private void creerAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String login = request.getParameter("Login");
		String mdp = request.getParameter("Mdp");
		String nom = request.getParameter("Nom");
		String prenom = request.getParameter("Prenom");
		String role = request.getParameter("Role");
		
		if("POST".equals(request.getMethod())) {
			try {
				HttpSession session = request.getSession();
				session.setAttribute("user", tas.ajouterCompte(login, mdp, nom, prenom, role));		
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
		
		request.getRequestDispatcher("/views/voyageur/creer.jsp").forward(request, response);
	}
	
	/**
	 * Récupérer les reservation du compte en session
	 * 
	 * @param request requête HTTP courante
	 * @param response réponse HTTP pour la requête courante
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
	 * Déconnecter le compte en session
	 * 
	 * @param request requête HTTP courante
	 * @param response réponse HTTP pour la requête courante
	 * @throws IOException
	 * @throws ServletException
	 */
	private void deconnexionAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getSession().removeAttribute("user");
		response.sendRedirect("home");
	}
	
	/**
	 * Vérifie si un compte est actuellement connecté
	 * 
	 * @param request requête HTTP courante
	 * @return true si l'utilisateur est connecté, false sinon
	 */
	private boolean estConnecte(HttpServletRequest request) {
		return(request.getSession().getAttribute("user") != null);
	}
}