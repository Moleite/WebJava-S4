package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import model.Billet;
import model.Depart;
import model.IntrouvableException;
import model.SNGF;
import model.Voyageur;


public class BilletController extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private SNGF sngf = SNGF.getInstance();

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
	 * Liste les départs en fonction des paramètres du formulaire
	 * 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void listerAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String villeDepart = request.getParameter("VilleDepart");
		String villeDestination = request.getParameter("VilleDestination");
		String dateDebut = request.getParameter("DateDebut");
		String dateFin = request.getParameter("DateFin");
		
		ArrayList<Depart> departs  = (ArrayList<Depart>) sngf.getDeparts(villeDepart, villeDestination, dateDebut, dateFin);
		request.setAttribute("departs", departs);
			
		request.getRequestDispatcher("/views/billet/departs.jsp").forward(request, response);
	}
	
	/**
	 * Permet de commander un billet
	 * 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void commandeAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String choix = (request.getParameter("choix") == null) ? "0" : request.getParameter("choix");
		int numeroDepart = Integer.parseInt(choix);
		
		if(!this.estConnecte(request)) {
			request.getSession().setAttribute("commande", numeroDepart);
			response.sendRedirect("login");
			return;
		}
		
		Voyageur voyageur = (Voyageur) request.getSession().getAttribute("user");
		try {
			Depart depart = sngf.getDepart(numeroDepart);
			sngf.addBillet(depart, voyageur);
		} catch (IntrouvableException e) {
			e.printStackTrace();
			request.setAttribute("erreur", "Le départ pour lequel vous souhaitez acheter un billet n'a pas été trouvé !");
			request.getRequestDispatcher("/views/erreur/500.jsp").forward(request, response);
			return;
		}
		request.getSession().removeAttribute("commande");
		response.sendRedirect("voyageur?action=billets");
	}
	
	/**
	 * Permet d'annuler un billet
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
		int numeroBillet = Integer.parseInt(id);
		long timeNow = System.currentTimeMillis();
		
		try {
			Billet billet = sngf.getBillet(numeroBillet);
			String dateDepart = billet.getDepart().getDateDep();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date dateDep;
			try {
				dateDep = formatter.parse(dateDepart);
				long dateDepinMillisec = dateDep.getTime();
				if(dateDepinMillisec - timeNow > 86400000L){
					sngf.removeBillet(billet);
				}
				else{
					request.setAttribute("erreur", "Il est trop tard pour annuler ce billet en date du " + (billet.getDepart().getDateDep()) + " !");
					request.getRequestDispatcher("/views/erreur/500.jsp").forward(request, response);
					return;
				}			
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} catch (IntrouvableException e) {
			e.printStackTrace();
			request.setAttribute("erreur", "Le billet que vous souhaitez annuler n'a pas été trouvé !");
			request.getRequestDispatcher("/views/erreur/500.jsp").forward(request, response);
			return;
		}
		
		response.sendRedirect("voyageur?action=billets");
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
