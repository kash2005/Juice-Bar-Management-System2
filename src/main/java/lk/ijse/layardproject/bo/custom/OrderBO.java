package lk.ijse.layardproject.bo.custom;

import lk.ijse.layardproject.bo.SuperBO;
import lk.ijse.layardproject.dto.OrderDTO;

import java.sql.SQLException;

public interface OrderBO extends SuperBO {
    String generateOrderId() throws SQLException;

    Integer[] lineChart() throws SQLException;

    OrderDTO searchOrderId(String orderId) throws SQLException;
}
