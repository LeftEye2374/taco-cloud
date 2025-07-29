package sia.tacoCloud.data.ingredient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {



    @Id
    private String id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type{
        WRAP,PROTEIN,VEGGIES,CHEESE,SAUCE
    }
}
