package model;
/**
 * This class Determines Order attributes, which are private and can 
 * only be accessed through getter and setter methods. 
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping System
 *
 */


public class Order {
	private Integer oID;
	private String oName;
	private double oTotalPrice;
	private Integer cID;
	private Integer oDeliveryStatus;
	private String oDate;
	private int pID;
	
	// private List<Order> order;

	// Empty Default constructor
	public Order() {
	}
	
	// Getter / Setter methods for each attribute of the class

	public Integer getOID() {
		return oID;
	}

	public void setOID(Integer id) {
		this.oID = id;
	}
	

	public String getONAME() {
		return oName;
	}

	public void setONAME(String name) {
		this.oName = name;
	}

	public double getOPrice() {
		return oTotalPrice;
	}

	public void setOPrice(double price) {
		this.oTotalPrice = price;
	}
	
	public Integer getCID() {
		return cID;
	}

	public void setCID(Integer id) {
		this.cID = id;
	}
	public int getODelStatus() {
		return oDeliveryStatus;
	}

	public void setODelStatus(int status) {
		this.oDeliveryStatus = status;
	}
	public String getODate() {
		return oDate;
	}

	public void setODAte(String date) {
		this.oDate = date;
	}
public int getPID(){
	return pID;
}
public void setPID(int id){
	this.pID=id;
}
/*
	public ArrayList<Integer> getProductID() {
		return p;
	}
	
	

	public void setProductList(ArrayList<Integer> productsID) {
		
		for (int i = 0; i < p.size(); i++) {
			p.set(i, null);
		}
		for (int i = 0; i < productsID.size(); i++) {
			p.add(productsID.get(i));
			
			
			
		}
	}
	*/
	
	
	
	
	
	

}
