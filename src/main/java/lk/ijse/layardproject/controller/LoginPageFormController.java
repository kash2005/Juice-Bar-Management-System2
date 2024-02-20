package lk.ijse.layardproject.controller;


import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.layardproject.bo.BOFactory;
import lk.ijse.layardproject.bo.custom.EmployeeBO;
import lk.ijse.layardproject.bo.custom.UserBO;
import lk.ijse.layardproject.dto.UserDTO;
import lk.ijse.layardproject.model.EmployeeModel;
import lk.ijse.layardproject.model.UserModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginPageFormController implements Initializable {
    @FXML
    private TextField userNameId;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private PasswordField passwordId;

    @FXML
    private TextField textPasswordId;

    @FXML
    private AnchorPane root;

    @FXML
    private ImageView showImg;

    @FXML
    private ImageView hideImg;

    @FXML
    private ComboBox<String> cmbEmployeeId;

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void cmbEmployeeIdOnACtion(ActionEvent event) {
        userNameId.requestFocus();
    }

    void setCmbEmployeeId(){
        try {
            ArrayList<String> cmbEmployeeId1 = employeeBO.getCmbEmployeeId();
            ObservableList<String> observableList = FXCollections.observableArrayList(cmbEmployeeId1);
            cmbEmployeeId.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void hidePasswordOnMouseClicked(MouseEvent event) {
        passwordId.setText(textPasswordId.getText());
        hideImg.setVisible(true);
        passwordId.setVisible(true);
        showImg.setVisible(false);
        textPasswordId.setVisible(false);
    }

    @FXML
    void showPasswordOnMouseClicked(MouseEvent event) {
        textPasswordId.setText(passwordId.getText());
        showImg.setVisible(true);
        textPasswordId.setVisible(true);
        hideImg.setVisible(false);
        passwordId.setVisible(false);
    }

    @FXML
    void loginBtnOnAction(ActionEvent event) throws IOException {
        String eId = cmbEmployeeId.getValue();
        String userName = userNameId.getText();
        String password = passwordId.getText();

        try {
            String jobRoll = EmployeeModel.searchId(eId);
            if (jobRoll.equals("Cashier")){
                try {
                    UserDTO user = userBO.getUser(userName);
                    if (userName.equals(user.getUserName()) && password.equals(user.getPassword())){
                        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/lk/ijse/layardproject/view/dashboardForm.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(anchorPane));
                        Stage stage1 = (Stage) root.getScene().getWindow();
                        stage1.close();
                        stage.setTitle("Juice Bar Management System - Dashboard Page");
                        stage.centerOnScreen();
                        stage.show();
                        UserDTO userDTO = new UserDTO(eId, userName, password);
                        UserEditFormController.userDTO = userDTO;
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }else if (jobRoll.equals("Admin")){
                try {
                    UserDTO user = userBO.getUser(userName);
                    if (userName.equals(user.getUserName()) && password.equals(user.getPassword())){
                        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/lk/ijse/layardproject/view/adminDashboardForm.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(anchorPane));
                        Stage stage1 = (Stage) root.getScene().getWindow();
                        stage1.close();
                        stage.setTitle("Juice Bar Management System - Admin Dashboard Page");
                        stage.centerOnScreen();
                        stage.show();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        loginBtn.fire();
    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {
        passwordId.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textPasswordId.setVisible(false);
        hideImg.setVisible(true);
        passwordId.setVisible(true);
        setCmbEmployeeId();
    }
}
