package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
/**
 * This class determines operations which connect Customer Class to its Database table. In 
 * this class Customer table can only be read, updated and added.
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping System
 *
 */

public class CustomerDAO {

	// Declaring connection and statement for DBMS connections
	private Connection connect = null;
	private Statement statement = null;
	// Declaring resultSet object for getting query results and use it to queue.
	private ResultSet resultSet = null;
	// private static ResultSet resultSet2 = null;
	private String url = "jdbc:mysql://localhost/javaproject?";
	private String username = "root";
	private String password = "";

	

	public Customer returnCID(int uid) {
		Customer c = new Customer();
		try

		{
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = (Connection) DriverManager.getConnection(url + "user=" + username + "&password=" + password);
			// Statements allow to issue SQL queries to the database
			statement = (Statement) connect.createStatement();
			// Result set gets the result of the SQL query
			resultSet = statement.executeQuery("select c_id,c_name,c_addr from massaCustomer where u_id=" + uid);
			// ResultSet is initially before the first data set
			while (resultSet.next()) {

				c.setCId(resultSet.getInt(1));
				c.setCNAME(resultSet.getString(2));
				c.setCAddr(resultSet.getString(3));


			}

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error from returnCID method: CustomerDAO");

		}
		try {
			connect.close();
			connect = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		return c;

	}

	public List<Customer> getCustomer(int cid) {

		List<Customer> customers = new ArrayList<>();

		// This will load the MySQL driver, each DB has its own driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = (Connection) DriverManager.getConnection(url + "user=" + username + "&password=" + password);
			// Statements allow to issue SQL queries to the database
			statement = (Statement) connect.createStatement();
			// Result set gets the result of the SQL query
			resultSet = statement.executeQuery("select c_id,c_name,c_addr from massacustomer where c_id=" + cid);
			// ResultSet is initially before the first data set
			while (resultSet.next()) {
				Customer c = new Customer();
				c.setCId(resultSet.getInt(1));
				c.setCNAME(resultSet.getString(2));
				c.setCAddr(resultSet.getString(3));

				customers.add(c);
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

		return customers;
	}

	public void updateCustomerAddr(Customer customer) {

		// Get a connection
		try {
			connect = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		System.out.println(customer.getCAddr());
		// Query to insert a record to the bank table
		String query = "UPDATE massacustomer SET c_addr='" + customer.getCAddr() + "' WHERE c_id=" + customer.getCId()
				+ ";";
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

			statement.executeUpdate();

		} catch (SQLException e) {
			customer = null;
			System.out.println("Error Creating Order: " + e);
			System.out.println("Error for Customer Update Method: CustomerDAO");
		}
		// Close the connection to the database - Very important!!!
		try {
			connect.close();
			connect = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
			System.out.println("Error for Customer Update Error Method: CustomerDAO");
		}

	}

	public void updateCustomerName(Customer customer) {
		// Get a connection
		try {
			connect = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		System.out.println(customer.getCNAME());
		// Query to insert a record to the bank table
		String query = "UPDATE massacustomer SET c_name='" + customer.getCNAME() + "' WHERE c_id=" + customer.getCId()
				+ ";";
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

			statement.executeUpdate();

		} catch (SQLException e) {
			customer = null;
			System.out.println("Error Creating Order: " + e);
			System.out.println("Error for Customer Update Method: CustomerDAO");
		}
		// Close the connection to the database - Very important!!!
		try {
			connect.close();
			connect = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
			System.out.println("Error for Customer Update Error Method: CustomerDAO");
		}

	}

	public List<Customer> getAllUserCustomers() {
		List<Customer> customers = new ArrayList<>();

		// This will load the MySQL driver, each DB has its own driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = (Connection) DriverManager.getConnection(url + "user=" + username + "&password=" + password);
			// Statements allow to issue SQL queries to the database
			statement = (Statement) connect.createStatement();

			// Result set gets the result of the SQL query
			resultSet = statement.executeQuery(
					"select massauser.u_id,massauser.user,massauser.pass,massacustomer.c_id,massacustomer.c_name,massacustomer.c_addr from massacustomer left join massauser on massacustomer.u_id=massauser.u_id");
			// ResultSet is initially before the first data set
			while (resultSet.next()) {

				Customer customer = new Customer();
				customer.setId(resultSet.getInt(1));
				customer.setUser(resultSet.getString(2));
				customer.setPass(resultSet.getString(3));
				customer.setCId(Integer.valueOf(resultSet.getString(4)));
				customer.setCNAME(resultSet.getString(5));
				customer.setCAddr(resultSet.getString(6));

				customers.add(customer);

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

		return customers;
	}

	public void addCustomer(Customer c) {
		// Get a connection
		// Get a connection
		try {
			connect = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		// Query to insert a record to the bank table
		String query = "INSERT INTO massacustomer (u_id,c_name,c_addr) VALUES (?,?,?) ;";
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query
			/*
			 * private Integer oID; private String oName; private double
			 * oTotalPrice; private Integer cID; private boolean
			 * oDeliveryStatus; private String oDate; private ArrayList<Product>
			 * p;
			 */
			// statement.setInt(1, order.getOID());
			statement.setInt(1, c.getId());
			statement.setString(2, c.getCNAME());
			statement.setString(3, c.getCAddr());

			statement.executeUpdate();
			/*
			 * //To get the primary key (id) of the newly inserted record
			 * ResultSet resultSet = statement.getGeneratedKeys();
			 * if(resultSet.next()) { //Set the id field of the database to the
			 * model order.setOID(resultSet.getInt(1)); }
			 */

			// order.setOID(nextOrderID());

		} catch (SQLException e) {
			c = null;
			System.out.println("Error Creating Product: " + e);
			System.out.println("Error for Create Error Method: CustomerDAO");
		}
		// Close the connection to the database - Very important!!!
		try {
			connect.close();
			connect = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
			System.out.println("Error for Create Error Method: CustomerDAO");
		}
	}
}
