package controller;

import java.util.ArrayList;
import dao.SoftwareDAO;
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
import model.Software;
/**
 * This class determines operations for Software configuration page for 
 * admin and perform operation if each button pressed
 * 
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping
 *         System
 *
 */

public class AdminSoftwareController implements backableOptions{
	private Stage dialogStage;

	@FXML
	private TableView<Software> softwareTable;

	@FXML
	private TableColumn<Software, String> Col1;
	@FXML
	private TableColumn<Software, Double> Col2;
	@FXML
	private TableColumn<Software, Integer> Col3;
	@FXML
	private TableColumn<Software, Integer> Col4;
	@FXML
	private TableColumn<Software, String> Col5;
	@FXML
	private TableColumn<Software, String> Col6;
	@FXML
	private TableColumn<Software, Integer> Col7;

	@FXML
	private ComboBox<String> combo;

	public ArrayList<String> addToArray() {
		ArrayList<String> comboOptions = new ArrayList<>();
		String s1 = "Add Software";
		String s2 = "Delete Software";

		comboOptions.add(s1);
		comboOptions.add(s2);

		return comboOptions;

	}

	public void setData() {

		Col1.setCellValueFactory(new PropertyValueFactory<>("pNAME"));

		Col2.setCellValueFactory(new PropertyValueFactory<>("size"));
		Col3.setCellValueFactory(new PropertyValueFactory<>("edition"));

		Col4.setCellValueFactory(new PropertyValueFactory<>("pPrice"));
		Col5.setCellValueFactory(new PropertyValueFactory<>("pType"));
		Col6.setCellValueFactory(new PropertyValueFactory<>("delType"));
		Col7.setCellValueFactory(new PropertyValueFactory<>("pID"));

		softwareTable.setItems(FXCollections.observableArrayList(new SoftwareDAO().getAllProductSoftwares()));
		combo.setItems(FXCollections.observableArrayList(addToArray()));
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void refreshPage() {
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

			AdminSoftwareController adminSoftPro = loader.getController();
			adminSoftPro.setDialogStage(dialogStage);
			adminSoftPro.setData();

			dialogStage.show();
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
			System.out.println("Error for view Software:AdminMenuController");
		}

	}

	public void goForSoftwares() throws Exception {
		String s = combo.getValue();

		try {

			if (s == null && s.equals("")) {
				throw new Exception();
			}

		

			if (s.equals("Add Software")) {
				System.out.println(s);

				try {
					// Create a loader for the UI components
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminAddSoftware.fxml"));
					// Inflate the view using the loader
					AnchorPane root = (AnchorPane) loader.load();
					// Set window title
					// Set window title
					dialogStage.setTitle("Add Softwares");

					// Create a scene with the inflated view
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
					// Set the scene to the stage
					dialogStage.setScene(scene);

					AdminAddSoftwareController adminAddSoftware = loader.getController();
					adminAddSoftware.setDialogStage(dialogStage);
					adminAddSoftware.setData();

					dialogStage.show();
				} catch (Exception e) {
					System.out.println("Error occured while inflating view: " + e);
					System.out.println("Error for add Software:AdminSoftwareController");
				}

			}
			if (s.equals("Delete Software")) {
				System.out.println(s);

				try {
					// Create a loader for the UI components
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminDeleteSoftware.fxml"));
					// Inflate the view using the loader
					AnchorPane root = (AnchorPane) loader.load();
					// Set window title
					dialogStage.setTitle("Delete Softwares");

					// Create a scene with the inflated view
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
					// Set the scene to the stage
					dialogStage.setScene(scene);

					AdminRemoveSoftwareController adminDelSoftware = loader.getController();
					adminDelSoftware.setDialogStage(dialogStage);
					adminDelSoftware.setData();

					dialogStage.show();
				} catch (Exception e) {
					System.out.println("Error occured while inflating view: " + e);
					System.out.println("Error for delete Software:AdminRemoveSoftwareController ");
				}
			}
			
		} catch (Exception firstError) {
			System.out.println("Please select one of the option for software from dopbox");
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
