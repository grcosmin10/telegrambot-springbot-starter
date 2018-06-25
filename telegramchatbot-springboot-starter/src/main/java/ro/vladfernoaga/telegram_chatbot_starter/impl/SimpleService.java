package ro.vladfernoaga.telegram_chatbot_starter.impl;

import org.springframework.stereotype.Service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

@Service
public class SimpleService {
	
	
	public void execute(TelegramBot bot,Message message)
	{
		Integer chatId = message.from().id();
		String messageText = message.text();
		Integer messageId =message.messageId();
		
		SendMessage request = new SendMessage(chatId, String.format("Execut comanda : %s",messageText))
				.parseMode(ParseMode.HTML)
				.disableNotification(false)
				.replyToMessageId(messageId)
				.replyMarkup(new ForceReply())
				;
		bot.execute(request);
	}

}
