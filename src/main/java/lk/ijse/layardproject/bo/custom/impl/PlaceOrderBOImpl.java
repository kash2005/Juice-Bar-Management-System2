package lk.ijse.layardproject.bo.custom.impl;

import lk.ijse.layardproject.bo.custom.PlaceOrderBO;
import lk.ijse.layardproject.dao.DAOFactory;
import lk.ijse.layardproject.dao.custom.PlaceOrderDAO;
import lk.ijse.layardproject.dto.CartDTO;
import lk.ijse.layardproject.dto.DeliveryDTO;
import lk.ijse.layardproject.dto.OrderDTO;
import lk.ijse.layardproject.dto.OrderDetailsDTO;
import lk.ijse.layardproject.entity.Delivery;
import lk.ijse.layardproject.entity.Order;

import java.sql.SQLException;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    PlaceOrderDAO placeOrderDAO = (PlaceOrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PLACEORDER);

    @Override
    public boolean savePlaceOrder(OrderDTO orderDTO, List<CartDTO> cartDTOList, List<OrderDetailsDTO> orderDetailsDTOList) throws SQLException {
        Order order = new Order(orderDTO.getOrderId(),orderDTO.getDate(),orderDTO.getCustomerId());


        return placeOrderDAO.savePlaceOrder(order,cartDTOList,orderDetailsDTOList);
    }

    @Override
    public boolean savePlaceOrderWithDelivery(OrderDTO orderDTO, List<CartDTO> cartDTOList, List<OrderDetailsDTO> orderDetailsDTOList, DeliveryDTO deliveryDTO) throws SQLException {
        Order order = new Order(orderDTO.getOrderId(),orderDTO.getDate(),orderDTO.getCustomerId());
        Delivery delivery = new Delivery(deliveryDTO.getDeliveryId(), deliveryDTO.getDistance(),deliveryDTO.getPrice(),
                deliveryDTO.getOrderId());
        return placeOrderDAO.savePlaceOrderWithDelivery(order,cartDTOList,orderDetailsDTOList,delivery);
    }
}
