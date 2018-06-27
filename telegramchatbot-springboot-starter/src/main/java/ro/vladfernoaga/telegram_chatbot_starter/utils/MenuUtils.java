package ro.vladfernoaga.telegram_chatbot_starter.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

import ro.vladfernoaga.telegram_chatbot_starter.config.Properties;

@Component
public final class MenuUtils {
	
	@Autowired
	private Properties properties;
	
	public final static String START = "/start";	
	public final static String HELP = "/help";	
	public final static String LOCATION = "/location";	
	public final static String ANOTHER_LOCATION = "alta locatie";	
	public final static String DENY = "nu";
	public final static String CHOSEN_LOCATION = "/loc ";
	
 public static String[] helpList = {START,HELP,LOCATION,ANOTHER_LOCATION,DENY};
	
	



	public ReplyKeyboardMarkup showMainMenu() {
		final KeyboardButton button1 = new KeyboardButton(
				properties.getButton1());
		final KeyboardButton button2 = new KeyboardButton(
				properties.getButton2());;
		
		
		button1.requestLocation(true);
		
		final KeyboardButton[][] buttonsList = new KeyboardButton[1][2];
		buttonsList[0][0] = button1;
		buttonsList[0][1] = button2;
		//buttonsList[1][0] = button3;
		//buttonsList[1][1] = button4;
		
		final ReplyKeyboardMarkup menuReplyKeyboard = new ReplyKeyboardMarkup(buttonsList);
		menuReplyKeyboard.resizeKeyboard(true);
		menuReplyKeyboard.oneTimeKeyboard(true);
		return menuReplyKeyboard;
	}
	
	public ReplyKeyboardMarkup shareDetailsMenu(Integer chatId, Integer messageId) {
		final KeyboardButton button3 = new KeyboardButton(properties.getButton3());
		final KeyboardButton button4 = new KeyboardButton(properties.getButton4());
		button3.requestContact(true);
		
		final KeyboardButton[][] buttonsList = new KeyboardButton[1][2];
		buttonsList[0][0] = button3;
		buttonsList[0][1] = button4;
		
		final ReplyKeyboardMarkup contactDetailsKeyboard = new ReplyKeyboardMarkup(buttonsList);
		contactDetailsKeyboard.resizeKeyboard(true);
		contactDetailsKeyboard.oneTimeKeyboard(true);
		
		return contactDetailsKeyboard;
	}
}
