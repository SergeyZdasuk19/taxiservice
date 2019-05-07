package taxiservice.repository;

import org.springframework.data.repository.CrudRepository;
import taxiservice.entity.Person;

public interface PersonRepo extends CrudRepository<Person, Long> {
}
