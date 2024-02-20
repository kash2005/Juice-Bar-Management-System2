package lk.ijse.layardproject.bo.custom;

import lk.ijse.layardproject.bo.SuperBO;
import lk.ijse.layardproject.dto.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBO extends SuperBO {
    boolean delete(String id) throws SQLException;

    boolean save(UserDTO userDTO) throws SQLException;

    boolean update(UserDTO userDTO) throws SQLException;

    UserDTO searchId(String id) throws SQLException;

    ArrayList<UserDTO> getAll() throws SQLException;
}
