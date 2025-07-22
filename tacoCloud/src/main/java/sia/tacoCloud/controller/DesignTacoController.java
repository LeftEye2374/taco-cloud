package sia.tacoCloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import sia.tacoCloud.data.Ingredient;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    @ModelAttribute
    public void addIngredientsToModel(Model model){
        List<Ingredient> ingredients = Arrays.asList(
          new Ingredient("FLTO","Flour Tortilla", Ingredient.Type.WRAP),
        );
    }
}
