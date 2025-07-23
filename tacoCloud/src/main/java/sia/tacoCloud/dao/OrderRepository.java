package sia.tacoCloud.dao;

import org.springframework.data.repository.CrudRepository;
import sia.tacoCloud.data.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
