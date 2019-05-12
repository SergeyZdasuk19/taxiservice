package taxiservice.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import taxiservice.entity.Order;
import taxiservice.entity.Taxidriver;
import taxiservice.entity.User;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderRepo extends CrudRepository<Order, Long>{

    @Transactional
    @Query(value = "SELECT orders FROM Order orders where orders.taxidriver=:taxidriver")
    List<Order> findTaxistOrders(@Param("taxidriver") Taxidriver taxidriver);

}
