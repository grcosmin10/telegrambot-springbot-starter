package ro.vladfernoaga.telegram_chatbot_starter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "Oferte")
public class Offer {
	
	
	@Id
	@Column(name= "ID")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	
	private String address;
	
	private String offerDetails;
	
	private double price;
	
	private double mp;

	public Offer( String address, String offerDetails,double price,double mp) {
		
		this.address=address;
		this.offerDetails = offerDetails;
		this.price=price;
		this.mp=mp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getMp() {
		return mp;
	}

	public void setMp(double mp) {
		this.mp = mp;
	}

	public String getOfferDetails() {
		return offerDetails;
	}

	public void setOfferDetails(String offerDetails) {
		this.offerDetails = offerDetails;
	}

	@Override
	public String toString() {
		return "\r\n  Location: " + address
		+ " \r\n Description: " + offerDetails + " \r\n"+
		 " Price: " +String.format("%.2f", price) + " $" + " \r\n"
		+ " Mp2: " + String.format("%.2f", mp) +"metrii patrati"+ " \r\n";
		//return "Offer [id=" + id + ", address=" + address + ", offerDetails=" + offerDetails + "]";
	}
	
	
	
}
