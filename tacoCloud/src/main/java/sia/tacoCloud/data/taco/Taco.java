package sia.tacoCloud.data.taco;

import lombok.Data;
import sia.tacoCloud.data.ingredient.Ingredient;

import java.util.List;

@Data
public class Taco {

    private String name;
    private List<Ingredient> ingredients;
}
