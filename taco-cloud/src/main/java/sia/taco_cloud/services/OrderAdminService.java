package sia.taco_cloud.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import sia.taco_cloud.repository.OrderRepository;

@Service
public class OrderAdminService {

    private final OrderRepository orderRepository;

    public OrderAdminService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }
}
