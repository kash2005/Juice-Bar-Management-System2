package lk.ijse.layardproject.dao.custom.impl;

import lk.ijse.layardproject.dao.SQLUtil;
import lk.ijse.layardproject.dao.custom.CustomerDAO;
import lk.ijse.layardproject.dto.CustomerDTO;
import lk.ijse.layardproject.entity.Customer;
import lk.ijse.layardproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean save(Customer customer) throws SQLException {
        String sql = "insert into customer(customerId,name,address,email,contact) values (?,?,?,?,?)";
        return SQLUtil.execute(sql,customer.getCustomerId(),customer.getName(),customer.getAddress(),
                customer.getEmail(),customer.getContact());
    }

    @Override
    public Customer search(String searchIdText) throws SQLException {
        String sql = "select * from customer where customerId = ?";
        ResultSet resultSet = SQLUtil.execute(sql,searchIdText);
        Customer customer = null;
        if (resultSet.next()){
            String customerId = resultSet.getString("customerId");
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            String email = resultSet.getString("email");
            String contact = resultSet.getString("contact");
            customer = new Customer(customerId,name,address,email,contact);
        }
        return customer;
    }
}
