package sia.tacoCloud.dao;

import org.springframework.data.repository.CrudRepository;
import sia.tacoCloud.data.taco.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
