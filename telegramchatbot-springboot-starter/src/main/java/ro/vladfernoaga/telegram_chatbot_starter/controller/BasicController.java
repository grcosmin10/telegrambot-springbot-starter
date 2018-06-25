package ro.vladfernoaga.telegram_chatbot_starter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ro.vladfernoaga.telegram_chatbot_starter.dto.BasicInfoDto;
import ro.vladfernoaga.telegram_chatbot_starter.impl.LocationService;
import ro.vladfernoaga.telegram_chatbot_starter.service.BasicService;

@RestController
public class BasicController {

	private BasicService basicService;
	
	private LocationService locationService;

	@Autowired
	public BasicController(BasicService basicService) {
		this.basicService = basicService;
	}
	
	public BasicController(LocationService locationservice)
	{
		this.locationService = locationService;
	}

	@RequestMapping(value = "/basicinfo/{name}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<BasicInfoDto> getStudent(@PathVariable("name") String name) {
		return ResponseEntity.ok().body(basicService.addNewOrGetExisting(name));
	}
}
