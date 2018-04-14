package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Logout des zuvor bereits eingeloggten Users.
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Konstruktor
     */
    public LogoutServlet() {
        super();
        
    }

	/**
	 * Das ServletContextAttribute wird auf null gesetzt und es kommt zu einer Weiterleitung auf index.jsp 
	 * in einen Zustand, in dem der User nicht mehr eingloggt ist und somit nicht authentifiziert ist, 
	 * Daten ueber User einzusehen.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().setAttribute("jsonUser", null);
		request.getServletContext().setAttribute("username", null);
		request.getServletContext().setAttribute("id", null);
		request.getServletContext().setAttribute("lastname", null);
		request.getServletContext().setAttribute("firstname", null);
		request.getServletContext().setAttribute("city", null);
		request.getServletContext().setAttribute("password", null);

		HttpSession session = request.getSession(false);
		if (session != null) {
		    session.invalidate();
		}
	    request.setAttribute("message", "Logout erfolgreich!");
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		view.forward(request, response);
	}

	/**
	 * doPost forwarded zum index.jsp.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		view.forward(request, response);
	}

}
