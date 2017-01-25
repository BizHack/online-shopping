package model;
/**
 * This class Determines User attributes, which are private and can 
 * only be accessed through getter and setter methods. 
 * @author Manoochehr Assa-ITMD510 Final Project- Computer Online Shopping System
 *
 */

public class User{

	
	
	
	private Integer uID; 
	private String username; 
	private String password;
	private String uType;
	
	//Empty Default constructor
	public User() {
	}
	
	//Getter / Setter methods for each attribute of the class
	
	public Integer getId() {
		return uID;
	}

	public void setId(Integer id) {
		this.uID = id;
	}

	public String getUser() {
		return username;
	}

	public void setUser(String name) {
		this.username = name;
	}

	public String getPass() {
		return password;
	}

	public void setPass(String pass) {
		this.password= pass;
	}
	public String getUType() {
		return uType;
	}

	public void setUType(String utype) {
		this.uType= utype;
	}
}
