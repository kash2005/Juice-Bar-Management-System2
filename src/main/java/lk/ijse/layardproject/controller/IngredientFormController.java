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
import lk.ijse.layardproject.bo.custom.IngredientBO;
import lk.ijse.layardproject.dto.IngredientDTO;
import lk.ijse.layardproject.dto.tm.IngredientTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class IngredientFormController implements Initializable {
    @FXML
    private TextField ingredientId;

    @FXML
    private TextField ingredientDescription;

    @FXML
    private TextField ingredientPrice;

    @FXML
    private TextField ingredientWeight;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private TableView<Object> tblIngredient;

    @FXML
    private TableColumn<String, IngredientTM> tblId;

    @FXML
    private TableColumn<String, IngredientTM> tblDescription;

    @FXML
    private TableColumn<String, IngredientTM> tblWeight;

    @FXML
    private TableColumn<String, IngredientTM> tblPrice;


    @FXML
    private TextField searchId;

    IngredientBO ingredientBO = (IngredientBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.INGREDIENT);

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        String id = ingredientId.getText();
        try {
            boolean isDeleted = ingredientBO.deleteIngredient(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Ingredient is deleted ").show();
                clearTextFields();
                generateIngredientId();
                getAll();
                saveBtn.setText("Save");
                saveBtn.setStyle("-fx-background-color: green; -fx-background-radius: 10");
            }else {
                new Alert(Alert.AlertType.ERROR,"Ingredient is not deleted ").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void descriptionTxtOnAction(ActionEvent event) {
        ingredientWeight.requestFocus();
    }

    @FXML
    void tblIngredientOnMouseClick(MouseEvent event) {
        IngredientTM selectedIngredient = (IngredientTM) tblIngredient.getSelectionModel().getSelectedItem();
        try {
            IngredientDTO ingredientDTO = ingredientBO.searchIngredientId(selectedIngredient.getIngredientId());
            ingredientId.setText(ingredientDTO.getIngredientId());
            ingredientDescription.setText(ingredientDTO.getDescription());
            ingredientWeight.setText(ingredientDTO.getWeight());
            ingredientPrice.setText(String.valueOf(ingredientDTO.getPrice()));
            saveBtn.setText("Update");
            saveBtn.setStyle("-fx-background-color: blue; -fx-background-radius: 10;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void priceTxtOnAction(ActionEvent event) {
        saveBtn.fire();
    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {
        String id = ingredientId.getText();
        String description = ingredientDescription.getText();
        String weight = ingredientWeight.getText();
        Double price = Double.valueOf(ingredientPrice.getText());
        if (saveBtn.getText().equals("Save")){
            try {
                boolean isSaved = ingredientBO.saveIngredient(new IngredientDTO(id,description,price,weight));
                if (isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION,"Ingredient is saved !").show();
                    clearTextFields();
                    generateIngredientId();
                    getAll();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Ingredient is not saved").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else if (saveBtn.getText().equals("Update")){
            saveBtn.setText("Update");
            saveBtn.setStyle("-fx-background-color: blue; -fx-background-radius: 10");
            try {
                boolean isUpdate = ingredientBO.updateIngredient(new IngredientDTO(id,description,price,weight));
                if (isUpdate){
                    saveBtn.setText("Save");
                    saveBtn.setStyle("-fx-background-color: green; -fx-background-radius: 10");
                    new Alert(Alert.AlertType.CONFIRMATION,"Ingredient is updated !").show();
                    clearTextFields();
                    generateIngredientId();
                    getAll();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Ingredient is not updated !").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void searchId(){
        String id = searchId.getText();
        try {
            IngredientDTO ingredientDTO = ingredientBO.searchIngredientId(id);
            if (ingredientDTO != null){
                saveBtn.setText("Update");
                saveBtn.setStyle("-fx-background-color: blue; -fx-background-radius: 10");
                String ingredientId1 = ingredientDTO.getIngredientId();
                String description = ingredientDTO.getDescription();
                double price = ingredientDTO.getPrice();
                String weight = ingredientDTO.getWeight();
                ingredientId.setText(ingredientId1);
                ingredientDescription.setText(description);
                ingredientWeight.setText(weight);
                ingredientPrice.setText(String.valueOf(price));
                searchId.clear();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
    void weightTxtOnAction(ActionEvent event) {
        ingredientPrice.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateIngredientId();
        setValueFactory();
        getAll();
    }

    private void setValueFactory() {
        tblId.setCellValueFactory(new PropertyValueFactory<String,IngredientTM>("ingredientId"));
        tblDescription.setCellValueFactory(new PropertyValueFactory<String,IngredientTM>("description"));
        tblPrice.setCellValueFactory(new PropertyValueFactory<String,IngredientTM>("price"));
        tblWeight.setCellValueFactory(new PropertyValueFactory<String,IngredientTM>("weight"));
    }

    private void getAll() {
        ObservableList<Object> observableList = FXCollections.observableArrayList();
        try {
            ArrayList<IngredientDTO> all = ingredientBO.getAll();
            for(IngredientDTO ingredientDTO:all){
                observableList.add(new IngredientTM(
                        ingredientDTO.getIngredientId(),
                        ingredientDTO.getDescription(),
                        ingredientDTO.getPrice(),
                        ingredientDTO.getWeight()
                ));
            }
            tblIngredient.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateIngredientId() {
        try {
            String id = ingredientBO.generateIngredientId();
            ingredientId.setText(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearTextFields(){
        ingredientId.clear();
        ingredientDescription.clear();
        ingredientWeight.clear();
        ingredientPrice.clear();
    }
}


