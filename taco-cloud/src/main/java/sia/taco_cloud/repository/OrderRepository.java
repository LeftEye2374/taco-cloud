package sia.taco_cloud.repository;

import sia.taco_cloud.model.TacoOrder;

public interface OrderRepository {

    TacoOrder save(TacoOrder tacoOrder);
}
