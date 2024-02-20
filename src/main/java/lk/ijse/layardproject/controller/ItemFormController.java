package lk.ijse.layardproject.controller;


import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.layardproject.bo.BOFactory;
import lk.ijse.layardproject.bo.custom.ItemBO;
import lk.ijse.layardproject.dto.ItemDTO;
import lk.ijse.layardproject.dto.tm.ItemTM;
import lk.ijse.layardproject.model.ItemModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {
    @FXML
    private TableView<ItemTM> tblItem;

    @FXML
    private TableColumn<String, ItemTM> tblItemCode;

    @FXML
    private TableColumn<String, ItemTM> tblItemDescription;

    @FXML
    private TableColumn<String, ItemTM> tblItemQty;

    @FXML
    private TableColumn<String, ItemTM> tblItemPerPrice;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private TextField itemCode;

    @FXML
    private TextField itemDescription;

    @FXML
    private TextField itemQtyId;

    @FXML
    private TextField itemPerPriceId;

    @FXML
    private TextField searchId;

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        String searchIdText = itemCode.getText();
        try {
            boolean isDeleted = itemBO.deleteItem(searchIdText);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Item is deleted !").show();
                clearTextFields();
                generateItemId();
                getAll();
                saveBtn.setText("Save");
                saveBtn.setStyle("-fx-background-color: green; -fx-background-radius: 10");
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Item is deleted !").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void itemDescriptionOnAction(ActionEvent event) {
        itemQtyId.requestFocus();
    }

    @FXML
    void itemPerPriceOnAction(ActionEvent event) {
        saveBtn.fire();
    }

    @FXML
    void itemQtyOnAction(ActionEvent event) {
        itemPerPriceId.requestFocus();
    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {
        String code = itemCode.getText();
        String description = itemDescription.getText();
        int qty = Integer.parseInt(itemQtyId.getText());
        double price = Double.parseDouble(itemPerPriceId.getText());
        ItemDTO itemDTO = new ItemDTO(code, description, qty, price);
        if (saveBtn.getText().equals("Save")){
            try {
                boolean isSave = itemBO.saveItem(itemDTO);
                if (isSave){
                    getAll();
                    new Alert(Alert.AlertType.CONFIRMATION,"Item is saved !").show();
                    clearTextFields();
                    generateItemId();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Item is not saved !").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else if (saveBtn.getText().equals("Update")){
            saveBtn.setText("Update");
            saveBtn.setStyle("-fx-background-color: blue; -fx-background-radius: 10;");
            try {
                boolean isUpdate = itemBO.updateItem(itemDTO);
                if (isUpdate){
                    saveBtn.setText("Save");
                    saveBtn.setStyle("-fx-background-color:  green; -fx-background-radius: 10;");
                    new Alert(Alert.AlertType.CONFIRMATION,"item is updated !").show();
                    clearTextFields();
                    generateItemId();
                    getAll();
                }else {
                    new Alert(Alert.AlertType.ERROR,"item is not updated !").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void searchId(){
        String searchIdText = searchId.getText();
        try {
            ItemDTO itemDTO = itemBO.searchItem(searchIdText);
            if (itemDTO != null){
                saveBtn.setText("Update");
                saveBtn.setStyle("-fx-background-color: blue; -fx-background-radius: 10;");
                String itemId = itemDTO.getItemId();
                String description = itemDTO.getDescription();
                int qty = itemDTO.getQty();
                double price = itemDTO.getPrice();
                itemCode.setText(itemId);
                itemDescription.setText(description);
                itemQtyId.setText(String.valueOf(qty));
                itemPerPriceId.setText(String.valueOf(price));
                searchId.clear();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void searchImgOnAction(ActionEvent event) {
        searchId();
    }

    @FXML
    void searchTxtOnAction(ActionEvent event) {
        searchId();
    }

    @FXML
    void tblItemOnMouseClick(MouseEvent event) {
        ItemTM selectedItem = (ItemTM) tblItem.getSelectionModel().getSelectedItem();
        try {
            ItemDTO item = itemBO.searchItem(selectedItem.getItemId());
            itemCode.setText(item.getItemId());
            itemDescription.setText(item.getDescription());
            itemQtyId.setText(String.valueOf(item.getQty()));
            itemPerPriceId.setText(String.valueOf(item.getPrice()));
            saveBtn.setText("Update");
            saveBtn.setStyle("-fx-background-color: blue; -fx-background-radius: 10;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateItemId();
        setValueFactory();
        getAll();
    }

    private void getAll() {
        ObservableList<ItemTM> observableList = FXCollections.observableArrayList();
        try {
            ArrayList<ItemDTO> all = itemBO.getAll();
            for (ItemDTO itemDTO:all) {
                observableList.add(new ItemTM(
                        itemDTO.getItemId(),
                        itemDTO.getDescription(),
                        itemDTO.getQty(),
                        itemDTO.getPrice()
                ));
            }
            tblItem.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setValueFactory() {
        tblItemCode.setCellValueFactory(new PropertyValueFactory<String,ItemTM>("itemId"));
        tblItemDescription.setCellValueFactory(new PropertyValueFactory<String,ItemTM>("description"));
        tblItemQty.setCellValueFactory(new PropertyValueFactory<String,ItemTM>("qty"));
        tblItemPerPrice.setCellValueFactory(new PropertyValueFactory<String,ItemTM>("price"));
    }

    void generateItemId(){
        try {
            String id = itemBO.generateItemId();
            itemCode.setText(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void clearTextFields(){
        itemCode.clear();
        itemDescription.clear();
        itemQtyId.clear();
        itemPerPriceId.clear();
    }
}
