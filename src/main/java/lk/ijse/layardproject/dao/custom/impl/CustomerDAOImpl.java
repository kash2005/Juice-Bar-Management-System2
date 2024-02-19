package lk.ijse.layardproject.dao.custom.impl;

import lk.ijse.layardproject.dao.SQLUtil;
import lk.ijse.layardproject.dao.custom.CustomerDAO;
import lk.ijse.layardproject.entity.Customer;

import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean save(Customer customer) throws SQLException {
        String sql = "insert into customer(customerId,name,address,email,contact) values (?,?,?,?,?)";
        return SQLUtil.execute(sql,customer.getCustomerId(),customer.getName(),customer.getAddress(),
                customer.getEmail(),customer.getContact());
    }
}
