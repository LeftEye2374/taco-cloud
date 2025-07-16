package sia.taco_cloud.model.taco;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;
import sia.taco_cloud.model.ingredient.IngredientUDT;

import java.util.List;

@Data
@UserDefinedType("taco")
public class TacoUDT {
    private final String name;
    private final List<IngredientUDT> ingredients;
}
