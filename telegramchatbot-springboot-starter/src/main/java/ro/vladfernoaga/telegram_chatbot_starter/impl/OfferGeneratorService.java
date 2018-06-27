package ro.vladfernoaga.telegram_chatbot_starter.impl;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import ro.vladfernoaga.telegram_chatbot_starter.model.*;
import ro.vladfernoaga.telegram_chatbot_starter.service.ILocationService;
import ro.vladfernoaga.telegram_chatbot_starter.service.IOfferGeneratorService;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.maps.errors.ApiException;
import com.pengrad.telegrambot.model.Location;


@Service
public class OfferGeneratorService implements IOfferGeneratorService{

	@Autowired
	private ILocationService locationService;

	private Map<String, List<String>> m = new HashMap<>();

	protected OfferGeneratorService() {

	}

	void init() {

		m.put("seller", Arrays.asList("Ion Popescu", "Cristi Tanase", "Cristian Borcea", "Andrei Tanase"));
		m.put("rating", Arrays.asList("4", "3", "2", "5"));
	}

	private Map<String, String> generateOfferDetails() {
		init();
		Map<String, String> ret = new HashMap<>();
		for (String k : m.keySet()) {
			int index = new Random().nextInt(m.get(k).size());
			ret.put(k, m.get(k).get(index));
		}
		return ret;
	}

	public Offer generateOffer(double lat,double lng) {

		JSONObject details = new JSONObject();
		String address = "";
		
		try {
			address = getRandomLocation(lat,lng, 1000);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String> m = generateOfferDetails();
		for (String k : m.keySet()) {
			details.put(k, m.get(k));
		}
		double price =ThreadLocalRandom.current().nextDouble(45000, 50000);
		double mp =ThreadLocalRandom.current().nextDouble(45, 90);
		return new Offer(address,details.toString(),price,mp);
	}

	private String getRandomLocation(double lat, double longit, int radius)
			throws ApiException, InterruptedException, IOException {

		Random random = new Random();


		double radiusInDegrees = radius / 111000f;

		double u = random.nextDouble();
		double v = random.nextDouble();
		double w = radiusInDegrees * Math.sqrt(u);
		double t = 2 * Math.PI * v;
		double x = w * Math.cos(t);
		double y = w * Math.sin(t);


		double new_x = x / Math.cos(Math.toRadians(longit));

		double foundLatitude = new_x + lat;
		double foundLongitude = y + longit;
		String location = locationService.getStreet(foundLatitude, foundLongitude);

		return location;

	}
	
	
	
}
