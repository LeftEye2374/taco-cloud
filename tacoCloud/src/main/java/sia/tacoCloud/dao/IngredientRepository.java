package sia.tacoCloud.dao;

import org.springframework.data.repository.CrudRepository;
import sia.tacoCloud.data.ingredient.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
