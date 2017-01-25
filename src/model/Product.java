package model;
/**
 * This class Determines Product attributes, which are private and can 
 * only be accessed through getter and setter methods. 
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping System
 *
 */

public class Product {

	private Integer pID;
	private String pName;
	private double pPrice;
	private String pType;
	// private List<Order> order;

	// Empty Default constructor
	public Product() {
	}
	public Product(String id, String name){
		this.pID=Integer.valueOf(id);
		this.pName=name;
	}

	// Getter / Setter methods for each attribute of the class

	public Integer getPID() {
		return pID;
	}

	public void setPID(Integer id) {
		this.pID = id;
	}
	

	public String getPNAME() {
		return pName;
	}

	public void setPNAME(String name) {
		this.pName = name;
	}

	public double getPPrice() {
		return pPrice;
	}

	public void setPPrice(double price) {
		this.pPrice = price;
	}
	
	public String getPType() {
		return pType;
	}

	public void setPType(String type) {
		this.pType = type;
	}
	

}
