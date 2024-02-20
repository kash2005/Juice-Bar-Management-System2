package lk.ijse.layardproject.bo.custom;

import lk.ijse.layardproject.bo.SuperBO;
import lk.ijse.layardproject.dto.CartDTO;
import lk.ijse.layardproject.dto.OrderDTO;
import lk.ijse.layardproject.dto.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.List;

public interface PlaceOrderBO extends SuperBO {
    boolean savePlaceOrder(OrderDTO orderDTO, List<CartDTO> cartDTOList, List<OrderDetailsDTO> orderDetailsDTOList) throws SQLException;
}
