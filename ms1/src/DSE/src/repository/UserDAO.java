package repository;

import java.util.ArrayList;

import model.User;

/**
 * UserDAO ist ein Interface, das die Methoden fuer Speichern, Loeschen, Veraendern von 
 * Instanzen der Klasse User anbietet.
 * @author Cordula Eggerth
 */
public interface UserDAO {

	/**
	 * Liste aller User aus der DB generieren
	 * @return ArrayList von Usern
	 */
	public ArrayList<User> getUserListe();
	
	/**
	 * User anhand der id suchen
	 * @param id (int)
	 * @return User
	 */
	public User getUserById(int id);

	/**
	 * Neuen User anlegen
	 * @param User
	 */
	public void addUser(User user);
	
	/**
	 * User loeschen  
	 * @param id
	 */
	public void deleteUser(int id);
	
	/**
	 * User updaten bzw. aendern 
	 * @param User
	 */
	public void updateUser(User user);
	
}




