package ro.vladfernoaga.telegram_chatbot_starter.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

import ro.vladfernoaga.telegram_chatbot_starter.repo.CommonMessagesRepo;
import ro.vladfernoaga.telegram_chatbot_starter.utils.MenuUtils;

@Service
public class UnknownCommandService {

	
	@Autowired
	private MenuUtils menuUtils;
	
	public Void execute(TelegramBot bot,Message message)
	{
		
		
		Integer chatId = message.from().id();
		String messageText = message.text();
		Integer messageId =message.messageId();
		
		SendMessage request = new SendMessage(chatId, String.format("Nu recunosc mesajul %s",messageText))
				.parseMode(ParseMode.HTML)
				.disableNotification(false)
				.replyToMessageId(messageId)
				.replyMarkup(new ForceReply())
				;
		bot.execute(request);
		return null;
	}

}
