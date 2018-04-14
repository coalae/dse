package services;

import java.util.ArrayList;


import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import logic.UserManagement;
import model.User;

/**
 * Die Klasse UserService liegt im Package services, und enthaelt die Pfade, ueber die die jeweiligen angebotenen 
 * Services erreicht und aufgerufen werden koennen.
 * @author Cordula Eggerth
 */
@Path("/users")
public class UserService {

	/**
	 * Instanzvariable vom Typ UserManagement
	 */
	private UserManagement userManagement;
	
	
	/**
	 * Konstruktor der Klasse UserService
	 */
	public UserService() {
		userManagement = new UserManagement();
	}
	
	
	/**
	 * Web Service fuer den Login 
	 * Es wird GET verwendet und der Aufruf erfolgt ueber den Pfad /users/login. 
	 * Uebergeben werden die Parameter username und password, damit der login stattfinden kann.
	 * Die Funktion login gibt einen User zurueck - es wird im Format JSON (object) der User
	 * an den Client zurueckgegeben.
	 * @param username
	 * @param password
	 * @return User
	 */
	@GET
	@Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
   // @Consumes(MediaType.TEXT_HTML)
    public User login(@CookieParam(value="username") String username, @CookieParam(value="password") String password)
    {
        return userManagement.login(username, password);
    }
	
	/**
	 * Web Service fuer Login (fuer MS3-Client)
	 * Es wird POST verwendet und der Aufruf erfolgt ueber den Pfad /users/loginMS3.
	 * Uebergeben werden username und password als Parameter. 
	 * Die Funktion gibt bei erfolgreichem Login einen User in Format JSON zurueck an den Client.
	 * @param username
	 * @param password
	 * @return User
	 */ 
	 @POST
	 @Path("/loginMS3")
     @Produces(MediaType.APPLICATION_JSON)
     public User loginMS3(@HeaderParam("username") String username, @HeaderParam("password") String password) {
        return userManagement.login(username, password);
     }
	
	
	
	/**
	 * Web Service fuer den Aufruf der Userliste
	 * Es wird GET verwendet und der Aufruf erfolgt ueber den Pfad /users/userlist.
	 * Die Userliste wird als ArrayList vom Typ User retourniert (wobei die Abfrage im JSON-Format zurueckgegeben wird an
	 * den Client, der anfragt).
	 * @return ArrayList<User>
	 */
	@GET
	@Path("/userlist")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<User> getUserList(){
		return userManagement.getUserListe();
	}
	
	
	/**
	 * Web Service fuer die Registrierung eines Users.
	 * Es wird GET verwendet und der Aufruf erfolgt ueber den Pfad /users/register.
	 * Es werden die Parameter username, password, firstname, lastname und city als QueryParam uebergeben.
	 * Retourniert wird Response mit einem Status und einer Nachricht, je nachdem ob der fuer die 
	 * Registierung uebergebene User tatsaechlich angelegt werden konnte oder nicht.
	 * @param username
	 * @param password
	 * @param firstname
	 * @param lastname
	 * @param city
	 * @return Response
	 */
	@GET
	@Path("/register")
	public Response register(@QueryParam("username") String username, @QueryParam("password") String password, 
								@QueryParam("firstname") String firstname, @QueryParam("lastname") String lastname, 
								@QueryParam("city") String city) {
		System.out.println(username);
		if (userManagement.checkUserNameExists(username))
			return Response.status(400).entity("User existiert schon.").build();
		else {
			User user = new User(0,username, password, firstname, lastname, city);
			userManagement.addUser(user);
			return Response.status(200).entity("User wurde hinzugefuegt.").build();
		}
	}
	
	
	/**
	 * Web Service fuer das Loeschen eines Users.
	 * Es wird GET verwendet und der Aufruf erfolgt ueber den Pfad /users/delete.
	 * Es wird der Parameter userId als QueryParam uebergeben.
	 * Retourniert wird Response mit einem Status und einer Nachricht, deren Inhalt
	 * davon abhaengt, ob geloescht wurde oder nicht.
	 * @param userId
	 * @return Response
	 */
	@GET
	@Path("/delete")
	public Response delete(@QueryParam("userId") int userId) {
	  System.out.println(userId);
	  
	  if(userManagement.checkUserNameExists((userManagement.getUserById(userId)).getUsername())){
	    userManagement.deleteUser(userId);
	    return Response.status(200).entity("User wurde geloescht.").build();
	  }
	  else {
	    return Response.status(400).entity("User existiert nicht. Kein Loeschen durchgefuehrt.").build();
	  }
	}

	
	/**
	 * Web Service fuer das Aendern des password eines Users.
	 * Es wird POST verwendet und HeaderParam ueber den Pfad users/changePassword
	 * @param username
	 * @param password
	 * @param firstname
	 * @param lastname
	 * @param city
	 * @return Response	 
	 */
	@POST 
	@Path("/changePassword")
	// @Produces(MediaType.APPLICATION_JSON)	  
	public Response changePassword(@HeaderParam("id") String id, @HeaderParam("username") String username, 
			@HeaderParam("password") String password, @HeaderParam("firstname") String firstname,
			@HeaderParam("lastname") String lastname, @HeaderParam("city") String city) {

		  if(userManagement.checkUserNameExists(username)){
			    // System.out.println("in if changePW ok: "+ password);
				User user = new User(Integer.parseInt(id), username, password, firstname, lastname, city);
			    userManagement.updateUser(user);
			    return Response.status(200).entity("Passwort wurde geaendert.").build();
			  }
			  else {
			    return Response.status(400).entity("User existiert nicht. Keine Aenderung durchgefuehrt.").build();
			  }
		  
    }
	
	
	
	// TEST CONNECTION
	/**
	 * Testet die Connection.
	 * @param name
	 * @return Response
	 */
	@GET
	@Path("/{name}")
	public Response sayHallo(@PathParam("name") String name) {
		return Response.status(200).entity("Hallo " + name).build();
	}
	
	// TEST WEB SERVICES
	/*
	@GET
	public Response sayHallo2(@QueryParam("name") String name, @QueryParam("id") int id) {
		return Response.status(200).entity("Hallo " + name + " id:" + id).build();
	}
	
	@GET
	@Path("/{name}")
	public Response sayHallo(@PathParam("name") String name) {
		return Response.status(200).entity("Hallo " + name).build();
	}
	@POST
    public Response sayHello(@HeaderParam("name") String msg) {
        String output = "Hello, " + msg + "!";
        return Response.status(200).entity(output).build();
    }
	*/
	/*
	@POST
	@Path("/loginXX")
    public Response loginXX(@HeaderParam("username") String username, @HeaderParam("password") String password) {
        if (username.equals("coala") && password.equals("123")) {
        	String output = "Hello, " + username + "!";
        	return Response.status(200).entity(output).build();
        }
        else {
        	String output = "Fehler !";
        	return Response.status(600).entity(output).build();
        }
    }
	*/
	

}
