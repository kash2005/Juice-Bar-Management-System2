package lk.ijse.layardproject.bo.custom.impl;

import lk.ijse.layardproject.bo.custom.SupplierBO;
import lk.ijse.layardproject.dao.DAOFactory;
import lk.ijse.layardproject.dao.custom.SupplierDAO;
import lk.ijse.layardproject.dto.SupplierDTO;
import lk.ijse.layardproject.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException {
        return supplierDAO.save(new Supplier(supplierDTO.getSupplierId(),supplierDTO.getName(),supplierDTO.getContact(),
                supplierDTO.getCompany()));
    }

    @Override
    public SupplierDTO searchSupplier(String id) throws SQLException {
        Supplier supplier = supplierDAO.search(id);
        return new SupplierDTO(supplier.getSupplierId(),supplier.getName(),supplier.getContact(),supplier.getCompany());
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException {
        return supplierDAO.update(new Supplier(supplierDTO.getSupplierId(),supplierDTO.getName(),supplierDTO.getContact(),
                supplierDTO.getCompany()));
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException {
        return supplierDAO.delete(id);
    }

    @Override
    public String generateId() throws SQLException {
        return supplierDAO.generateId();
    }

    @Override
    public ArrayList<SupplierDTO> getAll() throws SQLException {
        ArrayList<SupplierDTO> supplierDTOS = new ArrayList<>();
        ArrayList<Supplier> suppliers = supplierDAO.getAll();
        for (Supplier supplier : suppliers){
            supplierDTOS.add(new SupplierDTO(supplier.getSupplierId(),supplier.getName(),supplier.getContact(),supplier.getCompany()));
        }
        return supplierDTOS;
    }
}
