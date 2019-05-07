package taxiservice.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import taxiservice.entity.Car;

import javax.transaction.Transactional;

public interface CarRepo extends CrudRepository<Car, Long> {

    Car findById(long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Car car set car.mark =:mark, car.model =:model, car.seatsnumber =:seatsnumber, car.govnumber =:govnumber  where car.id =:carId")
    void updateAll(@Param("carId") Long carId, @Param("mark") String mark, @Param("model") String model, @Param("seatsnumber") Integer searsnumber, @Param("govnumber") String govnumber);
}
