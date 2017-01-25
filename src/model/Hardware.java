package model;
/**
 * This Class Determines Hardware attributes, which are private and can 
 * only be accessed through getter and setter methods. This class is also a subclass of 
 * Product class.
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping System
 *
 */
public class Hardware extends Product {

	private double weight;
	private double dimension;
	private int fragile;
	private String deliveryMethod;
	private int availabilityNum;
	
	public Hardware() {
	}

	// Getter / Setter methods for each attribute of the class

	public double getWeight() {
		return weight;
	}

	public void setWeight(double w) {
		this.weight = w;
	}

	public double getDimension() {
		return dimension;
	}

	public void setDimension(Double dim) {
		this.dimension = dim;
	}

	public int getFragile() {
		return fragile;
	}

	public void setFragile(int fragile) {
		this.fragile = fragile;
	}
	
	public String getDelMethod() {
		return deliveryMethod;
	}

	public void setDelMethod(String method) {
		this.deliveryMethod = method;
	}
	public int getAvailNum() {
		return availabilityNum;
	}

	public void setAvailNum(int num) {
		this.availabilityNum = num;
	}
}
