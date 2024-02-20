package lk.ijse.layardproject.bo.custom;

import lk.ijse.layardproject.bo.SuperBO;
import lk.ijse.layardproject.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    boolean delete(String id) throws SQLException;

    boolean save(EmployeeDTO employeeDTO) throws SQLException;

    boolean update(EmployeeDTO employeeDTO) throws SQLException;

    EmployeeDTO searchEmployeeId(String id) throws SQLException;

    String generateId() throws SQLException;

    ArrayList<EmployeeDTO> getAll() throws SQLException;

    ArrayList<String> getEmployeeId() throws SQLException;

    ArrayList<String> getCmbEmployeeId() throws SQLException;
}