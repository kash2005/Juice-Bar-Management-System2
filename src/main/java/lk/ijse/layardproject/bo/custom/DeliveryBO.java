package lk.ijse.layardproject.bo.custom;

import lk.ijse.layardproject.bo.SuperBO;

import java.sql.SQLException;

public interface DeliveryBO extends SuperBO {
    String generateDeliveryId() throws SQLException;
}
