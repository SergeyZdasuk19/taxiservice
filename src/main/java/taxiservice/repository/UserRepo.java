package taxiservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import taxiservice.entity.Car;
import taxiservice.entity.User;

import javax.transaction.Transactional;

public interface UserRepo extends JpaRepository<User, Long> {


    User findByUsername(String username);

    User findById(long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update User user set user.username =:username, user.password =:password, user.active =:active where user.id =:userId")
    void updateAll(
                    @Param("userId") Long userId,
                    @Param("username") String username,
                    @Param("password") String password,
                    @Param("active") Boolean active);



}


