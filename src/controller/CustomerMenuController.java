package controller;

import dao.CustomerDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * This class determines operations for Menu page, when customer logged-in 
 * and perform operations if each button pressed
 * 
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping
 *         System
 *
 */

public class CustomerMenuController implements MenuControllable{
	CustomerDAO cdao = new CustomerDAO();
	
	
	@FXML
	private javafx.scene.control.Label label;
	
	
	private Stage dialogStage;
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	public String returnCIDName(int id){
		for (int i = 0; i < cdao.getCustomer(id).size(); i++) {
			return cdao.getCustomer(id).get(i).getCNAME();
		}
		return null;
	}
	public void setData(){
		label.setText(returnCIDName(UserLoginController.getCID()));
	}
	public void viewSoftware(){
		System.out.println("You have clicked Software");
		try {
			// Create a loader for the UI components
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CustomerSoftwareProduction.fxml"));
			// Inflate the view using the loader
			AnchorPane root = (AnchorPane) loader.load();
			// Set window title
			// Set window title
			dialogStage.setTitle("Software Production");

			// Create a scene with the inflated view
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			// Set the scene to the stage
			dialogStage.setScene(scene);
			
			CustomerSoftwareController customerSoftPro= loader.getController();
			customerSoftPro.setDialogStage(dialogStage);
			customerSoftPro.setData();
			
			// Get the controller instance from the loader
			// AddBankController controller = loader.getController();
			// Set the parent stage in the controller
			// controller.setDialogStage(primaryStage);
			// Show the view
			dialogStage.show();
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
			System.out.println("Error for view Software:CustomerMenuController");
		}
		
	}
	public void viewHardware(){
		System.out.println("You have clicked Hardware");
		try {
			// Create a loader for the UI components
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CustomerHardwareProduction.fxml"));
			// Inflate the view using the loader
			AnchorPane root = (AnchorPane) loader.load();
			// Set window title
			// Set window title
			dialogStage.setTitle("Hardware Production");

			// Create a scene with the inflated view
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			// Set the scene to the stage
			dialogStage.setScene(scene);
			
			CustomerHardwareController customerHardPro= loader.getController();
			customerHardPro.setDialogStage(dialogStage);
			customerHardPro.setData();
			
			// Get the controller instance from the loader
			// AddBankController controller = loader.getController();
			// Set the parent stage in the controller
			// controller.setDialogStage(primaryStage);
			// Show the view
			dialogStage.show();
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
			System.out.println("Error for view Software:CustomerMenuController");
		}
	}

	public void viewOrder(){
		System.out.println("you have clicked viewing Order");
		try {
			// Create a loader for the UI components
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CustomerOrders.fxml"));
			// Inflate the view using the loader
			AnchorPane root = (AnchorPane) loader.load();
			// Set window title
			// Set window title
			dialogStage.setTitle("Orders");

			// Create a scene with the inflated view
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			// Set the scene to the stage
			dialogStage.setScene(scene);
			
			CustomerOrdersController customerOrder= loader.getController();
			customerOrder.setDialogStage(dialogStage);
			customerOrder.setData();
			
			dialogStage.show();
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
			System.out.println("Error for view Orders:CustomerMenuController");
		}
	}
	
	public void signOut(){
		System.out.println("you have clicked Signing Out");

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
			System.out.println("Error for Sign Out method:CustomerMenuController");
			
		}
		
	}
	
	
	public void editProfile(){
		System.out.println("you have clicked editing Profile");
		
		try {
			// Create a loader for the UI components
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CustomerProfile.fxml"));
			// Inflate the view using the loader
			AnchorPane root = (AnchorPane) loader.load();
			// Set window title
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
}
