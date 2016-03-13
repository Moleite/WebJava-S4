package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.SNGF;
import model.Voyageur;
import model.VoyageurIntrouvableException;

public class LoginController extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private SNGF sngf = SNGF.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		request.getRequestDispatcher("/views/login/index.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String loginVoyageur = request.getParameter("LoginVoyageur");
		String passVoyageur = request.getParameter("PassVoyageur");
		HttpSession session = request.getSession();
		
		Voyageur user;
		try {
			user = sngf.getVoyageur(loginVoyageur, passVoyageur);
			session.setAttribute("user", user);		
			if(session.getAttribute("commande") != null) {
				response.sendRedirect("billet?action=commande&choix=" + session.getAttribute("commande"));
				return;
			}
			response.sendRedirect("home");
		} catch (VoyageurIntrouvableException e) {
			e.printStackTrace();
			response.sendRedirect("login?erreur");
		}
	}
}
