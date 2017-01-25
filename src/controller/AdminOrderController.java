package controller;

import java.util.ArrayList;
import java.util.List;
import dao.OrderDAO;
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
import model.Order;
/**
 * This class determines operations for Order Configuration page and perform operation
 * if each button pressed
 * 
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping
 *         System
 *
 */
public class AdminOrderController implements backableOptions {
	
	
		private Stage dialogStage;

		@FXML
		private TextField id;

		@FXML
		private TextField name;

		@FXML
		private TextField date;

		@FXML
		private TableView<Order> orderTable;

		@FXML
		private TableColumn<Order, Integer> Col1;
		@FXML
		private TableColumn<Order, String> Col2;
		@FXML
		private TableColumn<Order, Double> Col3;
		@FXML
		private TableColumn<Order, Integer> Col4;
		@FXML
		private TableColumn<Order, String> Col5;
		@FXML
		private TableColumn<Order, Integer> Col6;
		
		
		
		@FXML
		private ComboBox<Integer> combo;
		
		

		public void setDialogStage(Stage dialogStage) {
			this.dialogStage = dialogStage;
		}

		public void setData() {
			Col1.setCellValueFactory(new PropertyValueFactory<>("oID"));
			Col2.setCellValueFactory(new PropertyValueFactory<>("oNAME"));
			Col3.setCellValueFactory(new PropertyValueFactory<>("oPrice"));
			Col4.setCellValueFactory(new PropertyValueFactory<>("oDelStatus"));
			Col5.setCellValueFactory(new PropertyValueFactory<>("oDate"));
			Col6.setCellValueFactory(new PropertyValueFactory<>("pID"));

			orderTable.setItems(
					FXCollections.observableArrayList(new OrderDAO().getAllOrders()));
			
			combo.setItems(FXCollections.observableArrayList(returnOID(new OrderDAO().getAllOrders())));
			
		}
		public ArrayList<Integer> returnOID(List<Order> o){
			ArrayList<Integer> arr=new ArrayList<>();
			for (int i = 0; i < o.size(); i++) {
				arr.add(o.get(i).getOID());
			}
			return arr;
		}

		

		public void removeOrder() {
			if (combo.getValue()==null) {
				System.out.println("you have to select order id from dropbox");
				return;
			}
			System.out.println("Remove Order");

			int oID = 0;
	
			
			oID = Integer.valueOf(combo.getValue());

			Order order = new Order();
			order.setOID(oID);

			new OrderDAO().removeOrder(order);

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
				System.out.println("Error for return Home method:AdminOrderController");

			}
		}
	
	}



	
	


