package ro.vladfernoaga.telegram_chatbot_starter.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.vladfernoaga.telegram_chatbot_starter.model.CommonMessage;

@Repository
public interface CommonMessagesRepo extends JpaRepository<CommonMessage,String>{
	
	CommonMessage findByCondition(String condition);

}
