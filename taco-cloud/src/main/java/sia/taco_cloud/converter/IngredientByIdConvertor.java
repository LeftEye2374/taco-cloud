package sia.taco_cloud.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import sia.taco_cloud.model.ingredient.Ingredient;
import sia.taco_cloud.repository.IngredientRepository;


@Component
public class IngredientByIdConvertor implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientByIdConvertor(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }


    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findById(id).orElse(null);
    }
}
