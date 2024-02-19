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

    @Override
    public boolean update(Customer customer) throws SQLException {
        String sql = "update customer set name = ?,address = ?,email = ?,contact = ? where customerId = ?;";
        return SQLUtil.execute(sql,customer.getName(),customer.getAddress(),customer.getEmail(),customer.getContact(),
                customer.getCustomerId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "delete from customer where customerId = ?;";
        return SQLUtil.execute(sql,id);
    }

    @Override
    public String generateId() throws SQLException {
        String sql = "select max(customerId) as lastCustomerId from customer";
            ResultSet resultSet = SQLUtil.execute(sql);
            if (resultSet.next()){
                String lastCustomerId = resultSet.getString("lastCustomerId");
                if (lastCustomerId == null){
                    return "C001";
                }else {
                    int nextId = Integer.parseInt(lastCustomerId.substring(1))+1;
                    return "C" + String.format("%03d",nextId);
                }
            }
        return null;
    }
}
