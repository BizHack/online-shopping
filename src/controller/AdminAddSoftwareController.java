package controller;

import java.util.ArrayList;
import dao.ProductDAO;
import dao.SoftwareDAO;
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
import model.Product;
import model.Software;
/**
 * This class determines operations for add software page and perform operation
 * if each button pressed
 * 
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping
 *         System
 *
 */

public class AdminAddSoftwareController implements backableOptions {

	ProductDAO pdao=new ProductDAO();
	SoftwareDAO sdao=new SoftwareDAO();

	
		private Stage dialogStage;

		@FXML
		private TableView<Software> softwareTable;
		
		@FXML
		private TextField name;
		
		@FXML
		private TextField size;

		@FXML
		private TextField edition;

		@FXML
		private TextField price;

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
		private ComboBox<String> typeCombo;

		
		public String calculateDownloadLink(String name,String edition){
			String s= "http://"+name+edition+".com";
			return s;
		}
		public ArrayList<String> typeComboArray(){
			ArrayList<String> comboOptions= new ArrayList<>();
			
			
			String s1="Download";
			String s2="CD";
			String s3="DVD";
			
			comboOptions.add(s1);
			comboOptions.add(s2);
			comboOptions.add(s3);
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
			typeCombo.setItems(
					FXCollections.observableArrayList(typeComboArray()));
			
	
			
		}
		
		public void setDialogStage(Stage dialogStage) {
			this.dialogStage = dialogStage;
		}


		public void addSoftwares() {
			try {

				if (this.name.getText()==null||this.name.getText().equals("")) {
					System.out.println("please enter name");
					return;
				}
				
				if (this.size.getText()==null||this.size.getText().equals("")) {
					System.out.println("please enter size");
					return;
				}
				if (Double.valueOf(this.size.getText())<0) {
					System.out.println("Invalid Input, Please enter positive number");
					refreshPage();
				}
				if (this.edition.getText()==null||this.edition.getText().equals("")) {
					System.out.println("please enter edition");
					return;
				}
				if (Integer.valueOf(this.edition.getText())<0) {
					System.out.println("Invalid Input, Please enter positive number");
					refreshPage();
				}
				if (this.price.getText()==null||this.price.getText().equals("")) {
					System.out.println("please enter price");
					return;
				}
				if (Integer.valueOf(this.price.getText())<0) {
					System.out.println("Invalid Input, Please enter positive number");
					refreshPage();
				}
				if (typeCombo.getValue()==null) {
					System.out.println("please select software delivery types from dropbox");
					return;
				}
				
				String name=this.name.getText();
				double size=Double.valueOf(this.size.getText());
				int edition=Integer.valueOf(this.edition.getText());
				double price=Double.valueOf(this.price.getText());
				String s= typeCombo.getValue();
				
				
				
				Product product= new Product();
				product.setPNAME(name);
				product.setPPrice(price);
				product.setPType("s");
				
				pdao.addProduct(product);
				
				int i=pdao.getAllProducts().get(pdao.getAllProducts().size()-1).getPID();
				
				Software soft=new Software();
				soft.setPID(i);
				soft.setEdition(edition);
				soft.setSize(size);
				soft.setDelType(s);
				soft.setDelMethod("NULL");
				
				sdao.addSoftware(soft);
				refreshPage();

				
			} catch (Exception e) {
				System.out.println("Your Inputs are invalid.Please enter that again");
				refreshPage();
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
		public void refreshPage(){
			try {
				// Create a loader for the UI components
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminAddSoftware.fxml"));
				// Inflate the view using the loader
				AnchorPane root = (AnchorPane) loader.load();
				// Set window title
				dialogStage.setTitle("Add Softwares");

				// Create a scene with the inflated view
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				// Set the scene to the stage
				dialogStage.setScene(scene);
				
				AdminAddSoftwareController adminAddSoft= loader.getController();
				adminAddSoft.setDialogStage(dialogStage);
				adminAddSoft.setData();

				dialogStage.show();
			} catch (Exception e) {
				System.out.println("Error occured while inflating view: " + e);
				System.out.println("Error for adding softwares:AdminAddSoftwareController");
			}
		}
		


}
