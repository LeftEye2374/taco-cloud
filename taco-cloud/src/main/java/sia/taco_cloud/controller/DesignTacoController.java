package sia.taco_cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import sia.taco_cloud.model.Ingredient;
import sia.taco_cloud.model.Type;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    List<Ingredient> ingredients = Arrays.asList(
            new Ingredient("FLTO","Flour Tortilla", Type.WRAP),
            new Ingredient("COTO","Corn Tortilla", Type.WRAP),
            new Ingredient("GRBF","Ground Beef", Type.PROTEIN),
            new Ingredient("CARN","Carnitas", Type.PROTEIN),
            new Ingredient("TMTO","Diced Tomatoes", Type.VEGGIES),
            new Ingredient("LETC","Lettuce", Type.VEGGIES),
            new Ingredient("CHED","Cheddar", Type.CHEESE),
            new Ingredient("JACK","Monterrey Jck", Type.CHEESE),
            new Ingredient("SRCR","Sour Cream", Type.SAUCE)
            );

    Type[] types = Type.values();
}
