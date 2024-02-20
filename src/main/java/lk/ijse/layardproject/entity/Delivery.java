package lk.ijse.layardproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {
    private String deliveryId;
    private String distance;
    private double price;
    private String orderId;
}