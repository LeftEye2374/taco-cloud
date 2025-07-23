package sia.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sia.tacoCloud.data.Ingredient;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
