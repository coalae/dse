package repository;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.ToDo;

/**
 * Klasse DatabaseToDoListDAO bietet Methoden fuer das Speichern, Loeschen, Veraendern von 
 * Instanzen der Klasse ToDo. Sie verwendet die mysql-Datenbank, die von der Universitaet
 * Wien zur Verfuegung gestellt wird. Darin wird die Tabelle ToDo verwaltet.
 * Der Name der Datenbank ist a0750881mysql4. 
 * @author Cordula Eggerth
 */
public class DatabaseToDoListDAO implements ToDoListDAO {

	private String DBAdresse = "jdbc:mysql://a0750881mysql4.mysql.univie.ac.at/a0750881?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String username = "a0750881"; 
	private String password = "coalacoala1"; 
	
	/**
	 * get todoliste (i.e. SELECT * FROM ToDo).
	 * Liste aller ToDoa aus der DB herausnehmen.
	 * @return ArrayList von ToDos
	 */
	@Override
	public ArrayList<ToDo> getToDoListe() {
		Connection con = null;
        ArrayList<ToDo> toDoList = new ArrayList<ToDo>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");   
            con = DriverManager.getConnection(DBAdresse, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql;   
        sql = "SELECT toDoId, userId, category, toDoName FROM a0750881mysql4.ToDo";
        PreparedStatement prest = null;
        try {

            prest = con.prepareStatement(sql);
            ResultSet rs = prest.executeQuery();
            
            while (rs.next()) {
             int id=rs.getInt(1);
             int userId=rs.getInt(2);
             String category=rs.getString(3);
             String toDoName=rs.getString(4);

             ToDo toDo=new ToDo(id,userId, category, toDoName);
             toDoList.add(toDo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prest != null)
                    prest.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return toDoList;
	}


	/**
     * GetToDoById (SELECT ... FROM ToDo WHERE toDoId= ...).
     * ToDo mittels toDoId suchen.
     * @param id
     * @return
     */
	@Override 
	public ToDo getToDoById(int id){
		  
		Connection con = null;
		ToDo suchToDo = null;
		try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // alternativ testen: org.mariadb.jdbc.Driver
            con = DriverManager.getConnection(DBAdresse, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

	    String sql;  
	    sql = "SELECT toDoId, userId, category, toDoName FROM a0750881mysql4.ToDo WHERE toDoId='" + id + "'";
	        PreparedStatement prest = null;
	        try {

	            prest = con.prepareStatement(sql);
	            ResultSet rs = prest.executeQuery();

	            while (rs.next()) {      
	                int toDoId=rs.getInt(1);
	                int userId=rs.getInt(2);
	                String category=rs.getString(3);
	                String toDoName=rs.getString(4);

	                suchToDo=new ToDo(toDoId, userId, category, toDoName);	
	            }     
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (prest != null)
	                    prest.close();
	                if (con != null)
	                    con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }

	        }
			return suchToDo;
	}
	    	
	
	/**
	 * addToDO (INSERT INTO ToDo ... VALUES ...).
	 * Neues ToDo hinzufuegen.
	 * @param toDo
	 */
	@Override
	public void addToDo(ToDo toDo) {
		Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  
            con = DriverManager.getConnection(DBAdresse, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
        	
              // insert     
              String query = " insert into a0750881mysql4.ToDo (userId, category, toDoName)"
                + " values (?, ?, ?)";
              // create insert preparedstatement
              PreparedStatement preparedStmt = con.prepareStatement(query); // achtung: auto-increment fuegt userId automatisch ein
              preparedStmt.setInt (1, toDo.getUserId());
              preparedStmt.setString (2, toDo.getCategory());
              preparedStmt.setString (3, toDo.getToDoName());

              // execute preparedstatement
              preparedStmt.execute();
              
              con.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }finally {
		        try {
		            if (con != null)
		                con.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }		
	}
	
	
	/**
	 * deleteToDo (DELETE FROM ToDo WHERE toDoIs=...).
	 * ToDo loeschen.
	 */
	@Override
	public void deleteToDo (int id) {
  		Connection con = null;
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");  
            con = DriverManager.getConnection(DBAdresse, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	    String sql = "DELETE FROM a0750881mysql4.ToDo WHERE toDoId='" + id + "'";

        try {
        	Statement stmt = con.createStatement();        	         	 
        	stmt.execute(sql);            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }		
	}	
	
	
	/**
	 * updateToDo (by Id) (UPDATE ToDo SET ... WHERE toDoId=...).
	 *  ToDo updaten.
	 */
	@Override
	public void updateToDo(ToDo toDo) {
			
		 String sql; 
		 sql = "UPDATE a0750881mysql4.ToDo SET userId=?, category=?, toDoName=? WHERE toDoId = ?";
		
			Connection con = null;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");  
	            con = DriverManager.getConnection(DBAdresse, username, password);

	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
		    PreparedStatement preparedStmt = null;
		    try {
		        preparedStmt = con.prepareStatement(sql);
		        preparedStmt.setInt(1, toDo.getUserId());
		        preparedStmt.setString(2, toDo.getCategory());
		        preparedStmt.setString(3, toDo.getToDoName());
		        
		        preparedStmt.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }finally {
		        try {
		            if (preparedStmt != null)
		                preparedStmt.close();
		            if (con != null)
		                con.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		
		    }
		    return;		
	}	
	   
    
	/*
	 * main Funktion fuer Tests
	 * @param args
	*/ 
	/*
	public static void main(String[] args){
		DatabaseToDoListDAO dao = new DatabaseToDoListDAO();
		ToDo newToDo = new ToDo(0,2,"sport","laufen gehen");
		 dao.addToDo(newToDo);
		 ToDo toDoById = dao.getToDoById(1);
		 System.out.println("TODOID: " + toDoById.getId() + ", Category: " + toDoById.getCategory() + ", ToDoName: " + toDoById.getToDoName() + "\n");
		 // dao.deleteToDo(1);
		 ArrayList<ToDo> todolist = dao.getToDoListe();
		 for(int i=0;i<todolist.size();i++) {
			 System.out.println("ToDoId: " + todolist.get(i).getId() + ", Category: " + todolist.get(i).getCategory() + 
					 ", ToDoName: " + todolist.get(i).getToDoName() + ", userId: " + todolist.get(i).getUserId());
			 System.out.println("\n");
		 }
	}
	*/
	
   
}
