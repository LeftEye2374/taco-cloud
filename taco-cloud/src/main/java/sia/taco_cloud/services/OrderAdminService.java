package sia.taco_cloud.services;

import org.springframework.stereotype.Service;
import sia.taco_cloud.repository.OrderRepository;

@Service
public class OrderAdminService {

    private final OrderRepository orderRepository;

    public OrderAdminService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }
}
