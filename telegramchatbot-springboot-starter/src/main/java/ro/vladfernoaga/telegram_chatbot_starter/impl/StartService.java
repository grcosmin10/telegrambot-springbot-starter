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
public class StartService {
	@Autowired
	CommonMessagesRepo commonMessagesRepo;
	
	@Autowired
	MenuUtils menuUtils;
	
	public void execute(TelegramBot bot,Message message)
	{
		Integer chatId = message.from().id();
		String messageText = message.text();
		Integer messageId =message.messageId();
		
		
		CommonMessage commonMessage = commonMessagesRepo.findByCondition("start");
		String mesaj = commonMessage.getMessage();
		
		SendMessage request = new SendMessage(chatId, String.format(mesaj))
				.parseMode(ParseMode.HTML)
				.disableNotification(false)
				.replyToMessageId(messageId)
				.replyMarkup(menuUtils.showMainMenu())
				;
		bot.execute(request);
	}

}
