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

/**
 * The AddToDoServlet adds a new ToDo to the ToDoList of the User
 */
@WebServlet("/AddToDoServlet")
public class AddToDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Konstruktor
     */
    public AddToDoServlet() {
        super();
    }

	/**
	 * doGet leitet auf indexLoggedIn.jsp weiter
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("indexLoggedIn.jsp");
		view.forward(request, response);
	}

	/**
	 * doPost nimmt die Informationen ueber das neu anzulegende ToDo entgegen und versucht, ein
	 * neues ToDo zu erstellen
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		int id = 0; // dummy id (weil auto-increment in DB setzt korrekte next id)
		int userId = (int) request.getServletContext().getAttribute("id");
		String category = (String) request.getParameter("category");
		String todoname = (String) request.getParameter("toDoName");	

		
		HttpURLConnection c = null;
	    try {
	        URL u = new URL("http://tomcat01lab.cs.univie.ac.at:31811/ToDoListTestServer/todolist/newToDo?userId="+userId
	        		+"&category="+category+"&toDoName="+todoname);
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
                 view = request.getRequestDispatcher("toDoHinzufuegen.jsp");
            }
            else {
            	BufferedReader br = new BufferedReader(new InputStreamReader(c.getErrorStream()));
                message = br.readLine();
                br.close();
            	view = request.getRequestDispatcher("indexLoggedIn.jsp");
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
