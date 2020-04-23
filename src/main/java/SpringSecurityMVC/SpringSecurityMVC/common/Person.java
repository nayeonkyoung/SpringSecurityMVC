package SpringSecurityMVC.SpringSecurityMVC.common;

import java.io.Serializable;
public class Person implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	// TODO Auto-generated method stub
	private String name = "";
	private Company company;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
