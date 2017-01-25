package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.User;
/**
 * This class determines operations which connect User Class to its Database table. In 
 * this class User table can only be read and added.
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping System
 *
 */

public class userDAO {

	// Declaring connection and statement for DBMS connections
	private Connection connect = null;
	private Statement statement = null;
	// Declaring resultSet object for getting query results and use it to queue.
	private ResultSet resultSet = null;
	// private static ResultSet resultSet2 = null;
	private String url = "jdbc:mysql://localhost/javaproject?";
	private String username = "root";
	private String password = "";

	public User readUsers(User user) {
		User u = new User();
		// This will load the MySQL driver, each DB has its own driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			try {
				connect = (Connection) DriverManager.getConnection(url + "user=" + username + "&password=" + password);

			} catch (Exception e) {
				System.out.println("Error creating connection to database: " + e);
				System.exit(-1);
			}

			// Statements allow to issue SQL queries to the database
			statement = (Statement) connect.createStatement();
			// Result set gets the result of the SQL query
			resultSet = statement
					.executeQuery("select user,pass,u_type,u_id from massauser where user='" + user.getUser() + "';");
			// ResultSet is initially before the first data set
			while (resultSet.next()) {

				u.setUser(resultSet.getString(1));
				u.setPass(resultSet.getString(2));
				u.setUType(resultSet.getString(3));
				u.setId(resultSet.getInt(4));

			}
		} catch (Exception e) {
			// e.printStackTrace();

		}
		try {
			connect.close();
			connect = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}

		return u;

	}

	public void addUser(User u) {
		// Get a connection
		try {
			connect = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		// Query to insert a record to the bank table
		String query = "INSERT INTO massauser (user, pass,u_type) VALUES (?,?,?) ;";
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			
			statement.setString(1, u.getUser());
			statement.setString(2, u.getPass());
			statement.setString(3, u.getUType());

			statement.executeUpdate();
			

		} catch (SQLException e) {
			u = null;
			System.out.println("Error Creating Product: " + e);
			System.out.println("Error for Create Error Method: UserDAO");
		}
		// Close the connection to the database 
		try {
			connect.close();
			connect = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
			System.out.println("Error for Create Error Method: UserDAO");
		}

	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();

		// This will load the MySQL driver, each DB has its own driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = (Connection) DriverManager.getConnection(url + "user=" + username + "&password=" + password);
			// Statements allow to issue SQL queries to the database
			statement = (Statement) connect.createStatement();
			// Result set gets the result of the SQL query
			resultSet = statement.executeQuery("select user,pass,u_type,u_id from massauser");

			// ResultSet is initially before the first data set
			while (resultSet.next()) {
				User user = new User();
				user.setUser(resultSet.getString(1));
				user.setPass(resultSet.getString(2));
				user.setUType(resultSet.getString(3));
				user.setId(resultSet.getInt(4));

				users.add(user);

			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("error in getAllUser:UserDAO");
			System.out.println("Cannot read from Papademas database");
			System.exit(0);
		}
		try {
			connect.close();
			connect = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}

		return users;
	}

	
}
