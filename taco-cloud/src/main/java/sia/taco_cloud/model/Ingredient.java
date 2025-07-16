package sia.taco_cloud.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE , force = true)
@Entity
public class Ingredient{

    @Id
    private final String id;
    private final String name;
    private final IngredientType type;
}
