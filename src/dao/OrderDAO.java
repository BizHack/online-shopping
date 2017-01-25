package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Order;
/**
 * This class determines operations which connect Order Class to its Database table. In 
 * this class Order table can only be read, deleted and added.
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping System
 *
 */
public class OrderDAO {
	// Declaring connection and statement for DBMS connections
	private Connection connect = null;
	private Statement statement = null;
	private String url = "jdbc:mysql://localhost/javaproject?";
	private String username = "root";
	private String password = "";
	private ResultSet resultSet = null;

	public void createOrder(Order order) {
		// Get a connection
		try {
			connect = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		// Query to insert a record to the bank table
		String query = "INSERT INTO massaorder (o_name, o_totalprice,c_id,o_deliveryStatus,o_date,p_id) VALUES (?,?,?,?,?,?) ;";
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

			statement.setString(1, order.getONAME());
			statement.setDouble(2, order.getOPrice());
			statement.setInt(3, order.getCID());
			statement.setInt(4, order.getODelStatus());
			statement.setString(5, order.getODate());
			statement.setInt(6, order.getPID());

			// Execute the insert

			statement.executeUpdate();

		} catch (SQLException e) {
			order = null;
			System.out.println("Error Creating Order: " + e);
			System.out.println("Error for Create Error Method: OrderDAO");
		}
		
		try {
			connect.close();
			connect = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
			System.out.println("Error for Create Error Method: OrderDAO");
		}

	}

	public List<Order> getAllOrders() {
		List<Order> orders = new ArrayList<>();

		// This will load the MySQL driver, each DB has its own driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = (Connection) DriverManager.getConnection(url + "user=" + username + "&password=" + password);
			// Statements allow to issue SQL queries to the database
			statement = (Statement) connect.createStatement();
			// Result set gets the result of the SQL query
			resultSet = statement
					.executeQuery("select o_id,o_name,o_totalprice,o_deliveryStatus,o_date,p_id from massaorder");
			// ResultSet is initially before the first data set
			while (resultSet.next()) {
				Order order = new Order();
				order.setOID(resultSet.getInt(1));
				order.setONAME(resultSet.getString(2));
				order.setOPrice(resultSet.getDouble(3));
				order.setODelStatus(resultSet.getInt(4));
				order.setODAte(resultSet.getString(5));
				order.setPID(resultSet.getInt(6));
				orders.add(order);
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

		return orders;
	}

	public List<Order> getCustomerOrder(int cid) {
		List<Order> orders = new ArrayList<>();

		// This will load the MySQL driver, each DB has its own driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = (Connection) DriverManager.getConnection(url + "user=" + username + "&password=" + password);
			// Statements allow to issue SQL queries to the database
			statement = (Statement) connect.createStatement();
			// Result set gets the result of the SQL query
			resultSet = statement.executeQuery(
					"select o_id,o_name,o_totalprice,o_deliveryStatus,o_date,p_id from massaorder where c_id=" + cid);
			// ResultSet is initially before the first data set
			while (resultSet.next()) {
				Order order = new Order();
				order.setOID(resultSet.getInt(1));
				order.setONAME(resultSet.getString(2));
				order.setOPrice(resultSet.getDouble(3));
				order.setODelStatus(resultSet.getInt(4));
				order.setODAte(resultSet.getString(5));
				order.setPID(resultSet.getInt(6));
				orders.add(order);
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
		return orders;
	}

	public void removeOrder(Order order) {
		// Get a connection
		try {
			connect = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		
		String query = "DELETE FROM massaorder where o_id=" + order.getOID() + ";";
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

			statement.executeUpdate();

		} catch (SQLException e) {
			order = null;
			System.out.println("Error Creating Order: " + e);
			System.out.println("Error for Remove Error Method: OrderDAO");
		}
		// Close the connection to the database - Very important!!!
		try {
			connect.close();
			connect = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
			System.out.println("Error for Remove Error Method: OrderDAO");
		}

	}

}
