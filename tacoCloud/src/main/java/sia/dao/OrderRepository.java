package sia.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sia.tacoCloud.data.TacoOrder;

@Repository
public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
