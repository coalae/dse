package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/** 
 * Die Klasse ToDo enthaelt das Model fuer einen Task, der als ToDo angelegt wird.
 * Ausserdem werden die jeweiligen Elemente des ToDo (bzw. deren get-Methoden) als xml Attribute bzw. Elemente annotiert.
 * Todo wird als xml root element angegeben.
 * @author Cordula Eggerth
 */
@XmlRootElement(name = "todo")
public class ToDo {
	
	/**
	 * Instanzvariablen der Klasse ToDo
	 */
	private int id;
	private int userId;
	private String category;
	private String toDoName;

	/**
	 * Konstruktor der Klasse User
	 * @param id
	 * @param userId
	 * @param category
	 * @param toDoName
	 */
	public ToDo (int id, int userId, String category, String toDoName) {
		
		this.id = id; 
		this.userId = userId;
		this.category = category;
		this.toDoName = toDoName;

	}
	
	/**
	 * Getter & Setter 
	 */

	/**
	 * getId retourniert die ID des ToDo
	 * @return id
	 */
	@XmlAttribute
	public int getId() {
		return id;
	}

	/**
	 * setId setzt die ID des ToDo anhand des uebergebenen int
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * getUserId retourniert die ID des Users, dem das ToDo gehoert
	 * @return username
	 */
	@XmlElement
	public int getUserId() {
		return userId;
	}

	/**
	 * setUserId setzt die ID des Users, dem das ToDo gehoert
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * getCategory retourniert die Kategorie des ToDo
	 * @return category
	 */
	@XmlElement
	public String getCategory() {
		return category;
	}

	/**
	 * setCategory setzt die Kategorie des ToDo
	 * @param category
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	/**
	 * getToDoName retourniert Name des ToDo 
	 * @return toDoName
	 */
	@XmlElement
	public String getToDoName() {
		return toDoName;
	}

	/**
	 * setToDoName setzt den Name des ToDo
	 * @param toDoName
	 */
	public void setToDoName(String toDoName) {
		this.toDoName = toDoName;
	}
	

}
