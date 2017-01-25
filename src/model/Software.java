package model;
/**
 * This class Determines Software attributes, which are private and can 
 * only be accessed through getter and setter methods. This class is also a subclass of
 * Product class.
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping System
 *
 */

public class Software extends Product{
	private double size;
	private String deliveryType;
	private String deliveryMethod;
	private int edition;
	
	public Software() {
	}

	// Getter / Setter methods for each attribute of the class

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public String getDelType() {
		return deliveryType;
	}

	public void setDelType(String delType) {
		this.deliveryType = delType;
	}
	
	public String getDelMethod() {
		return deliveryMethod;
	}

	public void setDelMethod(String method) {
		this.deliveryMethod = method;
	}
	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition= edition;
	}
}
