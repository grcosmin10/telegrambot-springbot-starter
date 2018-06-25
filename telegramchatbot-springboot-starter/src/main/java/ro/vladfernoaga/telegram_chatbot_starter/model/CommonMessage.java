package ro.vladfernoaga.telegram_chatbot_starter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COMMONMESSAGE")
public class CommonMessage {
	@Id
	@Column(name= "ID")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	
	@Column(name = "LANGUAGE")
	private String language;
	
	@Column(name = "MESSAGE", unique = true)
	private String message;
	
	@Column(name = "CONDITION", unique = true)
	private String condition;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

}
