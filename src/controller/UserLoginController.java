package controller;

import dao.AdminDAO;
import dao.CustomerDAO;
import dao.ProductDAO;
import dao.userDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;

/**
 * This class determines operations for first login page and perform operation
 * if each button pressed
 * 
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping
 *         System
 *
 */
public class UserLoginController  {

	ProductDAO pdao = new ProductDAO();
	private static int CID;
	private static int AID;

	private Stage dialogStage;

	@FXML
	private TextField name;

	@FXML
	private TextField pass;

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean authorizeUsers(String pass1, String pass2) {

		if (pass1.trim().toLowerCase().equals(pass2.trim().toLowerCase())) {
			return true;
		} else {
			return false;
		}

	}

	public boolean checkUserAvailabity(String name) {
		userDAO udao = new userDAO();
		for (int i = 0; i < udao.getAllUsers().size(); i++) {
			if (udao.getAllUsers().get(i).getUser().equals(name)) {
				return true;
			}
		}

		return false;
	}
/**
 * if sign-in button pressed,,,
 */
	public void signin() {
		// Extract the data from the view elements
		userDAO udao = new userDAO();
		String name = this.name.getText();
		String pass = this.pass.getText();
		name = name.trim().toLowerCase();
		pass = pass.trim().toLowerCase();

		

		// Validate the data
		if (name == null || name.trim().equals("")) {
			return;
		}
		if (pass == null || pass.trim().equals("")) {
			return;
		}

		if (!checkUserAvailabity(name)) {
			System.out.println("Incorrect user");
			this.name.setText("");
			this.pass.setText("");
			return;
			
			//refreshPage();
			//System.exit(0);
		}

		User user = new User();
		user.setUser(name);

		if (authorizeUsers(pass, udao.readUsers(user).getPass())) {
			System.out.println("Correct Password");
			System.out.println("USername: "+udao.readUsers(user).getUser());
			System.out.println("Password: "+udao.readUsers(user).getPass());
			//System.out.println("Your Type: "+udao.readUsers(user).getUType());

			if (udao.readUsers(user).getUType().equals("customer")) {
				System.out.println("logging as Customer");
				CustomerDAO cdao = new CustomerDAO();
				
				CID = cdao.returnCID(udao.readUsers(user).getId()).getCId();
				System.out.println("Here");
				CustomerOpenSighinWindow();

			} else if (udao.readUsers(user).getUType().equals("admin")) {
				System.out.println("Logging as Admin");
				AdminDAO adao = new AdminDAO();

				AID = adao.returnAID(udao.readUsers(user).getId()).getAId();
				AdminOpenSighinWindow();

			} else {
				System.out.println("other types");
				System.exit(0);
			}

		} else {
			System.out.println("Incorrect Password!!");
			this.name.setText("");
			this.pass.setText("");
			return;
			
			//refreshPage();
		}
	}
/**
 * if cancel pressed,,,
 */
	public void cancel() {
		System.out.println("Cancel");
		System.out.println(pass.getText());

		OpenCancelWindow();

	}

	public static int getCID() {
		return CID;
	}

	public static int getAID() {
		return AID;
	}

	public void CustomerOpenSighinWindow() {

		try {
			// Create a loader for the UI components
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CustomerMenu.fxml"));
			// Inflate the view using the loader
			AnchorPane root = (AnchorPane) loader.load();
			// Set window title
			dialogStage.setTitle("Cusotmer Menu");

			// Create a scene with the inflated view
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// Set the scene to the stage
			dialogStage.setScene(scene);

			CustomerMenuController customerMenu = loader.getController();
			customerMenu.setDialogStage(dialogStage);
			customerMenu.setData();

			dialogStage.show();
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
			System.out.println("Error for OpenSigninwindows:UserLoginController");
		}

	}

	public void AdminOpenSighinWindow() {

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

			AdminMenuController adminMenu = loader.getController();
			adminMenu.setDialogStage(dialogStage);
			adminMenu.setData();

			dialogStage.show();
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
			System.out.println("Error for AdminOpenSigninwindows:UserLoginController");
		}

	}

	public void OpenCancelWindow() {

		try {
			// Create a loader for the UI components
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GoodbyeWindow.fxml"));
			// Inflate the view using the loader
			AnchorPane root = (AnchorPane) loader.load();
			// Set window title
			dialogStage.setTitle("Goodbye");
			// Create a scene with the inflated view
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			// Set the scene to the stage
			dialogStage.setScene(scene);

			cancelControll cControll = loader.getController();
			cControll.setDialogStage(dialogStage);

			dialogStage.show();
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
			System.out.println("Error for OpenCancelWindows:UserLoginController");
		}

	}

}
