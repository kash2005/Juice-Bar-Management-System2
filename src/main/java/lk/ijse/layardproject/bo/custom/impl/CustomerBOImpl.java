package lk.ijse.layardproject.bo.custom.impl;

import lk.ijse.layardproject.bo.custom.CustomerBO;
import lk.ijse.layardproject.dao.DAOFactory;
import lk.ijse.layardproject.dao.custom.CustomerDAO;
import lk.ijse.layardproject.dto.CustomerDTO;
import lk.ijse.layardproject.entity.Customer;

import java.sql.SQLException;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException {
        return customerDAO.save(new Customer(customerDTO.getCustomerId(),customerDTO.getName(),customerDTO.getAddress(),
                customerDTO.getEmail(),customerDTO.getContact()));
    }

    @Override
    public CustomerDTO searchCustomer(String searchIdText) throws SQLException {
        Customer customer = customerDAO.search(searchIdText);
        return new CustomerDTO(customer.getCustomerId(),customer.getName(),customer.getAddress(),customer.getEmail()
                ,customer.getContact());
    }
}
