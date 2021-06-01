package org.cap.wallet.model;

public class Address {
	
	
	private String houseNumber;
	private String streetName;
	private String city;
	private String country;
	private String state;
	private int zipcode;
	
	
	public Address(String houseNumber, String streetName, String city, String country, String state, int zipcode) {
		super();
		this.houseNumber = houseNumber;
		this.streetName = streetName;
		this.city = city;
		this.country = country;
		this.state = state;
		this.zipcode = zipcode;
	}
	public Address() {
		// TODO Auto-generated constructor stub
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	
	@Override
	public String toString() {
		return "Address [houseNumber=" + houseNumber + ", streetName=" + streetName + ", city=" + city + ", country="
				+ country + ", state=" + state + ", zipcode=" + zipcode + "]";
	}
	
	

}
