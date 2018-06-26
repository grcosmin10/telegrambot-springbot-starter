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

	public Offer( String address, String offerDetails) {
		
		this.address=address;
		this.offerDetails = offerDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		+ " \r\n Description: " + offerDetails + " \r\n";
		//return "Offer [id=" + id + ", address=" + address + ", offerDetails=" + offerDetails + "]";
	}
	
	
	
}
