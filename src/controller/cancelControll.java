package controller;

import javafx.stage.Stage;
/**
 * This class determines ok button operation, when good bye windows pops up
 * 
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping
 *         System
 *
 */
public class cancelControll {

	private Stage dialogStage;
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void confirmCancel() {
		System.out.println("See u later");
		System.exit(0);
		
	}
	
	
	

}
