package ro.vladfernoaga.telegram_chatbot_starter.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.google.maps.GeoApiContext;
import com.pengrad.telegrambot.TelegramBot;

import ro.vladfernoaga.telegram_chatbot_starter.handler.SimpleUpdateHandler;

@Configuration
@ComponentScan({"ro.vladfernoaga.telegram_chatbot_starter"})
public class BeanConfig {
	
	/** The Constant LOGGER. */
	public static final Logger LOGGER = LogManager.getLogger();

	private static final String API_TOKEN = "502357720:AAEFcorQSqI4WXBAMDAARMEUwfiXPb3sVGQ";
	private static final String GEOAPI_TOKEN = "AIzaSyD2vVD-ZgpKbAvjtk158jIu72UwYm44fp0";

	@Autowired
	private SimpleUpdateHandler updateHandler;
	
	
	@Bean
	public CommandLineRunner runTelegramBoot() {
		return (args) -> {
			TelegramBot bot = getTelegramBot();
			bot.setUpdatesListener(updateHandler);
		};
	}

	@Bean
	public TelegramBot getTelegramBot() {
		TelegramBot bot = new TelegramBot(API_TOKEN);
		return  bot;
	}
	@Bean
	public GeoApiContext getGeoContext() {
		final GeoApiContext context = new GeoApiContext.Builder().apiKey(GEOAPI_TOKEN).build();
		return context;
	}
}
