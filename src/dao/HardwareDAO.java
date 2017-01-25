package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Hardware;
/**
 * This class determines operations which connect Hardware Class to its Database table. In 
 * this class Hardware table can only be read, deleted and added.
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping System
 *
 */
public class HardwareDAO {
	// Declaring connection and statement for DBMS connections
	private Connection connect = null;
	private Statement statement = null;
	// Declaring resultSet object for getting query results and use it to queue.
	private ResultSet resultSet = null;
	// private static ResultSet resultSet2 = null;
	private String url = "jdbc:mysql://localhost/javaproject?";
	private String username = "root";
	private String password = "";
	
	public List<Hardware> getAllProductHardware() {
		List<Hardware> hardwares = new ArrayList<>();

		// This will load the MySQL driver, each DB has its own driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = (Connection) DriverManager.getConnection(url + "user=" + username + "&password=" + password);
			// Statements allow to issue SQL queries to the database
			statement = (Statement) connect.createStatement();
			// Result set gets the result of the SQL query
			resultSet = statement
					.executeQuery("select massahardware.p_id,massahardware.h_weight,massahardware.h_dimension,massahardware.h_fragile,massahardware.h_deliveryMethod,massahardware.h_availabilityNum,massaproduction.p_type,massaproduction.p_price,massaproduction.p_name from massahardware left join massaproduction on massahardware.p_id=massaproduction.p_id");
			
			while (resultSet.next()) {
				Hardware hardware = new Hardware();
				hardware.setPID(resultSet.getInt(1));
				hardware.setWeight(Double.valueOf(resultSet.getString(2)));
				hardware.setDimension(Double.valueOf(resultSet.getString(3)));
				hardware.setFragile(resultSet.getInt(4));
				hardware.setAvailNum(resultSet.getInt(6));
				hardware.setPType(resultSet.getString(7));
				hardware.setPPrice(resultSet.getDouble(8));
				hardware.setPNAME(resultSet.getString(9));
				hardwares.add(hardware);
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

		return hardwares;
	}
	public void addHardware(Hardware h){
		// Get a connection
				try {
					connect = DriverManager.getConnection(url, username, password);
				} catch (SQLException e) {
					System.out.println("Error creating connection to database: " + e);
					System.exit(-1);
				}
				// Query to insert a record to the bank table
				String query = "INSERT INTO massahardware (p_id,h_weight,h_dimension,h_fragile,h_deliveryMethod,h_availabilityNum) VALUES (?,?,?,?,?,?) ;";
				// Use prepared statements to avoid SQL injection attacks
				try (PreparedStatement statement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
					
					statement.setInt(1, h.getPID());
					statement.setDouble(2, h.getWeight());
					statement.setDouble(3, h.getDimension());
					statement.setInt(4, h.getFragile());
					statement.setString(5, h.getDelMethod());
					statement.setInt(6, h.getAvailNum());
	

					statement.executeUpdate();

				} catch (SQLException e) {
					h = null;
					System.out.println("Error Creating Product: " + e);
					System.out.println("Error for Create Error Method: HardwareDAO");
				}
				// Close the connection to the database - Very important!!!
				try {
					connect.close();
					connect = null;
				} catch (SQLException e) {
					System.out.println("Error closing connection: " + e);
					System.out.println("Error for Create Error Method:HardwareDAO");
				}

	}
	public void removeHardware(Hardware hardware) {
		// Get a connection
				try {
					connect = DriverManager.getConnection(url, username, password);
				} catch (SQLException e) {
					System.out.println("Error creating connection to database: " + e);
					System.exit(-1);
				}
				// Query to insert a record to the bank table
				String query = "DELETE FROM massahardware where p_id="+ hardware.getPID()+ ";";
				// Use prepared statements to avoid SQL injection attacks
				try (PreparedStatement statement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
					

					statement.executeUpdate();
					

				} catch (SQLException e) {
					hardware = null;
					System.out.println("Error Creating Order: " + e);
					System.out.println("Error for Remove Error Method: HardwareDAO");
				}
				
				try {
					connect.close();
					connect = null;
				} catch (SQLException e) {
					System.out.println("Error closing connection: " + e);
					System.out.println("Error for Remove Error Method: HardwareDAO");
				}


	}

}
