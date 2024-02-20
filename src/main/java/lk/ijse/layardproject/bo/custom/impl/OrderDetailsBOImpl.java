package lk.ijse.layardproject.bo.custom.impl;

import lk.ijse.layardproject.bo.SuperBO;
import lk.ijse.layardproject.bo.custom.OrderDetailsBO;
import lk.ijse.layardproject.dao.DAOFactory;
import lk.ijse.layardproject.dao.custom.OrderDetailsDAO;
import lk.ijse.layardproject.dto.ItemDTO;
import lk.ijse.layardproject.dto.OrderDetailsDTO;
import lk.ijse.layardproject.entity.Item;
import lk.ijse.layardproject.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsBOImpl implements OrderDetailsBO {

    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);


    @Override
    public ArrayList<OrderDetailsDTO> getAll() throws SQLException {
        ArrayList<OrderDetailsDTO> orderDetailsDTOS = new ArrayList<>();
        ArrayList<OrderDetails> orderDetailsArrayList = orderDetailsDAO.getAll();
        for (OrderDetails orderDetails : orderDetailsArrayList){
            orderDetailsDTOS.add(new OrderDetailsDTO(orderDetails.getOrderId(),orderDetails.getItemId(),
                    orderDetails.getGetQty(),orderDetails.getAmount()));
        }
        return orderDetailsDTOS;
    }
}
