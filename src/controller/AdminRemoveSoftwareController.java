package controller;

import java.util.ArrayList;
import java.util.List;
import dao.ProductDAO;
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
import model.Product;
import model.Software;
/**
 * This class determines operations for removing software page and perform operation
 * if each button pressed
 * 
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping
 *         System
 *
 */

public class AdminRemoveSoftwareController implements backableOptions {
	ProductDAO pdao=new ProductDAO();
	SoftwareDAO sdao=new SoftwareDAO();

	
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
		private ComboBox<Integer> combo;


		public void setData(){
		
			
			Col1.setCellValueFactory(new PropertyValueFactory<>("pNAME"));

			Col2.setCellValueFactory(new PropertyValueFactory<>("size"));
			Col3.setCellValueFactory(new PropertyValueFactory<>("edition"));

			Col4.setCellValueFactory(new PropertyValueFactory<>("pPrice"));
			Col5.setCellValueFactory(new PropertyValueFactory<>("pType"));
			Col6.setCellValueFactory(new PropertyValueFactory<>("delType"));
			Col7.setCellValueFactory(new PropertyValueFactory<>("pID"));

			softwareTable.setItems(FXCollections.observableArrayList(new SoftwareDAO().getAllProductSoftwares()));
			combo.setItems(FXCollections.observableArrayList(getSoftwarePIDList(new SoftwareDAO().getAllProductSoftwares())));
		}
		public ArrayList<Integer> getSoftwarePIDList(List<Software> s){
			
			ArrayList<Integer> arr= new ArrayList<>();
			for (int i = 0; i < s.size(); i++) {
				arr.add(s.get(i).getPID());
				
			}
			return arr;
		}

		public String returnProductName(int pID){
			for (int i = 0; i < pdao.getAllProducts().size(); i++) {
				if (pdao.getAllProducts().get(i).getPID()==pID) {
					return pdao.getAllProducts().get(i).getPNAME();
				}
			}
			return null;
		}
		public Double returnProductPrice(int PID){
			for (int i = 0; i < pdao.getAllProducts().size(); i++) {
				if (pdao.getAllProducts().get(i).getPID()==PID) {
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
		public void removeSoftwares(){
			if (combo.getValue()==null) {
				System.out.println("Please select product id from dropbox");
				return;
			}
			
			System.out.println("Remove Software");
			// Extract the data from the view elements
			int pID = 0;
			System.out.println(combo.getValue());
	
			
			pID = Integer.valueOf(combo.getValue());

			Product product=new Product();
			Software soft=new Software();
			product.setPID(pID);
			soft.setPID(pID);

			new SoftwareDAO().removeSoftware(soft);
			pdao.removeProduct(product);
			


			setData();
		}
		
		public void setDialogStage(Stage dialogStage) {
			this.dialogStage = dialogStage;
		}


}
