package ro.vladfernoaga.telegram_chatbot_starter.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AddressType;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Location;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

import ro.vladfernoaga.telegram_chatbot_starter.dao.IPersonDAO;
import ro.vladfernoaga.telegram_chatbot_starter.model.Offer;
import ro.vladfernoaga.telegram_chatbot_starter.utils.MenuUtils;

@Service
public class LocationService{
	
	@Autowired
	private GeoApiContext context;
	
	@Autowired
	private OfferGenerator offerGenerator;
	
	@Autowired
	private IPersonDAO personDAO;
	
	@Autowired
	private MenuUtils menuUtils;
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	
	public void execute(TelegramBot bot, Message message){
		
		final Integer chatId = message.from().id();
		final Integer messageId = message.messageId();
		final Location userLocation = message.location();
		
		String currentLocation = "N/A";
		
		try {
			currentLocation = getCity(userLocation.latitude(), userLocation.longitude());
		} catch (ApiException | InterruptedException | IOException e) {
			LOGGER.error(String.format("Error caught while trying to parse the location: " + e.getMessage()));
			
			final SendMessage botResponse = new SendMessage(chatId,
					String.format("The location could not be parsed due to a conversion error, please try again later")).parseMode(ParseMode.HTML)
							.disableNotification(false).replyToMessageId(messageId).replyMarkup(new ForceReply());
			bot.execute(botResponse);
			
			
		}
		
		personDAO.insertLocation(currentLocation, chatId);
		final SendMessage response = processLocation(chatId, messageId,userLocation, currentLocation, false);
		bot.execute(response);
		
	
	}
	
public String getCity(double lat, double lng) throws ApiException, InterruptedException, IOException {
		
		final GeocodingResult[] results = GeocodingApi.newRequest(context).latlng(new LatLng(lat, lng)).language("en")
				.resultType(AddressType.COUNTRY, AddressType.ADMINISTRATIVE_AREA_LEVEL_2).await();
		
		final String city = results[0].addressComponents[0].longName;
		
		return city;
		
	}

public String getStreet(double lat, double lng) throws ApiException, InterruptedException, IOException {
	
	final GeocodingResult[] results = GeocodingApi.newRequest(context).latlng(new LatLng(lat, lng)).language("en")
			.resultType(AddressType.STREET_ADDRESS, AddressType.ADMINISTRATIVE_AREA_LEVEL_2).await();
	
	final String street = results[0].formattedAddress;
	
	return street;
	
}


public String genString(Location userLocation,int n) throws ApiException, InterruptedException, IOException
{	StringBuffer sb = new StringBuffer();
	List <Offer>list =new ArrayList<>();
	for(int i=0;i<n;i++)
	{
		list.add(offerGenerator.generateOffer(userLocation));
	}
	 for(Offer o : list)
		{
			sb.append(o.toString());
		}
	 String newValue = sb.toString();
	 return newValue;
}


public SendMessage processLocation(Integer chatId, Integer messageId,Location userLocation, String locationStr,boolean saveLocation) {
	SendMessage sendMessage;
	if (locationStr == null) {
		sendMessage = new SendMessage(chatId, String.format("location was not received"))
				.parseMode(ParseMode.HTML).disableNotification(false).replyToMessageId(messageId)
				.replyMarkup(new ForceReply());
	} else {
		try {
			
			if (saveLocation) {
				sendMessage = new SendMessage(chatId,
						String.format("location saved"+ locationStr)).parseMode(ParseMode.HTML).disableNotification(false)
										.replyToMessageId(messageId).replyMarkup((new ForceReply()));
			} else {
				sendMessage = new SendMessage(chatId,
						String.format("Your location is :"+ locationStr+"\r\n Offers : "+genString(userLocation,3))).parseMode(ParseMode.HTML).disableNotification(false)
										.replyMarkup(new ForceReply());
			}
		} catch (final Exception e) {
			sendMessage = new SendMessage(chatId, String.format("not parsed"))
					.parseMode(ParseMode.HTML).disableNotification(false).replyToMessageId(messageId)
					.replyMarkup(menuUtils.showMainMenu());
		}
	}
	return sendMessage;
}
}