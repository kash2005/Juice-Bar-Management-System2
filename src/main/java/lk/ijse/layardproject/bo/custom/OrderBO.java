package lk.ijse.layardproject.bo.custom;

import lk.ijse.layardproject.bo.SuperBO;

import java.sql.SQLException;

public interface OrderBO extends SuperBO {
    String generateOrderId() throws SQLException;

    Integer[] lineChart() throws SQLException;
}
