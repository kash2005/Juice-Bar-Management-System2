package lk.ijse.layardproject.dao.custom;

import lk.ijse.layardproject.dao.CrudDAO;
import lk.ijse.layardproject.entity.Delivery;

import java.sql.SQLException;

public interface DeliveryDAO extends CrudDAO<Delivery> {
    String getallDelivery(String orderId) throws SQLException;
}
