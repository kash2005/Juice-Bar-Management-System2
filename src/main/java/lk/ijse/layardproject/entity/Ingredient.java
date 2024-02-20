package lk.ijse.layardproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ingredient {
    private String ingredientId;
    private String description;
    private double price;
    private String weight;
}
