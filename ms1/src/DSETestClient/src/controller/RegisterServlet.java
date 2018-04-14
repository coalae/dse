package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Das Servlet RegisterServlet registriert einen User, der seine Daten im Registrierungsformular eingegeben hat und 
 * eine Anfrage gestellt hat. Je nachdem ob die Registrierung durchgefuehrt werden konnte oder nicht, wird die 
 * entsprechende Status-Meldung geprueft und jeweils der passende Fall durchgefuehrt.
 * Falls der User angelegt werden konnte, bekommt man den Status-Code 200 und die Nachricht "User wurde hinzugefuegt"
 * zurueck und man wird auf userlogin.jsp weitergeleitet.
 * Falls der User nicht angelegt werden konnte, bekommt man den Status-Code 400 und die Nachricht "User existiert schon" 
 * zurueck und man wird auf userregistrieren.jsp weitergeleitet, damit man den Registrierungsvorgang nochmals probieren kann.
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
 
	    /**
	     * Konstruktor
	     */
	    public RegisterServlet() {
	        super();
	    }

		/**
		 * doGet leitet auf index.jsp weiter
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		}

		/**
		 * doPost nimmt die Eingaben der Userdaten ueber das entsprechende Formular entgegen und versucht, den User in der Datenbank anzulegen.
		 * Wenn Eingabefelder leer: wird "Eingabe erforderlich" angezeigt und schon im JSP File abgefangen.
		 * Wenn Fehler waren bzw. User schon existiert: Anzeige, dass Fehler passiert ist und es findet keine Registrierung statt.
		 * Wenn User erstellt wurde: Nachricht, dass Useraccount erstellt wurde und man kann sich nun einloggen.
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			
			int id = 0; // dummy id (weil auto-increment in DB setzt korrekte next id)
			String username = (String) request.getParameter("username");
			String password = (String) request.getParameter("password");
			String firstname = (String) request.getParameter("firstname");
			String lastname = (String) request.getParameter("lastname");
			String city = (String) request.getParameter("city");		


			
			HttpURLConnection c = null;
		    try {
		        URL u = new URL("http://tomcat01lab.cs.univie.ac.at:31811/DSE/users/register?username="+username+"&password="+password+"&firstname="+ firstname +
		        		"&lastname="+lastname+"&city="+city);
		        c = (HttpURLConnection) u.openConnection();
		        c.setRequestMethod("GET");
		        c.setRequestProperty("Content-length", "0");
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
                     view = request.getRequestDispatcher("userlogin.jsp");
                }
                else {
                	BufferedReader br = new BufferedReader(new InputStreamReader(c.getErrorStream()));
                    message = br.readLine();
                    br.close();
                	view = request.getRequestDispatcher("userregistrieren.jsp");
                }
                request.setAttribute("message", message);
				view.forward(request, response);
		        /*
		        switch (status) {
		            case 200:
		            case 201:
		                BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
		                StringBuilder sb = new StringBuilder();
		                String line;
		                while ((line = br.readLine()) != null) {
		                    sb.append(line+"\n");
		                }
		                br.close();
		                return sb.toString();
		        }*/

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
		
		// Quellenangabe zur untenstehenden readUrl Methode: https://stackoverflow.com/questions/7467568/parsing-json-from-url
		private static String readUrl(String urlString) throws Exception {
		    BufferedReader reader = null;
		    try {
		        URL url = new URL(urlString);
		        reader = new BufferedReader(new InputStreamReader(url.openStream()));
		        StringBuffer buffer = new StringBuffer();
		        int read;
		        char[] chars = new char[1024];
		        while ((read = reader.read(chars)) != -1)
		            buffer.append(chars, 0, read); 

		        return buffer.toString();
		    } finally {
		        if (reader != null)
		            reader.close();
		    }
		}
}		
