package lk.ijse.layardproject.bo.custom;

import lk.ijse.layardproject.bo.SuperBO;
import lk.ijse.layardproject.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    boolean saveCustomer(CustomerDTO customerDTO) throws SQLException;

    CustomerDTO searchCustomer(String searchIdText) throws SQLException;

    boolean updateCustomer(CustomerDTO customerDTO) throws SQLException;

    boolean deleteCustomer(String id) throws SQLException;

    String generateCustomerId() throws SQLException;

    ArrayList<CustomerDTO> getAll() throws SQLException;
}
