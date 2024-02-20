package lk.ijse.layardproject.bo.custom;

import lk.ijse.layardproject.bo.SuperBO;
import lk.ijse.layardproject.dao.SuperDAO;
import lk.ijse.layardproject.dto.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsBO extends SuperBO {

    ArrayList<OrderDetailsDTO> getAll() throws SQLException;
}
