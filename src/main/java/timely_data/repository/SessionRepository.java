package timely_data.repository;

import timely_data.data_model.Session;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Kateryna Sergieieva on 06.07.2017.
 */
public interface SessionRepository  extends CrudRepository<Session, Long> {
}
