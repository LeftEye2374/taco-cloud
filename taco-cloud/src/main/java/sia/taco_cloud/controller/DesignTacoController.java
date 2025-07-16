package sia.taco_cloud.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import sia.taco_cloud.model.ingredient.Ingredient;
import sia.taco_cloud.model.ingredient.IngredientType;
import sia.taco_cloud.model.taco.Taco;
import sia.taco_cloud.model.taco.TacoOrder;
import sia.taco_cloud.repository.IngredientRepository;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
        IngredientType[] types = IngredientType.values();
        for (IngredientType type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients,type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order(){
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(){
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder order){
        if (errors.hasErrors()){
            return "design";
        }
        order.addTaco(taco);
        log.info("Processing taco {}", taco);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(
            Iterable<Ingredient> ingredients, IngredientType type
    ){
        return StreamSupport.stream(ingredients.spliterator(), false)
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());    }
}
