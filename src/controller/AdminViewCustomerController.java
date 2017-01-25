package controller;

import java.util.ArrayList;

import dao.CustomerDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Customer;
/**
 * This class determines operations for viewing customers page and perform operation
 * if each button pressed
 * 
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping
 *         System
 *
 */
public class AdminViewCustomerController implements backableOptions {
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
	private ComboBox<String> combo;

	public ArrayList<String> addToArray() {
		ArrayList<String> comboOptions = new ArrayList<>();
		String s1 = "Add Customer";
		String s2 = "Delete Customer";

		comboOptions.add(s1);
		comboOptions.add(s2);
		return comboOptions;

	}

	public void setData() {

		Col1.setCellValueFactory(new PropertyValueFactory<>("id"));
		Col2.setCellValueFactory(new PropertyValueFactory<>("cId"));
		Col3.setCellValueFactory(new PropertyValueFactory<>("user"));
		Col4.setCellValueFactory(new PropertyValueFactory<>("pass"));
		Col5.setCellValueFactory(new PropertyValueFactory<>("cNAME"));
		Col6.setCellValueFactory(new PropertyValueFactory<>("cAddr"));

		customerTable.setItems(FXCollections.observableArrayList(new CustomerDAO().getAllUserCustomers()));
		combo.setItems(FXCollections.observableArrayList(addToArray()));
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void goForCustomers() {
		if (combo.getValue() == null) {
			System.out.println("please select adding customer");
			return;
		}
		// System.out.println("Go for Customers");
		String s = combo.getValue();

		if (s.equals("Add Customer")) {
			System.out.println(s);

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
				System.out.println("Error for add Customer:AdminViewCustomerController");
			}

		}
		if (s.equals("Delete Customer")) {
			System.out.println(s);

		}
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
