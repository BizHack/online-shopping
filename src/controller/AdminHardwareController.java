package controller;

import java.util.ArrayList;

import dao.HardwareDAO;
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
import model.Hardware;
/**
 * This class determines operations for Hardware Configuration page and 
 * perform operation if each button pressed
 * 
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping
 *         System
 *
 */

public class AdminHardwareController implements backableOptions {
	private Stage dialogStage;

	@FXML
	private TableView<Hardware> hardwareTable;

	@FXML
	private TableColumn<Hardware, String> Col1;
	@FXML
	private TableColumn<Hardware, Double> Col2;
	@FXML
	private TableColumn<Hardware, Double> Col3;
	@FXML
	private TableColumn<Hardware, Double> Col4;
	@FXML
	private TableColumn<Hardware, String> Col5;
	@FXML
	private TableColumn<Hardware, Integer> Col6;
	@FXML
	private TableColumn<Hardware, Integer> Col7;
	@FXML
	private TableColumn<Hardware, Integer> Col8;

	@FXML
	private ComboBox<String> combo;

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setData() {

		Col1.setCellValueFactory(new PropertyValueFactory<>("pNAME"));

		Col2.setCellValueFactory(new PropertyValueFactory<>("weight"));
		Col3.setCellValueFactory(new PropertyValueFactory<>("dimension"));

		Col4.setCellValueFactory(new PropertyValueFactory<>("pPrice"));
		Col5.setCellValueFactory(new PropertyValueFactory<>("pType"));
		Col6.setCellValueFactory(new PropertyValueFactory<>("fragile"));
		Col7.setCellValueFactory(new PropertyValueFactory<>("pID"));
		Col8.setCellValueFactory(new PropertyValueFactory<>("availNum"));

		hardwareTable.setItems(FXCollections.observableArrayList(new HardwareDAO().getAllProductHardware()));
	
		combo.setItems(FXCollections.observableArrayList(addToArray()));

	}

	public ArrayList<String> addToArray() {
		ArrayList<String> comboOptions = new ArrayList<>();
		String s1 = "Add Hardware";
		String s2 = "Delete Hardware";

		comboOptions.add(s1);
		comboOptions.add(s2);
	//	comboOptions.add(s3);
		return comboOptions;

	}

	public void goForHardwares() {
		if (combo.getValue()==null) {
			System.out.println("please enter select Oprtion for hardware");
			return;
		}
		
		String s = combo.getValue();
		

		if (s.equals("Add Hardware")) {
			System.out.println(s);
			try {
				// Create a loader for the UI components
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminAddHardware.fxml"));
				// Inflate the view using the loader
				AnchorPane root = (AnchorPane) loader.load();
				// Set window title
				dialogStage.setTitle("Add Hardware");
				// Create a scene with the inflated view
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				// Set the scene to the stage
				dialogStage.setScene(scene);
				// Get the controller instance from the loader
				AdminAddHardwareController adminAddHardcontroller = loader.getController();

				adminAddHardcontroller.setDialogStage(dialogStage);
				adminAddHardcontroller.setData();
				// Show the view
				dialogStage.show();
			} catch (Exception e) {

				System.out.println("Error occured while inflating view: " + e);
				System.out.println("Main error");

			}
		}
		if (s.equals("Delete Hardware")) {
			System.out.println(s);
			try {
				// Create a loader for the UI components
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminRemoveHardware.fxml"));
				// Inflate the view using the loader
				AnchorPane root = (AnchorPane) loader.load();
				// Set window title
				dialogStage.setTitle("Remove Hardware");
				// Create a scene with the inflated view
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				// Set the scene to the stage
				dialogStage.setScene(scene);
				// Get the controller instance from the loader
				AdminRemoveHardwareController adminRemoveHardcontroller = loader.getController();

				adminRemoveHardcontroller.setDialogStage(dialogStage);
				adminRemoveHardcontroller.setData();
				// Show the view
				dialogStage.show();
			} catch (Exception e) {

				System.out.println("Error occured while inflating view: " + e);
				System.out.println("Main error");

			}
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
