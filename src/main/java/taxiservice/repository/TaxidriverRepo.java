package taxiservice.repository;

import org.springframework.data.repository.CrudRepository;
import taxiservice.entity.Taxidriver;

public interface TaxidriverRepo extends CrudRepository<Taxidriver, Long> {
}
