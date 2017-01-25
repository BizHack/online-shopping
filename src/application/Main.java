package application;
	

import controller.UserLoginController;
//import itmd510.fp.controller.AddBankController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * This class will show login page 
 * 
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping
 *         System
 *Admin:
 *Username:manoochehr	
 *Pass: assa
 *Customer1:
 *Username: fpuser	
 *pass: 510
 *Customer2:
 *Username: fpuser2
 *Pass: 510
 *Customer3
 *Username: fpuser3
 *Pass: 510 
 */
public class Main extends Application {
	
	
	@Override
	public void start(Stage primaryStage) {

		
		try {
			//Create a loader for the UI components
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
			//Inflate the view using the loader
            AnchorPane root = (AnchorPane) loader.load();
            //Set window title
            primaryStage.setTitle("Log In");
            //Create a scene with the inflated view
            Scene scene = new Scene(root);
            //adding css file
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            
            
            //Set the scene to the stage
            primaryStage.setScene(scene);
            //Get the controller instance from the loader
            UserLoginController ucontroller = loader.getController();

            ucontroller.setDialogStage(primaryStage);
            //Show the view
            primaryStage.show();
		} catch(Exception e) {
			
			System.out.println("Error occured while inflating view: " + e);
			System.out.println("Main error");
			
		}
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
