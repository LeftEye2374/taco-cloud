package sia.taco_cloud.model.taco;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import sia.taco_cloud.model.ingredient.Ingredient;

import java.util.Date;
import java.util.List;

@Data
public class Taco {

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    private Date createdAt = new Date();

    @Size(min = 1, message = "Your must choose at least 1 ingredient")
    @Column("ingredients")
    private List<Ingredient> ingredients;

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }
}
