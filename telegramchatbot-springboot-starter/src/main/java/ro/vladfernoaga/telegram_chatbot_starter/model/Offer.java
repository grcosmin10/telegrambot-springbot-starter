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
	
	private double latitude;
	
	private double longtitude;
	
	private String offerDetails;

	public Offer( double latitude, double longtitude, String offerDetails) {
		
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.offerDetails = offerDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public String getOfferDetails() {
		return offerDetails;
	}

	public void setOfferDetails(String offerDetails) {
		this.offerDetails = offerDetails;
	}
	
	
	
}
