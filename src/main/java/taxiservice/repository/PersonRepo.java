package taxiservice.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import taxiservice.entity.Person;

import javax.transaction.Transactional;

public interface PersonRepo extends CrudRepository<Person, Long> {

    Person findById(long id);

    Person findByPhoneNumber(String phoneNumber);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Person person set person.name =:name, person.surname =:surname, person.phoneNumber =:phoneNumber where person.id =:personId")
    void updateAll(
            @Param("personId") Long personId,
            @Param("name") String name,
            @Param("surname") String surname,
            @Param("phoneNumber") String phoneNumber);
}
