package sia.tacoCloud.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sia.tacoCloud.dao.OrderRepository;
import sia.tacoCloud.data.ingredient.Ingredient;
import sia.tacoCloud.data.taco.Taco;
import sia.tacoCloud.data.taco.TacoOrder;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/api/orders", produces = "application/json")
@CrossOrigin("*")
public class RestTacoOrderController {

    private OrderRepository orderRepo;

    public RestTacoOrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }


    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public TacoOrder postOrder(@RequestBody TacoOrder order) {
        return orderRepo.save(order);
    }

    @PutMapping(path = "/{orderId}", consumes = "application/json")
    public TacoOrder putOrder(
            @PathVariable Long orderId,
            @RequestBody TacoOrder order
    ){
        order.setId(orderId);
        return orderRepo.save(order);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public TacoOrder patchOrder(@PathVariable Long orderId,
                                @RequestBody TacoOrder patch) {
        TacoOrder order = orderRepo.findById(orderId).get();
        if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
        }
        if(patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if(patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }
        if(patch.getDeliveryState() != null) {
            order.setDeliveryState(patch.getDeliveryState());
        }
        if(patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryZip());
        }
        if(patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if(patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if(patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }
        return orderRepo.save(order);
    }

    @DeleteMapping("/api/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long orderId) {
        try {
            orderRepo.deleteById(orderId);
        }catch (EmptyResultDataAccessException e){}
    }
}
