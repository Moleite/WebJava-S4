package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import model.Billet;
import model.SNGF;
import model.Voyageur;
import model.VoyageurExisteDejaException;


public class VoyageurController extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private SNGF sngf = SNGF.getInstance();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { 
        
		String action = request.getParameter("action");
		action = (action == null) ? "index" : action;
		
		if(action.equals("index"))
			this.indexAction(request, response);
		else if(action.equals("creer"))
			this.creerAction(request, response);
		else if(action.equals("billets"))
			this.billetsAction(request, response);
		else if(action.equals("deconnexion"))
			this.deconnexionAction(request, response);
		else
			request.getRequestDispatcher("/views/erreur/404.jsp").forward(request, response);
	}

	/**
	 * Afficher la page d'accueil de l'espace voyageurs
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
	 * Inscrire un nouveau voyageur
	 * 
	 * @param request requête HTTP courante
	 * @param response réponse HTTP pour la requête courante
	 * @throws IOException
	 * @throws ServletException
	 */
	private void creerAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String loginVoyageur = request.getParameter("LoginVoyageur");
		String passVoyageur = request.getParameter("PassVoyageur");
		String nomVoyageur = request.getParameter("NomVoyageur");
		String adresseVoyageur = request.getParameter("AdresseVoyageur");
		
		if("POST".equals(request.getMethod())) {
			try {
				HttpSession session = request.getSession();
				session.setAttribute("user", sngf.addVoyageur(loginVoyageur, passVoyageur, nomVoyageur, adresseVoyageur));		
				if(session.getAttribute("commande") != null) {
					response.sendRedirect("billet?action=commande&choix=" + session.getAttribute("commande"));
					return;
				}
				response.sendRedirect("home");
				return;
			}
			catch (VoyageurExisteDejaException e) {
				e.printStackTrace();
				request.setAttribute("erreur", true);
			}
		}
		
		request.getRequestDispatcher("/views/voyageur/creer.jsp").forward(request, response);
	}
	
	/**
	 * Récupérer les billets du voyageur en session
	 * 
	 * @param request requête HTTP courante
	 * @param response réponse HTTP pour la requête courante
	 * @throws IOException
	 * @throws ServletException
	 */
	private void billetsAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if(!this.estConnecte(request)) {
			response.sendRedirect("login");
			return;
		}
		
		Voyageur voyageur = (Voyageur) request.getSession().getAttribute("user");
		ArrayList<Billet> billets = (ArrayList<Billet>) this.sngf.getBillets(voyageur);
				
		request.setAttribute("billets", billets);
		request.getRequestDispatcher("/views/voyageur/billets.jsp").forward(request, response);
	}

	/**
	 * Déconnecter le voyageur en session
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
	 * Vérifie si un voyageur est actuellement connecté
	 * 
	 * @param request requête HTTP courante
	 * @return true si l'utilisateur est connecté, false sinon
	 */
	private boolean estConnecte(HttpServletRequest request) {
		return(request.getSession().getAttribute("user") != null);
	}
}
