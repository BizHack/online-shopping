package controller;

import dao.CustomerDAO;
import dao.OrderDAO;
import dao.ProductDAO;
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
/**
 * This class determines operations for Customer Profile page and perform operation
 * if each button pressed
 * 
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping
 *         System
 *
 */

public class CustomerProfileController implements backableOptions {
	private Stage dialogStage;
	int pID;

	ProductDAO pdao = new ProductDAO();
	OrderDAO odao= new OrderDAO();

	@FXML
	private TextField address;

	@FXML
	private TextField name;

	
	
	@FXML
	private TableView<Customer> customerTable;
	@FXML
	private TableColumn<Customer, Integer> Col1;
	@FXML
	private TableColumn<Customer, String> Col2;
	@FXML
	private TableColumn<Customer, String> Col3;
	
	
	

	
	public void setData(){
	
		
		
		
		Col1.setCellValueFactory(new PropertyValueFactory<>("cId"));
		
		Col2.setCellValueFactory(new PropertyValueFactory<>("cNAME"));
		Col3.setCellValueFactory(new PropertyValueFactory<>("cAddr"));

	customerTable.setItems(FXCollections.observableArrayList(new CustomerDAO().getCustomer(UserLoginController.getCID())));
	
	}


	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	public void refreshPage(){
		try {
			// Create a loader for the UI components
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CustomerProfile.fxml"));
			// Inflate the view using the loader
			AnchorPane root = (AnchorPane) loader.load();
			// Set window title
			dialogStage.setTitle("Customer Profile");

			// Create a scene with the inflated view
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			// Set the scene to the stage
			dialogStage.setScene(scene);
			
			CustomerProfileController customerProfile= loader.getController();
			customerProfile.setDialogStage(dialogStage);
			customerProfile.setData();
			
			dialogStage.show();
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
			System.out.println("Error for viewing Customer Profile:CustomerMenuController");
		}
	}

	public void modifyAddr() {
		System.out.println("Change Address");
		
		String addr = this.address.getText();

		addr=addr.trim().toLowerCase();
		
		
		
		
		if (addr == null || addr.trim().equals("")) {
			System.out.println("Please enter new address");
			return;
		}
		int cID=UserLoginController.getCID();
		
		System.out.println(cID);
		Customer cust = new Customer();

		cust.setCAddr(addr);
		cust.setCId(cID);
		

		new CustomerDAO().updateCustomerAddr(cust);
		
		refreshPage();
		
		
		
	}

	public void modifyName() {
		System.out.println("Change Names");

		String name = this.name.getText();
		
		name=name.trim().toLowerCase();

		
		
		
		// Validate the data
		if (name == null || name.trim().equals("")) {
			System.out.println("Please enter a name to update the information");
			return;
		}

		name=name.trim().toLowerCase();
		
		int cID=UserLoginController.getCID();

		Customer cust = new Customer();
		cust.setCNAME(name);;
		cust.setCId(cID);
		

		new CustomerDAO().updateCustomerName(cust);

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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CustomerMenu.fxml"));
			// Inflate the view using the loader
			AnchorPane root = (AnchorPane) loader.load();
			// Set window title
			dialogStage.setTitle("Customer Menu");
			// Create a scene with the inflated view
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			// Set the scene to the stage
			dialogStage.setScene(scene);
			// Get the controller instance from the loader
			CustomerMenuController customMenu = loader.getController();

			customMenu.setDialogStage(dialogStage);
			customMenu.setData();
			// Show the view
			dialogStage.show();
		} catch (Exception e) {

			System.out.println("Error occured while inflating view: " + e);
			System.out.println("Error for return Home method: CustomerSoftwareController");

		}
	}

}
