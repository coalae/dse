package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Das ChangePasswordServlet dient dazu, dass die Aenderung des Passwortes des
 * eingloggten Users durchgefuehrt werden kann.
 */
@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Konstruktor
     */
    public ChangePasswordServlet() {
        super();
    }


		/**
		 * doGet leitet auf meinprofil.jsp weiter
		 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("meinprofil.jsp");
		view.forward(request, response);
	}


	 /**
		* doPost nimmt die Aenderungsanfrage entgegen und macht an den DSE Server einen Request, dass die Daten
		* geaendert werden. Wenn die Aenderung erfolgreich durchgefuehrt wurde, wird das neue Passwort in meinprofil.jsp
		* angezeigt. Wenn keine erfolgreiche Aenderung stattfinden konnte, wird meinprofil.jsp mit den alten Daten vor
		* der Aenderungsanfrage angezeigt.
		*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = String.valueOf(request.getServletContext().getAttribute("id"));
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String password = (String) request.getParameter("password");
		String firstname = (String) request.getServletContext().getAttribute("firstname");
		String lastname = (String) request.getServletContext().getAttribute("lastname");
		String city = (String) request.getServletContext().getAttribute("city");

		HttpURLConnection c = null;
		    try {
		        URL u = new URL("http://tomcat01lab.cs.univie.ac.at:31811/DSE/users/changePassword");
		        c = (HttpURLConnection) u.openConnection();
		        c.setRequestMethod("POST");
		        c.setRequestProperty("Content-length", "0");

		        c.setRequestProperty("id", id);
		        c.setRequestProperty("username", username);
		        c.setRequestProperty("password", password);
		        c.setRequestProperty("firstname", firstname);
		        c.setRequestProperty("lastname", lastname);
		        c.setRequestProperty("city", city);

		        c.setUseCaches(false);
		        c.setAllowUserInteraction(false);

		        //c.setConnectTimeout(timeout);
		        //c.setReadTimeout(timeout);
		        c.connect();

		        int status = c.getResponseCode();
		        String message;
	            RequestDispatcher view;
	            if (status == 200) {
	            	 BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
	            	 message = br.readLine();
	                 br.close();
	     			 request.getServletContext().setAttribute("password", password);
	                 view = request.getRequestDispatcher("meinprofil.jsp");
	            }
	            else {
	            	BufferedReader br = new BufferedReader(new InputStreamReader(c.getErrorStream()));
	                message = br.readLine();
	                br.close();
	            	view = request.getRequestDispatcher("meinprofil.jsp");
	            }
	            request.setAttribute("message", message);
				view.forward(request, response);


		    } catch (MalformedURLException ex) {

		        ex.printStackTrace();

		    } catch (IOException ex) {

		    	ex.printStackTrace();

		    } finally {

		       if (c != null) {
		          try {

		              c.disconnect();

		          } catch (Exception ex) {

		        	  ex.printStackTrace();

		          }
		       }
		    }

	}

}
