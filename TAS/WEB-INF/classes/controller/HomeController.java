package controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

public class HomeController extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		request.getRequestDispatcher("/views/home/index.jsp").forward(request, response);
	}
}
