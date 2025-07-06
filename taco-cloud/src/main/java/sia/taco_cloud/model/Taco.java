package sia.taco_cloud.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class Taco {
    @NotBlank
    @Size(min=5, message = "Name must by at least 5 characters long")
    private String name;

    @NotBlank
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;
}
