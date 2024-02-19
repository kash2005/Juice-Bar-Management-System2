package lk.ijse.layardproject.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemTM {
    private String itemId;
    private String description;
    private int qty;
    private double price;
}
