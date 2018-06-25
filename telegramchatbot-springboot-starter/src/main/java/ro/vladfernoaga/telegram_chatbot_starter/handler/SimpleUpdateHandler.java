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

import ro.vladfernoaga.telegram_chatbot_starter.impl.HelpService;
import ro.vladfernoaga.telegram_chatbot_starter.impl.LocationService;
import ro.vladfernoaga.telegram_chatbot_starter.impl.SimpleService;
import ro.vladfernoaga.telegram_chatbot_starter.impl.StartService;
import ro.vladfernoaga.telegram_chatbot_starter.impl.UnknownCommandService;
import ro.vladfernoaga.telegram_chatbot_starter.utils.MenuUtils;

@Service
public class SimpleUpdateHandler implements UpdatesListener {

	/** The Constant LOGGER. */
	public static final Logger LOGGER = LogManager.getLogger();


	@Autowired
	private TelegramBot bot;
	
	@Autowired
	private SimpleService simpleService;

	@Autowired
	private StartService startService;
	
	@Autowired
	private UnknownCommandService  unknownCommandService;
	
	@Autowired
	private HelpService  helpService;
	
	@Autowired
	private LocationService  locationService;
	
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
			return;
		} else if (update.message() != null) {
			processUserMessages(update.message());
			return;
		}
	}
	
	private void processUserLocation(Message message) {
		locationService.execute(bot, message);
		
	}

	private void processUserMessages(Message message)  {
		
		final String messageText = message.text();
			switch(messageText)
			{
				case MenuUtils.START: {
					startService.execute(bot,message);
					break;
				}
				case MenuUtils.HELP: {
					helpService.execute(bot,message);
					break;
				}
				default : 
				{
					unknownCommandService.execute(bot, message);
					break;
				}
			}
		
		
	}
	

}
