package sia.tacoCloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import sia.tacoCloud.data.taco.TacoOrder;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    @GetMapping("/current")
    public String orderForm(@ModelAttribute("tacoOrder") TacoOrder tacoOrder) {
        log.info("Current order: {}", tacoOrder); 
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@ModelAttribute("tacoOrder") TacoOrder tacoOrder,
                               SessionStatus sessionStatus) {
        log.info("Order submitted: {}", tacoOrder);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}