package ro.vladfernoaga.telegram_chatbot_starter.handler;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.maps.errors.ApiException;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

import ro.vladfernoaga.telegram_chatbot_starter.impl.DenyService;
import ro.vladfernoaga.telegram_chatbot_starter.impl.HelpService;
import ro.vladfernoaga.telegram_chatbot_starter.impl.LocationService;
import ro.vladfernoaga.telegram_chatbot_starter.impl.ShareContactService;
import ro.vladfernoaga.telegram_chatbot_starter.impl.SimpleService;
import ro.vladfernoaga.telegram_chatbot_starter.impl.StartService;
import ro.vladfernoaga.telegram_chatbot_starter.impl.UnknownCommandService;
import ro.vladfernoaga.telegram_chatbot_starter.service.IAnotherLocationService;
import ro.vladfernoaga.telegram_chatbot_starter.service.IBasicLocationService;
import ro.vladfernoaga.telegram_chatbot_starter.service.IHelpService;
import ro.vladfernoaga.telegram_chatbot_starter.service.ILocationService;
import ro.vladfernoaga.telegram_chatbot_starter.service.IStartService;
import ro.vladfernoaga.telegram_chatbot_starter.service.IUnknownCommandService;
import ro.vladfernoaga.telegram_chatbot_starter.utils.MenuUtils;

@Service
public class SimpleUpdateHandler implements UpdatesListener {

	/** The Constant LOGGER. */
	public static final Logger LOGGER = LogManager.getLogger();

	@Autowired
	private TelegramBot bot;

	@Autowired
	private IStartService startService;
	
	@Autowired
	private IUnknownCommandService  unknownCommandService;
	
	@Autowired
	private IHelpService  helpService;
	
	@Autowired
	private ILocationService  locationService;
	
	@Autowired
	private IBasicLocationService basicLocationService;
	
	@Autowired
	private IAnotherLocationService anotherLocationService;
	
	@Autowired
	private ShareContactService shareContactService;
	
	@Autowired
	private DenyService denyService;
	
	@Override
	public int process(List<Update> updates) {
		for (Update update : updates) {
			process(update);
		}
		return UpdatesListener.CONFIRMED_UPDATES_ALL;
	}

	private void process(Update update) {
		if (update.message() != null && update.message().location() != null) {
			processUserLocation(update.message());
		} else if (update.message() != null && update.message().contact() != null) {
			processUserContact(update.message());	
			
		} else if (update.message() != null) {
			processUserMessages(update.message());
		
		}
	}
	
	private void processUserContact(Message message) {
		shareContactService.start(bot, message);	
	}

	private void processUserLocation(Message message) {
		locationService.start(bot, message);
		
	}

	private void processUserMessages(Message message)  {
		final String messageText = message.text();
		if(messageText.startsWith(MenuUtils.CHOSEN_LOCATION)) 
			basicLocationService.start(bot, message);
		else
			switch(messageText)
			{
				case MenuUtils.START: {
					startService.start(bot,message);
					break;
				}
				case MenuUtils.HELP: {
					helpService.start(bot,message);
					break;
				}
				case MenuUtils.ANOTHER_LOCATION: {
					anotherLocationService.start(bot,message);
					break;
				}
				case MenuUtils.DENY :{
					denyService.start(bot,message);
					break;
				}
				default : 
				{
					unknownCommandService.start(bot, message);
					break;
				}
			}
		
		
	}
	

}
