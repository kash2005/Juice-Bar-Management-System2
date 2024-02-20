package lk.ijse.layardproject.dao.custom.impl;

import lk.ijse.layardproject.dao.SQLUtil;
import lk.ijse.layardproject.dao.SuperDAO;
import lk.ijse.layardproject.dao.custom.OrderDetailsDAO;
import lk.ijse.layardproject.dto.CartDTO;

import java.sql.SQLException;
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
}
