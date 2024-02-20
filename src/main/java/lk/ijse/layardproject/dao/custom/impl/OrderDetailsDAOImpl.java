package lk.ijse.layardproject.dao.custom.impl;

import lk.ijse.layardproject.dao.SQLUtil;
import lk.ijse.layardproject.dao.SuperDAO;
import lk.ijse.layardproject.dao.custom.OrderDetailsDAO;
import lk.ijse.layardproject.dto.CartDTO;
import lk.ijse.layardproject.entity.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public boolean saveOrderDetails(String orderId, List<CartDTO> cartDTOList) throws SQLException {
        for (CartDTO cartDTO : cartDTOList){
            if(!saveOrderDetails(orderId,cartDTO)){
                return false;
            }
        }
        return true;
    }
    public static boolean saveOrderDetails(String orderId,CartDTO cartDTO) throws SQLException {
        String sql = "insert into orderDetails(orderId,itemId,getQty,amount) values(?,?,?,?);";
        return SQLUtil.execute(sql,orderId,cartDTO.getItemId(),cartDTO.getQty(),(cartDTO.getPrice()* cartDTO.getQty()));
    }

    @Override
    public boolean save(OrderDetails entity) throws SQLException {
        return false;
    }

    @Override
    public OrderDetails search(String searchIdText) throws SQLException {
        return null;
    }

    @Override
    public boolean update(OrderDetails entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public String generateId() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<OrderDetails> getAll() throws SQLException {
        String sql = "select * from orderDetails;";
        ArrayList<OrderDetails> orderDetailsArrayList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()){
            OrderDetails orderDetails = new OrderDetails(
                    resultSet.getString("orderId"),
                    resultSet.getString("itemId"),
                    resultSet.getInt("getQty"),
                    resultSet.getDouble("amount")
            );
            orderDetailsArrayList.add(orderDetails);
        }
        return orderDetailsArrayList;
    }
}
