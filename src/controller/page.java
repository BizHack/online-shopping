package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class page {
	private Stage dialogStage;	
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
