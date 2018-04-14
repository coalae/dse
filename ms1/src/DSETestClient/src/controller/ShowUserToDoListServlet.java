package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Das ShowUserToDoListServlet fragt die ToDoListe des Users, der gerade eingeloggt ist, an.
 */
@WebServlet("/ShowUserToDoListServlet")
public class ShowUserToDoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Konstruktor
     */
    public ShowUserToDoListServlet() {
        super();
    }

	/**
	 * doGet fragt die ToDoListe des Users an
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		int userId =  (int) request.getServletContext().getAttribute("id");
		System.out.println("userid: " + userId);
		
		String jsonStr = null;
	    try {
	    	try {
				jsonStr = readUrl("http://tomcat01lab.cs.univie.ac.at:31811/ToDoListTestServer/todolist/todolistPerUser?userId="+userId);
			} catch (Exception e) {
				e.printStackTrace();
			}
	        JSONArray todoliste = new JSONArray(jsonStr);
	        System.out.println(todoliste.length());
	        String message = "";
            RequestDispatcher view;
            if (todoliste == null) {
            	 message = "ToDoListe hat keine Eintraege.";
                 view = request.getRequestDispatcher("meineToDoListe.jsp");
            }
            else {
            	ServletContext con = request.getServletContext();
            	con.setAttribute("jsonArray", todoliste);
            	/*
        		for (int i = 0; i < todoliste.length(); i++) {
        		    JSONObject jsonobject = todoliste.getJSONObject(i);
        		    
        		    System.out.println(jsonobject.getString("toDoName"));
        		}
				*/
            	view = request.getRequestDispatcher("meineToDoListe.jsp");
            }
            request.setAttribute("message", message);
			view.forward(request, response);

	    } catch (MalformedURLException ex) {
	        ex.printStackTrace();
	    } catch (IOException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	
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


	/**
	 * doPost leitet auf doGet weiter
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
