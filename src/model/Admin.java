package model;
/**
 * This Class Determines Admin attributes, which are private and can 
 * only be accessed through getter and setter methods. 
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping System
 *
 */

public class Admin extends User {
	private Integer aID; 
	private String aName; 
	private String aType;
	private Integer uID;

	
	//Empty Default constructor
	public Admin() {
	}
	
	

	//Getter / Setter methods for each attribute of the class
	
	public Integer getAId() {
		return aID;
	}

	public void setAId(Integer id) {
		this.aID = id;
	}

	public Integer getUId() {
		return uID;
	}

	public void setUId(Integer id) {
		this.uID = id;
	}
	public String getANAME() {
		return aName;
	}

	public void setANAME(String name) {
		this.aName = name;
	}

	public String getAType() {
		return aType;
	}

	public void setAType(String type) {
		this.aType= type;
	}
}
