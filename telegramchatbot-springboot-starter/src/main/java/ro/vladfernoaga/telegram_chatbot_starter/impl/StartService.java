package ro.vladfernoaga.telegram_chatbot_starter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import ro.vladfernoaga.telegram_chatbot_starter.dao.ICommonMessageDAO;
import ro.vladfernoaga.telegram_chatbot_starter.dao.PersonDAO;
import ro.vladfernoaga.telegram_chatbot_starter.model.Person;
import ro.vladfernoaga.telegram_chatbot_starter.repo.PersonRepo;
import ro.vladfernoaga.telegram_chatbot_starter.service.IStartService;
import ro.vladfernoaga.telegram_chatbot_starter.utils.MenuUtils;

@Service
public class StartService implements IStartService{
	@Autowired
	private ICommonMessageDAO commonMessageDAO;
	@Autowired
	PersonDAO personDAO;
	@Autowired
	private PersonRepo personRepo;
	@Autowired
	private MenuUtils menuUtils;
	
	public void start(TelegramBot bot,Message message)
	{	SendMessage botResponse;
		Integer chatId = message.from().id();
		Integer messageId =message.messageId();
		final Person inputPerson = personRepo.findById(chatId);
		if (inputPerson != null && inputPerson.getFirstName() != null) {
			botResponse = new SendMessage(chatId, String.format(commonMessageDAO.getMessage("regUser")))
				.parseMode(ParseMode.HTML)
				.disableNotification(false)
				.replyToMessageId(messageId)
				.replyMarkup(menuUtils.showMainMenu())
				;
		bot.execute(botResponse);}
		
		else
		{
			
				personDAO.insertPerson(chatId);
				
				ReplyKeyboardMarkup shareKeyboard = menuUtils.shareDetailsMenu(chatId, messageId);
				
				botResponse = new SendMessage(chatId, commonMessageDAO.getMessage("newUser")).parseMode(ParseMode.HTML)
						.disableNotification(false).replyToMessageId(messageId).replyMarkup(shareKeyboard);
				
				bot.execute(botResponse);
			
		}
	}  

}
