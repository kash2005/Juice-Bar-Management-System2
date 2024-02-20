package lk.ijse.layardproject.bo.custom;

import lk.ijse.layardproject.bo.SuperBO;
import lk.ijse.layardproject.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    boolean deleteItem(String searchIdText) throws SQLException;

    boolean saveItem(ItemDTO itemDTO) throws SQLException;

    boolean updateItem(ItemDTO itemDTO) throws SQLException;

    ItemDTO searchItem(String searchIdText) throws SQLException;

    ArrayList<ItemDTO> getAll() throws SQLException;

    String generateItemId() throws SQLException;

    ArrayList<String> getItemId() throws SQLException;
}
