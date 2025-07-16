package sia.taco_cloud.repository;


import org.springframework.data.repository.CrudRepository;
import sia.taco_cloud.model.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder,String> {

}
