package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Admin;
/**
 * This class determines operations which connect Admin Class to its Database table. In 
 * this class Admin table can only be read and updated.
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping System
 *
 */

public class AdminDAO {
	
		// Declaring connection and statement for DBMS connections
		private Connection connect = null;
		private Statement statement = null;
		// Declaring resultSet object for getting query results and use it to queue.
		private ResultSet resultSet = null;
		private String url = "jdbc:mysql://localhost/javaproject?";
		private String username = "root";
		private String password = "";

		//private Integer result = null;
		public List<Admin> getAdminList(int aid) {

			List<Admin> admins = new ArrayList<>();

			// This will load the MySQL driver, each DB has its own driver
			try {
				Class.forName("com.mysql.jdbc.Driver");
				// Setup the connection with the DB
				connect = (Connection) DriverManager.getConnection(url + "user=" + username + "&password=" + password);
				// Statements allow to issue SQL queries to the database
				statement = (Statement) connect.createStatement();
				// Result set gets the result of the SQL query
				resultSet = statement.executeQuery("select a_id,a_name,a_type from massaadmin where a_id=" + aid);
				// ResultSet is initially before the first data set
				while (resultSet.next()) {
					Admin a = new Admin();
					a.setAId(resultSet.getInt(1));
					a.setANAME(resultSet.getString(2));
					a.setAType(resultSet.getString(3));

					admins.add(a);
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

			return admins;
		}
		public Admin returnAID(int uid) {
			Admin a= new Admin();
			try

			{
				// This will load the MySQL driver, each DB has its own driver
				Class.forName("com.mysql.jdbc.Driver");
				// Setup the connection with the DB
				connect = (Connection) DriverManager.getConnection(url + "user=" + username + "&password=" + password);
				// Statements allow to issue SQL queries to the database
				statement = (Statement) connect.createStatement();
				// Result set gets the result of the SQL query
				resultSet = statement.executeQuery("select a_id,a_name,a_type from massaadmin where u_id=" + uid);
				// ResultSet is initially before the first data set
				while (resultSet.next()) {
					
					a.setAId(resultSet.getInt(1));
					a.setANAME(resultSet.getString(2));
					a.setAType(resultSet.getString(3));

					//System.out.println(result);
					// if (result.trim().equals(uname)&&result2.trim().equals(pass))
					// {
					// return true;
					//
					// }
					// System.out.println(result);

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
			return a;

		}

		public Admin getAdmin(int aid) {

			Admin admin = new Admin();

			// This will load the MySQL driver, each DB has its own driver
			try {
				Class.forName("com.mysql.jdbc.Driver");
				// Setup the connection with the DB
				connect = (Connection) DriverManager.getConnection(url + "user=" + username + "&password=" + password);
				// Statements allow to issue SQL queries to the database
				statement = (Statement) connect.createStatement();
				// Result set gets the result of the SQL query
				resultSet = statement.executeQuery("select a_id,a_name,a_type from massaadmin where a_id="+aid);
				// ResultSet is initially before the first data set
				while (resultSet.next()) {
				
					admin.setAId(resultSet.getInt(1));
					admin.setANAME(resultSet.getString(2));
					admin.setAType(resultSet.getString(3));
					
					

					
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

			return admin;
		}

		public void updateAdminType(Admin admin) {

			// Get a connection
			try {
				connect = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				System.out.println("Error creating connection to database: " + e);
				System.exit(-1);
			}
			System.out.println(admin.getAType());
			// Query to insert a record to the bank table
			String query = "UPDATE massaadmin SET a_type='"+admin.getAType()+"' WHERE a_id="+admin.getAId()+";";
			// Use prepared statements to avoid SQL injection attacks
			try (PreparedStatement statement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

				statement.executeUpdate();

			} catch (SQLException e) {
				admin = null;
				System.out.println("Error Creating Order: " + e);
				System.out.println("Error for Admin Update Method: AdminDAO");
			}
			// Close the connection to the database - Very important!!!
			try {
				connect.close();
				connect = null;
			} catch (SQLException e) {
				System.out.println("Error closing connection: " + e);
				System.out.println("Error for Admin Update Error Method: AdminDAO");
			}

		}
		
		public void updateAdminName(Admin admin){
			// Get a connection
					try {
						connect = DriverManager.getConnection(url, username, password);
					} catch (SQLException e) {
						System.out.println("Error creating connection to database: " + e);
						System.exit(-1);
					}
					System.out.println(admin.getANAME());
					// Query to insert a record to the bank table
					String query = "UPDATE massaadmin SET a_name='"+admin.getANAME()+"' WHERE a_id="+admin.getAId()+";";
					// Use prepared statements to avoid SQL injection attacks
					try (PreparedStatement statement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

						statement.executeUpdate();

					} catch (SQLException e) {
						admin = null;
						System.out.println("Error Creating Order: " + e);
						System.out.println("Error for Admin Update Method: ADminDAO");
					}
					// Close the connection to the database - Very important!!!
					try {
						connect.close();
						connect = null;
					} catch (SQLException e) {
						System.out.println("Error closing connection: " + e);
						System.out.println("Error for ADmin Update Error Method: AdminDAO");
					}

		}

	

}
