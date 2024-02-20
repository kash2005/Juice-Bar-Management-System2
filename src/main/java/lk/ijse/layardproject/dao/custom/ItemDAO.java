package lk.ijse.layardproject.dao.custom;

import lk.ijse.layardproject.dao.CrudDAO;
import lk.ijse.layardproject.dto.CartDTO;
import lk.ijse.layardproject.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item> {
    ArrayList<String> getId() throws SQLException;

    boolean updateQty(List<CartDTO> cartDTOList) throws SQLException;
}
