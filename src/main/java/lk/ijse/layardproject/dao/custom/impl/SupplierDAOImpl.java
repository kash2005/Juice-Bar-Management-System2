package lk.ijse.layardproject.dao.custom.impl;

import lk.ijse.layardproject.dao.SQLUtil;
import lk.ijse.layardproject.dao.custom.SupplierDAO;
import lk.ijse.layardproject.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public boolean save(Supplier supplier) throws SQLException {
        String sql = "insert into supplier(supplierId,name,contact,company) values(?,?,?,?)";
        return SQLUtil.execute(sql,supplier.getSupplierId(),supplier.getName(),supplier.getContact(),supplier.getCompany());
    }

    @Override
    public Supplier search(String searchIdText) throws SQLException {
        String sql = "select * from supplier where supplierId = ?;";
        ResultSet resultSet = SQLUtil.execute(sql,searchIdText);
        Supplier supplier = null;
        while (resultSet.next()){
            String supplierId = resultSet.getString("supplierId");
            String name = resultSet.getString("name");
            String contact = resultSet.getString("contact");
            String company = resultSet.getString("company");
            supplier = new Supplier(supplierId,name,contact,company);
        }
        return supplier;
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException {
        String sql = "update supplier set name = ?, contact = ?, company = ? where supplierId = ?;";
        return SQLUtil.execute(sql,supplier.getName(),supplier.getContact(),supplier.getCompany(),supplier.getSupplierId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "delete from supplier where supplierId = ?";
        return SQLUtil.execute(sql,id);
    }

    @Override
    public String generateId() throws SQLException {
        String sql = "select max(supplierId) as lastSupplierId from supplier;";
        ResultSet resultSet = SQLUtil.execute(sql);
        if (resultSet.next()){
            String lastSupplierId = resultSet.getString("lastSupplierId");
            if (lastSupplierId == null){
                return "S001";
            }else {
                int nextId = Integer.parseInt(lastSupplierId.substring(1)) + 1;
                return "S" + String.format("%03d",nextId);
            }
        }
        return null;
    }

    @Override
    public ArrayList<Supplier> getAll() throws SQLException {
        String sql = "select * from supplier";
        ResultSet resultSet = SQLUtil.execute(sql);
        ArrayList<Supplier> suppliers = new ArrayList<>();
        while (resultSet.next()){
            Supplier supplier = new Supplier(
                    resultSet.getString("supplierId"),
                    resultSet.getString("name"),
                    resultSet.getString("contact"),
                    resultSet.getString("company")
            );
            suppliers.add(supplier);
        }
        return suppliers;
    }
}
