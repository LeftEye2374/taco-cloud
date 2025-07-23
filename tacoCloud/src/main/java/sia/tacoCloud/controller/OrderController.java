package sia.tacoCloud.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import sia.dao.OrderRepository;
import sia.tacoCloud.data.TacoOrder;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(@ModelAttribute("tacoOrder") TacoOrder tacoOrder) {
        log.info("Current order: {}", tacoOrder);
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@ModelAttribute("tacoOrder") @Valid TacoOrder tacoOrder,
                               SessionStatus sessionStatus, Errors errors) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        orderRepository.save(tacoOrder);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}