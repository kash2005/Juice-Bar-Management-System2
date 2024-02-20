package lk.ijse.layardproject.bo.custom;

import lk.ijse.layardproject.bo.SuperBO;
import lk.ijse.layardproject.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException;

    SupplierDTO searchSupplier(String id) throws SQLException;

    boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException;

    boolean deleteSupplier(String id) throws SQLException;

    String generateId() throws SQLException;

    ArrayList<SupplierDTO> getAll() throws SQLException;
}
