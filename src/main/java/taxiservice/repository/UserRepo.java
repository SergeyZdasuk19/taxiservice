package taxiservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import taxiservice.entity.User;

import javax.transaction.Transactional;

public interface UserRepo extends JpaRepository<User, Long> {


    User findByUsername(String username);


}