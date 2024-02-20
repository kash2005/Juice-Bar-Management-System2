package lk.ijse.layardproject.dao.custom.impl;

import lk.ijse.layardproject.dao.SQLUtil;
import lk.ijse.layardproject.dao.SuperDAO;
import lk.ijse.layardproject.dao.custom.OrderDAO;
import lk.ijse.layardproject.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean save(Order order) throws SQLException {
        String sql = "insert into orders(orderId,date,customerId) values (?,?,?);";
        return SQLUtil.execute(sql, order.getOrderId(), order.getDate(), order.getCustomerId());
    }

    @Override
    public String generateId() throws SQLException {
        String sql = "select max(orderId) as lastOrder from orders";
        ResultSet resultSet = SQLUtil.execute(sql);
        if (resultSet.next()){
            String lastId = resultSet.getString("lastOrder");
            if (lastId == null){
                return "OR001";
            }else {
                int nextId = Integer.parseInt(lastId.substring(2))+1;
                return "OR"+String.format("%03d",nextId);
            }
        }
        return null;
    }

    @Override
    public Order search(String searchIdText) throws SQLException {
        String sql = "select * from orders where orderId = ?;";
        ResultSet resultSet = SQLUtil.execute(sql, searchIdText);
        Order order = null;
        while (resultSet.next()){
            String orderId1 = resultSet.getString("orderId");
            LocalDate date = LocalDate.parse(resultSet.getString("date"));
            String customerId = resultSet.getString("customerId");
            order = new Order(orderId1,date,customerId);
        }
        return order;
    }

    @Override
    public boolean update(Order entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException {
        return null;
    }
}
