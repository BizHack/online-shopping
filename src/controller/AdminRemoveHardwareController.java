package controller;

import java.util.ArrayList;
import java.util.List;

import dao.HardwareDAO;
import dao.ProductDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Hardware;
import model.Product;
/**
 * This class determines operations for removing hardware page and perform operation
 * if each button pressed
 * 
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping
 *         System
 *
 */

public class AdminRemoveHardwareController implements backableOptions {

	ProductDAO pdao = new ProductDAO();
	HardwareDAO hdao = new HardwareDAO();

	private Stage dialogStage;

	@FXML
	private TableView<Hardware> hardwareTable;

	@FXML
	private TextField name;

	@FXML
	private TextField weight;

	@FXML
	private TextField dimension;
	@FXML
	private TextField price;
	@FXML
	private TextField fragile;
	@FXML
	private TextField availability;

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
	private ComboBox<Integer> combo;

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

		combo.setItems(
				FXCollections.observableArrayList(getHardwarePIDList(new HardwareDAO().getAllProductHardware())));

	}

	public ArrayList<Integer> getHardwarePIDList(List<Hardware> h) {

		ArrayList<Integer> arr = new ArrayList<>();
		for (int i = 0; i < h.size(); i++) {
			arr.add(h.get(i).getPID());

		}
		return arr;
	}

	public String returnProductName(int pID) {
		for (int i = 0; i < pdao.getAllProducts().size(); i++) {
			if (pdao.getAllProducts().get(i).getPID() == pID) {
				return pdao.getAllProducts().get(i).getPNAME();
			}
		}
		return null;
	}

	public Double returnProductPrice(int PID) {
		for (int i = 0; i < pdao.getAllProducts().size(); i++) {
			if (pdao.getAllProducts().get(i).getPID() == PID) {
				return pdao.getAllProducts().get(i).getPPrice();
			}
		}
		return -1.0;
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
			dialogStage.setTitle("Customer Menu");
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
			System.out.println("Error for return Home method: AdminRemoveHardwareController");

		}
	}

	public void removeHardware() {
		if (combo.getValue() == null) {
			System.out.println("please select product id");
			return;
		}
		System.out.println("Remove Hardware");
		// Extract the data from the view elements

		int pID = 0;
		System.out.println(combo.getValue());

		pID = Integer.valueOf(combo.getValue());
		// pdao.checkAuthorization();

		Product product = new Product();
		Hardware hard = new Hardware();
		product.setPID(pID);
		hard.setPID(pID);

		new HardwareDAO().removeHardware(hard);
		pdao.removeProduct(product);

		setData();
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

}
