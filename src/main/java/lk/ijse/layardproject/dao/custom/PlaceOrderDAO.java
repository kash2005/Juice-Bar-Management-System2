package lk.ijse.layardproject.dao.custom;

import lk.ijse.layardproject.dao.SuperDAO;
import lk.ijse.layardproject.dto.CartDTO;
import lk.ijse.layardproject.dto.OrderDTO;
import lk.ijse.layardproject.dto.OrderDetailsDTO;
import lk.ijse.layardproject.entity.Delivery;
import lk.ijse.layardproject.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface PlaceOrderDAO extends SuperDAO {
    boolean savePlaceOrder(Order order, List<CartDTO> cartDTOList, List<OrderDetailsDTO> orderDetailsDTOList) throws SQLException;

    boolean savePlaceOrderWithDelivery(Order order, List<CartDTO> cartDTOList, List<OrderDetailsDTO> orderDetailsDTOList, Delivery delivery) throws SQLException;
}
