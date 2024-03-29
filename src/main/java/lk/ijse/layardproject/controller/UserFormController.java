package lk.ijse.layardproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.layardproject.bo.BOFactory;
import lk.ijse.layardproject.bo.custom.EmployeeBO;
import lk.ijse.layardproject.bo.custom.UserBO;
import lk.ijse.layardproject.dto.UserDTO;
import lk.ijse.layardproject.dto.tm.UserTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserFormController implements Initializable {

    @FXML
    private TextField userName;

    @FXML
    private TextField password;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private TableView<UserTM> tblUser;

    @FXML
    private TableColumn<String, UserTM> colId;

    @FXML
    private TableColumn<String, UserTM> colName;

    @FXML
    private TableColumn<String, UserTM> colPassword;

    @FXML
    private TextField searchId;

    @FXML
    private ComboBox<String> cmbUserId;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        String id = cmbUserId.getValue();
        try {
            boolean delete = userBO.delete(id);
            if (delete){
                new Alert(Alert.AlertType.CONFIRMATION,"User is Deleted !").show();
                clear();
                getAll();
                saveBtn.setText("Save");
                saveBtn.setStyle("-fx-background-color: green; -fx-background-radius: 10");
            }else {
                new Alert(Alert.AlertType.ERROR,"User is not Deleted !").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void passwordOnAction(ActionEvent event) {
        saveBtn.fire();
    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {
        String id = cmbUserId.getValue();
        String name = userName.getText();
        String password1 = password.getText();

        if (saveBtn.getText().equals("Save")) {
            try {
                boolean save = userBO.save(new UserDTO(id, name, password1));
                if (save){
                    new Alert(Alert.AlertType.CONFIRMATION,"User is Saved !").show();
                    clear();
                    getAll();
                }else {
                    new Alert(Alert.AlertType.ERROR,"User is not Saved !").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else if (saveBtn.getText().equals("Update")){
            try {
                boolean update = userBO.update(new UserDTO(id, name, password1));
                if (update){
                    new Alert(Alert.AlertType.CONFIRMATION,"User is Updated !").show();
                    saveBtn.setText("Save");
                    saveBtn.setStyle("-fx-background-color: green; -fx-background-radius: 10");
                    clear();
                    getAll();
                }else {
                    new Alert(Alert.AlertType.ERROR,"User is not Updated !").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void searchId(){
        String id = searchId.getText();
        try {
            UserDTO userDTO = userBO.searchId(id);
            cmbUserId.setValue(id);
            userName.setText(userDTO.getUserName());
            password.setText(userDTO.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        saveBtn.setText("Update");
        saveBtn.setStyle("-fx-background-color: blue; -fx-background-radius: 10");
    }

    @FXML
    void searchIdOnAction(ActionEvent event) {
        searchId();
    }

    @FXML
    void searchImgOnAction(ActionEvent event) {
        searchId();
    }

    @FXML
    void tblUserOnMouseClick(MouseEvent event) {
        UserTM selectedItem = (UserTM) tblUser.getSelectionModel().getSelectedItem();
        try {
            UserDTO userDTO = userBO.searchId(selectedItem.getUserId());
            cmbUserId.setValue(userDTO.getUserId());
            userName.setText(userDTO.getUserName());
            password.setText(userDTO.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        saveBtn.setText("Update");
        saveBtn.setStyle("-fx-background-color: blue; -fx-background-radius: 10");
    }

    @FXML
    void userNameOnAction(ActionEvent event) {
        password.requestFocus();
    }

    @FXML
    void cmbUserIdOnAction(ActionEvent event) {
        userName.requestFocus();
    }

    void getCashierId(){
        try {
            ArrayList<String> cashierId = employeeBO.getCmbEmployeeId();
            ObservableList<String> observableList = FXCollections.observableArrayList(cashierId);
            cmbUserId.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getCashierId();
        setValueFactory();
        getAll();
    }

    private void setValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<String, UserTM>("userId"));
        colName.setCellValueFactory(new PropertyValueFactory<String, UserTM>("userName"));
        colPassword.setCellValueFactory(new PropertyValueFactory<String, UserTM>("password"));
    }

    private void getAll() {
        ObservableList<UserTM> observableList = FXCollections.observableArrayList();
        try {
            ArrayList<UserDTO> all = userBO.getAll();
            for (UserDTO userDTO : all){
                observableList.add(new UserTM(
                        userDTO.getUserId(),
                        userDTO.getUserName(),
                        userDTO.getPassword()
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tblUser.setItems(observableList);
    }

    void clear(){
        cmbUserId.setValue("");
        userName.clear();
        password.clear();
    }
}
