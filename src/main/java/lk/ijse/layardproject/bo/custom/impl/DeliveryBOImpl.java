package lk.ijse.layardproject.bo.custom.impl;

import lk.ijse.layardproject.bo.SuperBO;
import lk.ijse.layardproject.bo.custom.DeliveryBO;
import lk.ijse.layardproject.dao.DAOFactory;
import lk.ijse.layardproject.dao.custom.DeliveryDAO;

import java.sql.SQLException;

public class DeliveryBOImpl implements DeliveryBO {

    DeliveryDAO deliveryDAO = (DeliveryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DELIVERY);

    @Override
    public String generateDeliveryId() throws SQLException {
        return deliveryDAO.generateId();
    }
}
