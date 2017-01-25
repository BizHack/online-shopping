package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Software;
/**
 * This class determines operations which connect Software Class to its Database table. In 
 * this class Software table can only be read, deleted and added.
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping System
 *
 */
public class SoftwareDAO {
	// Declaring connection and statement for DBMS connections
	private static Connection connect = null;
	private static Statement statement = null;
	// Declaring resultSet object for getting query results and use it to queue.
	private static ResultSet resultSet = null;


	private static String url = "jdbc:mysql://localhost/javaproject?";
	private static String username = "root";
	private static String password = "";


	public List<Software> getAllSoftwares() {
		List<Software> softwares = new ArrayList<>();

		// This will load the MySQL driver, each DB has its own driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = (Connection) DriverManager.getConnection(url + "user=" + username + "&password=" + password);
			// Statements allow to issue SQL queries to the database
			statement = (Statement) connect.createStatement();
			// Result set gets the result of the SQL query
			resultSet = statement
					.executeQuery("select p_id,s_size,s_edition,s_deliverytype,s_deliveryMethod from massasoftware");
			// ResultSet is initially before the first data set
			while (resultSet.next()) {
				Software software = new Software();
				software.setPID(resultSet.getInt(1));
				software.setSize(Double.valueOf(resultSet.getString(2)));
				software.setEdition(Integer.valueOf(resultSet.getString(3)));
				software.setDelType(resultSet.getString(4));
				softwares.add(software);
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

		return softwares;
	}

	public List<Software> getAllProductSoftwares() {
		List<Software> softwares = new ArrayList<>();

		// This will load the MySQL driver, each DB has its own driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = (Connection) DriverManager.getConnection(url + "user=" + username + "&password=" + password);
			// Statements allow to issue SQL queries to the database
			statement = (Statement) connect.createStatement();
			// Result set gets the result of the SQL query
			resultSet = statement
					.executeQuery("select massasoftware.p_id,massasoftware.s_size,massasoftware.s_edition,massasoftware.s_deliverytype,massasoftware.s_deliveryMethod,massaproduction.p_type,massaproduction.p_price,massaproduction.p_name from massasoftware left join massaproduction on massasoftware.p_id=massaproduction.p_id");
			// ResultSet is initially before the first data set
			while (resultSet.next()) {
				Software software = new Software();
				software.setPID(resultSet.getInt(1));
				software.setSize(Double.valueOf(resultSet.getString(2)));
				software.setEdition(Integer.valueOf(resultSet.getString(3)));
				software.setDelType(resultSet.getString(4));
				software.setPType(resultSet.getString(6));
				software.setPPrice(resultSet.getDouble(7));
				software.setPNAME(resultSet.getString(8));
				softwares.add(software);
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

		return softwares;
	}
	public void addSoftware(Software s){
		// Get a connection
				try {
					connect = DriverManager.getConnection(url, username, password);
				} catch (SQLException e) {
					System.out.println("Error creating connection to database: " + e);
					System.exit(-1);
				}
				// Query to insert a record to the bank table
				String query = "INSERT INTO massasoftware (p_id,s_size,s_edition,s_deliverytype,s_deliverymethod) VALUES (?,?,?,?,?) ;";
				// Use prepared statements to avoid SQL injection attacks
				try (PreparedStatement statement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
				
					statement.setInt(1, s.getPID());
					statement.setDouble(2, s.getSize());
					statement.setInt(3, s.getEdition());
					statement.setString(4, s.getDelType());
					statement.setString(5, s.getDelMethod());
				


					statement.executeUpdate();
					

				} catch (SQLException e) {
					s = null;
					System.out.println("Error Creating Product: " + e);
					System.out.println("Error for Create Error Method: SoftwareDAO");
				}
				// Close the connection to the database
				try {
					connect.close();
					connect = null;
				} catch (SQLException e) {
					System.out.println("Error closing connection: " + e);
					System.out.println("Error for Create Error Method: SoftwareDAO");
				}

	}
	public void removeSoftware(Software software) {
		// Get a connection
				try {
					connect = DriverManager.getConnection(url, username, password);
				} catch (SQLException e) {
					System.out.println("Error creating connection to database: " + e);
					System.exit(-1);
				}
				// Query to insert a record to the bank table
				String query = "DELETE FROM massasoftware where p_id="+ software.getPID()+ ";";
				// Use prepared statements to avoid SQL injection attacks
				try (PreparedStatement statement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
					

					statement.executeUpdate();
					

				} catch (SQLException e) {
					software = null;
					System.out.println("Error Creating Order: " + e);
					System.out.println("Error for Remove Error Method: SoftwareDAO");
				}
				// Close the connection to the database 
				try {
					connect.close();
					connect = null;
				} catch (SQLException e) {
					System.out.println("Error closing connection: " + e);
					System.out.println("Error for Remove Error Method: SoftwareDAO");
				}


	}
}
