package lk.ijse.layardproject.bo.custom.impl;

import lk.ijse.layardproject.bo.SuperBO;
import lk.ijse.layardproject.bo.custom.DeliveryBO;
import lk.ijse.layardproject.dao.DAOFactory;
import lk.ijse.layardproject.dao.custom.DeliveryDAO;
import lk.ijse.layardproject.entity.Delivery;

import java.sql.SQLException;

public class DeliveryBOImpl implements DeliveryBO {

    DeliveryDAO deliveryDAO = (DeliveryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DELIVERY);

    @Override
    public String generateDeliveryId() throws SQLException {
        return deliveryDAO.generateId();
    }

    @Override
    public String getAllDelivery(String orderId) throws SQLException {
        return deliveryDAO.getallDelivery(orderId);
    }
}
