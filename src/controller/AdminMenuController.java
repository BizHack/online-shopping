package controller;

import dao.AdminDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * This class determines operations for Menu page when admin logs in and perform operation
 * if each button pressed
 * 
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping
 *         System
 *
 */

public class AdminMenuController implements MenuControllable {
	private Stage dialogStage;
	AdminDAO adao= new AdminDAO();
	
	@FXML
	private javafx.scene.control.Label label;
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setData(){
		//System.out.println(UserLoginController.getAID());
		label.setText(adao.getAdmin(UserLoginController.getAID()).getANAME());
	}
	public void viewSoftware(){
		System.out.println("Software");
		
		
		
		try {
			// Create a loader for the UI components
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminSoftwareProduction.fxml"));
			// Inflate the view using the loader
			AnchorPane root = (AnchorPane) loader.load();
			// Set window title

			dialogStage.setTitle("Software Production");

			// Create a scene with the inflated view
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			// Set the scene to the stage
			dialogStage.setScene(scene);
			
			AdminSoftwareController adminSoftPro= loader.getController();
			adminSoftPro.setDialogStage(dialogStage);
			adminSoftPro.setData();

			dialogStage.show();
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
			System.out.println("Error for view Software:AdminMenuController");
		}
		
	}
	public void viewHardware(){
		System.out.println("Hardware");

		
		try {
			// Create a loader for the UI components
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminHardwareProduction.fxml"));
			// Inflate the view using the loader
			AnchorPane root = (AnchorPane) loader.load();
			// Set window title
			dialogStage.setTitle("Hardware Production");

			// Create a scene with the inflated view
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			// Set the scene to the stage
			dialogStage.setScene(scene);
			
			AdminHardwareController adminHardPro= loader.getController();
			adminHardPro.setDialogStage(dialogStage);
			adminHardPro.setData();

			dialogStage.show();
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
			System.out.println("Error for view Hardware:AdminMenuController");
		}
		
	}
	
	
	public void viewOrder(){
		System.out.println("Order");
		
		try {
			// Create a loader for the UI components
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminOrder.fxml"));
			// Inflate the view using the loader
			AnchorPane root = (AnchorPane) loader.load();
			// Set window title
			dialogStage.setTitle("Orders");

			// Create a scene with the inflated view
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			// Set the scene to the stage
			dialogStage.setScene(scene);
			
			AdminOrderController adminOrder= loader.getController();
			adminOrder.setDialogStage(dialogStage);
			adminOrder.setData();

			dialogStage.show();
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
			System.out.println("Error for view Orders:AdminMenuController");
		}
		
	}
	public void signOut(){
		System.out.println("Signing Out");

		try {
			//Create a loader for the UI components
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
			//Inflate the view using the loader
            AnchorPane root = (AnchorPane) loader.load();
            //Set window title
            dialogStage.setTitle("Log In");
            //Create a scene with the inflated view
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            //Set the scene to the stage
            dialogStage.setScene(scene);
            //Get the controller instance from the loader
            UserLoginController ucontroller = loader.getController();

            ucontroller.setDialogStage(dialogStage);
            //Show the view
            dialogStage.show();
		} catch(Exception e) {
			
			System.out.println("Error occured while inflating view: " + e);
			System.out.println("Error for Sign Out method:AdminMenuController");
			
		}
	
	}
	

	public void editProfile(){
		System.out.println("Edit Profile");
		
		try {
			// Create a loader for the UI components
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminProfile.fxml"));
			// Inflate the view using the loader
			AnchorPane root = (AnchorPane) loader.load();
			// Set window title
			dialogStage.setTitle("Customer Profile");

			// Create a scene with the inflated view
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			// Set the scene to the stage
			dialogStage.setScene(scene);
			
			AdminProfileController adminProfile= loader.getController();
			adminProfile.setDialogStage(dialogStage);
			adminProfile.setData();

			dialogStage.show();
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
			System.out.println("Error for viewing Admin Profile:AdminMenuController");
		}
		
		
	}
	
	public void viewCustomers(){
		System.out.println("View Customers");
		
		try {
			// Create a loader for the UI components
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminViewCustomers.fxml"));
			// Inflate the view using the loader
			AnchorPane root = (AnchorPane) loader.load();
			// Set window title
			dialogStage.setTitle("Customer Lists");

			// Create a scene with the inflated view
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			// Set the scene to the stage
			dialogStage.setScene(scene);
			
			AdminViewCustomerController adminViewCustomers= loader.getController();
			adminViewCustomers.setDialogStage(dialogStage);
			adminViewCustomers.setData();
			
			dialogStage.show();
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
			System.out.println("Error for view Customers:AdminViewCustomersController");
		}
		
	}
	
}
