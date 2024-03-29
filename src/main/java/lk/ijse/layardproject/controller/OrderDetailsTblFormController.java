package lk.ijse.layardproject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.layardproject.bo.BOFactory;
import lk.ijse.layardproject.bo.custom.DeliveryBO;
import lk.ijse.layardproject.bo.custom.ItemBO;
import lk.ijse.layardproject.bo.custom.OrderBO;
import lk.ijse.layardproject.bo.custom.OrderDetailsBO;
import lk.ijse.layardproject.dto.ItemDTO;
import lk.ijse.layardproject.dto.OrderDTO;
import lk.ijse.layardproject.dto.OrderDetailsDTO;
import lk.ijse.layardproject.dto.tm.OrderDetailsTM;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderDetailsTblFormController implements Initializable {
    @FXML
    private TableView<OrderDetailsTM> orderDetailsTbl;

    @FXML
    private TableColumn<String, OrderDetailsTM> colOrderId;

    @FXML
    private TableColumn<String, OrderDetailsTM> colOrderDate;

    @FXML
    private TableColumn<String, OrderDetailsTM> colCustomerId;

    @FXML
    private TableColumn<String, OrderDetailsTM> colItemId;

    @FXML
    private TableColumn<String, OrderDetailsTM> colDescription;

    @FXML
    private TableColumn<String, OrderDetailsTM> colGetQty;

    @FXML
    private TableColumn<String, OrderDetailsTM> colPrice;

    @FXML
    private TableColumn<String, OrderDetailsTM> colDeliveryStatus;

    ObservableList<OrderDetailsTM> observableList = FXCollections.observableArrayList();

    OrderDetailsBO orderDetailsBO = (OrderDetailsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDERDETAILS);
    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER);
    DeliveryBO deliveryBO = (DeliveryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DELIVERY);
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValueFactory();
        getAll();
    }

    private void setValueFactory() {
        colOrderId.setCellValueFactory(new PropertyValueFactory<String, OrderDetailsTM>("orderId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<String, OrderDetailsTM>("date"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<String, OrderDetailsTM>("customerId"));
        colItemId.setCellValueFactory(new PropertyValueFactory<String, OrderDetailsTM>("itemId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<String, OrderDetailsTM>("description"));
        colGetQty.setCellValueFactory(new PropertyValueFactory<String, OrderDetailsTM>("getQty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<String, OrderDetailsTM>("amount"));
        colDeliveryStatus.setCellValueFactory(new PropertyValueFactory<String, OrderDetailsTM>("deliveryStatus"));
    }
    private void getAll() {

        String orderId = null;
        String itemId = null;
        int getQty = 0;
        double amount = 0.0;
        LocalDate date = null;
        String customerId= null;
        String description = null;
        String deliveryStatus = null;
        OrderDetailsTM orderDetailsTM = null;
        try {
            ArrayList<OrderDetailsDTO> orderDetails = orderDetailsBO.getAll();
            for (OrderDetailsDTO orderDetailsDTO : orderDetails) {
                orderId = orderDetailsDTO.getOrderId();
                itemId = orderDetailsDTO.getItemId();
                getQty = orderDetailsDTO.getGetQty();
                amount = orderDetailsDTO.getAmount();

                OrderDTO orderDTO = orderBO.searchOrderId(orderId);
                date = orderDTO.getDate();
                customerId = orderDTO.getCustomerId();
                ItemDTO itemDTO = itemBO.searchItem(itemId);
                description = itemDTO.getDescription();
                deliveryStatus = deliveryBO.getAllDelivery(orderId);
                if (deliveryStatus == null) {
                    deliveryStatus = "No";
                } else {
                    deliveryStatus = "Yes";
                }
                orderDetailsTM = new OrderDetailsTM(orderId,date,customerId,itemId,description,getQty,amount,deliveryStatus);
                observableList.add(orderDetailsTM);
                orderDetailsTbl.setItems(observableList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
