package lk.ijse.layardproject.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IngredientTM {
    private String ingredientId;
    private String description;
    private double price;
    private String weight;
}
