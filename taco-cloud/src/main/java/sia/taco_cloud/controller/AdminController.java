package sia.taco_cloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.taco_cloud.services.OrderAdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final OrderAdminService orderAdminService;

    public AdminController(OrderAdminService orderAdminService) {
        this.orderAdminService = orderAdminService;
    }

    @PostMapping("/deleteOrders")
    public String deleteOrders() {
        orderAdminService.deleteAllOrders();
        return "redirect:/admin";
    }
}
