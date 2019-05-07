package taxiservice.repository;

import org.springframework.data.repository.CrudRepository;
import taxiservice.entity.Order;

public interface OrderRepo extends CrudRepository<Order, Long>{
}
