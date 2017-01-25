package controller;
import dao.HardwareDAO;
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
import model.Hardware;
import model.Product;
/**
 * This class determines operations for adding hardware page and perform operation
 * if each button pressed
 * 
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping
 *         System
 *
 */

public class AdminAddHardwareController implements backableOptions {


		ProductDAO pdao=new ProductDAO();
		HardwareDAO hdao=new HardwareDAO();

		
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

			}

			public void refreshPage(){


				try {
					// Create a loader for the UI components
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminAddHardware.fxml"));
					// Inflate the view using the loader
					AnchorPane root = (AnchorPane) loader.load();
					// Set window title
					dialogStage.setTitle("Add Hardwares");

					// Create a scene with the inflated view
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
					// Set the scene to the stage
					dialogStage.setScene(scene);
					
					AdminAddHardwareController adminAddHard= loader.getController();
					adminAddHard.setDialogStage(dialogStage);
					adminAddHard.setData();
	
					dialogStage.show();
				} catch (Exception e) {
					System.out.println("Error occured while inflating view: " + e);
					System.out.println("Error for adding Hardware:AdminAddHardwareController");
				}
			}

			public void addHardwares() {
				try {
					//System.out.println("Go for Softwares");
					if (this.name.getText()==null||this.name.getText().equals("")) {
						System.out.println("please enter name");
						return;
					}
					if (this.weight.getText()==null||this.weight.getText().equals("")) {
						System.out.println("please enter weight");
						return;
					}
					if (Double.valueOf(this.weight.getText())<0) {
						System.out.println("Invalid Input, Please enter positive number");
						refreshPage();
					}
					if (this.dimension.getText()==null||this.dimension.getText().equals("")) {
						System.out.println("please enter dimension");
						return;
					}
					if (Double.valueOf(this.dimension.getText())<0) {
						System.out.println("Invalid Input, Please enter positive number");
						refreshPage();
					}
					if (this.price.getText()==null||this.price.getText().equals("")) {
						System.out.println("please enter price");
						return;
					}
					if (Double.valueOf(this.price.getText())<0) {
						System.out.println("Invalid Input, Please enter positive number");
						refreshPage();
					}
					if (this.fragile.getText()==null||this.fragile.getText().equals("")) {
						System.out.println("please select fragility");
						return;
					}
					if (Integer.valueOf(this.fragile.getText())<0) {
						System.out.println("Invalid Input, Please enter positive number");
						refreshPage();
					}
					if (this.availability.getText()==null||this.availability.getText().equals("")) {
						System.out.println("please availability number");
						return;
					}
					if (Integer.valueOf(this.availability.getText())<0) {
						System.out.println("Invalid Input, Please enter positive number");
						refreshPage();
					}
					
					String name=this.name.getText();
					double weight=Double.valueOf(this.weight.getText());
					double dimension=Double.valueOf(this.dimension.getText());
					double price=Double.valueOf(this.price.getText());
					int fragile=Integer.valueOf(this.fragile.getText());
					int availNum=Integer.valueOf(this.availability.getText());
				
					
					Product product= new Product();
					product.setPNAME(name);
					product.setPPrice(price);
					product.setPType("s");
					
					pdao.addProduct(product);
					
					int i=pdao.getAllProducts().get(pdao.getAllProducts().size()-1).getPID();
					
					Hardware hard=new Hardware();
					hard.setPID(i);
					hard.setWeight(weight);
					hard.setAvailNum(availNum);
					hard.setDelMethod("NULL");
					hard.setDimension(dimension);
					hard.setFragile(fragile);

					hdao.addHardware(hard);

					refreshPage();
				} catch (Exception e) {
					System.out.println("Your input are invalid.Please enter inputs again");
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

		
}
