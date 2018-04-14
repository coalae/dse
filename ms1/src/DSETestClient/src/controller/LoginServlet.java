package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

import org.json.JSONObject;

/**
 * Dieses Servlet dient dem Einloggen von Usern.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor 
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * doGet leitet zu login.jsp.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("login.jsp");
		view.forward(request, response);
	}

	/**
	 * doPost wird mittels Klick auf den Login Button gestartet. 
	 * Die Methode nimmt Username und Passwort und prueft, ob der User schon einen Account hat.
	 * User wird eingeloggt, wenn Daten richtig sind. User wird nicht eingeloggt, wenn die Login-Daten keinen Match haben.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("password"));
		
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
	    WebTarget webTarget = client.target("http://tomcat01lab.cs.univie.ac.at:31811/DSE/users/login");
	      
	    Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
	    Response responseFromWS = invocationBuilder.cookie("username",request.getParameter("username"))
	                                .cookie(new Cookie("password", request.getParameter("password")))
	                                .get();
	      
	    String json = responseFromWS.readEntity(String.class);
 	          
	    System.out.println(responseFromWS.getStatus());
	   	System.out.println(json);

	   	
		
		if (responseFromWS.getStatus() == 200 && json != null) {
			HttpSession session = request.getSession();
			
			JSONObject object = new JSONObject(json);
			session.setAttribute("username", object.getString("username"));
			request.getServletContext().setAttribute("jsonUser", object);
			
			request.getServletContext().setAttribute("id", object.getInt("id"));
			request.getServletContext().setAttribute("password", object.getString("password"));
			request.getServletContext().setAttribute("firstname", object.getString("firstname"));
			request.getServletContext().setAttribute("lastname", object.getString("lastname"));
			request.getServletContext().setAttribute("city", object.getString("city"));

			RequestDispatcher view = request.getRequestDispatcher("indexLoggedIn.jsp");
			request.setAttribute("message", "Login erfolgreich!");
			view.forward(request, response);
			
		} else {
			
			request.setAttribute("message", "Login nicht moeglich, falsches Password oder Username!");
			RequestDispatcher view = request.getRequestDispatcher("userlogin.jsp");
			view.forward(request, response);
		
		}
		

	}
	

}
