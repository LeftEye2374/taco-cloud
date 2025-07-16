package sia.taco_cloud.repository;

import org.springframework.data.repository.CrudRepository;
import sia.taco_cloud.model.ingredient.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient,String> {

}
