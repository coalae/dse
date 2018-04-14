package logic;

import java.util.ArrayList;

import model.User;
import repository.DatabaseUserDAO;

/**
 * Die Klasse UserManagement hat die Methoden, mit denen auf die Klasse DatabaseUserDAO (die den
 * Datenbankzugriff verwaltet) zugegriffen werden kann und die die Berechnung der Werte macht, die 
 * an die Controller (bzw. die Servlets) im TestClient weitergeleitet werden. 
 * @author Cordula Eggerth
 */
public class UserManagement {
	
	/**
	 * Instanzvariable vom Typ DatabaseUserDAO
	 */
	private DatabaseUserDAO userdao;

	/**
	 * Konstruktor der Klasse UserManagement
	 */
	public UserManagement(){
		setUserDAO();
	}
	
	/** 
	 * Getter fuer Instanzvariable userdao
	 * @return userdao
	 */
	public DatabaseUserDAO getUserdao(){
		return userdao;
	}

	/**
	 * Setter fuer Instanzvariable userdao
	 */
	public void setUserDAO(){
		this.userdao = new DatabaseUserDAO();
	}	
	
	/**
	 * getUserListe gibt eine Liste aller User zurueck.
	 * @return ArrayList von Usern
	 */	
	public ArrayList<User> getUserListe(){
		ArrayList<User> userliste=userdao.getUserListe();
		return userliste;
	}	
	
	/**
	 * getUserById gibt einen User mittels uebergebener id zurueck.
	 * @param id
	 * @return user
	 */
	public User getUserById(int id){
		User user = userdao.getUserById(id);
		return user;
	}
	
	/**
	 * getUserByUsername gibt einen User mittels uebergebenem Username zurueck.
	 * @param username
	 * @return user
	 */
	public User getUserByUsername(String username){
		User suchUser=null;
		ArrayList<User> userliste=getUserListe();
		for(int i=0;i<userliste.size();i++){
			if(userliste.get(i).getUsername().equals(username)){
				suchUser=userliste.get(i);
			}
		}
		return suchUser;
	}
	
	/**
	 * getUserByKeyword gibt einen User mittels uebergebenem keyword zurueck.
	 * @param keyword
	 * @return suchliste
	 */
	public ArrayList<User> getUserByKeyword(String keyword){ // sucht in Vorname, Nachname und Username nach Keyword
		ArrayList<User> suchliste=new ArrayList<User>();
		ArrayList<User> gesamtliste=getUserListe();
		for(int i=0;i<gesamtliste.size();i++){
			if((gesamtliste.get(i).getFirstname().toLowerCase().contains(keyword.toLowerCase()) || (gesamtliste.get(i).getLastname().toLowerCase().contains(keyword.toLowerCase()) || gesamtliste.get(i).getUsername().toLowerCase().contains(keyword.toLowerCase())))){
				suchliste.add(gesamtliste.get(i));
			}
		}
		return suchliste;
	}
	
	/**
	 * User hinzufuegen.
	 * @param user
	 */
	public void addUser(User user){
		userdao.addUser(user);
	}
	
	/**
	 * User loeschen.
	 * @param id
	 */
	public void deleteUser(int id){
		userdao.deleteUser(id);
	}
	
	/**
	 * User updaten bzw. aendern.
	 * @param user
	 */
	public void updateUser(User user){
		userdao.updateUser(user);	
	}
	
	/**
	 * Check, ob User (username) in der DB schon existiert.
	 * @param username
	 * @return
	 */
	public boolean checkUserNameExists(String username){
		boolean exists=false;
		
		ArrayList<User> userliste=getUserListe();
		
		for(int i=0;i<userliste.size();i++){
			if(userliste.get(i).getUsername().equals(username)){
				exists=true;
			}
		}	
		return exists;
	}
	
	/**
	 * User-Login prueft, ob username und password uebereinstimmen, und ob Login moeglich.
	 * @param username
	 * @param password
	 * @return user
	 */
	public User login(String username, String password) {
		if (username.isEmpty() || password.isEmpty())
			return null;
		try {
			checkUserNameExists(username);

			User user = this.getUserByUsername(username);
			if (user.getPassword().equals(password)) {
				return user;
			} else{
				return null;
			}
			
		} catch (NullPointerException e) {
			return null;
		} catch (IllegalArgumentException e) {
			return null;
		} 
	}

	

	/* main Funktion fuer Tests	*/ 
	 public static void main(String[] args){
		UserManagement usermgmt = new UserManagement();

		/* TEST addUser - user mit dummyId 0 uebergeben 
		User neuerUser=new User(0,"username1","passw1","firstname1","firstname2","city1");
		usermgmt.addUser(neuerUser);
		*/
		
		/* TEST getUserListe
		ArrayList<User> userliste = usermgmt.getUserListe();
		userliste.forEach((user)-> System.out.println(user.getUsername() + " " + user.getCity() + " " + user.getFirstname()));
		*/
	
		/* TEST getUserById 
		User user = usermgmt.getUserById(1);
		System.out.println(user.getId());
		System.out.println(user.getFirstname());
		System.out.println(user.getLastname());
	    */
		
		/* TEST getUserByUsername 
		String username="username1";
		User unuser=usermgmt.getUserByUsername(username);
		System.out.println(unuser.getId());
		System.out.println(unuser.getFirstname());
		System.out.println(unuser.getLastname());
		*/

		/* TEST deleteUser */
		// usermgmt.deleteUser(6);

		/* TEST updateUser 
		User UserToUpdate=usermgmt.getUserById(7);
		userToUpdate.setPassword("pw1updated");
		usermgmt.updateUser(userToUpdate);
		*/
		
		/* TEST checkUserExists 
		System.out.println(usermgmt.checkUserNameExists("username1"));
		*/
	// }
	 }
}
