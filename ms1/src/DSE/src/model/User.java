package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/** 
 * Die Klasse User enthaelt das Model fuer den User.
 * Ausserdem werden die jeweiligen Elemente (bzw. deren get-Methoden) als xml Attribute bzw. Elemente annotiert.
 * User wird als xml root element angegeben.
 * @author Cordula Eggerth
 */
@XmlRootElement(name = "user")
public class User {
	
	/**
	 * Instanzvariablen der Klasse User
	 */
	private int id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String city;

	/**
	 * Konstruktor der Klasse User
	 * @param id
	 * @param username
	 * @param password
	 * @param firstname
	 * @param lastname
	 * @param city
	 */
	public User (int id, String username, String password, String firstname, String lastname, String city) {
		
		this.id = id; 
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.city = city;
	}
	
	/**
	 * Getter & Setter 
	 */

	/**
	 * getId retourniert die ID des Users
	 * @return id
	 */
	@XmlAttribute
	public int getId() {
		return id;
	}

	/**
	 * setId setzt die ID des Users anhand des uebergebenen int
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * getUsername retourniert den Username des Users
	 * @return username
	 */
	@XmlElement
	public String getUsername() {
		return username;
	}

	/**
	 * setUsername setzt den Username des Users
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * getPassword retourniert das Passwort des Users
	 * @return password
	 */
	@XmlElement
	public String getPassword() {
		return password;
	}

	/**
	 * setPassword setzt das Passwort des Users
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * getFirstname retourniert den Vorname des Users
	 * @return firstname
	 */
	@XmlElement
	public String getFirstname() {
		return firstname;
	}

	/**
	 * setFirstname setzt den Vorname des Users
	 * @param firstname
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	/**
	 * getLastname retourniert den Nachname des Users
	 * @return lastname
	 */
	@XmlElement
	public String getLastname() {
		return lastname;
	}

	/**
	 * setLastname setzt den Nachname des Users
	 * @param lastname
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	/**
	 * getCity retourniert den Wohnort des Users
	 * @return city
	 */
	@XmlElement
	public String getCity() {
		return city;
	}

	/** 
	 * setCity setzt den Wohnort des Users
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	
}
