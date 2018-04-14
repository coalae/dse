package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.User;

/**
 * Klasse DatabaseUserDAO bietet Methoden fuer das Speichern, Loeschen, Veraendern von 
 * Instanzen der Klasse User. Sie verwendet die mysql-Datenbank, die von der Universitaet
 * Wien zur Verfuegung gestellt wird. Darin wird die Tabelle Users verwaltet.
 * Der Name der Datenbank ist a0750881mysql3. 
 * @author Cordula Eggerth
 */
public class DatabaseUserDAO implements UserDAO {

	private String DBAdresse = "jdbc:mysql://a0750881mysql3.mysql.univie.ac.at/a0750881?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String username = "a0750881"; 
	private String password = "coalacoala1"; 
	
	/**
	 * get userliste (i.e. SELECT * FROM Users).
	 * Liste aller User aus der DB herausnehmen.
	 * @return ArrayList von Usern
	 */
	@Override
	public ArrayList<User> getUserListe() {
		Connection con = null;
        ArrayList<User> userList = new ArrayList<User>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");   
            con = DriverManager.getConnection(DBAdresse, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql;   
        sql = "SELECT userId, username, passw, firstname, lastname, city FROM a0750881mysql3.Users";
        PreparedStatement prest = null;
        try {

            prest = con.prepareStatement(sql);
            ResultSet rs = prest.executeQuery();
            
            while (rs.next()) {
             int id=rs.getInt(1);
             String username=rs.getString(2);
             String passw=rs.getString(3);
             String firstname=rs.getString(4);
             String lastname=rs.getString(5);
             String city=rs.getString(6);

             User user=new User(id,username, passw, firstname, lastname, city);
             userList.add(user);
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
        return userList;
	}


	/**
     * GetUserById (SELECT ... FROM Users WHERE userId= ...).
     * User mittels id suchen.
     * @param id
     * @return
     */
	@Override 
	public User getUserById(int id){
		  
		Connection con = null;
		User suchUser = null;
		try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // alternativ testen: org.mariadb.jdbc.Driver
            con = DriverManager.getConnection(DBAdresse, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

	    String sql;  
	    sql = "SELECT userId, username, passw, firstname, lastname, city FROM a0750881mysql3.Users WHERE userId='" + id + "'";
	        PreparedStatement prest = null;
	        try {

	            prest = con.prepareStatement(sql);
	            ResultSet rs = prest.executeQuery();

	            while (rs.next()) {      
	                int userId=rs.getInt(1);
	                String username=rs.getString(2);
	                String passw=rs.getString(3);
	                String firstname=rs.getString(4);
	                String lastname=rs.getString(5);
	                String city=rs.getString(6);

	                suchUser=new User(userId, username, passw, firstname, lastname, city);	
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
			return suchUser;
	}
	    	
	
	/**
	 * addUser (INSERT INTO Users ... VALUES ...).
	 * Neuen User hinzufuegen.
	 * @param user
	 */
	@Override
	public void addUser(User user) {
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
              String query = " insert into a0750881mysql3.Users (username, passw, firstname, lastname, city)"
                + " values (?, ?, ?, ?, ?)";
              // create insert preparedstatement
              PreparedStatement preparedStmt = con.prepareStatement(query); // achtung: auto-increment fuegt userId automatisch ein
              preparedStmt.setString (1, user.getUsername());
              preparedStmt.setString (2, user.getPassword());
              preparedStmt.setString (3, user.getFirstname());
              preparedStmt.setString(4, user.getLastname());
              preparedStmt.setString(5, user.getCity());

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
	 * deleteUser (DELETE FROM User WHERE userId=...).
	 * User loeschen.
	 */
	@Override
	public void deleteUser (int id) {
  		Connection con = null;
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");  
            con = DriverManager.getConnection(DBAdresse, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	    String sql = "DELETE FROM a0750881mysql3.Users WHERE userId='" + id + "'";

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
	 * updateUser (by Id) (UPDATE Users SET ... WHERE userId=...).
	 *  User updaten.
	 */
	@Override
	public void updateUser(User user) {
			
		 String sql; 
		 sql = "UPDATE a0750881mysql3.Users SET username=?, passw=?, firstname=?, lastname=?, city=? WHERE userId = ?";
		
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
		        preparedStmt.setString(1, user.getUsername());
		        preparedStmt.setString(2, user.getPassword());
		        preparedStmt.setString(3, user.getFirstname());
		        preparedStmt.setString(4, user.getLastname());
		        preparedStmt.setString(5, user.getCity());
		        preparedStmt.setInt(6, user.getId());
		        
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
		DatabaseUserDAO dao = new DatabaseUserDAO();
		 dao.select();
		 dao.insert();
		 dao.update();
		 User userById = dao.getUserById(2);
		 System.out.println("UN: " + userById.getUsername() + " PW " + userById.getPassword());
		 dao.deleteUserById(3);
	}
	*/
   

}






