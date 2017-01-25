package dao;

import java.util.List;
import model.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * This class determines operations which connect Product Class to its Database table. In 
 * this class Product table can only be read and added.
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping System
 *
 */
public class ProductDAO {
	// Declaring connection and statement for DBMS connections
	private Connection connect = null;
	private Statement statement = null;
	// Declaring resultSet object for getting query results and use it to queue.
	private ResultSet resultSet = null;
	//private static ResultSet resultSet2 = null;

	private String url = "jdbc:mysql://localhost/javaproject?";
	private String username = "root";
	private String password = "";
	
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<>();
		
		// This will load the MySQL driver, each DB has its own driver
					try {
						Class.forName("com.mysql.jdbc.Driver");
					// Setup the connection with the DB
					connect = (Connection) DriverManager.getConnection(url + "user=" + username + "&password=" + password);
					// Statements allow to issue SQL queries to the database
					statement = (Statement) connect.createStatement();
					// Result set gets the result of the SQL query
					resultSet = statement.executeQuery("select p_id, p_name,p_type,p_price from massaproduction");
					// ResultSet is initially before the first data set
					while (resultSet.next()) {
						Product product = new Product();
						product.setPID(resultSet.getInt(1));
						product.setPNAME(resultSet.getString(2));
						product.setPType(resultSet.getString(3));
						product.setPPrice(resultSet.getDouble(4));
						products.add(product);
					}
					} catch (Exception e) {
						//e.printStackTrace();
					}
					try {
			            connect.close();
			            connect = null;
			        } catch(SQLException e) {
			            System.out.println("Error closing connection: " + e);
			        }
		
		return products;
	}
	
	public void addProduct(Product p){
		// Get a connection
				try {
					connect = DriverManager.getConnection(url, username, password);
				} catch (SQLException e) {
					System.out.println("Error creating connection to database: " + e);
					System.exit(-1);
				}
				// Query to insert a record to the bank table
				String query = "INSERT INTO massaproduction (p_name, p_price,p_type) VALUES (?,?,?) ;";
				// Use prepared statements to avoid SQL injection attacks
				try (PreparedStatement statement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
					
					statement.setString(1, p.getPNAME());
					statement.setDouble(2, p.getPPrice());
					statement.setString(3, p.getPType());
				
					statement.executeUpdate();
				

				} catch (SQLException e) {
					p = null;
					System.out.println("Error Creating Product: " + e);
					System.out.println("Error for Create Error Method: ProductDAO");
				}
				// Close the connection to the database
				try {
					connect.close();
					connect = null;
				} catch (SQLException e) {
					System.out.println("Error closing connection: " + e);
					System.out.println("Error for Create Error Method: ProductDAO");
				}

	}

	public void removeProduct(Product product) {
		// Get a connection
				try {
					connect = DriverManager.getConnection(url, username, password);
				} catch (SQLException e) {
					System.out.println("Error creating connection to database: " + e);
					System.exit(-1);
				}
			
				String query = "DELETE FROM massaproduction where p_id="+ product.getPID()+ ";";
				// Use prepared statements to avoid SQL injection attacks
				try (PreparedStatement statement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
					

					statement.executeUpdate();
					

				} catch (SQLException e) {
					product = null;
					
				}
				// Close the connection to the database 
				try {
					connect.close();
					connect = null;
				} catch (SQLException e) {
					//System.out.println("Error closing connection: " + e);
					//System.out.println("Error for Remove Error Method: ProductDAO");
				}


	}

	
}
