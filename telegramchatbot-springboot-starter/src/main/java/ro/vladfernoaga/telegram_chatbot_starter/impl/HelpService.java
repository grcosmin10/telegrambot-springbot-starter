package ro.vladfernoaga.telegram_chatbot_starter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

import ro.vladfernoaga.telegram_chatbot_starter.model.CommonMessage;
import ro.vladfernoaga.telegram_chatbot_starter.repo.CommonMessagesRepo;
import ro.vladfernoaga.telegram_chatbot_starter.utils.MenuUtils;

@Service
public class HelpService {
	@Autowired
	CommonMessagesRepo commonMessagesRepo;
	
	public void execute(TelegramBot bot,Message message)
	{
		Integer chatId = message.from().id();
		Integer messageId =message.messageId();
		String helpList = String.join(System.lineSeparator(), MenuUtils.helpList);
		
		SendMessage request = new SendMessage(chatId, String.format("Am urmatoarele optiuni :"  +System.lineSeparator()+helpList))
				.parseMode(ParseMode.HTML)
				.disableNotification(false)
				.replyToMessageId(messageId)
				.replyMarkup(new ForceReply())
				;
		bot.execute(request);
	}

}
