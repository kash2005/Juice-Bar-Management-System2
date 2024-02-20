package lk.ijse.layardproject.dao.custom.impl;

import lk.ijse.layardproject.dao.DAOFactory;
import lk.ijse.layardproject.dao.SuperDAO;
import lk.ijse.layardproject.dao.custom.ItemDAO;
import lk.ijse.layardproject.dao.custom.OrderDAO;
import lk.ijse.layardproject.dao.custom.OrderDetailsDAO;
import lk.ijse.layardproject.dao.custom.PlaceOrderDAO;
import lk.ijse.layardproject.db.DbConnection;
import lk.ijse.layardproject.dto.CartDTO;
import lk.ijse.layardproject.dto.OrderDTO;
import lk.ijse.layardproject.dto.OrderDetailsDTO;
import lk.ijse.layardproject.entity.Order;
import lk.ijse.layardproject.model.ItemModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PlaceOrderDAOImpl implements PlaceOrderDAO {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);

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
}
