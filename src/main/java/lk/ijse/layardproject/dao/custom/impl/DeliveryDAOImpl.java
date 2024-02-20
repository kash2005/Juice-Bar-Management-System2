package lk.ijse.layardproject.dao.custom.impl;

import lk.ijse.layardproject.dao.SQLUtil;
import lk.ijse.layardproject.dao.custom.DeliveryDAO;
import lk.ijse.layardproject.entity.Delivery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryDAOImpl implements DeliveryDAO {
    @Override
    public boolean save(Delivery delivery) throws SQLException {
        String sql = "insert into delivery(deliveryId,distance,price,orderId) values(?,?,?,?);";
        return SQLUtil.execute(sql,delivery.getDeliveryId(),delivery.getDistance(),delivery.getPrice(),delivery.getOrderId());
    }

    @Override
    public Delivery search(String searchIdText) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Delivery entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public String generateId() throws SQLException {
        String sql = "select max(deliveryId) as lastId from delivery;";
        ResultSet resultSet = SQLUtil.execute(sql);
        if(resultSet.next()){
            String lastId = resultSet.getString("lastId");
            if (lastId == null){
                return "D001";
            }else {
                int nextId = Integer.parseInt(lastId.substring(1)) + 1;
                return "D" + String.format("%03d",nextId);
            }
        }
        return null;
    }

    @Override
    public ArrayList<Delivery> getAll() throws SQLException {
        return null;
    }

    @Override
    public String getallDelivery(String orderId) throws SQLException {
        String sql = "select * from delivery where orderId = ?;";
        Delivery delivery = null;
        String status = "";
        ResultSet resultSet = SQLUtil.execute(sql,orderId);
        if (resultSet.next()){
            String deliveryId = resultSet.getString("deliveryId");
            String distance = resultSet.getString("distance");
            double price = resultSet.getDouble("price");
            String orderId1 = resultSet.getString("orderId");
            delivery = new Delivery(deliveryId,distance,price,orderId1);
            if (orderId.equals(orderId1)){
                return status;
            }
        }
        return null;
    }

}
