package sia.taco_cloud.model.ingredient;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE , force = true)
@Document(collection = "ingredients")
public class Ingredient{

    @Id
    private final String id;
    private final String name;
    private final IngredientType type;
}
