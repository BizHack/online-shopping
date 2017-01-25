package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import dao.HardwareDAO;
import dao.OrderDAO;
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
import model.Order;
/**
 * This class determines operations for Hardware page for customer and perform operation
 * if each button pressed
 * 
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping
 *         System
 *
 */

public class CustomerHardwareController implements backableOptions {
	private Stage dialogStage;
	int pID;

	ProductDAO pdao = new ProductDAO();
	OrderDAO odao = new OrderDAO();

	@FXML
	private TextField id;

	@FXML
	private TextField name;

	@FXML
	private TextField weight;

	@FXML
	private TableView<Hardware> productTable;
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

		productTable.setItems(FXCollections.observableArrayList(new HardwareDAO().getAllProductHardware()));
		combo.setItems(
				FXCollections.observableArrayList(getHardwarePIDList(new HardwareDAO().getAllProductHardware())));
	}

	public String returnProductName(int pID){
		for (int i = 0; i < pdao.getAllProducts().size(); i++) {
			if (pdao.getAllProducts().get(i).getPID()==pID) {
				return pdao.getAllProducts().get(i).getPNAME();
			}
		}
		return null;
	}
	public boolean checkAvail(int id){
		for (int i = 0; i < pdao.getAllProducts().size(); i++) {
			if (id==pdao.getAllProducts().get(i).getPID()) {
				return true;
				
			}
		}
		return false;
	}

	public Double returnProductPrice(int PID){
		for (int i = 0; i < pdao.getAllProducts().size(); i++) {
			if (pdao.getAllProducts().get(i).getPID()==PID) {
				return pdao.getAllProducts().get(i).getPPrice();
			}
		}
		return -1.0;
	}
	public void purchase() throws Exception {
		try {
			if (combo.getValue() == null) {
				throw new Exception();
			}
			System.out.println("Add to Cart");

			pID = Integer.valueOf(combo.getValue());
			System.out.println(pID);
			// pdao.checkAuthorization();

			Order order = new Order();

			String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
			
			if (checkAvail(pID)) {
				System.out.println("order has been submitted");
				order.setPID(pID);
				order.setODAte(timeStamp);
				order.setODelStatus(0);
			
				order.setONAME(returnProductName(pID));
			
				order.setOPrice(returnProductPrice(pID));
				order.setCID(UserLoginController.getCID());

				odao.createOrder(order);

			} else {
				return;
			}
		} catch (Exception firstError) {
			System.out.println("You have to select product id from drop box!!");
			
		}
	}

	public void viewOrder() {
		System.out.println("Add to Cart");
		try {
			// Create a loader for the UI components
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CustomerOrders.fxml"));
			// Inflate the view using the loader
			AnchorPane root = (AnchorPane) loader.load();
			// Set window title
			dialogStage.setTitle("Orders");

			// Create a scene with the inflated view
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			// Set the scene to the stage
			dialogStage.setScene(scene);

			CustomerOrdersController customerOrder = loader.getController();
			customerOrder.setDialogStage(dialogStage);
			customerOrder.setData();
			// Show the view
			dialogStage.show();
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
			System.out.println("Error for view Orders:CustomerHardwareController");
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

	public ArrayList<Integer> getHardwarePIDList(List<Hardware> h) {

		ArrayList<Integer> arr = new ArrayList<>();
		for (int i = 0; i < h.size(); i++) {
			arr.add(h.get(i).getPID());

		}
		return arr;
	}

}
