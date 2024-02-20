package lk.ijse.layardproject.bo.custom.impl;

import lk.ijse.layardproject.bo.SuperBO;
import lk.ijse.layardproject.bo.custom.OrderBO;
import lk.ijse.layardproject.dao.DAOFactory;
import lk.ijse.layardproject.dao.custom.OrderDAO;
import lk.ijse.layardproject.dto.OrderDTO;
import lk.ijse.layardproject.entity.Order;

import java.sql.SQLException;

public class OrderBOImpl implements OrderBO {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public String generateOrderId() throws SQLException {
        return orderDAO.generateId();
    }

    @Override
    public Integer[] lineChart() throws SQLException {
        return orderDAO.lineChart();
    }

    @Override
    public OrderDTO searchOrderId(String orderId) throws SQLException {
        Order order = orderDAO.search(orderId);
        return new OrderDTO(order.getOrderId(),order.getDate(),order.getCustomerId());
    }
}
