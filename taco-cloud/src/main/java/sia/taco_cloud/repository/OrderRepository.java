package sia.taco_cloud.repository;


import org.springframework.data.repository.CrudRepository;
import sia.taco_cloud.model.taco.TacoOrder;
import java.util.UUID;

public interface OrderRepository extends CrudRepository<TacoOrder, UUID> {
}
