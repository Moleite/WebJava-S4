package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.TAS;
import model.Compte;
import model.CompteIntrouvableException;

public class LoginController extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private TAS tas = TAS.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		request.getRequestDispatcher("/views/login/index.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String login = request.getParameter("login");
		String mdp = request.getParameter("mdp");
		HttpSession session = request.getSession();
		
		Compte compte;
		try {
			compte = tas.getCompte(login, mdp);
			session.setAttribute("user", compte);		
			if(session.getAttribute("commande") != null) {
				response.sendRedirect("reservation?action=commande&choix=" + session.getAttribute("commande"));
				return;
			}
			response.sendRedirect("home");
		} catch (CompteIntrouvableException e) {
			e.printStackTrace();
			response.sendRedirect("login?erreur");
		}
	}
}
