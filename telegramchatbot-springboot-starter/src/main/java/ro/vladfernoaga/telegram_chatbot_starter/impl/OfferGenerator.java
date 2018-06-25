package ro.vladfernoaga.telegram_chatbot_starter.impl;

import java.util.*;
import ro.vladfernoaga.telegram_chatbot_starter.model.*;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.pengrad.telegrambot.model.Location;
@Service
public class OfferGenerator {
	
	
	private static final double radius = 0.02;
	
	private Map<String, List<String>> m = new HashMap<>();
	
	
	protected OfferGenerator() {
		
	}
	
	void init() {
	
		m.put("price", Arrays.asList("699.99", "299.99", "400.50"));
		m.put("seller", Arrays.asList("Ion Popescu", "Cristi Tanase", "Cristian Borcea", "Andrei Tanase"));
		m.put("rating", Arrays.asList("4","3","2","5"));
	
	}
	
	
	private Map<String, String> generateOfferDetails() {
		
		Map<String, String> ret = new HashMap<>();
		
		for(String k : m.keySet()) {
			
			int index = new Random().nextInt(m.get(k).size());
			
			ret.put(k, m.get(k).get(index));
			
			
		}
		
		return ret;
		
	}
	
	public Offer generateOffer(Location location) {
		
		
//		double x = new Random().nextDouble(location.latitude());
//		new Random().next
//		
		double x = 0.0;
		double y = 1.1;
		
		JSONObject details = new JSONObject();
		
		Map<String, String> m = generateOfferDetails();
		
		for(String k : m.keySet()) {
			details.put(k, m.get(k)); 
		}
		
		return new Offer(x, y, details.toString());
		
		
	}
	
}
