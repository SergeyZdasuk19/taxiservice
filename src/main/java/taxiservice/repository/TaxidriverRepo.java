package taxiservice.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import taxiservice.entity.Taxidriver;
import taxiservice.entity.User;

import javax.transaction.Transactional;

public interface TaxidriverRepo extends CrudRepository<Taxidriver, Long> {

    @Transactional
    @Query("select taxidriver from Taxidriver taxidriver where taxidriver.user =:user")
    Taxidriver findByUser(@Param("user") User user);

    @Transactional
    @Query("select taxidriver from Taxidriver taxidriver where taxidriver.user.id =:userId")
    Taxidriver findByUserId(@Param("userId") Long userId);
}
