package logic;


import java.util.ArrayList;

import model.ToDo;
import repository.DatabaseToDoListDAO;

/**
 * Die Klasse ToDoListManagement hat die Methoden, mit denen auf die Klasse DatabaseToDoListDAO (die den
 * Datenbankzugriff verwaltet) zugegriffen werden kann und die die Berechnung der Werte macht, die 
 * an die Controller (bzw. die Servlets) im TestClient weitergeleitet werden. 
 * @author Cordula Eggerth
 */
public class ToDoListManagement {
	
	/**
	 * Instanzvariable vom Typ DatabaseToDoListDAO
	 */
	private DatabaseToDoListDAO todolistdao;

	/**
	 * Konstruktor der Klasse ToDoListManagement
	 */
	public ToDoListManagement(){
		setToDoListDAO();
	}
	
	/** 
	 * Getter fuer Instanzvariable todolistdao
	 * @return todolistdao
	 */
	public DatabaseToDoListDAO getToDoListdao(){
		return todolistdao;
	}

	/**
	 * Setter fuer Instanzvariable userdao
	 */
	public void setToDoListDAO(){
		this.todolistdao = new DatabaseToDoListDAO();
	}	
	
	/**
	 * getToDoList gibt eine Liste aller ToDos zurueck.
	 * @return ArrayList von ToDos
	 */	
	public ArrayList<ToDo> getToDoListe(){
		ArrayList<ToDo> todoliste=todolistdao.getToDoListe();
		return todoliste;
	}	
	
	/**
	 * getToDoById gibt einen ToDo mittels uebergebener id zurueck.
	 * @param id
	 * @return todo
	 */
	public ToDo getToDoById(int id){
		ToDo todo = todolistdao.getToDoById(id);
		return todo;
	}
	
	/**
	 * getToDoByUserId gibt eine Liste von ToDos mittels uebergebener userId zurueck.
	 * @param userId
	 * @return ArrayList von ToDos
	 */
	public ArrayList<ToDo> getToDoByUserId(int userId){
		ArrayList<ToDo> listOfUser = new ArrayList<ToDo>();
		ArrayList<ToDo> todoliste=getToDoListe();
		for(int i=0;i<todoliste.size();i++){
			if(todoliste.get(i).getUserId() == userId){
				listOfUser.add(todoliste.get(i));
			}
		}
		return listOfUser;
	}
	
	/**
	 * getToDoByUserIdAndByCategory gibt eine Liste von ToDos mittels angefragter userId und category zurueck.
	 * @param userId
	 * @param category
	 * @return ArrayList von ToDos
	 */
	public ArrayList<ToDo> getToDoByUserIdAndByCategory(int userId, String category){
		ArrayList<ToDo> listOfUser = new ArrayList<ToDo>();
		ArrayList<ToDo> todoliste=getToDoListe();
		for(int i=0;i<todoliste.size();i++){
			if(todoliste.get(i).getUserId() == userId && todoliste.get(i).getCategory().equals(category)){
				listOfUser.add(todoliste.get(i));
			}
		}
		return listOfUser;
	}
	
	/**
	 * ToDo hinzufuegen.
	 * @param todo
	 */
	public void addToDo(ToDo todo){
		todolistdao.addToDo(todo);
	}
	
	/**
	 * ToDo loeschen.
	 * @param id
	 */
	public void deleteToDo(int id){
		todolistdao.deleteToDo(id);
	}
	
	/**
	 * ToDo updaten bzw. aendern.
	 * @param todo
	 */
	public void updateToDo(ToDo todo){
		todolistdao.updateToDo(todo);	
	}
	


	

	/* main Funktion fuer Tests	*/ 
	 public static void main(String[] args){
		ToDoListManagement todolistmgmt = new ToDoListManagement();

		/* TEST addToDo - todo mit dummyId 0 uebergeben */
		/*
		ToDo neuesToDo=new ToDo(0,3,"einkaufen","brot");
		todolistmgmt.addToDo(neuesToDo);
		 */
		
		
		/* TEST getUserListe */
		/*
		 ArrayList<ToDo> todolist = todolistmgmt.getToDoListe();
		 for(int i=0;i<todolist.size();i++) {
			 System.out.println("ToDoId: " + todolist.get(i).getId() + ", Category: " + todolist.get(i).getCategory() + 
					 ", ToDoName: " + todolist.get(i).getToDoName() + ", userId: " + todolist.get(i).getUserId());
		 }		 
		 System.out.println("\n");
		 */
		
		/* TEST getToDoById */
		/*
		ToDo todo = todolistmgmt.getToDoById(7);
		System.out.println(todo.getId());
		System.out.println(todo.getUserId());
		System.out.println(todo.getCategory());
		System.out.println(todo.getToDoName());
	    */
		
		/* TEST getUserByUsername 
		String username="username1";
		User unuser=usermgmt.getUserByUsername(username);
		System.out.println(unuser.getId());
		System.out.println(unuser.getFirstname());
		System.out.println(unuser.getLastname());
		*/

		/* TEST deleteUser */
		// todolistmgmt.deleteToDo(2);

		/* TEST updateToDo 
		ToDo todoToUpdate=todolistmgmt.getToDoById(7);
		todoToUpdate.setToDoName("todoname-updated");
		todolistmgmt.updateToDo(todoToUpdate);
		*/
		
		
	// }
	 }
}
