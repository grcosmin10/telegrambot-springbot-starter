package ro.vladfernoaga.telegram_chatbot_starter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

import ro.vladfernoaga.telegram_chatbot_starter.dao.ICommonMessageDAO;
import ro.vladfernoaga.telegram_chatbot_starter.service.IUnknownCommandService;


@Service
public class UnknownCommandService implements IUnknownCommandService{
	
	@Autowired
	private ICommonMessageDAO commonMessageDAO;
	
	public void start(TelegramBot bot,Message message)
	{
		
		
		Integer chatId = message.from().id();
		String messageText = message.text();
		Integer messageId =message.messageId();
		
		SendMessage request = new SendMessage(chatId, String.format(commonMessageDAO.getMessage("unknown"),messageText))
				.parseMode(ParseMode.HTML)
				.disableNotification(false)
				.replyToMessageId(messageId)
				.replyMarkup(new ForceReply())
				;
		bot.execute(request);
	}

}
