package repository;

import java.util.ArrayList;

import model.ToDo;

/**
 * ToDoListDAO ist ein Interface, das die Methoden fuer Speichern, Loeschen, Veraendern von 
 * Instanzen der Klasse ToDo anbietet.
 * @author Cordula Eggerth
 */
public interface ToDoListDAO {

	/**
	 * Liste aller ToDos aus der DB generieren
	 * @return ArrayList von ToDos
	 */
	public ArrayList<ToDo> getToDoListe();
	
	/**
	 * ToDo anhand der id suchen
	 * @param id (int)
	 * @return ToDo
	 */
	public ToDo getToDoById(int id);

	/**
	 * Neues ToDo anlegen
	 * @param ToDo
	 */
	public void addToDo(ToDo toDo);
	
	/**
	 * ToDo loeschen  
	 * @param id
	 */
	public void deleteToDo(int id);
	
	/**
	 * ToDo updaten bzw. aendern 
	 * @param ToDo
	 */
	public void updateToDo(ToDo toDo);
	
}




