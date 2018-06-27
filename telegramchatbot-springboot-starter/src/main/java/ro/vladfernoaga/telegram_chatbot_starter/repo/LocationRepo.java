package ro.vladfernoaga.telegram_chatbot_starter.repo;



import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import ro.vladfernoaga.telegram_chatbot_starter.model.Location;



public interface LocationRepo extends JpaRepository<Location, Long> {

	Optional<Location> findById(Long name);

	Location findByName(String name);

}
