package lk.ijse.layardproject.dao.custom;

import lk.ijse.layardproject.dao.CrudDAO;
import lk.ijse.layardproject.dao.SuperDAO;
import lk.ijse.layardproject.dto.CartDTO;
import lk.ijse.layardproject.entity.OrderDetails;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsDAO extends CrudDAO<OrderDetails> {
    boolean saveOrderDetails(String orderId, List<CartDTO> cartDTOList) throws SQLException;
}
