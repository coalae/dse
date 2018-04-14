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

import logic.ToDoListManagement;
import model.ToDo;

/**
 * Die Klasse ToDoListService liegt im Package services, und enthaelt die Pfade, ueber die die jeweiligen angebotenen 
 * Services erreicht und aufgerufen werden koennen.
 * @author Cordula Eggerth
 */
@Path("/todolist")
public class ToDoListService {

	/**
	 * Instanzvariable vom Typ ToDoListManagement
	 */
	private ToDoListManagement toDoListManagement;
	
	/**
	 * Konstruktor der Klasse UserService
	 */
	public ToDoListService() {
		toDoListManagement = new ToDoListManagement();
	}

	
	/**
	 * Web Service fuer den Aufruf der ToDoListe eines bestimmten Users (anhand userId)
	 * Es wird GET verwendet und der Aufruf erfolgt ueber den Pfad /todolist/todolistPerUser.
	 * Die ToDoListe wird als ArrayList vom Typ ToDo retourniert.
	 * @return ArrayList<User>
	 */
	@GET
	@Path("/todolistPerUser")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ToDo> getToDoByUserId(@QueryParam("userId") int userId){
		System.out.println("server");
		return toDoListManagement.getToDoByUserId(userId);
	}	
	
	
	/**
	 * Web Service fuer den Aufruf der ToDoListe eines bestimmten Users (anhand userId) in einer bestimmten Kategorie
	 * Es wird GET verwendet und der Aufruf erfolgt ueber den Pfad /todolist/todolistPerUserAndCategory.
	 * Die ToDoListe wird als ArrayList vom Typ ToDo retourniert.
	 * @return ArrayList<User>
	 */
	@GET
	@Path("/todolistPerUserAndCategory")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ToDo> getToDoByUserAndCategory(@QueryParam("userId") int userId, @QueryParam("category") String category){
		return toDoListManagement.getToDoByUserIdAndByCategory(userId, category);
	}	
	
	
	/**
	 * Web Service fuer das Anlegen eines neuen ToDos fuer den User, der gerade eingeloggt ist.
	 * Es wird GET verwendet und der Aufruf erfolgt ueber den Pfad /todolist/newToDo.
	 * Es werden die Parameter userId, category und toDoName als QueryParam uebergeben.
	 * Retourniert wird Response mit einem Status und einer Nachricht, dass das neue ToDo erfasst wurde.
	 * @param userId
	 * @param category
	 * @param toDoName
	 * @return Response
	 */
	@GET
	@Path("/newToDo")
	public Response addNewToDo(@QueryParam("userId") int userId, @QueryParam("category") String category, 
			@QueryParam("toDoName") String toDoName) {

			System.out.println(toDoName);
			ToDo todo = new ToDo(0,userId, category, toDoName);
			toDoListManagement.addToDo(todo);
			return Response.status(200).entity("ToDo wurde hinzugefuegt").build();
		
	}
	
}
	
	
	
	
	
