package lk.ijse.layardproject.dao.custom;

import lk.ijse.layardproject.dao.SuperDAO;
import lk.ijse.layardproject.dto.CartDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsDAO extends SuperDAO {
    boolean saveOrderDetails(String orderId, List<CartDTO> cartDTOList) throws SQLException;
}
