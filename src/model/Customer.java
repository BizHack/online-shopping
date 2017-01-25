package model;


/**
 * This Class Determines Customer attributes, which are private and can 
 * only be accessed through getter and setter methods. This class also is a subclass of 
 * User class. 
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping System
 *
 */

public class Customer extends User {

	private Integer cID; 
	private String cName; 
	private String cAddr;
	private Integer uID;
	//private List<Order> order;
	
	//Empty Default constructor
	public Customer() {
	}
	
	

	//Getter / Setter methods for each attribute of the class
	
	public Integer getCId() {
		return cID;
	}

	public void setCId(Integer id) {
		this.cID = id;
	}

	public Integer getUId() {
		return uID;
	}

	public void setUId(Integer id) {
		this.uID = id;
	}
	public String getCNAME() {
		return cName;
	}

	public void setCNAME(String name) {
		this.cName = name;
	}

	public String getCAddr() {
		return cAddr;
	}

	public void setCAddr(String pass) {
		this.cAddr= pass;
	}
	

}
