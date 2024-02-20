package lk.ijse.layardproject.dao.custom;

import lk.ijse.layardproject.dao.CrudDAO;
import lk.ijse.layardproject.dto.OrderDTO;
import lk.ijse.layardproject.entity.Order;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order> {
    Integer[] lineChart() throws SQLException;
}
