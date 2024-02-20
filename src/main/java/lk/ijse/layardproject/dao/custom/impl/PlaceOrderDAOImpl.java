package lk.ijse.layardproject.dao.custom.impl;

import lk.ijse.layardproject.dao.DAOFactory;
import lk.ijse.layardproject.dao.custom.*;
import lk.ijse.layardproject.db.DbConnection;
import lk.ijse.layardproject.dto.CartDTO;
import lk.ijse.layardproject.dto.OrderDetailsDTO;
import lk.ijse.layardproject.entity.Delivery;
import lk.ijse.layardproject.entity.Order;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PlaceOrderDAOImpl implements PlaceOrderDAO {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    DeliveryDAO deliveryDAO = (DeliveryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DELIVERY);

    @Override
    public boolean savePlaceOrder(Order order, List<CartDTO> cartDTOList, List<OrderDetailsDTO> orderDetailsDTOList) throws SQLException {
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean isOrderSave = orderDAO.save(order);
            if (isOrderSave){
                boolean isUpdatedItem = itemDAO.updateQty(cartDTOList);
                if (isUpdatedItem){
                    boolean isSaveOrderDetails = orderDetailsDAO.saveOrderDetails(order.getOrderId(),cartDTOList);
                    if (isSaveOrderDetails){
                        connection.commit();
                        return true;
                    }
                }
            }else {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connection.setAutoCommit(true);
        }
        return false;
    }

    @Override
    public boolean savePlaceOrderWithDelivery(Order order, List<CartDTO> cartDTOList, List<OrderDetailsDTO> orderDetailsDTOList, Delivery delivery) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            boolean isSaveOrder = orderDAO.save(order);
            if (isSaveOrder){
                boolean isUpdateQty = itemDAO.updateQty(cartDTOList);
                if (isUpdateQty){
                    boolean isSaveOrderDetails = orderDetailsDAO.saveOrderDetails(order.getOrderId(),cartDTOList);
                    if (isSaveOrderDetails){
                        boolean isSaveDelivery = deliveryDAO.save(delivery);
                        if (isSaveDelivery){
                            connection.commit();
                            return true;
                        }
                    }
                }
            }else {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connection.setAutoCommit(true);
        }
        return false;
    }
}
