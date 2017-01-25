package controller;

import dao.CustomerDAO;
import dao.userDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Customer;
import model.User;
/**
 * This class determines operations for adding customer page and perform operation
 * if each button pressed
 * 
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping
 *         System
 *
 */

public class AdminAddCustomerController implements backableOptions {

	userDAO udao = new userDAO();


	private Stage dialogStage;

	@FXML
	private TableView<Customer> customerTable;

	@FXML
	private TableColumn<Customer, Integer> Col1;
	@FXML
	private TableColumn<Customer, Integer> Col2;
	@FXML
	private TableColumn<Customer, String> Col3;
	@FXML
	private TableColumn<Customer, String> Col4;
	@FXML
	private TableColumn<Customer, String> Col5;
	@FXML
	private TableColumn<Customer, String> Col6;

	@FXML
	private TextField userName;
	@FXML
	private TextField pass;

	public void setData() {

		Col1.setCellValueFactory(new PropertyValueFactory<>("id"));
		Col2.setCellValueFactory(new PropertyValueFactory<>("cId"));
		Col3.setCellValueFactory(new PropertyValueFactory<>("user"));
		Col4.setCellValueFactory(new PropertyValueFactory<>("pass"));
		Col5.setCellValueFactory(new PropertyValueFactory<>("cNAME"));
		Col6.setCellValueFactory(new PropertyValueFactory<>("cAddr"));

		customerTable.setItems(FXCollections.observableArrayList(new CustomerDAO().getAllUserCustomers()));

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void refreshPage() {
		try {
			// Create a loader for the UI components
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminAddCustomer.fxml"));
			// Inflate the view using the loader
			AnchorPane root = (AnchorPane) loader.load();
			// Set window title
			dialogStage.setTitle("Add Customers");

			// Create a scene with the inflated view
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			// Set the scene to the stage
			dialogStage.setScene(scene);

			AdminAddCustomerController adminAddCustomer = loader.getController();
			adminAddCustomer.setDialogStage(dialogStage);
			adminAddCustomer.setData();

			dialogStage.show();
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
			System.out.println("Error for add Customer:AdminAddCustomerController");
		}

	}

	public boolean checkUseuAvailable(String name) {
		//System.out.println(name);
		userDAO udao = new userDAO();
		for (int i = 0; i < udao.getAllUsers().size(); i++) {
			//System.out.println(udao.getAllUsers().get(i).getUser());
			if (udao.getAllUsers().get(i).getUser().equals(name.trim().toLowerCase())) {
				return false;
			}
		}
		return true;
	}

	public void addCustomers() {
		System.out.println("Add Customer");
		if (this.userName.getText() == null || this.userName.getText().equals("")) {
			System.out.println("please enter user name");
			return;
		}
		if (this.pass.getText() == null || this.pass.getText().equals("")) {
			System.out.println("please enter password");
			return;
		}
		String username = this.userName.getText();

		String pass = this.pass.getText();

		if (!checkUseuAvailable(username)) {
			System.out.println("Username already Exits!!!Please add new Username");
			refreshPage();
			return;
		}
		User user = new User();
		user.setUser(username);
		user.setPass(pass);
		user.setUType("customer");

		udao.addUser(user);

		int i = udao.getAllUsers().get(udao.getAllUsers().size() - 1).getId();

		Customer cust = new Customer();
		cust.setId(i);
		cust.setCAddr(" ");
		cust.setCNAME(" ");

		new CustomerDAO().addCustomer(cust);

		refreshPage();

	}

	public void signOut() {
		System.out.println("Sign Out");
		try {
			// Create a loader for the UI components
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
			// Inflate the view using the loader
			AnchorPane root = (AnchorPane) loader.load();
			// Set window title
			dialogStage.setTitle("Log In");
			// Create a scene with the inflated view
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			// Set the scene to the stage
			dialogStage.setScene(scene);
			// Get the controller instance from the loader
			UserLoginController ucontroller = loader.getController();

			ucontroller.setDialogStage(dialogStage);
			// Show the view
			dialogStage.show();
		} catch (Exception e) {

			System.out.println("Error occured while inflating view: " + e);
			System.out.println("Main error");

		}
	}

	public void returnHome() {
		System.out.println("Home Menu");
		try {
			// Create a loader for the UI components
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminMenu.fxml"));
			// Inflate the view using the loader
			AnchorPane root = (AnchorPane) loader.load();
			// Set window title
			dialogStage.setTitle("Admin Menu");
			// Create a scene with the inflated view
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			// Set the scene to the stage
			dialogStage.setScene(scene);
			// Get the controller instance from the loader
			AdminMenuController adminMenu = loader.getController();

			adminMenu.setDialogStage(dialogStage);
			adminMenu.setData();
			// Show the view
			dialogStage.show();
		} catch (Exception e) {

			System.out.println("Error occured while inflating view: " + e);
			System.out.println("Error for return Home method: AdminSoftwareController");

		}
	}

}
