package controller;

import dao.AdminDAO;
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
import model.Admin;
import model.Customer;
import javafx.scene.control.*;
/**
 * This class determines operations for ADministrator personal profile page
 *  and perform operation if each button pressed
 * 
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping
 *         System
 *
 */

public class AdminProfileController implements backableOptions{

	private Stage dialogStage;
	int pID;

	ProductDAO pdao = new ProductDAO();
	OrderDAO odao = new OrderDAO();

	@FXML
	private CheckBox check1;

	@FXML
	private CheckBox check2;

	@FXML
	private TextField name;

	@FXML
	private TableView<Admin> adminTable;
	@FXML
	private TableColumn<Customer, Integer> Col1;
	@FXML
	private TableColumn<Customer, String> Col2;
	@FXML
	private TableColumn<Customer, String> Col3;

	public void setData() {

		Col1.setCellValueFactory(new PropertyValueFactory<>("aId"));

		Col2.setCellValueFactory(new PropertyValueFactory<>("aNAME"));
		Col3.setCellValueFactory(new PropertyValueFactory<>("aType"));

		adminTable
				.setItems(FXCollections.observableArrayList(new AdminDAO().getAdminList(UserLoginController.getAID())));

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void updateAdminTable(String str) {
		int aID = UserLoginController.getAID();
		Admin adm = new Admin();
		adm.setAId(aID);
		adm.setAType(str);

		new AdminDAO().updateAdminType(adm);

		setData();

	}

	public void modifyType() {
		System.out.println("Change Type");
		if (check1.isSelected() && !check2.isSelected()) {
			System.out.println("Administrator");
			updateAdminTable("Administrator");
		} else if (check2.isSelected() && !check1.isSelected()) {
			System.out.println("Manager");
			updateAdminTable("Manager");

		} else {
			System.out.println("error in input.Please choose one of them");
		}
		
	}

	public void modifyName() {

		System.out.println("Change Names");

		String name = this.name.getText();

		name = name.trim().toLowerCase();

		// Validate the data
		if (name == null || name.trim().equals("")) {
			System.out.println("please enter new name");
			return;
		}

		name = name.trim().toLowerCase();

		int aID = UserLoginController.getAID();


		Admin adm = new Admin();
		adm.setANAME(name);
		adm.setAId(aID);

		new AdminDAO().updateAdminName(adm);

		setData();

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
			System.out.println("Error for return Home method: CustomerSoftwareController");

		}
	}

}
